actuator "buzzer", 1
sensor "button1", 2
sensor "button2", 3

state "on"
    buzzer CONTINUE
    button1 LOW, "off"
    button2 LOW, "off"

state "off"
    buzzer SILENT
    button2 HIGH and() button1 HIGH, "on"

init "off"