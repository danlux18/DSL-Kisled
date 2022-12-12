package dsl.kisled.demo;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KisledFileType extends LanguageFileType {

    public static final KisledFileType INSTANCE = new KisledFileType();

    private KisledFileType() {
        super(KisledLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Kisled File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Kisled language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "kld";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return KisledIcons.FILE;
    }
}
