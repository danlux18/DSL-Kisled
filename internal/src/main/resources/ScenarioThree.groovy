sensor "button", 8
actuator "led", 9

state "off1"
    led OFF
    button LOW, "off2"

state "on2"
    button HIGH, "off1"

state "on1"
    led ON
    button LOW, "on2"

state "off2"
    button HIGH, "on1"

init "off2"