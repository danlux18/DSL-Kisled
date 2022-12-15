package dsl.kisled.demo.psi;

import com.intellij.psi.tree.TokenSet;

public interface KisledTokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(KisledTypes.NAME);

    TokenSet COMMENTS = TokenSet.create(KisledTypes.COMMENTS);
}
