// This is a generated file. Not intended for manual editing.
package com.jetbrains.lang.makefile.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.jetbrains.lang.makefile.psi.MakefileTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.jetbrains.lang.makefile.psi.*;

public class MakefilePrerequisitesImpl extends ASTWrapperPsiElement implements MakefilePrerequisites {

  public MakefilePrerequisitesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MakefileVisitor visitor) {
    visitor.visitPrerequisites(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MakefileVisitor) accept((MakefileVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MakefileNormalPrerequisites getNormalPrerequisites() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MakefileNormalPrerequisites.class));
  }

  @Override
  @Nullable
  public MakefileOrderOnlyPrerequisites getOrderOnlyPrerequisites() {
    return PsiTreeUtil.getChildOfType(this, MakefileOrderOnlyPrerequisites.class);
  }

}
