sensor "button", 1
actuator "led", 2

state "on"
    led CONTINUE
    button HIGH, "off"

state "off"
    led SILENT
    button HIGH, "on"

init "off"