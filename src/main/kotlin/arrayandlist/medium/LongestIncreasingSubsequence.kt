package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Longest increasing subsequence
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements. For example, [3, 6, 2, 7] is a subsequence
 * of the array [0, 3, 1, 6, 2, 2, 7]
 *
 * Example 1:
 *      Input: nums = [10,9,2,5,3,7,101,18]
 *      Output: 4
 *      Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 *      Input: nums = [0,1,0,3,2,3]
 *      Output: 4
 *
 * Example 3:
 *      Input: nums = [7,7,7,7,7,7,7]
 *      Output: 1
 *
 *Constraints:
 *      1 <= nums.length <= 2500
 *      -10^4 <= nums[i] <= 10^4
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
abstract class LongestIncreasingSubsequence {

    abstract fun lengthOfLIS(nums: IntArray): Int

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)

        // When
        val result = lengthOfLIS(input)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(0, 1, 0, 3, 2, 3)

        // When
        val result = lengthOfLIS(input)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test3() {
        // Given
        val input = intArrayOf(7,7,7,7,7,7,7)

        // When
        val result = lengthOfLIS(input)

        // Then
        assertEquals(1, result)
    }
}

class LongestIncreasingSubsequenceDynamic: LongestIncreasingSubsequence() {

    /**
     * We can do this on dynamic programming
     * Objective: Find the maximum length of increasing subsequence till end
     *
     * [0, 3, 1, 6, 2, 2, 7]
     * Per each element in the list, we can either choose it or not choose it
     *
     * - Subproblem
     * The subproblem is a smaller array of size m, where m < n
     * [0, 3, 1, 6, 2, 2, 7]
     * subproblems
     *      [0]
     *      - Objective: Find the maximum length of increasing subsequence till 0
     *      - Possible combinations: [0]
     *      - check: Is the sequence increasing
     *          -> Yes
     *      - Size = 0
     *      Max length = 0
     *
     *      [0, 3]
     *      - Objective: Find the maximum length of increasing subsequence till 0
     *      - Possible combinations:
     *          - [0]
     *              - Check: Is the sequence increasing -> Yes
     *              - Length = 0
     *              - Max length = 0
     *          - [0, 3]
     *              - check: Is the sequence increasing -> Yes
     *              - length: 2
     *              - Max length = 2

     *      [0, 3, 1]
     *      - Objective: Find the maximum length of increasing subsequence till 1
     *      - Possible combinations:
     *          - [0] -> Checked
     *          - [0, 3] -> Checked
     *          - [0, _, 1]
     *              - Check: Is the sequence increasing -> Yes
     *              - length: 2
     *              - Max length = 2
     *          - [   3, 1]
     *              - Check: Is the sequence increasing -> No
     *          - [0, 3, 1]
     *              - Check: Is the sequence increasing -> No
     */
    override fun lengthOfLIS(nums: IntArray): Int {
        return 42
    }

}
class LongestIncreasingSubsequenceImpl: LongestIncreasingSubsequence() {

    // Length of the longest strictly increasing subsequence
    // Find an n and m, such that all the elements from n to m which is bigger than
    // n is k. Find the biggest k
    // An easy solution is brute force, but it will have complexity of n^2
    // HashMap?
    // Stack?
    // Queue?
    // Min heap
    // [10, 9, 2, 5, 3, 7, 101, 18]
    // for 10: 101 or 18 -> 2
    // for 9: 101, or 18 -> 2
    // for 2: 5, 7, 101  -> 4
    //        5, 7, 18   -> 4
    //        3, 7, 101  -> 4
    //        3, 7, 18.  -> 4
    // for 5: 7, 101     -> 3
    //        7, 18.     -> 3
    //        101, 18    -> 3
    // for 3: 7 101      -> 2
    //        7, 18      -> 2
    // for 101           -> 1
    // for 18            -> 1
    // Repetition: We are comparing the current number with the every element in the array
    // then compare that element with the next element
    // The key is find the sequence. The sequence between the numbers does not change
    // We can manipulate the array but the order needs to be perserved
    // There could be different choices. We can compare the current number but we don't know
    // the result of next number
    // i.e. choose between 101 and 18        -> Dynamic programming
    // this is similar to the jumping problem
    // Find the longest jump
    // Map<Num, List of positions that could reach to it>
    // The current decision depends on the previous decision
    override fun lengthOfLIS(nums: IntArray): Int {
        val jumps = IntArray(nums.size)
        jumps[0] = 1

        // Build the list of jumps
        for (i in 1 until nums.size) {
            var max = 0
            for (j in 0 until i) {
                val currentNumber = nums[i]
                if (nums[j] < currentNumber && jumps[j] > max) {
                    max = jumps[j]
                }
            }
            jumps[i] = 1 + max
        }

        var maxLength = 0
        for (i in jumps.indices) {
            maxLength = Math.max(maxLength, jumps[i])
        }

        return maxLength
    }
}