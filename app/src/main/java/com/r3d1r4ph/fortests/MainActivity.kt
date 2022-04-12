package com.r3d1r4ph.fortests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.r3d1r4ph.fortests.exception.NotPositiveIntegerException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}