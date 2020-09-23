package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent

interface StartableActivity {

    fun getStartIntent(caller: Context): Intent
}