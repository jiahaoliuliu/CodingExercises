package bits.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Sort Integers by The Number of 1 Bits
 *
 * You are given an integer array arr. Sort the integers in the array in ascending order by the number
 * of 1's in their binary representation and in case of two or more integers have the same number of
 * 1's you have to sort them in ascending order.
 *
 * Return the array after sorting it.
 *
 * Example 1:
 *      Input: arr = [0,1,2,3,4,5,6,7,8]
 *      Output: [0,1,2,4,8,3,5,6,7]
 *      Explantion: [0] is the only integer with 0 bits.
 *                  [1,2,4,8] all have 1 bit.
 *                  [3,5,6] have 2 bits.
 *                  [7] has 3 bits.
 *     The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 *
 * Example 2:
 *      Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
 *      Output: [1,2,4,8,16,32,64,128,256,512,1024]
 *      Explantion: All integers have 1 bit in the binary representation, you should just sort them in
 *      ascending order.
 *
 * Constraints:
 *      1 <= arr.length <= 500
 *      0 <= arr[i] <= 104
 */
abstract class SortIntegersByTheNumberOf1Bits {

    abstract fun sortByBits(arr: IntArray): IntArray

    @Test
    fun test1() {
        // Given
        val arr = intArrayOf(0,1,2,3,4,5,6,7,8)

        // When
        val result = sortByBits(arr)

        // Then
        assertTrue(intArrayOf(0,1,2,4,8,3,5,6,7) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val arr = intArrayOf(1024,512,256,128,64,32,16,8,4,2,1)

        // When
        val result = sortByBits(arr)

        // Then
        assertTrue(intArrayOf(1,2,4,8,16,32,64,128,256,512,1024) contentEquals result)
    }
}

class SortIntegersByTheNumberOf1BitsUsingCache: SortIntegersByTheNumberOf1Bits() {

    /**
     * Initial thoughts
     *
     * 1. Create a custom comparator
     * 2. Compare the elements of the array using the special comparator
     *
     * Because when all the elements has same number of bits, then we need to sort them by their natural order
     * Since kotlin uses 32 bits for integer, we can create an array of from 0 to 32, where
     * - key: The number of bits
     * - value: List of numbers
     *
     * Then going through the int array to retrieve each bit and sort the elements
     *
     * Complexity:
     * - Time: O(n) + O(32 * n log n) = O(n log n)
     * - Space: O(n)
     */
    override fun sortByBits(arr: IntArray): IntArray {
        // 1. Init variable
        val myArray = Array<MutableList<Int>>(33) { mutableListOf() }

        // 2. Loop and fill the array
        for (num in arr) {
            val numberOfBits = num.countOneBits()
            myArray[numberOfBits].add(num)
        }

        // 3.Return result
        val result = mutableListOf<Int>()
        for (bits in myArray.indices) {
            val bitsList = myArray[bits]
            if (bitsList.isNotEmpty()) {
                bitsList.sort()
                result.addAll(bitsList)
            }
        }
        return result.toIntArray()
    }
}

class SortIntegersByTheNumberOf1BitsCustomSort: SortIntegersByTheNumberOf1Bits() {

    /**
     * Create a custom comparator
     */
    override fun sortByBits(arr: IntArray): IntArray {
        val comparator = Comparator<Int> { o1, o2 ->
            if (o1.countOneBits() == o2.countOneBits()) {
                o1 - o2
            } else {
                o1.countOneBits() - o2.countOneBits()
            }
        }

        return arr.sortedWith(comparator).toIntArray()
    }
}