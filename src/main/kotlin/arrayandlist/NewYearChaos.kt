package arrayandlist

import org.junit.Assert.assertEquals
import org.junit.Test

/*
* https://www.hackerrank.com/challenges/new-year-chaos/problem?isFullScreen=true
* It is New Year's Day and people are in line for the Wonderland rollercoaster ride.
* Each person wears a sticker indicating their initial position in the queue from  to.
* Any person can bribe the person directly in front of them to swap positions, but they
* still wear their original sticker. One person can bribe at most two others.

* Determine the minimum number of bribes that took place to get to a given queue order.
* Print the number of bribes, or, if anyone has bribed more than two people, print Too chaotic.
* Example 1
*   - q = [1, 2, 3, 5, 4, 6, 7, 8]
*   - If person  bribes person , the queue will look like this:\
*       q = [1, 2, 3, 5, 4, 6, 7, 8]
*     Only  bribe is required. Print 1.
* Example 2
*   - q = [4, 1, 2, 3]
*   - Person 4 had to bribe 3 people to get to the current position. Print Too chaotic.
*/
class NewYearChaos {
    /**
     * Initial
     */

    companion object {
        private const val TOO_CHAOTIC = "Too chaotic"
    }

    /**
     * Note this solution is not working
     */
    private fun minimumBribes(q: Array<Int>): String {
        // Write your code here
        var movement = 0
        for ((index, value) in q.withIndex()) {
            if (value - index > (2 + 1)) {
                return TOO_CHAOTIC
            }

            if (value > index) {
                movement += value - index - 1
            }
        }

        return movement.toString()
    }

    @Test // 51237864
    fun test1() {
        // Given
        val input = arrayOf(5, 1, 2, 3, 7, 8, 4)

        // When
        val result = minimumBribes(input)

        // Then
        assertEquals(TOO_CHAOTIC, result)
    }

    /**
     * [1, 2, 5, 3, 7, 8, 6, 4]
     * [1, 2, 3, 4, 5, 6, 7, 8]
     * [1, 2, 3, 5, 4, 6, 7, 8]
     * [1, 2, 5, 3, 4, 6, 7, 8]
     * [1, 2, 5, 3, 4, 7, 6, 8]
     * [1, 2, 5, 3, 7, 4, 6, 8]
     * [1, 2, 5, 3, 7, 4, 8, 6]
     * [1, 2, 5, 3, 7, 8, 4, 6]
     * [1, 2, 5, 3, 7, 8, 6, 4] !!
     * Graph
     *
     */
    @Test // 12537864
    fun test2() {
        // Given
        val input = arrayOf(1, 2, 5, 3, 7, 8, 6, 4)

        // When
        val result = minimumBribes(input)

        // Then
        assertEquals("7", result)
    }
}