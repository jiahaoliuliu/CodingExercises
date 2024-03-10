package string.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Letter Tile Possibilities
 *
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.
 *
 * Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
 *
 * Example 1:
 *      Input: tiles = "AAB"
 *      Output: 8
 *      Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Example 2:
 *      Input: tiles = "AAABBC"
 *      Output: 188
 *
 * Example 3:
 *      Input: tiles = "V"
 *      Output: 1
 *
 * Constraints:
 *      1 <= tiles.length <= 7
 *      tiles consists of uppercase English letters.
 */
abstract class LetterTilePossibilities {

    abstract fun numTilePossibilities(tiles: String): Int

    @Test
    fun test1() {
        // Given
        val tiles = "AAB"

        // When
        val result = numTilePossibilities(tiles)

        // Then
        assertEquals(8, result)
    }

    @Test
    fun test2() {
        // Given
        val tiles = "AAABBC"

        // When
        val result = numTilePossibilities(tiles)

        // Then
        assertEquals(188, result)
    }

    @Test
    fun test3() {
        // Given
        val tiles = "V"

        // When
        val result = numTilePossibilities(tiles)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test4() {
        // Given
        val tiles = "ABC"

        // When
        val result = numTilePossibilities(tiles)

        // Then
        assertEquals(15, result)
    }

}

class LetterTilePossibilitiesImpl: LetterTilePossibilities() {

    /**
     * Initial thoughts
     * ABC
     * "A", "B", "C",
     * "AB",  "AC",  "BA",  "BC",  "CA",  "CB",
     * "ABC", "ACB", "BAC", "BCA", "CAB", "CBA"
     *
     * First split them into single letters
     * "A", "B", "C"
     * Add them to the result
     *
     * Then, based on those letter, add the rest of the letters to it
     * "A" -> "AB", "AC"
     * "B" -> "BA", "BC"
     * "C" -> "CA", "CB"
     * Append them to the results
     *
     * Finally, append the missing letter
     * "AB" -> "ABC"
     * "AC" -> "ACB"
     * "BA" -> "BAC"
     * "BC" -> "BCA"
     * "CA" -> "CAB"
     * "CB" -> "CBA"
     *
     * So the algorithm needs to go thought each letter and split them into two parts:
     * - Already appended
     * - Waiting to be appended
     *
     */
    override fun numTilePossibilities(tiles: String): Int {
        val result = HashSet<String>()
        numTilePossibilities("", tiles, result)
        return result.size
    }

    private fun numTilePossibilities(appended: String, tiles: String, result: HashSet<String>) {
        if (appended.isNotEmpty()) {
            result.add(appended)
        }

        tiles.forEachIndexed { index, c ->
            numTilePossibilities(appended + c,
                allCharsExceptIndex(tiles, index), result)
        }
    }

    private fun allCharsExceptIndex(tile: String, pos:Int): String {
        return if (pos == 0) {
            if (tile.isNotEmpty()) {
                tile.substring(1)
            } else {
                ""
            }
        } else if (pos == tile.length - 1) {
            tile.substring(0, pos)
        } else {
            tile.substring(0, pos) + tile.substring(pos + 1)
        }
    }
}