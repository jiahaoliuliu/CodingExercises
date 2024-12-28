package string.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not
 * map to any letters.
 *
 * Example 1:
 *  Input: digits = "23"
 *  Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 *  Input: digits = ""
 *  Output: []
 *
 * Example 3:
 *  Input: digits = "2"
 *  Output: ["a","b","c"]
 *
 * Constraints:
 *  0 <= digits.length <= 4
 *  digits[i] is a digit in the range ['2', '9'].
 */
abstract class LetterCombinationsOfAPhoneNumber {

    abstract fun letterCombinations(digits: String): List<String>

    @ParameterizedTest(name = "The letter combination of {0} should be {1}")
    @MethodSource("getData")
    fun test(input: String, expectedValue: List<String>) {
        val result = letterCombinations(input)
        assertTrue(expectedValue.containsAll(result))
        assertTrue(result.containsAll(expectedValue))
    }

    companion object {

        @JvmStatic
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf("23", listOf("ad","ae","af","bd","be","bf","cd","ce","cf")),
                arrayOf("", emptyList<String>()),
                arrayOf("2", listOf("a", "b", "c")),
            )
        }
    }
}

class LetterCombinationsOfAPhoneNumberImp: LetterCombinationsOfAPhoneNumber() {

    /**
     * Initial thoughts
     *
     * We can use backtracking to track each one of the numbers
     * 1. There should be a mapper which maps each one of the numbers to 3 or 4 letters
     *
     */
    override fun letterCombinations(digits: String): List<String> {
        if (digits.isNullOrEmpty()) return emptyList()

        return letterCombinations(digits, listOf(""))
    }

    private fun letterCombinations(digits: String, combinations: List<String>): List<String> {
        var currentCombinations = combinations
        digits.forEach { char ->
            val newLetters = digitToLetters(char)
            currentCombinations = combine(newLetters, currentCombinations)
        }

        return currentCombinations
    }

    private fun combine(newLetters:List<String>, currentCombinations: List<String>): List<String> {
        // 1. Init the variable
        val results = mutableListOf<String>()

        // 2. Loop
        newLetters.forEach { newLetter ->
            currentCombinations.forEach {currentCombination ->
                results.add(currentCombination + newLetter)
            }
        }

        // 3. Return the result
        return results
    }

    private fun digitToLetters(digit: Char): List<String> {
        return when (digit) {
            '2' -> listOf("a", "b", "c")
            '3' -> listOf("d", "e", "f")
            '4' -> listOf("g", "h", "i")
            '5' -> listOf("j", "k", "l")
            '6' -> listOf("m", "n", "o")
            '7' -> listOf("p", "q", "r", "s")
            '8' -> listOf("t", "u", "v")
            '9' -> listOf("w", "x", "y", "z")
            else -> emptyList()
        }
    }
}

// 4ms
class LetterCombinationOfAPhoneNumberOptim: LetterCombinationsOfAPhoneNumber() {
    private val combinations = mutableListOf<String>()
    private val lettersMap: Map<Char, String> = hashMapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz",
    )

    /**
     * Optimization:
     * - Use a map structure instead of map function
     *  - Instead of using a list of strings, returns one single string
     * - Use String builder instead of appending strings one by one
     */
    override fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return combinations

        backtrack(digits)
        return combinations
    }

    private fun backtrack(
        digits: String,
        index: Int = 0, sb: StringBuilder = StringBuilder()) {
        if (sb.length == digits.length) {
            combinations.add(sb.toString())
            return
        }

        lettersMap[digits[index]]?.forEach {letter ->
            sb.append(letter)
            backtrack(digits, index + 1, sb)
            sb.deleteCharAt(sb.length - 1)
        }
    }
}

class LetterCombinationOfAPhoneNumberOptim2: LetterCombinationsOfAPhoneNumber() {
    fun letters(symbol: Char) = when(symbol) {
        '2' -> "abc"
        '3' -> "def"
        '4' -> "ghi"
        '5' -> "jkl"
        '6' -> "mno"
        '7' -> "pqrs"
        '8' -> "tuv"
        '9' -> "wxyz"
        else -> ""
    }

    /**
     * Optimization:
     * - Use a map structure instead of map function
     *  - Instead of using a list of strings, returns one single string
     * - Use String builder instead of appending strings one by one
     */
    override fun letterCombinations(digits: String): List<String> {
        val result = mutableListOf<String>()
        if (digits.isEmpty()) return result

        fillLetterCombinations(digits, result)
        return result
    }

    private fun fillLetterCombinations(
        digits: String,
        result: MutableList<String>,
        index: Int = 0,
        prefix: String = ""
    ) {
        if (index == digits.length) {
            result.add(prefix)
            return
        }

        for (letter in letters(digits[index])) {
            fillLetterCombinations(digits, result, index + 1, prefix + letter)
        }
    }
}