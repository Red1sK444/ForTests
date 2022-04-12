package com.r3d1r4ph.fortests

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun inputDataAndGiveCorrectAnswer() {
        Espresso.onView(ViewMatchers.withId(R.id.tillsCountTextInputEditText))
            .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.customersTimeTextInputEditText)).perform(
            ViewActions.typeText("4"),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.answerTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText("Answer: 4")))
    }

    @Test
    fun letOneEmptyFieldGetEmptyOutput() {
        Espresso.onView(ViewMatchers.withId(R.id.tillsCountTextInputEditText))
            .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.answerTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText("Answer: Empty fields")))
    }

    @Test
    fun inputIncorrectCustomersTimeAndGetIncorrectInputOutput() {
        Espresso.onView(ViewMatchers.withId(R.id.tillsCountTextInputEditText))
            .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.customersTimeTextInputEditText)).perform(
            ViewActions.typeText("4 ba 5.6"),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.answerTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText("Answer: Incorrect input")))
    }
}