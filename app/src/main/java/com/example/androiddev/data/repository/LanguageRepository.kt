
package com.example.androiddev.data.repository

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList

class LanguageRepository(
    private val context: Context
) {
    private val localeManager =
        context.getSystemService(LocaleManager::class.java)

    fun getLanguage(): String {
        val appLocales = localeManager.applicationLocales

        val language = if (!appLocales.isEmpty) {
            appLocales[0]?.language
        } else {
            context.resources.configuration.locales[0]?.language
        }

        return if (language == "en") "en" else "ru"
    }

    fun saveLanguage(language: String) {
        require(language == "ru" || language == "en")

        localeManager.applicationLocales =
            LocaleList.forLanguageTags(language)
    }
}
