package com.mistigo.app

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val welcome = TextView(this).apply {
            text = "Misti-go\n\nFrontend Android iniciado con Kotlin."
            textSize = 22f
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        setContentView(
            welcome,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            ),
        )
    }
}

