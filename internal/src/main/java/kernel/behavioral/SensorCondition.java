package kernel.behavioral;

import kernel.generator.Visitor;
import kernel.structural.SENSOR_SIGNAL;
import kernel.structural.Sensor;

/**
 * @author CanardNocturne
 */
public class SensorCondition extends AbstractCondition {

    private SENSOR_SIGNAL signal;
    protected Sensor sensor;

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSignal(SENSOR_SIGNAL signal) {
        this.signal = signal;
    }

    public SENSOR_SIGNAL getSignal() {
        return signal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
