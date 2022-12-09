abstract class ArduinoBaseScript extends Script {

    def state(String name) {
        return ((DSLLanguage) this.getBinding().getVariable("lang")).state(name);
        /*[led: { DSLLanguage.OutputState led_state -> println(led_state)},
                buzzer: { DSLLanguage.OutputState buzzer_state -> println(buzzer_state)},
                button: { DSLLanguage.ActionnerState button_state, String next_state -> { println(button_state); println next_state }}]*/
    }

    def sensor(String name, int port) {
//        ArduinoBaseScript.metaClass.properties.add(new MetaProperty() {}) {name { -> println("test2") } }
        /*ArduinoBaseScript.metaClass."${name}" = {test, test2 -> println("test2") }*/
        return ((DSLLanguage) this.getBinding().getVariable("lang")).sensor(name, port)
    }

    def actuator(String name, int port) {
        return ((DSLLanguage) this.getBinding().getVariable("lang")).actuator(name, port)
    }

    def init(String state) {
        ((DSLLanguage) this.getBinding().getVariable("lang")).init(state)
    }

    def methodMissing(String name, args) {
        /*if(name == "button2")
            [and: { test -> println(test)}]
        else*/
        return ((DSLLanguage) this.getBinding().getVariable("lang")).set(name, args)
    }

}
