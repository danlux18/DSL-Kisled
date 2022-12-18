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

public class KisledStatesImpl extends ASTWrapperPsiElement implements KisledStates {

  public KisledStatesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KisledVisitor visitor) {
    visitor.visitStates(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KisledVisitor) accept((KisledVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public KisledState getState() {
    return findNotNullChildByClass(KisledState.class);
  }

  @Override
  @Nullable
  public KisledStates getStates() {
    return findChildByClass(KisledStates.class);
  }

}