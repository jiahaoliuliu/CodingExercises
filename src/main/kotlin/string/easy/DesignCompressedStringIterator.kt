package string.easy

import org.junit.Assert.*
import org.junit.Test

/**
 * Design Compressed String Iterator
 *
 * Design and implement a data structure for a compressed string iterator. The given compressed string will be
 * in the form of each letter followed by a positive integer representing the number of this letter existing in
 * the original uncompressed string.
 *
 * Implement the StringIterator class:
 * next() Returns the next character if the original string still has uncompressed characters, otherwise returns a
 * white space.
 * hasNext() Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns
 * false.
 *
 * Example 1:
 *      Input ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
 *            [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
 *      Output
 *            [null, "L", "e", "e", "t", "C", "o", true, "d", true]
 *
 * Explanation
 *      StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
 *      stringIterator.next(); // return "L"
 *      stringIterator.next(); // return "e"
 *      stringIterator.next(); // return "e"
 *      stringIterator.next(); // return "t"
 *      stringIterator.next(); // return "C"
 *      stringIterator.next(); // return "o"
 *      stringIterator.hasNext(); // return True
 *      stringIterator.next(); // return "d"
 *      stringIterator.hasNext(); // return True
 *
 * Constraints:
 *      1 <= compressedString.length <= 1000
 *      compressedString consists of lower-case an upper-case English letters and digits.
 *      The number of a single character repetitions in compressedString is in the range [1, 10^9]
 *      At most 100 calls will be made to next and hasNext.
 */
class DesignCompressedStringIterator {

    /**
     * Initial though
     *
     * Let's decompress the string on the class creation
     * Then have a iterator pointing every position of the string
     *
     * Or we can decompress the string whenever the user calls for next
     * "L1e2t1C1o1d1e1"
     * The letters always comes in pair
     *  -> Have a pointer init to 0 and jump 2 by 2
     *
     *  1. Init the variables
     *  iterator = 0
     *  occurrences = 0
     *  currentChar = ' '
     */
    class StringIterator(private val compressedString: String) {
        var iterator = 0
        var occurrences = 0
        var currentChar = ' '

        /**
         * initial position
         * -> Iterator is pointing to the first char
         *  -> It should always point to the next char to be read
         *  -> Or at the end of the string
         * -> Occurrences is zero
         *
         * Every time next is called
         *      1. Check for the occurrences
         *      -> If it is zero
         *          -> If the iterator is not at the end of the string
         *              -> Read the current char
         *              -> Increase the iterator 1 time
         *              -> Read the occurrences
         *              -> Increase the iterator 1 time
         *          -> If the iterator is at the end of the string
         *              -> Return empty char
         *      2. decrease the occurrences
         *      return the current char
         *
         *  Variant: The occurrences could have more than 1 digit
         */
        fun next(): Char {
            // 1. Check for the occurrences
            if (occurrences == 0) {
                if (iterator < compressedString.length) {
                    currentChar = compressedString[iterator]
                    iterator++
                    occurrences = compressedString[iterator].digitToInt()
                    iterator++
                    // To parse all the rest of digits
                    while (iterator < compressedString.length && compressedString[iterator].isDigit()) {
                        occurrences *= 10
                        occurrences += compressedString[iterator].digitToInt()
                        iterator++
                    }

                } else {
                    return ' '
                }
            }

            occurrences--
            return currentChar
        }

        /**
         * if the iterator is smaller than the length of compressedString
         */
        fun hasNext(): Boolean {
            return occurrences > 0 || iterator < compressedString.length
        }
    }

    @Test
    fun test1() {
        // Given
        val input = "L1e2t1C1o1d1e1"

        // When
        val stringIterator = StringIterator(input)

        // Then
        assertEquals('L', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('t', stringIterator.next())
        assertEquals('C', stringIterator.next())
        assertEquals('o', stringIterator.next())
        assertTrue(stringIterator.hasNext())
        assertEquals('d', stringIterator.next())
        assertTrue(stringIterator.hasNext())
        assertEquals('e', stringIterator.next())
        assertFalse(stringIterator.hasNext())
    }

    @Test
    fun test2() {
        // Given
        val input = "L10e2t1C1o1d1e11"

        // When
        val stringIterator = StringIterator(input)

        // Then
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('L', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('t', stringIterator.next())
        assertEquals('C', stringIterator.next())
        assertEquals('o', stringIterator.next())
        assertTrue(stringIterator.hasNext())
        assertEquals('d', stringIterator.next())
        assertTrue(stringIterator.hasNext())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertEquals('e', stringIterator.next())
        assertFalse(stringIterator.hasNext())
    }
}