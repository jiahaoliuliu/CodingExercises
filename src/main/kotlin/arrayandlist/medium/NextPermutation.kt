package arrayandlist.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3],
 * [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical
 * order, then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in
 * ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger
 * rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * Example 1:
 * - Input: nums = [1,2,3]
 * - Output: [1,3,2]
 *
 * Example 2:
 * - Input: nums = [3,2,1]
 * - Output: [1,2,3]
 *
 * Example 3:
 * - Input: nums = [1,1,5]
 * - Output: [1,5,1]
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 100
 */
abstract class NextPermutation {

    abstract fun nextPermutation(nums: IntArray): Unit

    @ParameterizedTest(name = "The next permutation of {0} should be {1}")
    @ArgumentsSource(TestDataArgumentProvider::class)
    fun test(initialList: IntArray, finalList: IntArray) {
        nextPermutation(initialList)
        Assertions.assertTrue(initialList contentEquals  finalList)
    }

    class TestDataArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(1, 3, 2)),
                Arguments.of(intArrayOf(3, 2, 1), intArrayOf(1, 2, 3)),
                Arguments.of(intArrayOf(1, 1, 5), intArrayOf(1, 5, 1)),
                Arguments.of(intArrayOf(1, 3, 2), intArrayOf(2, 1, 3)),
                //                      0  1  2  3  4  5
                Arguments.of(intArrayOf(4, 3, 2, 5, 3, 1), intArrayOf(4, 3, 3, 1, 2, 5)),
                Arguments.of(intArrayOf(1, 2), intArrayOf(2, 1)),
                Arguments.of(intArrayOf(2), intArrayOf(2)),
                Arguments.of(intArrayOf(2,3,1,3,3), intArrayOf(2, 3, 3, 1, 3)),
            )
        }
    }
}

/**
 * Initial thoughts
 * 1. Find the number to be replaced
 * 432531
 * -> 1 cannot be changed
 * -> 31 cannot be increased
 * -> 531 cannot be increased
 * -> 2531 can be increased
 * 2. find replacement
 * 2 cannot be replaced by 1 -> it is not increase
 * 2 can be replaced by 3 -> It is increase and it is the lowest increase possible
 *
 * so the replacement is 3 -> 35_1, 2
 * 3. Find the smallest element on the right
 * Sort the elements 5, 2, 1 from smallest to the biggest
 * 3125
 *
 * The result is 433125
 */
class NextPermutationImpl: NextPermutation() {
    override fun nextPermutation(nums: IntArray) {
        // 0. Corner cases
        if (nums.size == 1) return
        if (nums.size == 2) {
            nums.swap(0, 1)
            return
        }

        // 1. Find the number to be replaced
        val posToBeReplaced = findPosToBeReplaced(nums)

        // 2. Element to replaced
        if (posToBeReplaced >= 0) {
            val posToReplace = findElementToReplace(posToBeReplaced, nums)
            if (posToReplace >= 0) {
                nums.swap(posToBeReplaced, posToReplace)
            }
        }

        // 3. Revert all the number from the right side because those number are bigger
        nums.reverse(posToBeReplaced + 1)
    }

    private fun IntArray.reverse(startPosition: Int) {
        var left = startPosition
        var right = this.size - 1
        while (left < right) {
            swap(left, right)
            left++
            right--
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        println("Swapping $i and $j")
        val tmp = get(i)
        set(i, get(j))
        set(j, tmp)
    }

    private fun findElementToReplace(posToBeReplaced: Int, nums: IntArray): Int {
        val numToBeReplaced = nums[posToBeReplaced]
        var nextBiggestNumber = Pair(-1, Int.MAX_VALUE)
        for (i in posToBeReplaced + 1 until nums.size) {
            if (nums[i] > numToBeReplaced) {
                nextBiggestNumber = Pair(i, nums[i])
            }
        }

        return nextBiggestNumber.first
    }

    private fun findPosToBeReplaced(nums: IntArray): Int {
        for (i in (0 until nums.size - 1).reversed()) {
            if (nums[i] < nums[i+1]) {
                return i
            }
        }
        return -1
    }
}

class NextPermutationClaude: NextPermutation() {
    override fun nextPermutation(nums: IntArray) {
        val n = nums.size

        // Step 1: Find the first pair from the right where nums[i-1] < nums[i]
        var i = n - 1
        while (i > 0 && nums[i-1] >= nums[i]) {
            i--
        }

        // If no such pair exists, the array is in descending order
        // Step 2: Reverse the array to get the smallest permutation
        if (i == 0) {
            nums.reverse()
            return
        }

        // Step 3: Find the rightmost element greater than nums[i-1]
        var j = n - 1
        while (nums[j] <= nums[i-1]) {
            j--
        }

        // Step 4: Swap nums[i-1] and nums[j]
        val temp = nums[i-1]
        nums[i-1] = nums[j]
        nums[j] = temp

        // Step 5: Reverse the subarray starting from position i
        var left = i
        var right = n - 1
        while (left < right) {
            val temp = nums[left]
            nums[left] = nums[right]
            nums[right] = temp
            left++
            right--
        }
    }

    // Helper function to reverse an array in-place
    fun IntArray.reverse() {
        var left = 0
        var right = this.size - 1
        while (left < right) {
            val temp = this[left]
            this[left] = this[right]
            this[right] = temp
            left++
            right--
        }
    }
}

class NextPermutationOptim: NextPermutation() {
    override fun nextPermutation(nums: IntArray) {
        // 1. Find the position to be replaced. Starting from the second position from right
        var positionToBeReplaced = nums.size - 1 - 1
        while (positionToBeReplaced >= 0 && nums[positionToBeReplaced] >= nums[positionToBeReplaced + 1]) {
            positionToBeReplaced--
        }

        // 2. Find element to replace.
        // It is the smallest element on the right and it is bigger than nums[i]
        if (positionToBeReplaced >= 0) {
            var positionToReplace = nums.size - 1
            while (nums[positionToReplace] <= nums[positionToBeReplaced]) {
                positionToReplace--
            }

            // 3. Swap both elements
            nums.swap(positionToReplace, positionToBeReplaced)
        }

        // 4. Reverse the elements from `positionToBeReplaced` to right
        nums.reverse(positionToBeReplaced + 1)
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val tmp = get(i)
        set(i, get(j))
        set(j, tmp)
    }

    private fun IntArray.reverse(startPosition: Int) {
        var left = startPosition
        var right = this.size - 1
        while (left < right) {
            swap(left, right)
            left++
            right--
        }
    }

}