package fr.kisled.intern.kernel.behavioral;


import fr.kisled.intern.kernel.NamedElement;
import fr.kisled.intern.kernel.generator.Visitable;
import fr.kisled.intern.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class State implements NamedElement, Visitable {

	private String name;
	private List<Action> actions = new ArrayList<Action>();
	private List<Transition> transitions = new ArrayList<>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
