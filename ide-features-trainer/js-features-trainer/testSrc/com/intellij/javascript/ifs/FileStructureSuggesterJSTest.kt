// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.javascript.ifs

import junit.framework.TestCase
import training.featuresSuggester.FeatureSuggesterTestUtils.focusEditor
import training.featuresSuggester.FeatureSuggesterTestUtils.logicalPositionToOffset
import training.featuresSuggester.FeatureSuggesterTestUtils.performFindInFileAction
import training.featuresSuggester.FeatureSuggesterTestUtils.testInvokeLater
import training.featuresSuggester.FileStructureSuggesterTest
import training.featuresSuggester.NoSuggestion

class FileStructureSuggesterJSTest : FileStructureSuggesterTest() {
  override val testingCodeFileName = "JavaScriptCodeExample.js"

  override fun getTestDataPath() = homePath + JsSuggestersTestUtils.relativeTestDataPath

  override fun `testFind field and get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(8, 0)
      performFindInFileAction("field", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      assertSuggestedCorrectly()
    }
  }

  override fun `testFind method and get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(4, 0)
      performFindInFileAction("mai", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      assertSuggestedCorrectly()
    }
  }

  override fun `testFind function parameter and don't get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(14, 0)
      performFindInFileAction("args", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      TestCase.assertTrue(expectedSuggestion is NoSuggestion)
    }
  }

  override fun `testFind local variable declaration and don't get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(14, 0)
      performFindInFileAction("abc", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      TestCase.assertTrue(expectedSuggestion is NoSuggestion)
    }
  }

  override fun `testFind variable usage and don't get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(18, 0)
      performFindInFileAction("abc", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      TestCase.assertTrue(expectedSuggestion is NoSuggestion)
    }
  }

  override fun `testFind method usage and don't get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(18, 0)
      performFindInFileAction("main", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      TestCase.assertTrue(expectedSuggestion is NoSuggestion)
    }
  }

  override fun `testFind type usage and don't get suggestion`() {
    with(myFixture) {
      val fromOffset = logicalPositionToOffset(16, 25)
      performFindInFileAction("anotherCl", fromOffset)
      focusEditor()
    }

    testInvokeLater(project) {
      TestCase.assertTrue(expectedSuggestion is NoSuggestion)
    }
  }
}
