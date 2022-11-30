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
NAME    [_a-z][_a-zA-Z0-9]*
PORT    [0-9]+
VALUE   [A-Z]+
```

```html
<app> ::= <declarations> <states>

<declarations> ::= <declaration> <declarations> | /* No declaration */ ;
    
<declaration> ::= NAME ':' PORT

<states> ::= <state> <states> | /* No state */ ;
    
<state> ::= NAME ':' <stmts>

<stmts> ::= <stmts> <stmt> | /* No statements */ ;

<stmt> ::= <action> | <transition> ;

<action> ::= NAME VALUE

<transition> ::= '(' <condition> ')' '->' NAME

<condition> ::= NAME VALUE | <condition> 'AND' NAME VALUE
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