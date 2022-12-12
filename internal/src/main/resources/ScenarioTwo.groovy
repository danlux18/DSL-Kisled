actuator "buzzer", 10
sensor "button1", 9
sensor "button2", 8

state "on"
    buzzer ON
    button1 LOW to "off"
    button2 LOW to "off"

state "off"
    buzzer OFF
    button2 HIGH and() button1 HIGH to "on"

init "off"