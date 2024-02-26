package arrayandlist.easy

import arrayandlist.swap
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Meeting rooms
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could
 * attend all meetings.
 * Example 1:
 *      Input: intervals = [[0,30],[5,10],[15,20]]
 *      Output: false
 *
 * Example 2:
 *      Input: intervals = [[7,10],[2,4]]
 *      Output: true
 *
 * Constraints:
 *      0 <= intervals.length <= 10^4
 *      intervals[i].length == 2
 *      0 <= start(i) < end(i) <= 10^6
 */
class MeetingRooms {

    /**
     * Initial thoughts
     * All the items would be easier to check if they are sorted
     * 1. Prepare data
     *  Sort the interval by the start time
     * 2. Loop through the list
     *  if the end of the previous item is bigger than the beginning of the current item
     *   -> return false
     * 3. Return data
     *      return the default result (true)
     */
    private fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        // 1. Sort intervals
        val intervalsSorted = intervals.toMutableList()
        sortIntervals(intervalsSorted)

        // 2. Loop through the intervals (all except the last element)
        for (i in 0 until intervalsSorted.size - 1) {
            if (intervalsSorted[i][1] > intervalsSorted[i + 1][0]) {
                return false
            }
        }

        // 3. Show the default result
        return true
    }

    // Sor the items using quick sort
    private fun sortIntervals(intervals: MutableList<IntArray>) {
        // 1. Init the data
        if (intervals.isEmpty() || intervals.size == 1) {
            return
        }

        // Choose the pivot
        val pivot = intervals.last()
        var l = 0
        var r = intervals.size - 1 - 1

        // 2. Loop through
        while (l < r) {
            // Find the element on the left which is bigger than pivot
            while (l < r && intervals[l][0] < pivot[0]) l++

            // Find the element on the right which is smaller than pivot
            while (l < r && intervals[r][0] > pivot[0]) r--

            // If we found an item to be switched
            if (l < r) {
                intervals.swap(l, r)
            // If the position found is the position where the pivot should be
            }
        }

        // [15, _] [10, _] [16, _]

        // Check if the pivot needs to be sorted
        if (intervals[l][0] > pivot[0]) {
            intervals.swap(l, intervals.size - 1)
        }

        // Sublist [0, l)
        sortIntervals(intervals.subList(0, l + 1))
        sortIntervals(intervals.subList(l + 1, intervals.size))

        // Show the result
        // Not need. It is a mutable list
    }

    @Test
    fun test1() {
        // Given
        val input = arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))

        // When
        val result = canAttendMeetings(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test2() {
        // Given
        val input = arrayOf(intArrayOf(7, 10), intArrayOf(2, 4))

        // When
        val result = canAttendMeetings(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test3() {
        // Given
        val input = arrayOf(intArrayOf(5, 8), intArrayOf(6, 8))

        // When
        val result = canAttendMeetings(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test4() {
        // Given
        val input = arrayOf(intArrayOf(15, 16), intArrayOf(10, 15), intArrayOf(16, 25))

        // When
        val result = canAttendMeetings(input)

        // Then
        assertTrue(result)
    }
}