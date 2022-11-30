// Yacc grammar for ArduinoML in C
//
//           Author: Erick Gallesio [eg@unice.fr]
//    Creation date: 16-Nov-2017 17:54 (eg)
// Last file update: 30-Nov-2017 15:27 (eg)

%{
#define  YYERROR_VERBOSE 1      // produce verbose syntax error messages

#include <stdio.h>
#include <stdlib.h>
#include "syntax.h"
#include "arduino.h"

#define  YYERROR_VERBOSE 1      // produce verbose syntax error messages

//  Prototypes
int  yylex(void);
void yyerror(const char *s);
%}

%union {
    int                        value;
    char                       *name;
    struct arduino_transition  *transition;
    struct arduino_action      *action;
    struct arduino_state       *state;
    struct arduino_brick       *brick;
    struct arduino_condition   *condition;
};

%token KSENSOR KACTUATOR LEFT RIGHT INITSTATE KAND
%token  <name>          IDENT KHIGH KLOW KCONTINUE KLONG KSHORT KSILENT
%token  <value>         PORT_NUMBER

%type   <name>          name
%type   <value>         signal act_signal port
%type   <condition>     conditions condition
%type   <transition>    transitions transition
%type   <action>        action actions
%type   <state>         state states
%type   <brick>         brick bricks
%%

start:          bricks  states init_state                   { emit_code($1, $2); }
     ;

bricks:         bricks brick                                { $$ = add_brick($2, $1); }
      |         error                                       { yyerrok; }
      |         /* empty */                                 { $$ = NULL; }
      ;

brick:          KACTUATOR name ':' port                     { $$ = make_brick($4, actuator, $2); }
     |          KSENSOR   name ':' port                     { $$ = make_brick($4, sensor, $2); }
     ;

states:         states state                                { $$ = add_state($1, $2); }
      |         /*empty */                                  { $$ = NULL; }
      ;

state:          name '{' actions  transitions '}'            { $$ = make_state($1, $3, $4); }
      ;


actions:        actions action                              { $$ = add_action($1, $2); }
       |        action                                      { $$ = $1; }
       |        error                                       { yyerrok; }
       ;

action:          name LEFT act_signal                       { $$ = make_action($1, $3); }
      ;

transitions:      transitions transition                    { $$ = add_transition($1, $2); }
      |           transition                                { $$ = $1; }
      ;

transition:     conditions RIGHT name                      { $$ = make_transition($1, $3); }
          |     error                                       { yyerrok; }
          ;

conditions:     conditions KAND condition                   { $$ = add_condition($1, $3); }
      |         condition                                   { $$ = $1; }
      ;

condition:      name signal                                 { $$ = make_condition($1, $2); }
      |         error                                       { yyerrok; }
      ;

signal:         KHIGH                                       { $$ = 1; }
      |         KLOW                                        { $$ = 0; }
      ;

act_signal:     KCONTINUE                                   { $$ = 1; }
      |         KLONG                                       { $$ = 2; }
      |         KSHORT                                      { $$ = 3; }
      |         KSILENT                                     { $$ = 0; }
      ;

init_state:     INITSTATE name                              { set_initial_state($2); }
      ;

name:           IDENT          ;
port:           PORT_NUMBER    ;


%%
void yyerror(const char *msg) { extern int yylineno; error_msg(yylineno, msg); }