package arrayandlist.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream


/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations
 * of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150
 * combinations for the given input.
 *
 * Example 1:
 * - Input: candidates = [2,3,6,7], target = 7
 * - Output: [[2,2,3],[7]]
 *
 * Explanation:
 * - 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * - 7 is a candidate, and 7 = 7.
 * - These are the only two combinations.
 *
 * Example 2:
 * - Input: candidates = [2,3,5], target = 8
 * - Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * - Input: candidates = [2], target = 1
 * - Output: []
 *
 * Constraints:
 * - 1 <= candidates.length <= 30
 * - 2 <= candidates[i] <= 40
 * - All elements of candidates are distinct.
 * - 1 <= target <= 40
 */
abstract class CombinationSum {

    abstract fun combinationSum(candidates: IntArray, target: Int): List<List<Int>>

    @ParameterizedTest(name = "The combination sum of {0} with target {1} should be {2}")
    @ArgumentsSource(TestDataArgumentProvider::class)
    fun test(nums: IntArray, target: Int, expectedValue: List<List<Int>>) {
        val result = combinationSum(nums, target)
        Assertions.assertEquals(expectedValue, result)
    }

    class TestDataArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(listOf(2, 3, 6, 7).toIntArray(), 7, listOf(listOf(2, 2, 3), listOf(7))),
                Arguments.of(listOf(2, 3, 5).toIntArray(), 8, listOf(listOf(2, 2, 2, 2), listOf(2, 3, 3), listOf(3, 5))),
                Arguments.of(listOf(2).toIntArray(), 1, emptyList<Int>())
            )
        }
    }
}

class CombinationSumImpl: CombinationSum() {
    /**
     * Initial thoughts
     *
     * It is a typical combination problem, where the elements could be repeated.
     * Example: [2, 3, 6, 7] [7]
     * 1. Take each one of items separately
     *  [2]          [3]           [6]               [7]
     * The sum of 7 is the target, so return it
     * - 7 - [2] = 5 > 0
     * - 7 - [3] = 4 > 0
     * - 7 - [6] = 1 > 0
     * - 7 - [7] = 0 == 0 -> Add it to the solution
     * Somehow we need to carry the existing list of numbers and the target
     * combinationSumHelp([2, 3, 6, 7], 7, [])
     *                                     ^
     *                             current combination
     * return the current combination if the target == 0
     * else, do not return anything (null?)
     *
     * Distributor
     * - Distribute each one of the candidates
     * - Create a list of lists and add each one of the results of the helper
     * Checker
     * - Check if the combination is correct
     * - if the target is 0, then return the combination + candidate
     * Aggregator
     * - Add all the results by the candidates and return as result
     *
     *                                         *
     *        [2]                [3]                    [6]                   [7]
     *
     * [2] [3] [6] [7]       [2] [3] [6] [7]       [2] [3] [6] [7]      [2] [3] [6] [7]
     */
    override fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        // Aggregator
//        val result = mutableListOf(listOf<Int>())
//        for (candidate in candidates) {
//            // Distributor
//            val currentCombination = mutableListOf(listOf<Int>())
//            combinationSum(candidates, target - candidate, mutableListOf(candidate), currentCombination)
//            result.addAll(currentCombination)
//        }
//        return result
        // Aggregator
        val result = mutableListOf<List<Int>>()
        combinationSum(candidates, target, mutableListOf(), result)
        return result
    }

    private fun combinationSum(candidates: IntArray, target: Int, currentCombination: MutableList<Int>, currentResult: MutableList<List<Int>>) {
        // Checker
        // If the current combination has already exceeded. Then there is nothing to do
        if (target < 0) return
        // If the target has been found, add the current combination to the list of results and return it
        if (target == 0) {
            currentResult.add(currentCombination)
            return
        }

        // If the target is still a positive number
        // Distributor
        // TODO: The combination needs to be reset at some point, otherwise it will contain all the possible paths
        for (candidate in candidates) {
            currentCombination.add(candidate)
            combinationSum(candidates, target - candidate, currentCombination, currentResult)
        }
    }
}

class CombinationSumCl: CombinationSum() {

    override fun combinationSum(
        candidates: IntArray,
        target: Int
    ): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun backtrack(start: Int, current: MutableList<Int>, total: Int) {
            // Base cases
            when {
                total == target -> {
                    result.add(current.toList())  // Found valid combination
                    return
                }
                total > target -> return  // Exceeded target, stop exploring
            }

            // Try each candidate starting from 'start' index
            for (i in start until candidates.size) {
                current.add(candidates[i])
                // Can reuse same element, so pass 'i' not 'i+1'
                backtrack(i, current, total + candidates[i])
                current.removeAt(current.size - 1)  // Backtrack
            }
        }

        backtrack(0, mutableListOf(), 0)
        return result
    }
}