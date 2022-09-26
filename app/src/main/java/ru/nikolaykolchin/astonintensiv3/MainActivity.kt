package ru.nikolaykolchin.astonintensiv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class MainActivity : AppCompatActivity(), OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val context = this@MainActivity
        if (view != null) {
            val intent = when (view.id) {
                R.id.button1 -> Intent(context, FirstActivity::class.java)
                R.id.button2 -> Intent(context, SecondActivity::class.java)
                else -> {
                    throw Exception("Invalid value!")
                }
            }
            startActivity(intent)
        }
    }
}