import io.github.mosser.arduinoml.kernel.App
import io.github.mosser.arduinoml.kernel.behavioral.Action
import io.github.mosser.arduinoml.kernel.behavioral.State
import io.github.mosser.arduinoml.kernel.behavioral.Transition
import io.github.mosser.arduinoml.kernel.structural.Actuator
import io.github.mosser.arduinoml.kernel.structural.Brick
import io.github.mosser.arduinoml.kernel.structural.SIGNAL
import io.github.mosser.arduinoml.kernel.structural.Sensor

class DSLLanguage {

    List<Brick> bricks
    State state
    List<State> states;
    Map<State, String> transitionWanted;

    public DSLLanguage() {
        this.bricks = new ArrayList<>()
        this.states = new ArrayList<>()
        this.transitionWanted = new HashMap<>()
//        this.app = new App()
    }

    def state(String name) {
        if (this.state != null) {
            this.states.add(this.state)
        }
        this.state = new State()
        this.state.setName(name)
    }

    def sensor(String name, int port) {
        Sensor button = new Sensor()
        button.setName(name)
        button.setPin(port)
        this.bricks.add(button)
    }

    def actuator(String name, int port) {
        Actuator led = new Actuator()
        led.setName(name)
        led.setPin(port)
        this.bricks.add(led)
    }

    def set(String varName, Object[] args) {
        def brick = this.bricks.find { it.getName() == varName }
        if (brick instanceof Actuator) {
            Action action = new Action()
            action.setActuator(brick)
            action.setValue(((ActionnerState) args[0]).signal)
            this.state.getActions().add(action)
        } else if (brick instanceof Sensor) {
            Transition transition = new Transition()
            transition.setSensor(brick)
            transition.setValue(((SensorState) args[0]).signal)
            this.state.setTransition(transition)
            this.transitionWanted.put(this.state, (String) args[1])
        }
    }

    /*def led(int port) {
        Actuator led = new Actuator()
        led.setName("led_"+String.valueOf(port))
        led.setPin(port)
        this.bricks.add(led)
    }*/

/*    def led(OutputState output) {
println(output)
    }*/

    /*def buzzer(int port) {
        Actuator led = new Actuator()
        led.setName("buzzer_"+String.valueOf(port))
        led.setPin(port)
        this.bricks.add(button)
    }*/

/*    def buzzer(ActionnerState state) {
println(state)
    }*/

    def App init(String stateName) {
        this.states.add(this.state)
        for(State state : this.states) {
            String transitionName = this.transitionWanted.get(state)
            state.getTransition().setNext(this.states.find {it.getName() == transitionName})
        }
        State init = this.states.find({it.getName() == stateName})
        App app = new App()
        app.setName("test")
        app.setBricks(this.bricks)
        app.setStates(this.states)
        app.setInitial(init)
        return app
    }

    public enum ActionnerState {
        CONTINUE(SIGNAL.HIGH), SILENT(SIGNAL.LOW)

        SIGNAL signal;

        ActionnerState(SIGNAL signal) {
            this.signal = signal;
        }
    }

    public enum SensorState {
        LOW(SIGNAL.LOW), HIGH(SIGNAL.HIGH)

        SIGNAL signal;

        SensorState(SIGNAL signal) {
            this.signal = signal;
        }
    }

    /*public static class State {

        private String name;
        private List<Action> actions;
        private Transition transition;

        public State(String name, List<Brick> actuators) {
            this.name = name;
            this.actions = new ArrayList<>();
        }

        public State led(*//*OutputState output*//*) {
            *//*def action = new Action()
            action.setActuator()
            this.actions.add()*//*
            return this
        }


    }*/

}
