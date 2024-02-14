package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Minimum Number of Operations to Convert Time
 *
 * You are given two strings current and correct representing two 24-hour times.
 *
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59.
 * The earliest 24-hour time is 00:00, and the latest is 23:59.
 *
 * In one operation you can increase the time current by 1, 5, 15, or 60 minutes. You can perform this operation
 * any number of times.
 *
 * Return the minimum number of operations needed to convert current to correct.
 *
 * Example 1:
 *      Input: current = "02:30", correct = "04:35"
 *      Output: 3
 *      Explanation:
 *          We can convert current to correct in 3 operations as follows:
 *          - Add 60 minutes to current. current becomes "03:30".
 *          - Add 60 minutes to current. current becomes "04:30".
 *          - Add 5 minutes to current. current becomes "04:35".
 * It can be proven that it is not possible to convert current to correct in fewer than 3 operations.
 *
 * Example 2:
 *      Input: current = "11:00", correct = "11:01"
 *      Output: 1
 *      Explanation: We only have to add one minute to current, so the minimum number of operations needed is 1.
 *
 * Constraints:
 *      current and correct are in the format "HH:MM"
 *      current <= correct
 *
 */
class MinimumNumberOfOperationsToConvertTime {

    /**
     * Initial thought
     * 1. Init data
     * 1.1 Convert both times into minutes
     * 1.2 Get the difference between then
     * 1.3 Set result as 0
     *
     * 2. Loops
     * loop 1
     *  while difference >= 60
     *      difference -= 60
     *      result ++
     * loop 2
     *  while difference >= 15
     *      difference -= 15
     *      result ++
     *  loop 3
     *  while difference >= 5
     *      difference -= 5
     *      result ++
     *  loop 4
     *  while difference >= 1
     *      difference -= 1
     *      result ++
     *
     *  3. Return result
     *
     *  Optimization:
     *  On step 2, instead of loop, we can use / and rem
     *
     *  result += differenceTime / 60
     *  differenceTime = differenceTime rem 60
     *
     *
     */
    private fun convertTime(current: String, correct: String): Int {
        // 1. Init
        val currentTime = convertTimeIntoMinutes(current)
        val correctTime = convertTimeIntoMinutes(correct)
        var differenceTime = correctTime - currentTime
        var result = 0

        // 2. Loop
        result += differenceTime / 60
        differenceTime %= 60

        result += differenceTime / 15
        differenceTime %= 15

        result += differenceTime / 5
        differenceTime %= 5

        result += differenceTime / 1
        differenceTime %= 1

        // 3. Return result
        return result
    }

    private fun convertTimeIntoMinutes(time: String): Int {
        var result = 0
        // Get the minute
        result += time[4].digitToInt()
        result += time[3].digitToInt() * 10

        // Get the hours
        result += time[1].digitToInt() * 60
        result += time[0].digitToInt() * 10 * 60
        return result
    }

    @Test
    fun test1() {
        // Given
        val current = "02:30"
        val correct = "04:35"

        // When
        val result = convertTime(current, correct)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        // Given
        val current = "11:00"
        val correct = "11:01"

        // When
        val result = convertTime(current, correct)

        // Then
        assertEquals(1, result)
    }
}