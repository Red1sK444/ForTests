package com.r3d1r4ph.fortests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.fortests.databinding.ActivityMainBinding
import com.r3d1r4ph.fortests.exception.NotPositiveIntegerException
import com.r3d1r4ph.fortests.model.TheSupermarketQueue

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind, R.id.rootLayout)
    private val theSupermarketQueue = TheSupermarketQueue()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewBinding) {
            answerTextView.text = getString(
                R.string.answer, ""
            )

            sendButton.setOnClickListener {
                if (customersTimeTextInputEditText.text.toString().isBlank()
                    || tillsCountTextInputEditText.text.toString().isBlank()
                ) {

                    answerTextView.text = getString(R.string.answer, "Empty fields")
                    return@setOnClickListener
                }

                val customersIntArray = customersTimeTextInputEditText.text.toString().split(" ")
                    .map {
                        val mapped = it.toIntOrNull()
                        if (mapped == null) {
                            answerTextView.text = getString(R.string.answer, "Incorrect input")
                            return@setOnClickListener
                        }
                        mapped
                    }.toIntArray()


                answerTextView.text = try {
                    getString(
                        R.string.answer, theSupermarketQueue.calculateTotalTimeOfQueues(
                            customers = customersIntArray,
                            tillsCount = tillsCountTextInputEditText.text.toString().toInt()
                        ).toString()
                    )
                } catch (e: NotPositiveIntegerException) {
                    getString(R.string.answer, "Incorrect input")
                }
            }

        }
    }
}