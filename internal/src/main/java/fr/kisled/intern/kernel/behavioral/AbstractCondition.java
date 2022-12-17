package fr.kisled.intern.kernel.behavioral;

import fr.kisled.intern.kernel.generator.Visitor;

/**
 * @author CanardNocturne
 */
public abstract class AbstractCondition {

    public abstract void accept(Visitor visitor);
}
