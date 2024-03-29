package arrayandlist.medium

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number
 * of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [["1","1","1","1","0"],
 *                ["1","1","0","1","0"],
 *                ["1","1","0","0","0"],
 *                ["0","0","0","0","0"]]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [["1","1","0","0","0"],
 *                ["1","1","0","0","0"],
 *                ["0","0","1","0","0"],
 *                ["0","0","0","1","1"]]
 * Output: 3
 *
 * Constraints:
 *      m == grid.length
 *      n == grid[i].length
 *      1 <= m, n <= 300
 *      grid[i][j] is '0' or '1'.
 */
class NumberOfIslandsTODO {

    /**
     * Initial though
     *
     * Loop through the matrix
     * - Per each item, if it is 1
     *  - count ++
     *  - Override all the connected 1s to 0s
     *
     *  return count
     */
    private fun numIslands(grid: Array<CharArray>): Int {
        return 0
    }
}