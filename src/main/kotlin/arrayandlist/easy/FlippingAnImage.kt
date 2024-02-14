package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Flipping an image
 *
 * Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.
 *
 * For example, flipping [1,1,0] horizontally results in [0,1,1].
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
 * For example, inverting [0,1,1] results in [1,0,0].
 *
 * Example 1:
 *      Input: image = [[1,1,0],[1,0,1],[0,0,0]]
 *      Output: [[1,0,0],[0,1,0],[1,1,1]]
 *      Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 *      Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * Example 2:
 *      Input: image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 *      Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *      Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 *      Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * Constraints:
 *      n == image.length
 *      n == image[i].length
 *      1 <= n <= 20
 *      images[i][j] is either 0 or 1.
 */
class FlippingAnImage {

    /**
     * Initial thought
     * We can use two data structures for it
     * - The general array is added, the first item added should be the first item extracted
     *   So it is a FIFO queue
     * - Per each one of the elements, the item needs to be flipped
     *  -> 110 will be converted to 011
     *  -> 1100 will be converted to 0011
     *  -> 1110 will be converted to 0111
     *  In this case, we need a structure which the last element entered will be the first element extracted
     *  We need a stack
     *
     *  Finally, whenever we extract an element, we need to reverse it. We can create a function for it
     *
     * Stack (LIFO):
     *  - Add first, extract first
     *
     * Queue (FIFO):
     *  - Add last, extract first
     *
     *  Optimization: We can reduce the space complexity by adding stack to the
     *  result on the step 2
     */
    private fun flipAndInvertImage(image: Array<IntArray>): Array<IntArray> {
        // 1. Prepare the data
        val result = mutableListOf<IntArray>()

        // 2. Loop
        for (row in image) {
            val stack = ArrayDeque<Int>()
            for (bit in row) {
                stack.addFirst(bit)
            }
            val rowData = mutableListOf<Int>()
            while (stack.isNotEmpty()) {
                rowData.add(invertBit(stack.removeFirst()))
            }
            result.add(rowData.toIntArray())
        }

        return result.toTypedArray()
    }

    private fun invertBit(bit: Int) = if (bit == 0) 1 else 0

    @Test
    fun test1() {
        // Given
        val input = arrayOf(intArrayOf(1,1,0), intArrayOf(1,0,1), intArrayOf(0,0,0))

        // When
        val result = flipAndInvertImage(input)

        // Then
        assertEquals(3, result.size)
        assertTrue(intArrayOf(1,0,0) contentEquals result[0])
        assertTrue(intArrayOf(0,1,0) contentEquals result[1])
        assertTrue(intArrayOf(1,1,1) contentEquals result[2])
    }

    @Test
    fun test2() {
        // Given
        val input = arrayOf(
            intArrayOf(1,1,0,0),
            intArrayOf(1,0,0,1),
            intArrayOf(0,1,1,1),
            intArrayOf(1,0,1,0)
        )

        // When
        val result = flipAndInvertImage(input)

        // Then
        assertEquals(4, result.size)
        assertTrue(intArrayOf(1,1,0,0) contentEquals result[0])
        assertTrue(intArrayOf(0,1,1,0) contentEquals result[1])
        assertTrue(intArrayOf(0,0,0,1) contentEquals result[2])
        assertTrue(intArrayOf(1,0,1,0) contentEquals result[3])
    }
}