actuator "buzzer", 10
actuator "led", 9
sensor "button", 8

state "buzzer_on"
    buzzer ON
    button HIGH, "led_on_t"

state "led_on_t"
    button LOW, "led_on"

state "led_on"
    buzzer OFF
    led ON
    button HIGH, "off_t"

state "off_t"
    button LOW, "off"

state "off"
    led OFF
    button HIGH, "buzzer_on_t"

state "buzzer_on_t"
    button LOW, "buzzer_on"

init "buzzer_on"