// File generated by ArduinoML (C/yacc/lex)

#define OFF LOW
#define ON HIGH
#define SHORT 1000
#define LONG 3000

long time = 0;
long debounce = 200;

int buzzer = 9;
int led = 8;
int button2 = 10;
int button1 = 11;

void setup() {
  pinMode(buzzer, OUTPUT);
  pinMode(led, OUTPUT);
  pinMode(button2, INPUT);
  pinMode(button1, INPUT);
}

void do_action(int actuator, int act_signal, boolean guard) {
  if (!guard) return;
  if (act_signal == OFF || act_signal == ON) {
    digitalWrite(actuator, act_signal);
  } else {
    digitalWrite(actuator, HIGH);
    delay(act_signal);
    digitalWrite(actuator, LOW);
  }
}

void state_enter_state(boolean act_guard) {
  do_action(led, ON, act_guard);
  do_action(buzzer, SHORT, act_guard);
  do_action(buzzer, SHORT, act_guard);
  do_action(buzzer, SHORT, act_guard);
  boolean guard =  millis() - time > debounce;
  if (digitalRead(button1) == HIGH && guard) {
    time = millis();
    state_exit_state(true);
  } else  {
    state_enter_state(false);
  }
}

void state_exit_state(boolean act_guard) {
  do_action(led, OFF, act_guard);
  do_action(buzzer, LONG, act_guard);
  boolean guard =  millis() - time > debounce;
  if (digitalRead(button2) == HIGH && guard) {
    time = millis();
    state_enter_state(true);
  } else  {
    state_exit_state(false);
  }
}

void loop() {
  state_exit_state(true);
}