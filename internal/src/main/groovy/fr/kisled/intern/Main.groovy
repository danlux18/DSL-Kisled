package fr.kisled.intern

public class Main {
  static void main(String[] args) {
    if(args.length >= 1) {
      ArduinoDSL dsl = new ArduinoDSL()
      dsl.eval(new File(args[0]))
    }
  }
}