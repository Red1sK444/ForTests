package com.r3d1r4ph.fortests.model

import com.r3d1r4ph.fortests.exception.NotPositiveIntegerException
import junit.framework.TestCase
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TheSupermarketQueueTest : TestCase() {
    private lateinit var theSupermarketQueue: TheSupermarketQueue

    @BeforeEach
    override fun setUp() {
        theSupermarketQueue = TheSupermarketQueue()
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideCustomersAndTillsCount")
    fun shouldReturnCorrectIntegerAnswer(
        message: String?,
        customers: IntArray,
        tillsCount: Int,
        expected: Int
    ) {
        assertDoesNotThrow {
            Assert.assertEquals(
                expected,
                theSupermarketQueue.calculateTotalTimeOfQueues(customers, tillsCount)
            )
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideCustomersAndTillsCountForException")
    fun shouldReturnException(message: String?, customers: IntArray, tillsCount: Int) {
        Assert.assertThrows(
            NotPositiveIntegerException::class.java
        ) { theSupermarketQueue.calculateTotalTimeOfQueues(customers, tillsCount) }
    }

    private companion object {
        @JvmStatic
        fun provideCustomersAndTillsCount(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("Массив большого размера", intArrayOf(12, 1, 4, 24, 4, 4, 4, 2, 12, 4, 56, 34), 12, 56),
                Arguments.of("Большое количество касс", intArrayOf(12, 1, 4, 4, 56, 34), 5172645, 56),
                Arguments.of("Число касс равно 1 и один элемент в массиве", intArrayOf(12), 1, 12),
                Arguments.of("Число касс равно 1", intArrayOf(5, 5, 4), 1, 14),
                Arguments.of("Пустой массив", intArrayOf(), 1, 0),
                Arguments.of("Массив размера 1", intArrayOf(5), 1, 5),
                Arguments.of("Массив размера 1, но количество касс > 1", intArrayOf(5), 14, 5)
            )
        }

        @JvmStatic
        fun provideCustomersAndTillsCountForException(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("Отрицательное число касс", intArrayOf(1, 1), -1),
                Arguments.of("Число касс равно 0", intArrayOf(1, 1), 0),
                Arguments.of("Отрицательное число касс и пустой массив", intArrayOf(), -1),
                Arguments.of("Наличие в массиве не позитивных чисел", intArrayOf(1, 3, 0, -1, -5, 3), 12)
            )
        }
    }
}