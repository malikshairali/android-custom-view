package com.example.customview.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
    }
}