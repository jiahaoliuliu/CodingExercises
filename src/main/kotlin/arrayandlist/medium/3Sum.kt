package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.HashSet

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * Example 1:
 *      Input: nums = [-1,0,1,2,-1,-4]
 *      Output: [[-1,-1,2],[-1,0,1]]
 *      Explanation:
 *          nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 *          nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 *          nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 *      The distinct triplets are [-1,0,1] and [-1,-1,2].
 *      Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 *      Input: nums = [0,1,1]
 *      Output: []
 *      Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *      Input: nums = [0,0,0]
 *      Output: [[0,0,0]]
 *      Explanation: The only possible triplet sums up to 0.
 *
 * Constraints:
 *      3 <= nums.length <= 3000
 *      -10^5 <= nums[i] <= 10^5
 */
abstract class `3Sum` {
    abstract fun threeSum(nums: IntArray): List<List<Int>>

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(-1, 0, 1, 2, -1, -4)

        // When
        val result = threeSum(input)

        // Then
        assertEquals(2, result.size)
        assertEquals(listOf(-1, -1, 2), result[0])
        assertEquals(listOf(-1, 0, 1), result[1])
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(0, 1, 1)

        // When
        val result = threeSum(input)

        // Then
        assertTrue(result.isEmpty())
    }
    @Test
    fun test3() {
        // Given
        val input = intArrayOf(0, 0, 0)

        // When
        val result = threeSum(input)

        // Then
        assertEquals(1, result.size)
        assertEquals(listOf(0, 0, 0), result[0])
    }
}

class `3SumImpl`: `3Sum`() {
    /**
     * Initial thought
     *
     * There is a problem called 2 sum
     * - What if for each one of the possible numbers
     *  - from 0 we extract that number
     *  - then we treat the rest of the numbers and that extracted number as 2 sum?
     *
     * To remove the duplicate
     * - We sort the nums
     * - for each element, before calculate the 2 sum, we check if the current number
     * is the same as the previous number
     *  -> if so, we just skip it
     *
     * ------------------------
     * Better approach
     * Using sliding windows
     *
     * 1. Sort the elements
     * 2. Loop (per each element of the array)
     * - left = i + 1
     * - right = length - 1
     * while (left < right)
     *      - check the sum of nums[i], nums[left], nums[right]
     *          - if it is 0
     *              -> Add it to the result
     *          - if it bigger than 0
     *              -> decrease right
     *          - if it smaller than 0
     *              -> increase left
     *
     * Time complexity O(n^2)
     */
    override fun threeSum(nums: IntArray): List<List<Int>> {
        val res:MutableSet<List<Int>> = HashSet()
        if (nums.isEmpty()) return arrayListOf()
        Arrays.sort(nums)
        for (i in 0 until nums.size - 1){
            var j = i +1
            var k = nums.size -1
            while (j < k) {
                val sum = nums [i] + nums[j] + nums[k]
                if (sum == 0) res.add(listOf(nums[i], nums[j++], nums[k--]))
                else if (sum > 0) k--
                else j++
            }

        }
        return res.toList()
    }
}

// [-1, 0, 1, 2, -1, -4]
// Sort [-4, -1, -1, 0, 1, 2]
// a      ^
// start      ^
// end                      ^
// sum = -1 + (0) + 1 = 0
// result = [[-1, -1, 2], [-1, 0, 1]]
class `3SumOptim` : `3Sum`() {
    override fun threeSum(nums: IntArray): List<List<Int>> {
        // 1. Init the variables
        val result: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        // 2. Loop through
        for (i in 0..nums.size - 2) {
            val a = nums[i]
            // Once the current number is bigger than 0, it does not
            // make sense to continue
            if (a > 0) continue
            // To avoid the same solution twice
            if (i > 0 && a == nums[i - 1]) continue
            var start = i + 1
            var end = nums.size - 1
            while (start < end) {
                val b = nums[start]
                val c = nums[end]
                val sum = a + b + c
                if (sum > 0) {
                    end--
                } else if (sum < 0) {
                    start++
                } else {
                    result.add(listOf(a, b, c))
                    start++
                    end--

                    // Discards the same solution
                    while (start < end && nums[start] == nums[start - 1]) {
                        start++
                    }
                }
            }
        }

        // 3. Return the final result
        return result
    }
}

class `3SumOptim2`: `3Sum`() {
    override fun threeSum(nums: IntArray): List<List<Int>> {
        Arrays.sort(nums)
        val list = mutableListOf<List<Int>>()
        for(index in nums.indices){
            if(nums[index] <= 0 )
                if(index == 0 || nums[index-1] != nums[index]){
                    twoSum(nums, index ,list)
                }
        }
        return list
    }

    private fun twoSum(nums: IntArray, i :Int,  list :MutableList<List<Int>>){
        var low = i + 1
        var high = nums.size - 1
        while( low < high){
            val sum = nums[low] + nums[high] + nums[i]
            if ( sum > 0 ){
                high--
            } else if(sum <0){
                low++
            }else{
                list.add( Arrays.asList(nums[i], nums[low++],nums[high--]))

                // Discards the same solution
                while (low < high && nums[low]==nums[low-1]) low++
            }
        }
    }

}