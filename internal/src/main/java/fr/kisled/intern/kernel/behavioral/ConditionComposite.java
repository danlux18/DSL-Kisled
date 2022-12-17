package fr.kisled.intern.kernel.behavioral;

import fr.kisled.intern.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CanardNocturne
 */
public class ConditionComposite extends AbstractCondition {

    private List<AbstractCondition> conditions = new ArrayList<>();

    public void addCondition(AbstractCondition abstractCondition) {
        this.conditions.add(abstractCondition);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public List<AbstractCondition> getConditions() {
        return conditions;
    }
}
