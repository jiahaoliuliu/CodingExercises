package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Number of valid clock times
 *
 * You are given a string of length 5 called time, representing the current time on a digital
 * clock in the format "hh:mm". The earliest possible time is "00:00" and the latest possible time is "23:59".
 *
 * In the string time, the digits represented by the ? symbol are unknown, and must be replaced with a digit
 * from 0 to 9.
 *
 * Return an integer answer, the number of valid clock times that can be created by replacing every ? with a digit from
 * 0 to 9.
 *
 * Example 1:
 *      Input: time = "?5:00"
 *      Output: 2
 *      Explanation: We can replace the ? with either a 0 or 1, producing "05:00" or "15:00". Note that we cannot
 *      replace it with a 2, since the time "25:00" is invalid. In total, we have two choices.
 *
 * Example 2:
 *      Input: time = "0?:0?"
 *      Output: 100
 *      Explanation: Each ? can be replaced by any digit from 0 to 9, so we have 100 total choices.
 *
 * Example 3:
 *      Input: time = "??:??"
 *      Output: 1440
 *      Explanation: There are 24 possible choices for the hours, and 60 possible choices for the minutes. In total,
 *      we have 24 * 60 = 1440 choices.
 *
 * Constraints:
 *      time is a valid string of length 5 in the format "hh:mm".
 *      "00" <= hh <= "23"
 *      "00" <= mm <= "59"
 * Some of the digits might be replaced with '?' and need to be replaced with digits from 0 to 9.
 */
class NumberOfValidClockTimes {

    /**
     * Initial thought
     * We have fix number of strings, each string represents N possibilities
     *  XY:ZT
     *      XY: 0 - 24 -> 24 possibilities
     *      Z: 0, 1, 2, 3, 4, 5 -> 6 possibilities
     *      T: 0 - 9: 10 possibilities
     *  The problem here relies on XY
     *  X:
     *      -> If Y < 4, X could be 0, 1, 2
     *      -> If Y >=4, X could be 0, 1
     *  Y:
     *      -> If X = 0, Y could be 0 - 9
     *      -> If X = 1, Y could be 0 - 9
     *      -> If X = 2, Y could be 0 - 3
     */
    private fun countTime(time: String): Int {
        // 1. Init data
        var result = 1

        // For ZT
        if (time[4] == '?') {
            result *= 10
        }

        if (time[3] == '?') {
            result *= 6
        }

        // For XY
        //  ??
        if (time[0] == '?' && time[1] == '?') {
            result *= 24
        // ?Y
        } else if (time[0] == '?') {
            result *= if (time[1] == '0' || time[1] == '1' || time[1] == '2' || time[1] == '3') {
                3
            } else {
                2
            }
        // X?
        } else if (time[1] == '?') {
            result *= if (time[0] == '0' || time[0] == '1') {
                10
            } else {
                4
            }
        }
        return result
    }

    @Test
    fun test1() {
        // Given
        val time = "?5:00"

        // When
        val result = countTime(time)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        // Given
        val time = "0?:0?"

        // When
        val result = countTime(time)

        // Then
        assertEquals(100, result)
    }

    @Test
    fun test3() {
        // Given
        val time = "??:??"

        // When
        val result = countTime(time)

        // Then
        assertEquals(1440, result)
    }


}