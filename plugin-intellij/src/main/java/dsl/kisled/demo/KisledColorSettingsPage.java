package dsl.kisled.demo;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class KisledColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Key", KisledSyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", KisledSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Value", KisledSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Condition value", KisledSyntaxHighlighter.COND_VALUE),
            new AttributesDescriptor("Port", KisledSyntaxHighlighter.PORT),
            new AttributesDescriptor("Bad value", KisledSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return KisledIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new KisledSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "// You are reading the \".kld\" entry.\n" +
                "sensor button : 1\n" +
                "actuator led : 2\n" +
                "\n" +
                "on {\n" +
                "   led <= ON\n" +
                "   button LOW => off\n" +
                "}\n" +
                "\n" +
                "off {\n" +
                "   led <= OFF\n" +
                "   button HIGH => on\n" +
                "}\n" +
                "\n" +
                "-> off";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Kisled";
    }
}
