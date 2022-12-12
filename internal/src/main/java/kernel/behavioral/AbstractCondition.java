package kernel.behavioral;

import kernel.generator.Visitor;
import kernel.structural.Sensor;

/**
 * @author CanardNocturne
 */
public abstract class AbstractCondition {

    public abstract void accept(Visitor visitor);
}
