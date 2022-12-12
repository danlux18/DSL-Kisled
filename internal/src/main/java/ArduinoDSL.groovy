import kernel.App
import kernel.behavioral.Action
import kernel.generator.ToWiring
import kernel.generator.Visitor
import kernel.structural.SENSOR_SIGNAL
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class ArduinoDSL {

    private GroovyShell shell;
    private CompilerConfiguration config;
    private ArduinoBinding binding;
    private ArduinoBaseScript basescript;

    ArduinoDSL() {
        this.binding = new ArduinoBinding()
        this.binding.setVariable("lang", new DSLLanguage())
        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStaticStars(SENSOR_SIGNAL.class.name)
        importCustomizer.addStaticStars(Action.ACTUATOR_SIGNAL.class.name)
        this.config = new CompilerConfiguration()
        config.setScriptBaseClass("ArduinoBaseScript")
        config.addCompilationCustomizers(importCustomizer)
        shell = new GroovyShell(config)
    }

    void eval(File scriptFile) {
        Script script = this.shell.parse(scriptFile)
        this.binding.setScript(script)
        script.setBinding(this.binding)
        App app = (App) script.run()
        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);
        Files.writeString(Path.of("").toAbsolutePath().resolve("ScenarioOne/ScenarioOne.ino"), codeGenerator.getResult().toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)
    }

}
