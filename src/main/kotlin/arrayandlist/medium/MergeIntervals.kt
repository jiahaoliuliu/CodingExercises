package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.Integer.max
import java.util.*

/**
 * Given an array of intervals where intervals[i] = [start^i, end^i], merge all overlapping intervals, and
 * return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 *      Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      Output: [[1,6],[8,10],[15,18]]
 *      Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 *      Input: intervals = [[1,4],[4,5]]
 *      Output: [[1,5]]
 *      Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 *      1 <= intervals.length <= 10^4
 *      intervals[i].length == 2
 *      0 <= start^i <= end^i <= 10^4
 */
class MergeIntervals {

    /**
     * Initial thoughts
     *
     * This problem is very similar to the problem of finding the maximum number of people who lives in the
     * certain year
     *
     * The items in the array are not related
     * They could be sorted. If we sort them by start point, do we gain anything?
     *
     * Or we can have them recorded in a data structure, loop through them
     * and start counting them
     *
     * Brute force:
     * Create an array of booleans
     * - Per each item
     *      -> Mark the items from start to end as true
     *
     * - Going through the list
     *   -> Check for intervals
     *
     * Time complexity: O(nm) where n is the size of the array and m is the size of the longest element
     * Space complexity: O(t) where t is the maximum number inside of the elements
     *
     * Better solution:
     * Marking the contents in the structure as number
     * The items are not sorted
     * - [[1,3],[2,6],[15,18],[8,10]]
     *
     * 1. We sort the array by the start number
     * - [[1,3],[2,6],[8,10],[15,18]]
     *
     * 2. Algo
     * 2.1 Init the data
     * - Init the list of results
     * - Init start to -1
     * - init end to -1
     *
     * 2.2 Loop
     * Loop through the list. Per each element
     * if the start and end has not started, start it
     * if the start of the current item is small or equal than the end of the existing item
     *  -> update end to max(end, element.end)
     * else // We found a new interval
     *  -> Add start and end to the result
     *  -> update start and end to the current item
     *
     * Last step
     * -> add current start and end to the result
     *
     * return result
     *
     */
    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        // 1. Init variables
        var start = -1
        var end = -1
        val result = mutableListOf<IntArray>()
        val intervalsSorted = intervals.sortedBy { it[0] }

        // 2. Loop
        for (interval in intervalsSorted) {
            if (start == -1 && end == -1) {
                start = interval[0]
                end = interval[1]
            } else if (interval[0] <= end) { // If it is inside the interval
                end = max(end, interval[1])
            } else {
                result.add(intArrayOf(start, end))
                start = interval[0]
                end = interval[1]
            }
        }
        // Add the last element
        if (start != -1 && end != -1) {
            result.add(intArrayOf(start, end))
        }

        return result.toTypedArray()
    }

    @Test
    fun test1() {
        // Given
        val intervals = arrayOf(intArrayOf(1,3), intArrayOf(2,6), intArrayOf(8,10), intArrayOf(15,18))

        // When
        val result = merge(intervals)

        // Then
        assertEquals(3, result.size)
        assertTrue(intArrayOf(1, 6) contentEquals result[0])
        assertTrue(intArrayOf(8, 10) contentEquals result[1])
        assertTrue(intArrayOf(15, 18) contentEquals result[2])
    }

    @Test
    fun test2() {
        // Given
        val intervals = arrayOf(intArrayOf(1,4), intArrayOf(4,5))

        // When
        val result = merge(intervals)

        // Then
        assertEquals(1, result.size)
        assertTrue(intArrayOf(1, 5) contentEquals result[0])
    }
}