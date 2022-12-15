# Intellij Plugin for Arduino External DSL

The code is based on [IntelliJ Language Plugin Tutorial](https://plugins.jetbrains.com/docs/intellij/custom-language-support-tutorial.html)

## Run the Project
The easiest way, is to open this project as a `gradle` project inside IntelliJ and run
the `Run Plugin` run configuration. This will open a new IDE window for which the plugin
will be installed.

Otherwise, here is the [Jetbrains doc to run gradle tasks](https://www.jetbrains.com/idea/guide/tutorials/working-with-gradle/running-gradle-tasks/)
```shell
./gradlew runIde
```