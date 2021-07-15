package com.inhouse.soccerstats.utils

import java.util.*

fun getInitials(name: String?): String {
    val stringBuilder = StringBuilder()
    name?.let {
        if (it.isNotEmpty()) {
            val words = it.split(" ")
            if (words.size == 1 && words.first().length > 2) {
                stringBuilder.append(words.first().substring(0, 3).uppercase(Locale.getDefault()))
            } else if (words.first().isNotEmpty()) {
                stringBuilder.append(words.first().first().uppercaseChar())
            }
            if (words.size > 1) {
                val lastWord = words.last().replace(".", "")
                if (lastWord.length > 1) {
                    stringBuilder.append(lastWord.substring(0, 2).uppercase(Locale.getDefault()))
                }
            }
        }
    }
    return stringBuilder.toString()
}