package fr.kisled.intern.kernel.generator;


import fr.kisled.intern.kernel.App;
import fr.kisled.intern.kernel.behavioral.*;
import fr.kisled.intern.kernel.structural.Actuator;
import fr.kisled.intern.kernel.structural.Brick;
import fr.kisled.intern.kernel.structural.Sensor;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO}


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s",s));
	}

	@Override
	public void visit(App app) {
		//first pass, create global vars
		context.put("pass", PASS.ONE);
		w("// Wiring code generated from an ArduinoML model\n");
		w(String.format("// Application name: %s\n", app.getName())+"\n");

		w("long debounce = 200;\n");
		w("\nenum STATE {");
		String sep ="";
		for(State state: app.getStates()){
			w(sep);
			state.accept(this);
			sep=", ";
		}
		w("};\n");
//		for(State state : app.getStates()) {
			w(String.format("\nboolean bounceGuard = false;\n"));
			w(String.format("long lastDebounceTime = 0;\n"));
//		}
		w("\n");

		w("boolean stateExecuted = false;\n\n");

		if (app.getInitial() != null) {
			w("STATE currentState = " + app.getInitial().getName()+";\n");
		}

		for(Brick brick: app.getBricks()){
			brick.accept(this);
		}

		//second pass, setup and loop
		context.put("pass",PASS.TWO);
		w("\nvoid setup(){\n");
		for(Brick brick: app.getBricks()){
			brick.accept(this);
		}
		w("}\n");

		w("\nvoid loop() {\n" +
			"\tswitch(currentState){\n");
		for(State state: app.getStates()){
			state.accept(this);
		}
		w("\t}\n" +
			"}");
	}

	@Override
	public void visit(Actuator actuator) {
		if(context.get("pass") == PASS.ONE) {
			return;
		}
		if(context.get("pass") == PASS.TWO) {
			w(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]\n", actuator.getPin(), actuator.getName()));
			return;
		}
	}


	@Override
	public void visit(Sensor sensor) {
		/*if(context.get("pass") == PASS.ONE) {
			w(String.format("\nboolean bounceGuard = false;\n", sensor.getName()));
			w(String.format("long lastDebounceTime = 0;\n", sensor.getName()));
			return;
		}*/
		if(context.get("pass") == PASS.TWO) {
			w(String.format("  pinMode(%d, INPUT);  // %s [Sensor]\n", sensor.getPin(), sensor.getName()));
			return;
		}
	}

	@Override
	public void visit(SensorCondition condition) {
		w(String.format("digitalRead(%d) == %s", condition.getSensor().getPin(), condition.getSignal()));
	}

	@Override
	public void visit(ConditionComposite conditions) {
		w("(");
		int conditionsCount = conditions.getConditions().size();
		for (int i = 0; i < conditionsCount; i++) {
			AbstractCondition condition = conditions.getConditions().get(i);
			condition.accept(this);
			if(i + 1 < conditionsCount)
				w(" && ");
		}
		w(")");
	}

	@Override
	public void visit(State state) {
		if(context.get("pass") == PASS.ONE){
			w(state.getName());
			return;
		}
		if(context.get("pass") == PASS.TWO) {
			w("\t\tcase " + state.getName() + ":\n");
			for (Action action : state.getActions()) {
				action.accept(this);
			}

			for (Transition transition : state.getTransitions()) {
				transition.accept(this);
			}
			w("\t\tbreak;\n");
		}

	}

	@Override
	public void visit(Transition transition) {
		if(context.get("pass") == PASS.ONE) {
			return;
		}
		if(context.get("pass") == PASS.TWO) {
			String transitionName = transition.getNext().getName();
			w(String.format("\t\t\tbounceGuard = millis() - lastDebounceTime > debounce;\n"));
			if(transition.getCondition() != null) {
				w("\t\t\tif (");
				transition.getCondition().accept(this);
				w(String.format(" && bounceGuard) {\n", transitionName));
				w(String.format("\t\t\t\tlastDebounceTime = millis();\n"));
				w("\t\t\t\tstateExecuted = false;\n");
				w("\t\t\t\tcurrentState = " + transition.getNext().getName() + ";\n");
				w("\t\t\t}\n");
			} else
				w("\t\t\t\tcurrentState = " + transition.getNext().getName() + ";\n");
		}
	}

	@Override
	public void visit(Action action) {
		if(context.get("pass") == PASS.ONE) {
			return;
		}
		if(context.get("pass") == PASS.TWO) {
			switch (action.getValue()) {
				case OFF, ON -> {
					w(String.format("\t\t\tdigitalWrite(%d,%s);\n",action.getActuator().getPin(),action.getValue().getLevel()));
				}
				case SHORT -> {
					w("\t\t\tif(!stateExecuted) {\n");
					for (int i = 0; i < action.getQuantity(); i++) {
						w(String.format("\t\t\t\tdigitalWrite(%d,%s);\n",action.getActuator().getPin(), "HIGH"));
						w("\t\t\t\tdelay(1000);\n");
						w(String.format("\t\t\t\tdigitalWrite(%d,%s);\n",action.getActuator().getPin(), "LOW"));
						if(i < action.getQuantity() - 1)
							w("\t\t\t\tdelay(1000);\n");
					}
					w("\t\t\t\tstateExecuted = true;\n\t\t\t}\n");
				}
				case LONG -> {
					w("\t\t\tif(!stateExecuted) {\n");
					for (int i = 0; i < action.getQuantity(); i++) {
						w(String.format("\t\t\t\tdigitalWrite(%d,%s);\n",action.getActuator().getPin(), "HIGH"));
						w("\t\t\t\tdelay(3000);\n");
						w(String.format("\t\t\t\tdigitalWrite(%d,%s);\n",action.getActuator().getPin(), "LOW"));
						if(i < action.getQuantity() - 1)
							w("\t\t\t\tdelay(1000);\n");
					}
					w("\t\t\t\tstateExecuted = true;\n\t\t\t}\n");
				}
			}
		}
	}



}
