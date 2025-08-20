package com.cashflow.utils

inline fun <reified T: Enum<T>> String.tryGetEnumValue() =
    try {
        enumValueOf(this)
    } catch (t: Throwable) {
        enumValues<T>()[0]
    }

inline fun <reified T: Enum<T>> String.getEnumValueOr(default: T) =
    try {
        enumValueOf(this)
    } catch (t: Throwable) {
        default
    }

inline fun <reified T: Enum<T>> String.getEnumValue(): T? =
    try {
        enumValueOf(this) as T
    } catch (t: Throwable) {
        null
    }
