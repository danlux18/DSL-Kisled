// File generated by ArduinoML (C/yacc/lex)

#define SILENT LOW
#define CONTINUE HIGH
#define SHORT 1000
#define LONG 3000

long time = 0;
long debounce = 200;

int buzzer = 4;
int led = 3;
int button2 = 2;
int button1 = 1;

void setup() {
  pinMode(buzzer, OUTPUT);
  pinMode(led, OUTPUT);
  pinMode(button2, INPUT);
  pinMode(button1, INPUT);
}

void do_action(int actuator, int act_signal) {
  if (act_signal == SILENT || act_signal == CONTINUE) {
    digitalWrite(actuator, act_signal);
  } else {
    digitalWrite(actuator, HIGH);
    delay(act_signal);
    digitalWrite(actuator, LOW);
  }
}

void state_on() {
  do_action(led, CONTINUE);
  do_action(buzzer, SHORT);
  boolean guard =  millis() - time > debounce;
  if (digitalRead(button1) == LOW && guard) {
    time = millis();
    state_off();
  } else if (digitalRead(button2) == LOW && guard) {
    time = millis();
    state_off();
  } else  {
    state_on();
  }
}

void state_off() {
  do_action(led, SILENT);
  do_action(buzzer, SILENT);
  boolean guard =  millis() - time > debounce;
  if (digitalRead(button1) == HIGH && digitalRead(button2) == HIGH && guard) {
    time = millis();
    state_on();
  } else  {
    state_off();
  }
}

void loop() {
  state_off();
}
