// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.grazie.jlanguage

import com.intellij.grazie.GrazieDynamic
import com.intellij.grazie.remote.RemoteLangDescriptor
import org.languagetool.language.Language

@Suppress("unused")
enum class Lang(val displayName: String, val className: String, val remote: RemoteLangDescriptor) {
  BRITISH_ENGLISH("English (GB)", "BritishEnglish", RemoteLangDescriptor.ENGLISH),
  AMERICAN_ENGLISH("English (US)", "AmericanEnglish", RemoteLangDescriptor.ENGLISH),
  CANADIAN_ENGLISH("English (Canadian)", "CanadianEnglish", RemoteLangDescriptor.ENGLISH),
  GERMANY_GERMAN("German (Germany)", "GermanyGerman", RemoteLangDescriptor.GERMAN),
  AUSTRIAN_GERMAN("German (Austria)", "AustrianGerman", RemoteLangDescriptor.GERMAN),
  PORTUGAL_PORTUGUESE("Portuguese (Portugal)", "PortugalPortuguese", RemoteLangDescriptor.PORTUGUESE),
  BRAZILIAN_PORTUGUESE("Portuguese (Brazil)", "BrazilianPortuguese", RemoteLangDescriptor.PORTUGUESE),
  SPANISH("Spanish", "Spanish", RemoteLangDescriptor.SPANISH),
  RUSSIAN("Russian", "Russian", RemoteLangDescriptor.RUSSIAN),
  FRENCH("French", "French", RemoteLangDescriptor.FRENCH),
  ITALIAN("Italian", "Italian", RemoteLangDescriptor.ITALIAN),
  DUTCH("Dutch", "Dutch", RemoteLangDescriptor.DUTCH),
  JAPANESE("Japanese", "Japanese", RemoteLangDescriptor.JAPANESE),
  CHINESE("Chinese", "Chinese", RemoteLangDescriptor.CHINESE),
  PERSIAN("Persian", "Persian", RemoteLangDescriptor.PERSIAN),
  POLISH("Polish", "Polish", RemoteLangDescriptor.POLISH),
  GREEK("Greek", "Greek", RemoteLangDescriptor.GREEK),
  ROMANIAN("Romanian", "Romanian", RemoteLangDescriptor.ROMANIAN),
  SLOVAK("Slovak", "Slovak", RemoteLangDescriptor.SLOVAK),
  UKRAINIAN("Ukrainian", "Ukrainian", RemoteLangDescriptor.UKRAINIAN);

  companion object {
    operator fun get(lang: Language): Lang? = values().find { lang.name == it.displayName }
    // NOTE: dialects have same short code
    operator fun get(code: String): Lang? = values().find { it.shortCode == code }

    fun sortedValues() = values().sortedBy(Lang::displayName)
  }

  val shortCode: String
    get() = remote.shortCode

  private var _jLanguage: Language? = null
  val jLanguage: Language?
    get() = _jLanguage ?: GrazieDynamic.loadLang(this)?.also {
      _jLanguage = it
    }

  fun isEnglish() = shortCode == "en"

  fun equalsTo(language: tanvd.grazie.langdetect.model.Language) = this.shortCode == language.iso.toString()

  override fun toString() = displayName
}

