package string.medium

/**
 * Basic calculator II
 *
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the
 * range of [-2^31, 2^31 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical
 * expressions, such as eval().
 *
 * Example 1:
 *      Input: s = "3+2*2"
 *      Output: 7
 *
 * Example 2:
 *      Input: s = " 3/2 "
 *      Output: 1
 *
 * Example 3:
 *      Input: s = " 3+5 / 2 "
 *      Output: 5
 *
 * Constraints:
 *      1 <= s.length <= 3 * 10^5
 *      s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 *      s represents a valid expression.
 *      All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 *      The answer is guaranteed to fit in a 32-bit integer.
 *
 */
class BasicCalculatorIITODO {

    /**
     * Initial thoughts
     *
     * 1. Init the value
     * - operation = +
     * - Stack
     * - current number = 0
     *
     * 2. loop
     * Using a stack to store all the operations and numbers related with + and -
     *
     * If the operation is * or /, pop the last number stored in the stack and add the result to the stacck
     *
     * 3. Return the result
     * From current number, sum all the numbers in stack
     * and return it
     */
    fun calculate(s: String): Int {
        return 0
    }
}