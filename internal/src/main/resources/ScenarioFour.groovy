actuator "buzzer", 10
actuator "led", 9
sensor "button", 8

state "buzzer_on"
    buzzer ON
    button HIGH to "led_on_t"

state "led_on_t"
    button LOW to "led_on"

state "led_on"
    buzzer OFF
    led ON
    button HIGH to "off_t"

state "off_t"
    button LOW to "off"

state "off"
    led OFF
    button HIGH to "buzzer_on_t"

state "buzzer_on_t"
    button LOW to "buzzer_on"

init "buzzer_on"