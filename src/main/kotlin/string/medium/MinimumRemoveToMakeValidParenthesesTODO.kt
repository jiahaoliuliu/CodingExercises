package string.medium

/**
 * Minimum Remove to Make Valid Parentheses
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the
 * resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *      It is the empty string, contains only lowercase characters, or
 *      It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *      It can be written as (A), where A is a valid string.
 *
 * Example 1:
 *      Input: s = "lee(t(c)o)de)"
 *      Output: "lee(t(c)o)de"
 *      Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 *      Input: s = "a)b(c)d"
 *      Output: "ab(c)d"
 *
 * Example 3:
 *      Input: s = "))(("
 *      Output: ""
 *      Explanation: An empty string is also valid.
 *
 * Constraints:
 *      1 <= s.length <= 10^5
 *      s[i] is either'(' , ')', or lowercase English letter.
 *
 */
class MinimumRemoveToMakeValidParenthesesTODO {

    /**
     * Initial thoughts
     *
     * A parenthesis is valid if
     * - it is ( for any case
     * - It is ) and there were a matching ( before
     *
     * We can have a stack and a queue
     * 1. Use the stack to know the valid parenthesis format
     * - If the stack is empty
     *      -> If the new symbol is "(" add it to the stack
     *      -> If the new symbol is ")", discard it
     * - If the stack is not empty
     *      -> If the on the top of the stack it is "("
     *          -> If the new symbol is "(",
     *              - add it to the stack
     *          -> If the new symbol is ")",
     *              - add "(" in the queue
     *              - add ")" in the queue
     *  At the end of the loop, the queue will have the right format for the list of parenthesis
     *
     *  2. Create a string builder and loop through the string again
     *  - When char is an english character, append it to the sb
     *  - When char is parenthesis
     *      -> if it matches with the top of the queue
     *          - Remove the top of the queue
     *          - Append the parenthesis to the sb
     *      -> If not
     *          - discard the symbol
     *
     *  3. Return result
     *  return sb.toString()
     *
     *  -------------------------------------
     *  Another solution:
     *  - Using an int variable to keep track of the opening parenthesis
     *  1. Use a string builder to append all the chars and any open parenthesis and discard bad close
     *  parenthesis
     *
     *  2. Loop the previous string builder backward, discarding any excess open parenthesis
     *  Using the open counter before. Discard as many open parenthesis as the open number
     *
     *  3. Reverse the sb on the step 2
     */
    private fun minRemoveToMakeValid(s: String): String {
        return ""
    }
}