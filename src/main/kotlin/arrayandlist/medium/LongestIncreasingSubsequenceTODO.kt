package arrayandlist.medium

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
class LongestIncreasingSubsequenceTODO {

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
     *
     *
     *
     *
     */
    fun lengthOfLIS(nums: IntArray): Int {
        return -1
    }
}