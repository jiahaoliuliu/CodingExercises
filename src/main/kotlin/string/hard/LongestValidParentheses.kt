package string.hard

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

/**
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)
 * parentheses substring.
 * Example 1:
 * - Input: s = "(()"
 * - Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 * - Input: s = ")()())"
 * - Output: 4
 * - Explanation: The longest valid parentheses substring is "()()".
 *
 * Example 3:
 * - Input: s = ""
 * - Output: 0
 *
 * Constraints:
 * - 0 <= s.length <= 3 * 10^4
 * - s[i] is '(', or ')'.
 */
abstract class LongestValidParentheses {

    abstract fun longestValidParentheses(s: String): Int

    @ParameterizedTest(name = "The longest valid parenthesis of {0} is {1}")
    @ArgumentsSource(TestDataArgumentProvider::class)
    fun test(input: String, expectedValue: Int) {
        val result = longestValidParentheses(input)
        assertEquals(expectedValue, result)
    }

    class TestDataArgumentProvider: ArgumentsProvider{
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of("(()", 2),
                Arguments.of(")()())", 4),
                Arguments.of("", 0),
            )
        }
    }
}

class LongestValidParenthesisImpl: LongestValidParentheses() {
    override fun longestValidParentheses(s: String): Int {
        return 2
    }
}