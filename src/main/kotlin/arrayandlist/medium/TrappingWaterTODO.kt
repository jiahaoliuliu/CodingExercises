package arrayandlist.medium

/**
 * Trapping water
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it can trap after raining.
 *
 * Example 1:
 *      Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *      Output: 6
 *      Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 *      In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *      Input: height = [4,2,0,3,2,5]
 *      Output: 9
 *
 * Constraints:
 *      n == height.length
 *      1 <= n <= 2 * 104
 *      0 <= height[i] <= 105
 */
class TrappingWaterTODO {

    /**
     * Initial thoughts
     *
     * The number of water that a specific spot can trap depends on
     * .The max high of the walls on the left
     * .The max high of the walls on the right
     * .It's own high
     * Algorithm
     *  Max left = array of the max high on the walls on the left
     *  Max right = same
     * Per each elemenet
     * If it's high is smaller than the min (left wall, right wall). Add the difference
     * https://youtu.be/ZI2z5pq0TqA?si=vTkeC-0h4wKYiCW5
     *
     * A better solution is using two pointers, left and right
     * On the left it will have the max hight of the left so far
     * On the right it will be the max height of right so far
     * We compare both of them, then we will be moving the smallest one
     *  - Comparing the value with the current value
     *  - Updating the result
     *  - Updating the max height
     *
     */
    fun trap(height: IntArray): Int {
        return 42
    }
}