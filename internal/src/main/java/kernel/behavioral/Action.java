package kernel.behavioral;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.Actuator;

public class Action implements Visitable {

	private ACTUATOR_SIGNAL value;
	private Actuator actuator;
	private int quantity = 1;


	public ACTUATOR_SIGNAL getValue() {
		return value;
	}

	public void setValue(ACTUATOR_SIGNAL value) {
		this.value = value;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public enum ACTUATOR_SIGNAL {
		OFF("LOW"), SHORT("HIGH"), LONG("HIGH"), ON("HIGH");

		private final String level;

		ACTUATOR_SIGNAL(String level) {

			this.level = level;
		}

		public String getLevel() {
			return level;
		}
	}

}
