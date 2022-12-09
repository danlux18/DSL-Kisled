package kernel.behavioral;

import kernel.generator.Visitor;
import kernel.structural.Sensor;

/**
 * @author CanardNocturne
 */
public abstract class AbstractCondition {

    protected Sensor sensor;

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public abstract void accept(Visitor visitor);
}
