sensor "button1", 8
sensor "button2", 9
actuator "led", 10
actuator "buzzer", 11

state "short_s"
    led ON
    buzzer SHORT, 3
    button1 HIGH, "short_f"
    button1 LOW, "short_f"

state "short_f"
    button1 HIGH, "long_s"

state "long_s"
    led OFF
    buzzer LONG
    button2 HIGH, "long_f"
    button2 LOW, "long_f"

state "long_f"
    button2 HIGH, "short_s"

init "short_s"