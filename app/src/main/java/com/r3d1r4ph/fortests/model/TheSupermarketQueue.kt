package com.r3d1r4ph.fortests.model

import com.r3d1r4ph.fortests.exception.NotPositiveIntegerException

class TheSupermarketQueue {

    fun calculateTotalTimeOfQueues(customers: IntArray, tillsCount: Int): Int {

        if (tillsCount <= 0) {
            throw NotPositiveIntegerException()
        }
        if (customers.isEmpty()) {
            return 0
        }

        for (customer in customers) {
            if (customer <= 0) {
                throw NotPositiveIntegerException()
            }
        }

        val queue = customers.toMutableList()
        var inTills = mutableListOf<Int>()

        var i = 0
        while (i < tillsCount && queue.isNotEmpty()) {
            inTills.add(queue.removeFirst())
            i++
        }

        var totalDuration = 0
        var minDuration: Int
        do {
            minDuration = inTills.minOrNull() ?: 0
            if (minDuration != 0) {
                totalDuration += minDuration
            } else {
                totalDuration += inTills.maxOrNull() ?: 0
                break
            }

            inTills = inTills.map {
                var new = it - minDuration
                if (new == 0 && queue.isNotEmpty()) {
                    new = queue.removeFirst()
                }
                new
            }.toMutableList()
        } while (minDuration > 0)

        return totalDuration
    }
}