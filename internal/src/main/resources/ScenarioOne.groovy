sensor "button", 0
actuator "led", 1
actuator "buzzer", 2

state "on"
    led CONTINUE
    buzzer CONTINUE
    button LOW, "off"

state "off"
    led SILENT
    buzzer SILENT
    button HIGH, "on"

init "off"