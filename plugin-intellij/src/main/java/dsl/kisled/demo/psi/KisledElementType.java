package dsl.kisled.demo.psi;

import com.intellij.psi.tree.IElementType;
import dsl.kisled.demo.KisledLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class KisledElementType extends IElementType {
    public KisledElementType(@NotNull @NonNls String debugName) {
        super(debugName, KisledLanguage.INSTANCE);
    }
}
