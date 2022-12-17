package fr.kisled.intern.kernel.generator;


import fr.kisled.intern.kernel.App;
import fr.kisled.intern.kernel.behavioral.SensorCondition;
import fr.kisled.intern.kernel.behavioral.ConditionComposite;
import fr.kisled.intern.kernel.behavioral.Action;
import fr.kisled.intern.kernel.behavioral.State;
import fr.kisled.intern.kernel.behavioral.Transition;
import fr.kisled.intern.kernel.structural.Actuator;
import fr.kisled.intern.kernel.structural.Sensor;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(State state);
	public abstract void visit(Transition transition);
	public abstract void visit(Action action);

	public abstract void visit(Actuator actuator);
	public abstract void visit(Sensor sensor);

	public abstract void visit(SensorCondition condition);
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

