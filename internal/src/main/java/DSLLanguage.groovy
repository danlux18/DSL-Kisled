import kernel.App
import kernel.behavioral.AbstractCondition
import kernel.behavioral.Action
import kernel.behavioral.Condition
import kernel.behavioral.ConditionComposite
import kernel.behavioral.State
import kernel.behavioral.Transition
import kernel.structural.Actuator
import kernel.structural.Brick
import kernel.structural.SIGNAL
import kernel.structural.Sensor

public class DSLLanguage {

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
        TransitionCondition.metaClass."${name}" = {SensorState signal, String state -> ((TransitionCondition) delegate).finishCondition(name, signal.signal, state) }
//        TransitionCondition.metaClass."${name}" = {test -> println("test3") }
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
           this.addAction(brick, ((ActionnerState) args[0]).signal)
        } else if (brick instanceof Sensor) {
            return this.createCondition(brick, ((SensorState) args[0]).signal, args.drop(1))
        }
    }

    def addAction(Actuator actuator, SIGNAL signal) {
        Action action = new Action()
        action.setActuator(actuator)
        action.setValue(signal)
        this.state.getActions().add(action)
    }

    def createCondition(Sensor sensor, SIGNAL signal, Object[] args) {
        Condition condition = new Condition();
        condition.setSensor(sensor)
        condition.setSignal(signal)
        if(args.length == 0) {
            return new TransitionCondition(condition, this.bricks.findAll { it instanceof Sensor }.collect {(Sensor) it}, this)
        } else
            this.createTransition(condition, args[0] as String)
    }

    def createTransition(AbstractCondition condition, String stateName) {
        Transition transition = new Transition()
        transition.setCondition(condition)
        this.state.setTransition(transition)
        this.transitionWanted.put(this.state, stateName)
    }

   /* def addTransition(Sensor sensor, ) {

    }*/

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

    public static class TransitionCondition {

        ConditionComposite composite;
        List<Sensor> sensors;
        DSLLanguage dsl;

        public TransitionCondition(Condition condition, List<Sensor> sensors, DSLLanguage dsl) {
            this.composite = new ConditionComposite()
            this.composite.addCondition(condition)
            this.sensors = sensors
            this.dsl = dsl
        }

        def and() {
            return this
        }

        def addCondition(String sensorName, SIGNAL signal) {
            Condition condition = new Condition()
            condition.setSensor(this.sensors.find {it.name == sensorName})
            condition.setSignal(signal)
            this.composite.addCondition(condition)
        }

        def finishCondition(String sensorName, SIGNAL signal, String stateName) {
            this.addCondition(sensorName, signal)

            this.dsl.createTransition(this.composite, stateName)
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
