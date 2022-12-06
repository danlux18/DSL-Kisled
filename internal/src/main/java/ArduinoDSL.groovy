import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

class ArduinoDSL {

    private GroovyShell shell;
    private CompilerConfiguration config;
    private ArduinoBinding binding;
    private ArduinoBaseScript basescript;

    ArduinoDSL() {
        this.binding = new ArduinoBinding()
        this.binding.setVariable("lang", new DSLLanguage())
        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStaticStars(DSLLanguage.ActionnerState.class.name)
        importCustomizer.addStaticStars(DSLLanguage.OutputState.class.name)
        this.config = new CompilerConfiguration()
        config.setScriptBaseClass("ArduinoBaseScript")
        config.addCompilationCustomizers(importCustomizer)
        shell = new GroovyShell(config)
    }

    void eval(File scriptFile) {
        Script script = this.shell.parse(scriptFile)
        this.binding.setScript(script)
        script.setBinding(this.binding)
        script.run()
    }

}
