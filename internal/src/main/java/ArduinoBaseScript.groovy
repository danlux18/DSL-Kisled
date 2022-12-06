abstract class ArduinoBaseScript extends Script {

    def state(String name) {
        return ((DSLLanguage) this.getBinding().getVariable("lang")).state(name);
        /*[led: { DSLLanguage.OutputState led_state -> println(led_state)},
                buzzer: { DSLLanguage.OutputState buzzer_state -> println(buzzer_state)},
                button: { DSLLanguage.ActionnerState button_state, String next_state -> { println(button_state); println next_state }}]*/
    }

    def button(int port) {
        ((DSLLanguage) this.getBinding().getVariable("lang")).button(port)
    }

    def led(int port) {
        ((DSLLanguage) this.getBinding().getVariable("lang")).led(port)
    }

    def buzzer(int port) {
        ((DSLLanguage) this.getBinding().getVariable("lang")).button(port)
    }

    def init(String state) {
        ((DSLLanguage) this.getBinding().getVariable("lang")).init(state)
    }

}
