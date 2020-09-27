package com.ordolabs.collect.ui.activity

import com.ordolabs.collect.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override fun setViews() {
        setFAB()
    }

    private fun setFAB() {
        home_fab.setOnClickListener {
            val intent = CreateItemActivity.getStartIntent(this)
            startActivity(intent)
        }
    }

}