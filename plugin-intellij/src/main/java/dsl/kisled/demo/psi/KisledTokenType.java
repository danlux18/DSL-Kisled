package dsl.kisled.demo.psi;

import com.intellij.psi.tree.IElementType;
import dsl.kisled.demo.KisledLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class KisledTokenType extends IElementType {
    public KisledTokenType(@NotNull @NonNls String debugName) {
        super(debugName, KisledLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SimpleTokenType." + super.toString();
    }
}
