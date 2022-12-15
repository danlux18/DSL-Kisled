package dsl.kisled.demo.psi.impl;

import com.intellij.lang.ASTNode;
import dsl.kisled.demo.psi.KisledBrick;
import dsl.kisled.demo.psi.KisledTypes;

public class KisledPsiImplUtil {
    public static String getName(KisledBrick element) {
        ASTNode keyNode = element.getNode().findChildByType(KisledTypes.NAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }
}