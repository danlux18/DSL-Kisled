import io.github.mosser.arduinoml.kernel.App
import io.github.mosser.arduinoml.kernel.behavioral.Transition
import io.github.mosser.arduinoml.kernel.generator.ToWiring
import io.github.mosser.arduinoml.kernel.generator.Visitor
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
        importCustomizer.addStaticStars(DSLLanguage.SensorState.class.name)
        importCustomizer.addStaticStars(DSLLanguage.ActionnerState.class.name)
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
        println(Path.of("/").toString())
        Files.writeString(Path.of("").toAbsolutePath().resolve("ScenarioOne.ino"), codeGenerator.getResult().toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
    }

}
