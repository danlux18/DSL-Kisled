button 0
led 1
buzzer 2

state "on"
    led <= CONTINUE
    buzzer CONTINUE
    button LOW "off"

state "off"
    led SILENT
    buzzer SILENT
    button HIGH "on"

init "off"