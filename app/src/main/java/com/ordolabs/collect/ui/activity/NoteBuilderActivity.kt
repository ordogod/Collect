package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import com.ordolabs.collect.R

class NoteBuilderActivity : BaseActivity(R.layout.activity_note_builder) {

    override fun setViews() {

    }

    companion object : StartableActivity {
        override fun getStartIntent(caller: Context): Intent {
            return Intent(caller, NoteBuilderActivity::class.java)
        }
    }
}