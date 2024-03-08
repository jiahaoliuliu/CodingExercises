package string.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * First repeating character
 *
 * Given a string str, create a function that returns the first repeating character.
 * If such character doesn't exist, return the null character '\0'.
 *
 * Example 1:
 *      Input: str = "inside code"
 *      Output: 'i'
 *
 * Example 2:
 *      Input: str = "programming"
 *      Output: 'r'
 *
 * Example 3:
 *      Input: str = "abcd"
 *      Output: 'null'
 *
 * Example 4:
 *      Input: str = "abba"
 *      Output: 'b'
 */
abstract class FirstRepeatingCharacter {

    abstract fun firstRepeatingCharacter(str: String): Char?

    @Test
    fun test1() {
        // Given
        val str = "inside code"

        // When
        val result = firstRepeatingCharacter(str)

        // Then
        assertEquals('i', result)
    }

    @Test
    fun test2() {
        // Given
        val str = "programming"

        // When
        val result = firstRepeatingCharacter(str)

        // Then
        assertEquals('r', result)
    }

    @Test
    fun test3() {
        // Given
        val str = "abcd"

        // When
        val result = firstRepeatingCharacter(str)

        // Then
        assertNull(result)
    }

    @Test
    fun test4() {
        // Given
        val str = "abba"

        // When
        val result = firstRepeatingCharacter(str)

        // Then
        assertEquals('b', result)
    }
}

class FirstRepeatingCharacterImpl: FirstRepeatingCharacter() {

    /**
     * Initial thoughts
     *
     * Using a hashSet
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(n)
     *
     */
    override fun firstRepeatingCharacter(str: String): Char? {
        // 1. Init the variable
        val hashSet = HashSet<Char>()

        // 2. Loop
        str.forEach {
            if (hashSet.contains(it)) {
                return it
            } else {
                hashSet.add(it)
            }
        }

        return null
    }
}

class FirstRepeatingCharacterOptimSpace: FirstRepeatingCharacter() {

    /**
     * Instead of using a hash set, use an array of chars
     */
    override fun firstRepeatingCharacter(str: String): Char? {
        // 1. Init the variable
        val myArray = Array<Boolean>(26) { false }

        // 2. Repeat
        str.forEach {
            if (it != ' ') {
                if (myArray[it - 'a']) {
                    return it
                } else {
                    myArray[it - 'a'] = true
                }
            }
        }

        return null
    }

}