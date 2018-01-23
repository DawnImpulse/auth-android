package com.dawnimpulse.authlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dawnimpulse.auth.Test
import com.dawnimpulse.library.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id1.text = Test.first(this).toString()
    }
}
