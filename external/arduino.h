/*
 * arduino.h     -- ArduinoML configuration and code generation
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 17-Nov-2017 11:13
 * Last file update: 28-Nov-2017 11:47 (eg)
 */

#ifndef ARDUINO_H
#define ARDUINO_H

#define OFF 0
#define SHORT 2
#define LONG 3
#define ON 1

enum port_assignment {sensor, actuator };
extern char *input_path;      ///< Name of the input path or NULL if stdin

/// Display error message using the GNU conventions
void error_msg(int lineno, const char *format, ...);

//
// ========== BRICKS ==========
//
typedef struct arduino_brick Brick;

/// Declare a new brick on port `number`
Brick *make_brick(int number, enum port_assignment kind, char *name);
/// Add a brick to a list of bricks
Brick *add_brick(Brick *b, Brick *list);

//
// ========== CONDITIONS ==========
//
typedef struct arduino_condition Condition;

// Make a new condition
Condition* make_condition(char *var, int signal);
// Add an new condition to our global condition
Condition* add_condition(Condition *list, Condition *c);

//
// ========== TRANSITIONS ==========
//
typedef struct arduino_transition Transition;

/// Make a new transition (when `var` is `signal` goto `newstate`
Transition *make_transition(Condition *c, char *newstate);
// Add a transaction to a list of transaction
Transition *add_transition(Transition *list, Transition *t);

//
// ========== ACTIONS ==========
//
typedef struct arduino_action Action;

// Make a new action (setting `var` to `signal`)
Action *make_action(char *var, int signal, int howMany);
// Add an action to a list of actions
Action *add_action(Action *list, Action *a);


//
// ========== STATES ==========
//
typedef struct arduino_state State;

// Make a new state named `var` with a list of `actions` and a `transition`
// `initial` must be one if the state is the initial one
State *make_state(char *var, Action *actions, Transition *transition);
// Add a state to a list of states
State *add_state(State *list, State *a);
// Set initial state
void set_initial_state(char *var);


//
// ========== CODE PRODUCTION ==========
//

/// emit the code for the parsed configuration
void emit_code(Brick *brick_list, State *state_list);

#endif // ARDUINO_H