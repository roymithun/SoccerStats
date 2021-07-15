package com.inhouse.soccerstats.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inhouse.soccerstats.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}