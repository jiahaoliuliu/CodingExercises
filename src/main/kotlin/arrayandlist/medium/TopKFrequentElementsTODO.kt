package arrayandlist.medium

/**
 * Top K frequent elements
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the
 * answer in any order.
 *
 * Example 1:
 *      Input: nums = [1,1,1,2,2,3], k = 2
 *      Output: [1,2]
 *
 * Example 2:
 *      Input: nums = [1], k = 1
 *      Output: [1]
 * Constraints:
 *      1 <= nums.length <= 10^5
 *      -10^4 <= nums[i] <= 10^4
 *      k is in the range [1, the number of unique elements in the array].
 *      It is guaranteed that the answer is unique.
 *      Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
class TopKFrequentElementsTODO {

    /**
     * Initial thought
     * We can create an heap of max element and return the first k elements
     *
     * A better solution:
     * 1. Build a frequency hashmap
     * - key: element
     * - value: Frequency
     *
     * 2. Create an array of n elements, where n = length of nums
     * - index: The frequency
     * - value: List of elements with that frequency
     *
     * 3. Going from the back of the list
     * - Extract the first k elements
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        return intArrayOf()
    }
}