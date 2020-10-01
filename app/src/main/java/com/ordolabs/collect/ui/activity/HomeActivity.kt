package com.ordolabs.collect.ui.activity

import com.ordolabs.collect.R
import com.ordolabs.collect.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override fun setViews() {
        setCreateFAB()
    }

    private fun setCreateFAB() {
        home_create_fab.setOnClickListener {
            val intent = CreateItemActivity.getStartIntent(this)
            startActivity(intent)
        }
    }

}