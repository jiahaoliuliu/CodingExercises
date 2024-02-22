package string.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max

/**
 * Longest common subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 *      Input: text1 = "abcde", text2 = "ace"
 *      Output: 3
 *      Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 *      Input: text1 = "abc", text2 = "abc"
 *      Output: 3
 *      Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 *      Input: text1 = "abc", text2 = "def"
 *      Output: 0
 *      Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 *      1 <= text1.length, text2.length <= 1000
 *      text1 and text2 consist of only lowercase English characters.
 */
abstract class LongestCommonSubsequence {

    abstract fun longestCommonSubsequence(text1: String, text2: String): Int

    @Test
    fun test1() {
        // Given
        val text1 = "abcde"
        val text2 = "ace"

        // When
        val result = longestCommonSubsequence(text1, text2)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        // Given
        val text1 = "abc"
        val text2 = "abc"

        // When
        val result = longestCommonSubsequence(text1, text2)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test3() {
        // Given
        val text1 = "abc"
        val text2 = "def"

        // When
        val result = longestCommonSubsequence(text1, text2)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test4() {
        // Given
        val text1 = "abdacbab"
        val text2 = "acebfca"

        // When
        val result = longestCommonSubsequence(text1, text2)

        // Then
        assertEquals(4, result)
    }
}

class LongestCommonSubsequenceRecursive: LongestCommonSubsequence() {
    /**
     * Initial thoughts
     * s1 = "a b d a c b a b"
     * s2 = "a c e b f c a"
     * longest substring = "abca"
     *
     * To get the longest string, we should start with the shortest string, which is s2
     * Now, per each char in the s2, we check if it is contained on the s1, or a substring of s1
     * In this case, we can start reducing the size of s1, s2, or have always a pointer to point
     * the first position to search from s1 and s2, which returns the size of the longest string
     * from that position
     *
     * Subproblem:
     * - Given s1, s2, pointer1, pointer2, return the longest substring
     *
     * Definition:
     * - lcb(t1, t2, p1, p2) = lcb from (p1, p2) until (t1.length -1, t2.length -1)
     *
     * - Given p1, p2, it is because t1[p1] and t2[p2] need to be checked, not skiped
     * The decision of skip them or not coming from the general statement
     *
     * DAG:
     * https://excalidraw.com/#room=3bd375d0b0a00596bd5d,lI8O11FAF-jjvi3HwhQpcA
     *
     * Choice:
     * - Per each char, the algo might choose to check it or skip it
     *
     * Merging subproblem:
     * - lcb(t1, t2, p1, p2):
     *      - if (t1[p1] == t2[p2]) -> p1 and p2 needs to be discarded
     *          1 + lcb(t1, t2, p1+1, p2+1)
     *      - if (t1[p1] != t2[p2]) -> We can either advance p1, or advance p2
     *          max(
     *              lcb(t1, t2, p1+1, p2)
     *              lcb(t1, t2, p1, p2+2)
     *          )
     * - Special cases
     *  - if p1 >= t1.length -> return 0 -> We finished checking t1
     *  - if p2 >= t2.length -> return 0 -> We finished checking t2
     *
     *  Base case:
     *  - if (p1 == t1.length - 1 && p2 == t2.length - 1)
     *      - if (p1[t1] == p2[t2]) return 1
     *      - else return 0
     *
     *  Complexity:
     *  - Time: O(2^(m + n))
     *  - Space: Calling stack = O(m + n)
     */
    override fun longestCommonSubsequence(text1: String, text2: String): Int {
        return longestCommonSubsequence(text1, text2, 0, 0)
    }

    private fun longestCommonSubsequence(text1: String, text2: String, p1: Int, p2: Int): Int {
        // Corner cases
        if (p1 == text1.length || p2 == text2.length) {
            return 0
        }

        // Base case -> This could be optimized
//        if (p1 == text1.length -1 && p2 == text2.length - 1) {
//            return if (text1[p1] == text2[p2]) {
//                1
//            } else {
//                0
//            }
//        }

        // Other cases
        return if (text1[p1] == text2[p2]) {
            1 + longestCommonSubsequence(text1, text2, p1+1, p2+1)
        } else {
            max(
                longestCommonSubsequence(text1, text2, p1+1, p2),
                longestCommonSubsequence(text1, text2, p1, p2+1)
            )
        }
    }
}

class LongestCommonSubsequenceMemoization: LongestCommonSubsequence() {

    /**
     * Lookup key size
     * - Key: Pair(p1, p2)
     * - value: longest common subsequence
     *
     * Size: m * n
     *
     * Complexity:
     * - Time: Number of subproblems * cost of each subproblem = (m+1) * (n+1) * O(1) = O(m*n)
     * - Space: Calling stack + lookup table size = O(n + m) + O(n * m) = O(n*m)
     */
    override fun longestCommonSubsequence(text1: String, text2: String): Int {
        return longestCommonSubsequence(text1, text2, 0, 0, HashMap<Pair<Int, Int>, Int>())
    }

    private fun longestCommonSubsequence(
        text1: String, text2: String, p1: Int, p2: Int,
        lookUpTable: HashMap<Pair<Int, Int>, Int>
    ): Int {
        if (lookUpTable.containsKey(Pair(p1, p2))) {
            return lookUpTable[Pair(p1, p2)]!!
        }

        // Corner cases
        if (p1 == text1.length || p2 == text2.length) {
            return 0
        }

        // Other cases
        return if (text1[p1] == text2[p2]) {
            lookUpTable[Pair(p1, p2)] = 1 +
                    longestCommonSubsequence(text1, text2, p1+1, p2+1, lookUpTable)
            lookUpTable[Pair(p1, p2)]!!
        } else {
            lookUpTable[Pair(p1, p2)] =
            max(
                longestCommonSubsequence(text1, text2, p1+1, p2, lookUpTable),
                longestCommonSubsequence(text1, text2, p1, p2+1, lookUpTable)
            )
            lookUpTable[Pair(p1, p2)]!!
        }
    }
}

class LongestCommonSubsequenceTabulation: LongestCommonSubsequence() {

    /**
     * DAG inverted and relation graph
     * https://excalidraw.com/#room=fa44db3cdea9f8b8b5af,cGz528GN6yGbDXlxLq2BbA
     * We need a matrix of (m, n) to store the previous values\
     *
     * lcd(t1, t2, p1, p2) = longest common subsequence from position (0, 0) to (p1, p2)
     *
     * Base case
     * - cache(0, 0) = if (t1[0] == t2[0]) 1 else 0
     *
     * First row
     *  - for p2 from 1 to t2.length -1
     *      cache(0, p2) = (if (t1[0] == t2[p2]) 1 else 0) + cache(0, p2 - 1)
     *
     * First column
     *  - for p1 from 1 to t1.length -1
     *      cache(p1, 0) = (if (t1[p1] == t2[0]) 1 else 0) + cache(p1 -1, 0)
     *
     * Normal cases
     * for p1 from 1 to t1.length -1
     *  for p2 from 1 to t2.length -1
     *      cache(p1, p2) = (if (t1[p1] == t2[p2]) 1 else 0) +
     *          max(cache(p1-1, p2), cache(p1, p2-1), cache(p1-1, p2-1))
     *
     * return cache[t1.length - 1][t2.length - 2]
     *
     * Complexity:
     * - Time: O(n*m)
     * - Space: O(n*m)
     */
    override fun longestCommonSubsequence(text1: String, text2: String): Int {
        // Create cache
        val cache = Array(text1.length) { IntArray(text2.length)}

        // Base case
        cache[0][0] = if (text1[0] == text2[0]) 1 else 0

        // First row
        for (p2 in 1 until text2.length) {
            cache[0][p2] =
                if (text1[0] == text2[p2]) 1
                else cache[0][p2 - 1]
        }

        // First column
        for (p1 in 1 until text1.length) {
            cache[p1][0] =
                if (text1[p1] == text2[0]) 1
                else cache[p1 - 1][0]

        }

        // The rest of bases
        for (p1 in 1 until text1.length) {
            for (p2 in 1 until text2.length) {
                if (text1[p1] == text2[p2]) {
                    cache[p1][p2] = 1 + cache[p1 -1][p2 -1]
                } else {
                    cache[p1][p2] = max(cache[p1 -1][p2], cache[p1][p2 - 1])
                }
            }
        }

        return cache[text1.length - 1][text2.length - 1]
    }
}

class LongestCommonSubsequenceTabulationRefactored: LongestCommonSubsequence() {

    /**
     * Since the logic for the first row and for the first column is almost the
     * same as normal case, we can create artificially a first row of 0 and first
     * column of 0, then refactor the code
     * Complexity:
     * - Time: (n*m) * O(1) = O(n*m)
     * - Space: O((n+1)(m+1)) = O(n*m)
     */
    override fun longestCommonSubsequence(text1: String, text2: String): Int {
        // Create cache
        val cache = Array(text1.length + 1) { IntArray(text2.length + 1)}
        // Normal case
        for (p1 in 1 until text1.length + 1) {
            for (p2 in 1 until text2.length + 1) {
                if (text1[p1 - 1] == text2[p2 - 1]) {
                    cache[p1][p2] = 1 + cache[p1 -1][p2 -1]
                } else {
                    cache[p1][p2] = max(cache[p1 -1][p2], cache[p1][p2 - 1])
                }
            }
        }

        return cache[text1.length][text2.length]
    }
}

class LongestCommonSubsequenceTabulationOptimSpace: LongestCommonSubsequence() {

    /**
     * In this case we can use two rows to store the previous data and fill the current data
     *
     */
    override fun longestCommonSubsequence(text1: String, text2: String): Int {
        // Create cache
        var previousRow = IntArray(text2.length + 1)
        var currentRow = IntArray(text2.length + 1)

        // Previous row is already filled with 0
        // We start filling the current row

        // Normal case
        for (p1 in 1 until text1.length + 1) {
            for (p2 in 1 until text2.length + 1) {
                if (text1[p1 - 1] == text2[p2 - 1]) {
                    currentRow[p2] = 1 + previousRow[p2 -1]
                } else {
                    currentRow[p2] = max(previousRow[p2], currentRow[p2 - 1])
                }
            }

            previousRow = currentRow
            currentRow = IntArray(text2.length + 1)
        }

        return previousRow[text2.length]
    }
}
