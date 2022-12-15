// This is a generated file. Not intended for manual editing.
package dsl.kisled.demo.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dsl.kisled.demo.psi.KisledTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import dsl.kisled.demo.psi.*;

public class KisledTransitionsImpl extends ASTWrapperPsiElement implements KisledTransitions {

  public KisledTransitionsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KisledVisitor visitor) {
    visitor.visitTransitions(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KisledVisitor) accept((KisledVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public KisledTransition getTransition() {
    return findNotNullChildByClass(KisledTransition.class);
  }

  @Override
  @Nullable
  public KisledTransitions getTransitions() {
    return findChildByClass(KisledTransitions.class);
  }

}
