# DSL-Kisled
Domain Specifc Language 2022-2023 Polytech Nice Sophia

## Authors
* Alexandre Arcil
* Gabriel Cogne
* Thomas Di Grande
* Dan Nakache

## Subject & Features
### Basic Scenario
1. Very simple alarm: Pushing a button activates a LED and a buzzer. Releasing the button switches
the actuators off.
2. Dual-check alarm: It will trigger a buzzer if and only if two buttons are pushed at the very same
   time. Releasing at least one of the button stop the sound.
3. State-based alarm: Pushing the button once switch the system in a mode where the LED is switched
   on. Pushing it again switches it off.
4. Multi-state alarm: Pushing the button starts the buzz noise. Pushing it again stop the buzzer and
   switch the LED on. Pushing it again switch the LED off, and makes the system ready to make noise
   again after one push, and so on.

## Semantic diagram

## Backus-Naur Form
```lex
NAME    [a-zA-Z][a-zA-Z0-9_]+
PORT    [1-9]|(1[012])
TIMES   [1-9]|(1[012])
```

```html
<start> ::= <bricks> <states> <init_state> ;

<bricks> ::= <bricks> <brick> | <error> | /* No declaration */ ;
    
<brick> ::= "actuator" NAME ':' PORT | "sensor" NAME ':' PORT ;

<states> ::= <states> <state> | /* No state */ ;
    
<state> ::= NAME '{' <actions> <transitions> '}' ;

<actions> ::= <actions> <action> | <action> | <error> ;

<action> ::= NAME "<=" <act_signal> | NAME "<=" <act_signal> "x" TIMES ;

<transitions> ::= <transitions> <transition> | <transition> ;

<transition> ::= <condition> "=>" NAME | error ;

<conditions> ::= <conditions> "AND" <condition> | <condition> ;

<condition> ::= <condition> 'AND' NAME <signal> | NAME <signal> ;

<signal> ::= "HIGH" | "LOW" ;

<act_signal> ::= "ON" | "LONG" | "SHORT" | "OFF";

<init_state> ::= "->" NAME ;
```

## Technological choice
### External DSL
The external DSL was implemented using lex/yacc based on [Éric Gallesio version of arduinoml](https://github.com/mosser/ArduinoML-kernel/tree/master/externals/yacc)

#### Compilation
```shell
make -C external
```

#### Usage
```shell
# Compile a file to a file
./external/arduinoml -o <destion> <src>

# Print the version
./external/arduinoml -v

# Print the help
./external/arduinoml -h
```

It is possible to use the compiler using `./external/arduinoml` with no argument. In that case, the compiler will wait for an input and will print the result
when the user would have enter a complete program.

### Embedded DSL