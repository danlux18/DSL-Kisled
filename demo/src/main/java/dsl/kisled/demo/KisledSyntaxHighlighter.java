package dsl.kisled.demo;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import dsl.kisled.demo.psi.KisledTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class KisledSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("KISLED_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("KISLED_NAME", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey VALUE =
            createTextAttributesKey("KISLED_VALUE", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey COND_VALUE =
            createTextAttributesKey("KISLED_COND_VALUE", DefaultLanguageHighlighterColors.CONSTANT);

    public static final TextAttributesKey PORT =
            createTextAttributesKey("KISLED_PORT", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("KISLED_COMMENTS", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("KISLED_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] COND_VALUE_KEYS = new TextAttributesKey[]{COND_VALUE};
    private static final TextAttributesKey[] PORT_KEYS = new TextAttributesKey[]{PORT};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new KisledFlexAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(KisledTypes.ASSIGN)) {
            return SEPARATOR_KEYS;
        }
        List<IElementType> keys = List.of(
                KisledTypes.KACTUATOR,
                KisledTypes.KSENSOR
        );
        if (keys.contains(tokenType)) {
            return KEY_KEYS;
        }
        List<IElementType> values = List.of(
                KisledTypes.KON,
                KisledTypes.KOFF,
                KisledTypes.KSHORT,
                KisledTypes.KLONG
        );
        if (values.contains(tokenType)) {
            return VALUE_KEYS;
        }

        List<IElementType> condValues = List.of(
                KisledTypes.KHIGH,
                KisledTypes.KLOW
        );
        if (condValues.contains(tokenType)) {
            return COND_VALUE_KEYS;
        }

        if (tokenType.equals(KisledTypes.PORT)) {
            return PORT_KEYS;
        }
        if (tokenType.equals(KisledTypes.COMMENTS)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}
