sensor "button", 8
actuator "led", 9

state "off1"
    led OFF
    button LOW to "off2"

state "on2"
    button HIGH to "off1"

state "on1"
    led ON
    button LOW to "on2"

state "off2"
    button HIGH to "on1"

init "off2"