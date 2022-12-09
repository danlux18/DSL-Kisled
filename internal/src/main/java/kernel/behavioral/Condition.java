package kernel.behavioral;

import kernel.generator.Visitor;
import kernel.structural.SIGNAL;

/**
 * @author CanardNocturne
 */
public class Condition extends AbstractCondition {

    private SIGNAL signal;

    public void setSignal(SIGNAL signal) {
        this.signal = signal;
    }

    public SIGNAL getSignal() {
        return signal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
