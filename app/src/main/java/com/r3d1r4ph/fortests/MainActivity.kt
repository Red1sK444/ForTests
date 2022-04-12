package com.r3d1r4ph.fortests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.fortests.databinding.ActivityMainBinding
import com.r3d1r4ph.fortests.model.TheSupermarketQueue

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind, R.id.rootLayout)
    private val theSupermarketQueue = TheSupermarketQueue()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewBinding) {
            answerTextView.text = getString(
                R.string.answer, 0
            )

            sendButton.setOnClickListener {
                answerTextView.text = getString(
                    R.string.answer, theSupermarketQueue.calculateTotalTimeOfQueues(
                        customers = customersTimeTextInputEditText.text.toString().split(" ")
                            .map { it.toInt() }.toIntArray(),
                        tillsCount = tillsCountTextInputEditText.text.toString().toInt()
                    )
                )
            }

        }
    }
}