package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max

/**
 * House robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount
 * of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
 * have security systems connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount
 * of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *      Input: nums = [1,2,3,1]
 *      Output: 4
 *      Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *      Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 *      Input: nums = [2,7,9,3,1]
 *      Output: 12
 *      Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *      Total amount you can rob = 2 + 9 + 1 = 12.
 * Constraints:
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 400
 */
abstract class HouseRobber {

    abstract fun robber(houses: IntArray): Int

    @Test
    fun test1() {
        // Given
        val houses = intArrayOf(1, 2, 3, 1)

        // When
        val result = robber(houses)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test2() {
        // Given
        val houses = intArrayOf(2, 7, 9, 3, 1)

        // When
        val result = robber(houses)

        // Then
        assertEquals(12, result)
    }

    @Test
    fun test3() {
        // Given
        val houses = intArrayOf(2, 10, 3, 6, 8, 1, 7)

        // When
        val result = robber(houses)

        // Then
        assertEquals(25, result)
    }

    @Test
    fun test4() {
        // Given
        val houses = intArrayOf(4, 8, 12, 1, 2, 10, 3, 6, 8)

        // When
        val result = robber(houses)

        // Then
        assertEquals(34, result)
    }
}

class HouseRobberRecursive: HouseRobber() {

    /**
     * Initial thoughts
     * houses = [2, 10, 3, 6, 8, 1, 7]
     * Basic logic
     * https://excalidraw.com/#room=1538587e1b97791c7bbb,yfQDoWcJmHyMrIFTIz-VXQ
     *
     * Based on this graph
     * - this problem could be divided into small subproblems
     * - the subproblems repeats
     *
     * Conditions:
     * - The robber has the option to skip a house
     * - The robber cannot steal two adjacent houses
     *
     * Simulation:
     * - The robber can start with any of the houses
     * - The skin from 1 to n houses, and go to next house
     *
     * General case:
     *  robber(i) = the maximum of money he can rob until he reaches to the last house
     *  If robber is in the house i it is because he can rob the house i.
     *
     *  the robber can decide:
     *  - Rob the current house and CHECK the next next house
     *  - Not rob the current house and CHECK the next house
     *     robber(i) = max(house[i] + robber(i+2), robber(i+1))
     *
     * Direct Acyclic Graph
     * https://excalidraw.com/#room=d6cfdff764c894977224,GB3F6Y2IHBvvEXUrvZPB_Q
     *
     * Base condition:
     * - If the robber reached to the last house then return the last house
     * Because the robber is looking for the maximum profit and there is not more
     * house than the last one
     *      robber(n - 1) = house[n - 1]
     *
     * - If the robber reached to the one before the last house he need to choose
     * between either rob the current house or rob the last house
     *      robber(n - 2) = max(house[n - 1], house[n - 2])
     * Either we do this, either we allow the robber go beyond the array of houses
     *      robber(i) = if (i > n -1) return 0
     *
     * Special condition:
     * The robber can start from any house in the array
     *
     * Complexity:
     * - Time: Worse case. the robber need to go through every 2 houses, choose 2 times, so it
     * is O(2^(n/2)), or O(2^n)
     * - Space: The calling stack could have n/2 levels, so it is O(n/2) or O(n)
     *
     */
    override fun robber(houses: IntArray): Int {
        // Corner case
        if (houses.isEmpty()) {
            return 0
        }

        // The follow case is not needed because all the houses will
        // be eventually checked
//        var maxAmount = houses[0]
//        for (i in 1 until houses.size) {
//            maxAmount = max(maxAmount, robber(houses, i))
//        }

//        return maxAmount
        return robber(houses, 0)
    }

    private fun robber(houses: IntArray, i: Int): Int {
        // Special border check
        val n = houses.size

        // Base case
        if (i > n - 1) {
            return 0
        }

        // Base case 2 -> This is not necessary because in case the robber ends up
        // in the last house, he need to choose between rob the current house or
        // rob the house next by. Since there is not house next by, the next house
        // returns 0. The robber will end up choosing the last house
//        if (i == n - 1) {
//            return houses[i]
//        }

        return max(houses[i] + robber(houses, i+2), robber(houses, i+1))
    }
}

class HouseRobberMemoization: HouseRobber() {

    /**
     * Memoization using a lookup table
     * - Key: i
     * - Value: robber(i)
     *
     * Complexity:
     * - Time: Number of problems * complexity of each problem
     *      - Number of problems: We go from position 0 to the position n + 1 so n + 2
     *      - Complexity of each problem: O(1)
     *   = (n + 2) * O(1) = O(n + 2) = O(n)
     * - Space: Calling stack size + Lookup table size = O(n) + O(n) = O(n)
     *
     */
    override fun robber(houses: IntArray): Int {
        // Corner case
        if (houses.isEmpty()) {
            return 0
        }

        return robber(houses, 0, HashMap<Int, Int>())
    }

    private fun robber(houses: IntArray, i: Int, lookUpTable: HashMap<Int, Int>): Int {
        // Special border check
        val n = houses.size

        // Base case
        if (i > n - 1) {
            return 0
        }

        // First check
        if (lookUpTable.containsKey(i)) {
            return lookUpTable[i]!!
        }

        lookUpTable[i] = max(houses[i] + robber(houses, i+2, lookUpTable),
                            robber(houses, i+1, lookUpTable))

        return lookUpTable[i]!!
    }
}

class HouseRobberTabulation: HouseRobber() {

    /**
     * Tabulation: Using an array of n+1 to record each value
     *
     * Definition:
     * - robber(i) = maximum money the robber can rob from the house 0 to the house i
     *
     * If there is only one house, the robber will rob that house
     * - robber(0) = houses[0]
     *
     * If there is two houses, the robber can either rob the first house, either rob the second house
     * - robber(1) = max(houses[0], houses[1])
     *
     * If there is three houses, the robber can
     *  - Rob the first house, then rob the third house
     *  - Rob the second house
     *  - robber(2) = max(robber(0) + robber(2), robber(1))
     *
     * If there is four houses, the maximum amount is
     *  - come from the previous house and skip the current house
     *  - skip the previous house and come to the current house
     *
     * The cache calculation is inverse to the DAG
     * https://excalidraw.com/#room=9233fb8baf07841b1ca9,hT2quYf_9dwSCk-Cgl7tow
     *
     * General statement:
     * robber(i) = max(robber(i - 2) + house[i], robber(i - 1))
     *
     * Complexity:
     * - Time: Traveling n elements while doing O(1) operations = O(n)
     * - Space: O(n) cache
     */
    override fun robber(houses: IntArray): Int {
        // Corner case
        if (houses.isEmpty()) return 0
        if (houses.size == 1) return houses[0]

        // Init the cache
        val n = houses.size
        val cache = IntArray(n + 1)

        // Basic cases
        cache[0] = houses[0]
        cache[1] = max(cache[0], houses[1])

        // Repeat
        for (i in 2 until n) {
            cache[i] = max(cache[i - 2] + houses[i], cache[i - 1])
        }

        return cache[n - 1]
    }
}

class HouseRobberTabulationOptimSpace: HouseRobber() {

    /**
     * Space optimization:
     *
     * Previously we have an array of integers, but based on the DAG, we just need
     * 2 previous values, so we can use 2 variables for that
     * - previous
     * - beforePrevious
     *
     * and 1 variable current to store the current value
     *
     * return the previous value
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     */
    override fun robber(houses: IntArray): Int {
        // Corner case
        if (houses.isEmpty()) return 0
        if (houses.size == 1) return houses[0]

        // Init the variables
        val n = houses.size
        var beforePrevious = houses[0]
        var previous = max(beforePrevious, houses[1])
        var current = previous

        // Repeat
        for (i in 2 until n) {
            current = max(beforePrevious + houses[i], previous)

            // Getting ready for next iteration
            beforePrevious = previous
            previous = current
        }

        return previous
    }
}