import io.github.mosser.arduinoml.kernel.App
import io.github.mosser.arduinoml.kernel.behavioral.Action
import io.github.mosser.arduinoml.kernel.behavioral.Transition
import io.github.mosser.arduinoml.kernel.structural.Actuator
import io.github.mosser.arduinoml.kernel.structural.Brick
import io.github.mosser.arduinoml.kernel.structural.Sensor

class DSLLanguage {

    List<Brick> bricks
    State state
    List<io.github.mosser.arduinoml.kernel.behavioral.State> states;

    public DSLLanguage() {
        this.bricks = new ArrayList<>()
//        this.app = new App()
    }

    def state(String name) {
        if(this.state != null) {
//            this.states.add(this.state)
        }
        this.state = new State(name, this.bricks)
        return this.state
//        state.setName(name)
    }

    def button(int port) {
        Sensor button = new Sensor()
        button.setName("button_"+String.valueOf(port))
        button.setPin(port)
        this.bricks.add(button)
    }

    def led(int port) {
        Actuator led = new Actuator()
        led.setName("led_"+String.valueOf(port))
        led.setPin(port)
        this.bricks.add(led)
    }

/*    def led(OutputState output) {
println(output)
    }*/

    def buzzer(int port) {
        Actuator led = new Actuator()
        led.setName("buzzer_"+String.valueOf(port))
        led.setPin(port)
        this.bricks.add(button)
    }

/*    def buzzer(ActionnerState state) {
println(state)
    }*/

    def init(String state) {
println(state)
    }

    public enum OutputState {
        CONTINUE, SILENT
    }

    public enum ActionnerState {
        LOW, HIGH
    }

    public static class State {

        private String name;
        private List<Action> actions;
        private Transition transition;

        public State(String name, List<Brick> actuators) {
            this.name = name;
            this.actions = new ArrayList<>();
        }

        public State led(/*OutputState output*/) {
            /*def action = new Action()
            action.setActuator()
            this.actions.add()*/
            return this
        }


    }

}
