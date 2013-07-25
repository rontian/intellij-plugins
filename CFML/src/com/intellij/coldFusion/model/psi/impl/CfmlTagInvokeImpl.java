package com.intellij.coldFusion.model.psi.impl;

import com.intellij.coldFusion.model.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;

/**
 * User: vnikolaenko
 * Date: 29.04.2009
 */
// corresponding text pattern: <cfinvoke ... > 
public class CfmlTagInvokeImpl extends CfmlTagImpl implements CfmlFunctionCall {
  private static final String TAG_NAME = "cfinvoke";

  public CfmlTagInvokeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public PsiType getPsiType() {
    return null;
  }

  public CfmlReference getReferenceExpression() {
    return findNotNullChildByClass(CfmlReferenceExpression.class);
  }

  @NotNull
  @Override
  public PsiElement getNavigationElement() {
    return getReferenceExpression();
  }

  public CfmlArgumentList findArgumentList() {
    return null;
  }

  public PsiType[] getArgumentTypes() {
    return new PsiType[0];
  }

  @Override
  public String getName() {
    CfmlReferenceExpression name = findChildByClass(CfmlReferenceExpression.class);
    return name != null ? name.getName() : null;
  }

  public String getTagName() {
    return TAG_NAME;
  }

  @NotNull
  @Override
  public PsiReference[] getReferences() {
    final PsiElement reference = getAttributeValueElement("component");

    if (reference != null) {
      ASTNode referenceNode = reference.getNode();
      if (referenceNode != null) {
        return new PsiReference[]{new CfmlComponentReference(referenceNode, this)};
      }
    }
    return super.getReferences();
  }
}
