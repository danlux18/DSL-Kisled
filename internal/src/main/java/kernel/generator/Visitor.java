package kernel.generator;


import kernel.App;
import kernel.behavioral.Condition;
import kernel.behavioral.ConditionComposite;
import kernel.behavioral.Action;
import kernel.behavioral.State;
import kernel.behavioral.Transition;
import kernel.structural.Actuator;
import kernel.structural.Sensor;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(State state);
	public abstract void visit(Transition transition);
	public abstract void visit(Action action);

	public abstract void visit(Actuator actuator);
	public abstract void visit(Sensor sensor);

	public abstract void visit(Condition condition);
	public abstract void visit(ConditionComposite conditions);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

