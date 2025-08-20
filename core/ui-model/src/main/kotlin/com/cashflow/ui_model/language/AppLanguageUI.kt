package com.cashflow.ui_model.language

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.ui_model.R

enum class AppLanguageUI(
    @StringRes val languageName: Int,
    @DrawableRes val icon: Int
) {
    EN(languageName = R.string.english, icon = R.drawable.ic_flag_en),
    RU(languageName = R.string.russian, icon = R.drawable.ic_flag_ru)
}

fun AppLanguage.toUI(): AppLanguageUI = when (this) {
    AppLanguage.EN -> AppLanguageUI.EN
    AppLanguage.RU -> AppLanguageUI.RU
}

fun AppLanguageUI.toModel() = when (this) {
    AppLanguageUI.EN -> AppLanguage.EN
    AppLanguageUI.RU -> AppLanguage.RU
}
