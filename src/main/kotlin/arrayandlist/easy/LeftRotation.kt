package arrayandlist.easy

import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Left rotation
 * A left rotation operation on an array shifts each of the array's elements  unit to the left.
 * For example, if 2 left rotations are performed on array [1, 2, 3, 4, 5] , then the array would become [3, 4, 5, 1, 2].
 * Note that the lowest index item moves to the highest index in a rotation. This is called a circular array.
 *
 * Given array a of n integers and a number d, perform d left rotations on the array. Return the updated array
 * to be printed as a single line of space-separated integers.
 *
 * Function Description
 *  Complete the function rotLeft in the editor below.
 *  rotLeft has the following parameter(s):
 *      int a[n]: the array to rotate
 *      int d: the number of rotations
 * Returns
 *        int a'[n]: the rotated array
 *
 * Check: Not in Leet code. In Hacker rank
 */
class LeftRotation {
    private fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
        if (d == 0 || d == a.size) {
            return a
        }

        val solution = Array(a.size) { 0 }
        var index = 0
        // First part of the algorithm
        for (n in d until a.size) {
            solution[index] = a[n]
            index++
        }

        // Second part of the algorithm
        for (n in 0 until d) {
            solution[index] = a[n]
            index++
        }

        return solution
    }

    @Test
    fun test1() {
        // Given
        val input = arrayOf(1, 2, 3, 4, 5)

        // When
        val result = rotLeft(input, 4)

        // Then
        assertTrue(arrayOf(5, 1, 2, 3, 4) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val input = arrayOf(41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51)

        // When
        val result = rotLeft(input, 10)

        // Then
        assertTrue(arrayOf(77, 97, 58, 1, 86, 58, 26, 10, 86, 51, 41, 73, 89, 7, 10, 1, 59, 58, 84, 77) contentEquals result)
    }

    @Test
    fun test3() {
        // Given
        val input = arrayOf(33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97)

        // When
        val result = rotLeft(input, 13)

        // Then
        assertTrue(arrayOf(87, 97, 33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60) contentEquals result)
    }

}