package arrayandlist.hard

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *      Input: nums1 = [1,3], nums2 = [2]
 *      Output: 2.00000
 *      Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *      Input: nums1 = [1,2], nums2 = [3,4]
 *      Output: 2.50000
 *      Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Constraints:
 *      nums1.length == m
 *      nums2.length == n
 *      0 <= m <= 1000
 *      0 <= n <= 1000
 *      1 <= m + n <= 2000
 *      -10^6 <= nums1[i], nums2[i] <= 10^6
 */
class MedianOfTwoSortedArrays {

    /**
     * Initial thoughts
     *
     * Based on the requirement of O(log(n + m)), it tells us that it is something similar to the binary search
     *
     * Due to that nums1 and nums2 are sorted, the key is search for the right element on every iteration
     * - Every step we need to get the half of n and half of m
     * Example:
     * - num1 = [1, 2, 3, 4], m = 4
     * - num2 = [1, 4, 5, 6, 7], n = 5
     *
     * Computing the half of each list
     *
     * 1. compute the half...
     * 1. compute the half...
     *
     * https://www.youtube.com/watch?v=q6IEA26hvXc
     *
     */
    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        return 0.0
    }
}