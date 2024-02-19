package arrayandlist.medium

/**
 * Rearrange array elements by sign
 *
 * You are given a 0-indexed integer array nums of even length consisting of an equal number of
 * positive and negative integers.
 *
 * You should return the array of nums such that the the array follows the given conditions:
 *      Every consecutive pair of integers have opposite signs.
 *      For all integers with the same sign, the order in which they were present in nums is preserved.
 *      The rearranged array begins with a positive integer.
 *      Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
 *
 * Example 1:
 *      Input: nums = [3,1,-2,-5,2,-4]
 *      Output: [3,-2,1,-5,2,-4]
 *      Explanation:
 *          The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
 *          The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
 *          Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.
 *
 * Example 2:
 *      Input: nums = [-1,1]
 *      Output: [1,-1]
 *      Explanation:
 *      1 is the only positive integer and -1 the only negative integer in nums.
 *      So nums is rearranged to [1,-1].
 * Constraints:
 *      2 <= nums.length <= 2 * 10^5
 *      nums.length is even
 *      1 <= |nums[i]| <= 10^5
 *      nums consists of equal number of positive and negative integers.
 * It is not required to do the modifications in-place.
 */
class RearrangeArrayElementsBySignTODO {

    /**
     * Initial thoughts
     *
     * Easy solution:
     * Have 2 stacks:
     * - One for positive number
     * - One for negative number
     *
     * Add all the elements into the stack
     * Extract them alternatively
     * Complexity:
     *  - Time: O(n)
     *  - Space: O(n)
     *
     * A O(1) space solution:
     * Have two pointer
     * - positive pointer
     * - negative pointer
     *
     * 1. Init the variables
     * - positive pointer = 0
     * - nagative pointer = 0
     * - result = empty list
     *
     * 2. Loop
     * while (positive integer < nums.length || negative integer < nums.length)
     *  while (nums[positive pointer] < 0 && positive integer < nums.length)
     *      positive pointer++
     *
     *  result add (nums[positive integer])
     *
     *  while (nums[negative pointer] > 0 && negative integer < nums.length)
     *      negative pointer ++
     *
     *  result add (nums[negative integer])
     *
     *  3. Return result
     *  return result
     *
     * Better solution
     * Have two pointers that points to the position on the result where to store the number
     * - Positive res pointer = 0
     * - Negative res pointer = 1
     *
     * For all the elements in the array
     * - If it is positive
     *   -> insert it into the result
     *   -> positive res pointer += 2
     *
     * - if it is negative
     *  -> insert it into the result
     *  -> negative res pointer += 2
     *
     * return result
     */
    private fun rearrangeArray(nums: IntArray): IntArray {
        return intArrayOf()
    }
}