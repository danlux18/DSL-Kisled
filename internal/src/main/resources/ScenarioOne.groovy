sensor "button", 8
actuator "led", 9
actuator "buzzer", 10

state "on"
    led ON
    buzzer ON
    button LOW, "off"

state "off"
    led OFF
    buzzer OFF
    button HIGH, "on"

init "off"