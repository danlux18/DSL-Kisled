package fr.kisled.intern.kernel.behavioral;

import fr.kisled.intern.kernel.generator.Visitable;
import fr.kisled.intern.kernel.generator.Visitor;

public class Transition implements Visitable {

	private State next;
	private AbstractCondition condition;
//	private Sensor sensor;
//	private SIGNAL value;


	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}

	public void setCondition(AbstractCondition condition) {
		this.condition = condition;
	}

	public AbstractCondition getCondition() {
		return condition;
	}

	//	public Sensor getSensor() {
//		return sensor;
//	}

//	public void setSensor(Sensor sensor) {
//		this.sensor = sensor;
//	}

//	public SIGNAL getValue() {
//		return value;
//	}

//	public void setValue(SIGNAL value) {
//		this.value = value;
//	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
