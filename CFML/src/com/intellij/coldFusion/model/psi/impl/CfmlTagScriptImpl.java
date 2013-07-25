package com.intellij.coldFusion.model.psi.impl;

import com.intellij.coldFusion.model.lexer.CfmlTokenTypes;
import com.intellij.coldFusion.model.psi.CfmlRecursiveElementVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

/**
 * @author vnikolaenko
 */
public class CfmlTagScriptImpl extends CfmlTagImpl {
  public static final String TAG_NAME = "cfscript";

  public CfmlTagScriptImpl(ASTNode astNode) {
    super(astNode);
  }

  @NotNull
  public String getTagName() {
    return TAG_NAME;
  }

  public boolean isInsideTag(int offset) {
    PsiElement scriptStartElement = findChildByType(CfmlTokenTypes.R_ANGLEBRACKET);
    PsiElement scriptEndElement = findChildByType(CfmlTokenTypes.LSLASH_ANGLEBRACKET);
    if (scriptStartElement != null &&
        scriptEndElement != null &&
        scriptStartElement.getTextRange().getEndOffset() <= offset &&
        scriptEndElement.getTextRange().getStartOffset() >= offset) {
      return true;
    }
    return false;
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CfmlRecursiveElementVisitor) {
      ((CfmlRecursiveElementVisitor)visitor).visitCfmlTag(this);
    }
    else {
      super.accept(visitor);
    }
  }
}

