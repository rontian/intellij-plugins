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
package com.intellij.protobuf.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.protobuf.lang.psi.PbServiceMethodType;
import com.intellij.protobuf.lang.psi.ProtoTokenTypes;

abstract class PbServiceMethodTypeMixin extends PbElementBase implements PbServiceMethodType {

  PbServiceMethodTypeMixin(ASTNode node) {
    super(node);
  }

  public boolean isStreaming() {
    return getNode().findChildByType(ProtoTokenTypes.STREAM) != null;
  }
}
