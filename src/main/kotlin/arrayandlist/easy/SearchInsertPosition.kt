package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums contains distinct values sorted in ascending order.
 * -10^4 <= target <= 10^4
 *
 */
abstract class SearchInsertPosition {

    abstract fun searchInsert(nums: IntArray, target: Int): Int

    @ParameterizedTest(name = "The position to insert of {0} for {1} should be {2}")
    @ArgumentsSource(TestDataArgumentProvider::class)
    fun test(nums: IntArray, target:Int, expectedValue: Int) {
        val result = searchInsert(nums, target)
        assertEquals(result, expectedValue)
    }

    class TestDataArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 3, 5, 6), 5, 2),
                Arguments.of(intArrayOf(1, 3, 5, 6), 2, 1),
                Arguments.of(intArrayOf(1, 3, 5, 6), 7, 4),
                Arguments.of(intArrayOf(2, 3, 5, 6), 1, 0),
                Arguments.of(intArrayOf(-50, -40, -30, -20), -60, 0),
            )
        }
    }
}

class SearchInsertPositionImpl: SearchInsertPosition() {
    override fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val middle = left + (right - left)/2
            if (nums[middle] == target) return middle
            // Check if the number could be inserted
            val leftNumber = if (middle > 0) {
                nums[middle - 1]
            } else Int.MIN_VALUE
            val rightNumber = nums[middle]
            if (target in (leftNumber + 1) until rightNumber) {
                return middle
            }
            if (nums[middle] < target) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }

        // If the number cannot be inserted in the array, so it must be inserted at the end of the array
        return nums.size
    }
}