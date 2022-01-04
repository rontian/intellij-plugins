/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.protobuf.shared.gencode;

import com.google.common.collect.ImmutableList;
import com.intellij.protobuf.lang.psi.PbFile;
import com.intellij.protobuf.lang.resolve.PbFileResolver;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Locates a .proto file based on generated source comments */
public final class ProtoFromSourceComments {

  private static final List<String> GENERATED_COMMENTS = ImmutableList.of(
      "Generated by the protocol buffer compiler.  DO NOT EDIT!",
      "Code generated by protoc-gen-go. DO NOT EDIT.",
      "Code generated by protoc-gen-gogo. DO NOT EDIT."
  );

  private static final Pattern SOURCE_PATTERN = Pattern.compile(".* source: (.*\\.proto)$");

  private ProtoFromSourceComments() {}

  @Nullable
  public static PbFile findProtoOfGeneratedCode(String commentPrefix, PsiFile file) {
    boolean foundGeneratedComment = false;
    String source = null;
    for (PsiElement element = file.getFirstChild();
         isWhitespaceOrComment(element); element = element.getNextSibling()) {

      if (isGeneratedComment(element)) {
        foundGeneratedComment = true;
        continue;
      }

      String extractedSource = extractSource(element);
      if (extractedSource != null) {
        source = extractedSource;
        break;
      }
    }
    if (!foundGeneratedComment || source == null) {
      return null;
    }

    List<PbFile> pbFiles = PbFileResolver.findFilesForContext(source, file);
    if (!pbFiles.isEmpty()) {
      return pbFiles.get(0);
    } else {
      return null;
    }
  }

  private static boolean isGeneratedComment(PsiElement element) {
    String text = element.getText();
    for (String alt : GENERATED_COMMENTS) {
      if (text.contains(alt)) {
        return true;
      }
    }
    return false;
  }

  private static String extractSource(PsiElement element) {
    Matcher matcher = SOURCE_PATTERN.matcher(element.getText());
    return matcher.matches() ? matcher.group(1) : null;
  }

  private static boolean isWhitespaceOrComment(@Nullable PsiElement node) {
    return node instanceof PsiWhiteSpace || node instanceof PsiComment;
  }
}
