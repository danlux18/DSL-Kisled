package dsl.kisled.demo;

import com.intellij.lang.Language;

public class KisledLanguage extends Language {
    public static final KisledLanguage INSTANCE = new KisledLanguage();

    private KisledLanguage() {
        super("Kisled");
    }
}
