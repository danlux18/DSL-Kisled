public class Main {
  static void main(String[] args) {
    ArduinoDSL dsl = new ArduinoDSL()
    dsl.eval(new File("src/main/java/ScenarioOne.groovy"))
  }
}