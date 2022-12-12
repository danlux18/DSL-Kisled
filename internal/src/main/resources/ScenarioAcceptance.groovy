sensor "button1", 8
sensor "button2", 9
actuator "led", 10
actuator "buzzer", 11

state "short_three"
    led ON
    buzzer SHORT, 3
    button1 HIGH, "long_one"

state "long_one"
    led OFF
    buzzer LONG
    button2 HIGH, "short_three"

init "short_three"