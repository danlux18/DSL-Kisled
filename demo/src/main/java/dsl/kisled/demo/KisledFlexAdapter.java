package dsl.kisled.demo;

import com.intellij.lexer.FlexAdapter;

public class KisledFlexAdapter extends FlexAdapter {
    public KisledFlexAdapter() {
        super(new KisledLexer(null));
    }
}
