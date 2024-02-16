package arrayandlist.easy

/**
 * Pascal's triangle
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 *      Input: numRows = 5
 *      Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2:
 *      Input: numRows = 1
 *      Output: [[1]]
 *
 * Constraints:
 *      1 <= numRows <= 30
 */
class PascalsTriangleTODO {

    /**
     * Initial thought
     *
     * If numRows = 0. Return empty list
     * If numRows = 1, return [[1]]
     * For the rest of numRows
     *
     * Create a result which is [[1]]
     * for every num rows
     *  - 1. Get the last element on the result (prev)
     *  - 1.1 Create an array of size (prev.size + 1). Called it as current
     *  - 2. for every element in the step 1
     *    - If i = 0, add prev[0] to current
     *    - If i = length of end of current, add prev[prev.size - 1] to the current
     *    - Else
     *     - add prev[prev[i] + prev[i + 1]] to the current
     *
     * Time complexity: O(n^2)
     * Space complexity:
     *  - If don't count result, O(1)
     *  - If count result, O(n(n + 1)/2)
     */
    private fun generate(numRows: Int): List<List<Int>> {
        return listOf(listOf())
    }
}