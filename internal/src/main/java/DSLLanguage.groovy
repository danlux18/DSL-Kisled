import kernel.App
import kernel.behavioral.*
import kernel.structural.Actuator
import kernel.structural.Brick
import kernel.structural.SENSOR_SIGNAL
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
        TransitionCondition.metaClass."${name}" = { SENSOR_SIGNAL signal -> ((TransitionCondition) delegate).addCondition(name, signal) }
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
           this.addAction(brick, (Action.ACTUATOR_SIGNAL) args[0], (int) (args.length >= 2 ? args[1] : 1))
        } else if (brick instanceof Sensor) {
            return this.createCondition(brick, (SENSOR_SIGNAL) args[0], args.drop(1))
        }
    }

    def addAction(Actuator actuator, Action.ACTUATOR_SIGNAL signal, int quantity) {
        Action action = new Action()
        action.setActuator(actuator)
        action.setValue(signal)
        action.setQuantity(quantity)
        this.state.getActions().add(action)
    }

    def createCondition(Sensor sensor, SENSOR_SIGNAL signal, Object[] args) {
        SensorCondition condition = new SensorCondition();
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
        this.state.addTransition(transition)
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
            state.getTransitions().each {
                it.setNext(this.states.find {it.getName() == transitionName})}
        }
        State init = this.states.find({it.getName() == stateName})
        App app = new App()
        app.setName("test")
        app.setBricks(this.bricks)
        app.setStates(this.states)
        app.setInitial(init)
        return app
    }

    public static class TransitionCondition {

        ConditionComposite composite;
        List<Sensor> sensors;
        DSLLanguage dsl;

        public TransitionCondition(SensorCondition condition, List<Sensor> sensors, DSLLanguage dsl) {
            this.composite = new ConditionComposite()
            this.composite.addCondition(condition)
            this.sensors = sensors
            this.dsl = dsl
        }

        def and() {
            return this
        }

        def addCondition(String sensorName, SENSOR_SIGNAL signal) {
            SensorCondition condition = new SensorCondition()
            condition.setSensor(this.sensors.find {it.name == sensorName})
            condition.setSignal(signal)
            this.composite.addCondition(condition)
            return this
        }

        def to(String stateName) {
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
