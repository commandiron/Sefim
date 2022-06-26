package com.commandiron.core.data.preferences

import android.content.SharedPreferences
import com.commandiron.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
}