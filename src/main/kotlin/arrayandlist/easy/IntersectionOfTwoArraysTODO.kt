package arrayandlist.easy

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in
 * the result must be unique and you may return the result in any order.
 *
 * Example 1:
 *      Input: nums1 = [1,2,2,1], nums2 = [2,2]
 *      Output: [2]
 *
 * Example 2:
 *      Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *      Output: [9,4]
 *      Explanation: [4,9] is also accepted.
 *
 * Constraints:
 *      1 <= nums1.length, nums2.length <= 1000
 *      0 <= nums1[i], nums2[i] <= 1000
 *
 */
class IntersectionOfTwoArraysTODO {

    /**
     * Initial thoughts
     *
     * Since the number is from 0 to 1000, we can create an bool array of size 1000
     *
     * 1. Go through the nums1 and per each element, set position (i - 1) as true
     * 2. Go through the nums2. Per each element, check if it was true in the int arra
     * - If so, add it to the list of results
     * 3. Return list of results
     *
     */
    private fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        return intArrayOf()
    }
}