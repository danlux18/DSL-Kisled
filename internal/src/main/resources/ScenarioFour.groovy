actuator "buzzer", 1
actuator "led", 2
sensor "button", 3

state "buzzer_on"
    buzzer CONTINUE
    button LOW, "led_on"

state "led_on"
    buzzer SILENT
    led CONTINUE
    button LOW, "off"

state "off"
    led SILENT
    button LOW, "buzzer_on"

init "buzzer_on"