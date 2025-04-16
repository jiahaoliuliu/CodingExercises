package arrayandlist.medium

import linkedlist.ListNode
import linkedlist.toLinkedList
import linkedlist.toList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
//                Arguments.of(intArrayOf(3, 2, 1), intArrayOf(1, 2, 3)),
//                Arguments.of(intArrayOf(1, 1, 5), intArrayOf(1, 5, 1)),
//                Arguments.of(intArrayOf(4, 3, 2, 5, 3, 1), intArrayOf(4, 3, 3, 1, 2, 5)),
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
        // 1. Find the number to be replaced
        val posToBeReplaced = findPosToBeReplaced(nums)
        println("Pos to be replaced $posToBeReplaced")
        // Nothing to be replaced
        if (posToBeReplaced == -1) {
            return
        }

        // 2. Element to be replaced
        val posToReplace = findElementToReplace(posToBeReplaced, nums)
        println("The element to replace is $posToReplace")
        if (posToReplace == -1) {
            return
        }

        val tmp = nums[posToBeReplaced]
        nums[posToBeReplaced] = nums[posToReplace]
        nums[posToReplace] = tmp

        println("After replace the elements ${nums.toList()}")

        // 3. Sort elements on the right of the posToBeReplaced
        val listToBeSorted = mutableListOf<Int>()
        for (i in posToBeReplaced + 1 until nums.size) {
            listToBeSorted.add(nums[i])
        }

        listToBeSorted.sort()
        println("List sorted $listToBeSorted")

        for (i in posToBeReplaced + 1 until nums.size) {
            println(i)
            println(i - posToReplace + 1)
            nums[i] = listToBeSorted[i - posToReplace + 1]
        }
        println(nums.toList())
    }

    private fun findElementToReplace(posToBeReplaced: Int, nums: IntArray): Int {
        val numToBeReplaced = nums[posToBeReplaced]
        var nextBiggestNumber = Pair(-1, Int.MAX_VALUE)
        for (i in posToBeReplaced + 1 until nums.size) {
            if (nums[i] in (numToBeReplaced + 1) until nextBiggestNumber.second) {
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