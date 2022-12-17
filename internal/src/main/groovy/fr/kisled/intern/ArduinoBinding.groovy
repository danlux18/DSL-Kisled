package fr.kisled.intern

class ArduinoBinding extends Binding {

    private Script script;

    public ArduinoBinding() {
        super
    }

    public ArduinoBinding(Script script) {
        super
        this.script = script
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public Object getVariable(String name) {

        return super.getVariable(name)
    }

    public void setVariable(String name, Object value) {
        super.setVariable(name, value)
    }

    def methodMissing(String name, args) {
        println("test")
    }

}
