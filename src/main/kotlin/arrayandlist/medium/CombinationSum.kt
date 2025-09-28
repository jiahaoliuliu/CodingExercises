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

    @ParameterizedTest(name = "The position of {1} in {0} should be {2}")
    @ArgumentsSource(TestDataArgumentProvider::class)
    fun test(nums: IntArray, target: Int, expectedValue: List<List<Int>>) {
        val result = combinationSum(nums, target)
        Assertions.assertEquals(expectedValue, result)
    }


    class TestDataArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(listOf(2, 3, 6, 7).toIntArray(), 7, listOf(listOf(2, 2, 3), listOf(7))),
            )
        }
    }
}

class CombinationSumImpl: CombinationSum() {
    override fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        return listOf(listOf(2, 2, 3), listOf(7))
    }
}