// This is a generated file. Not intended for manual editing.
package com.jetbrains.lang.dart.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.jetbrains.lang.dart.DartTokenTypes.*;
import com.jetbrains.lang.dart.psi.*;
import com.jetbrains.lang.dart.util.DartPsiImplUtil;

public class DartPartStatementImpl extends DartPsiCompositeElementImpl implements DartPartStatement {

  public DartPartStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DartVisitor visitor) {
    visitor.visitPartStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DartVisitor) accept((DartVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DartMetadata> getMetadataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DartMetadata.class);
  }

  @Override
  @NotNull
  public DartUriElement getUriElement() {
    return findNotNullChildByClass(DartUriElement.class);
  }

  @Override
  public @NotNull String getUriString() {
    return DartPsiImplUtil.getUriString(this);
  }

}
