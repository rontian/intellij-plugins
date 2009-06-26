package com.intellij.tapestry.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTagChild;
import com.intellij.xml.util.XmlUtil;

/**
 * @author Alexey Chmutov
 *         Date: Jun 24, 2009
 *         Time: 4:44:59 PM
 */
public class TelExpressionHolder extends ASTWrapperPsiElement implements XmlTagChild {
  public TelExpressionHolder(ASTNode node) {
    super(node);
  }

  public XmlTag getParentTag() {
    final PsiElement parent = getParent();
    if(parent instanceof XmlTag) return (XmlTag)parent;
    return null;
  }

  public XmlTagChild getNextSiblingInTag() {
    PsiElement nextSibling = getNextSibling();
    if(nextSibling instanceof XmlTagChild) return (XmlTagChild)nextSibling;
    return null;
  }

  public XmlTagChild getPrevSiblingInTag() {
    final PsiElement prevSibling = getPrevSibling();
    if(prevSibling instanceof XmlTagChild) return (XmlTagChild)prevSibling;
    return null;
  }

  public boolean processElements(PsiElementProcessor processor, PsiElement place) {
    return XmlUtil.processXmlElements(this, processor, false);
  }

  public String toString(){
    return "TelExpressionHolder";
  }
}

