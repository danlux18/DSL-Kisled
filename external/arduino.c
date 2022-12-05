/*
 * arduino.c     -- ArduinoML configuration and code generation
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 17-Nov-2017 11:13
 * Last file update:  1-Dec-2017 18:47 (eg)
 */

#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>
#include <string.h>
#include "arduino.h"

extern int yylineno;               ///< line number (managed by lex)
static int error_detected = 0;     ///< The number of errors while compiling a file
extern char *input_path;           ///< Name of the input path or NULL if stdin

extern FILE *fout;

static void *__must_malloc(size_t sz, const char *func, const char *file, int line);
#define must_malloc(_sz)         (__must_malloc((_sz), __func__, __FILE__, __LINE__))

// ======================================================================
//                              B R I C K S
// ======================================================================
struct arduino_brick {
  char *var;
  enum port_assignment kind;
  int port_number;
  struct arduino_brick *next;
};


/// Find name in the list of already declared bricks
static int find_brick(char *name, Brick *list) {
  for (Brick *p = list; p; p = p->next) {
    if (strcmp(name, p->var) == 0) return 1;
  }
  return 0;
}


/// declare a new brick on port `number`
Brick *make_brick(int number, enum port_assignment kind, char *name) {
  Brick *p = must_malloc(sizeof(Brick));

  p-> var  = name;
  p-> kind = kind;
  p->port_number = number;
  p->next = NULL;
  return p;
}


/// Add a brick to a list of bricks
Brick *add_brick(Brick *b, Brick *list) {
  // Check that the given variable is not already used
  if (find_brick(b->var, list)) {
    error_msg(yylineno, "name '%s' was already used", b->var);
  }
  // Check that the given port is not already used
  for (Brick *p = list; p; p = p->next) {
    if (p->port_number == b->port_number)
      error_msg(yylineno, "port %d was already used by '%s'", p->port_number, b->var);
  }
  b->next = list;
  return b;
}


// ======================================================================
//                            T R A N S I T I O N
// ======================================================================
struct arduino_condition {
  int lineno;
  char *var_name;
  int sig_value;
  struct arduino_condition* next;
};

// Make a new condition
Condition* make_condition(char *var, int signal) {
  Condition *p = must_malloc(sizeof(Condition));

  p->lineno    = yylineno;
  p->var_name  = var;
  p->sig_value = signal;
  p->next      = NULL;

  return p;
}

// Add an new condition to our global condition
Condition* add_condition(Condition *list, Condition *c) {
  if (list) {
    Condition *tmp = list;
    while (tmp->next) tmp = tmp->next;
    tmp->next = c;
    return list;
  }
  return c;
}

// ======================================================================
//                            T R A N S I T I O N
// ======================================================================
struct arduino_transition {
  int lineno;
  Condition *condition;
  char *newstate;
  struct arduino_transition *next;
};

/// Make a new transition (when `var` is `signal` goto `newstate`
Transition *make_transition(Condition *c, char *newstate) {
  Transition *p = must_malloc(sizeof(Transition));

  p->lineno    = yylineno;
  p->condition = c;
  p->newstate  = newstate;
  p->next = NULL;
  return p;
}
// Add a transaction to a list of transaction
Transition *add_transition(Transition *list, Transition *t) {
  if (list) {
    Transition *tmp = list;
    while (tmp->next) tmp = tmp->next;
    tmp->next = t;
    return list;
  }
  return t;
}


// ======================================================================
//                            A C T I O N
// ======================================================================
struct arduino_action {
  int lineno;
  char *var_name;
  int sig_value;
  int howMany;
  struct arduino_action *next;
};

// Make a new action (setting `var` to `signal`)
Action *make_action(char *var, int signal, int howMany) {
  Action *p = must_malloc(sizeof(Action));

  p->lineno    = yylineno;
  p->var_name  = var;
  p->sig_value = signal;
  p->howMany = howMany;
  p->next      = NULL;
  return p;
}

// Add an action to a list of actions
Action *add_action(Action *list, Action *a) {
  if (list) {
    Action *tmp = list;
    while (tmp->next) tmp = tmp->next;
    tmp->next = a;
    return list;
  }
  return a;
}

// ======================================================================
//                            S Τ A Τ E
// ======================================================================
struct arduino_state {
  int lineno;
  char *name;
  Action *actions;
  Transition *transition;
  struct arduino_state *next;
};

static char *initial_state = NULL;

static int find_state(char *name, State *list) {
  for (State *p = list; p; p = p->next) {
    if (strcmp(name, p->name) == 0) return 1;
  }
  return 0;
}


// Make a new state named `var` with a list of `actions` and a `transition`
// `initial` must be one if the state is the initial one
State *make_state(char *var, Action *actions, Transition *transition) {
  State *p = must_malloc(sizeof(State));

  p->lineno     = yylineno;
  p->name       = var;
  p-> actions   = actions;
  p->transition = transition;
  p->next       = NULL;
  return p;
}

// Add a state to a list of states
State *add_state(State *list, State *s) {
  if (list) {
    State *tmp = list;
    while (tmp->next) tmp = tmp->next;
    tmp->next = s;
    return list;
  }
  return s;
}

// Set initial state
void set_initial_state(char *var) {
  initial_state = var;
}


// ======================================================================
//                     S E M A N T I C   C H E C K S
// ======================================================================

static void check_actions(Brick *brick_list, Action *list) {
  for (Action *current = list; current; current = current->next) {
    if ((current->sig_value == ON || current->sig_value == OFF) && current->howMany != 1)
      error_msg(current->lineno, "ON or OFF action can't be done more than once");

    // Verify that the variable used in this action is declared
    if (! find_brick(current->var_name, brick_list))
      error_msg(list->lineno, "undeclared '%s'", current->var_name);
  }
}

static void check_conditions(Brick *brick_list, Condition *conditions) {
  for (Condition *c = conditions; c; c = c->next) {
    if (! find_brick(c->var_name, brick_list)) {
      error_msg(c->lineno, "undeclared '%s'", c->var_name);
    }
  }
}

static void check_transition(Brick *brick_list, State *state_list, Transition *trans){
  // Verify that the variable is declared
  check_conditions(brick_list, trans->condition);
  // if (! find_brick(trans->var_name, brick_list))
  //   error_msg(trans->lineno, "undeclared '%s'", trans->var_name);

  // Verify that the next state exists
  if (! find_state(trans->newstate, state_list))
    error_msg(trans->lineno, "undeclared state '%s'", trans->newstate);
}


static void check_states(Brick *brick_list, State *state_list) {
  for (State *current = state_list; current; current = current->next) {
    check_actions(brick_list, current->actions);
    check_transition(brick_list, state_list, current->transition);
    if (find_state(current->name, current->next))
      error_msg(current->lineno, "duplicate state name: '%s'", current->name);
  }
}


// ======================================================================
//                      C O D E   P R O D U C T I O N
// ======================================================================

/// Write code on the output file. The printf format conventions are accepted
static void emit(char *fmt, ...) {
    va_list ap;
    va_start(ap, fmt);
    vfprintf(fout, fmt, ap);
    va_end(ap);
}

/// Write the header of the <pre>.ino</pre> file
static void emit_header() {
  emit("// File generated by ArduinoML (C/yacc/lex)\n\n"); // Emit initial comment
  emit("#define OFF LOW\n#define ON HIGH\n"); // Emit OFF and ON definition
  emit("#define SHORT 1000\n#define LONG 3000\n\n"); // Emit SHORT and LONG value in millis
  emit("long time = 0;\nlong debounce = 200;\n\n"); // Emit default variable
}

/// Write the <pre>do_action(int actuator, int act_signal)</pre> function
static void emit_do_action() {
  emit("void do_action(int actuator, int act_signal, boolean guard) {\n");
  emit("  if (!guard) return;\n");
  emit("  if (act_signal == OFF || act_signal == ON) {\n");
  emit("    digitalWrite(actuator, act_signal);\n");
  emit("  } else {\n");
  emit("    digitalWrite(actuator, HIGH);\n");
  emit("    delay(act_signal);\n");
  emit("    digitalWrite(actuator, LOW);\n");
  emit("  }\n");
  emit("}\n\n");
}

/// Write the <pre>setup()</pre> function and initialise the actuators and sensors 
static void emit_bricks(Brick *lst) {
  // Produce variables
  for (Brick *p = lst; p; p = p->next) {
    emit("int %s = %d;\n", p->var, p->port_number);
  }

  // Produce setup() function
  emit("\nvoid setup() {\n");
  for (Brick *p = lst; p; p = p->next) {
    emit("  pinMode(%s, %s);\n", p->var, (p->kind == sensor)? "INPUT": "OUTPUT");
  }
  emit("}\n\n");
}

char *act_sigs[4] = {"OFF", "ON", "SHORT", "LONG"};

/// Write the actions bounded to a state
static void emit_actions(Action *list) {
  for (Action *p = list; p; p = p->next) {
    for (int i = 0; i < p->howMany; i++) {
      emit("  do_action(%s, %s, act_guard);\n", p->var_name, act_sigs[p->sig_value]);
      if (i != p->howMany - 1)
        emit("  delay(SHORT);\n");
    }
  }
}

/// Write the condition bounded to a transition
static void emit_condition(Condition *condition) {
  for (Condition *c = condition; c; c = c->next) {
    emit("digitalRead(%s) == %s ", c->var_name, c->sig_value ? "HIGH" : "LOW");
    if (c->next) {
      emit("&& ");
    }
  }
}

/// Write a transition bounded to a state
static void emit_transition(Transition *transition) {
  emit(" if (");
  emit_condition(transition->condition);
  emit("&& guard) {\n");
  emit("    time = millis();\n");
  emit("    state_%s(true);\n", transition->newstate);
}

/// Write the transitions bounded to a state
static void emit_transitions(char *current_state, Transition *list) {
  emit("  boolean guard =  millis() - time > debounce;\n");

  emit(" ");
  for (Transition *t = list; t; t = t->next) {
    emit_transition(t);
    emit("  } else");
  }

  emit("  {\n");
  emit("    state_%s(false);\n", current_state);
  emit("  }\n");
}

/// Write the states of the application
static void emit_states(State *list) {
  for (State *p = list; p; p = p->next) {
    emit("void state_%s(boolean act_guard) {\n", p->name);
    emit_actions(p->actions);
    emit_transitions(p->name, p->transition);
    emit("}\n\n");
  }
}

/// Write the <pre>loop()</pre> function
static void emit_loop(void) {
  emit("void loop() {\n  state_%s(true);\n}\n", initial_state);
}


/// emit the code for the parsed configuration
void emit_code(Brick *brick_list, State *state_list) {
  check_states(brick_list, state_list);
  if (! initial_state)
    error_msg(yylineno, "no initial state declared");

  if (error_detected) {
    fprintf(stderr, "**** %d error%s\n", error_detected, (error_detected>1) ? "s": "");
    return;
  }

  // No error ⇒ produce code
  emit_header();
  emit_bricks(brick_list);
  emit_do_action();
  emit_states(state_list);
  emit_loop();
}


// ======================================================================
//                               U T I L S
// ======================================================================

/// Display error message using the GNU conventions
void error_msg(int lineno, const char *format, ...) {
  va_list ap;

  if (input_path) fprintf(stderr, "%s:", input_path);
  fprintf(stderr, "%d: ", lineno);
  va_start(ap, format);  vfprintf(stderr, format, ap);  va_end(ap);
  fprintf(stderr, "\n");

  error_detected += 1;
}

// Allocate memory and die if not possible
static void *__must_malloc(size_t sz, const char *func, const char *file, int line) {
  void *res = malloc(sz);
  if (!res) {
    fprintf(stderr, "**** function '%s' cannot allocate memory (in file '%s':%d)\n", func, file, line);
    fprintf(stderr, "Abort.\n");
    exit(1);
  }
  return res;
}