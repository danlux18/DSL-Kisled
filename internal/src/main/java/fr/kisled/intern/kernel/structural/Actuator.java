package fr.kisled.intern.kernel.structural;

import fr.kisled.intern.kernel.generator.Visitor;

public class Actuator extends Brick {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
