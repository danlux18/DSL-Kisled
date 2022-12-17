package dsl.kisled.demo.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import dsl.kisled.demo.KisledFileType;
import dsl.kisled.demo.KisledLanguage;
import org.jetbrains.annotations.NotNull;

public class KisledFile extends PsiFileBase {

    public KisledFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, KisledLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return KisledFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Simple File";
    }
}
