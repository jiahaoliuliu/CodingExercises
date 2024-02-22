package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*


/**
 * Assign cookies
 *
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give
 * each child at most one cookie.
 *
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be
 * content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the
 * child i, and the child i will be content. Your goal is to maximize the number of your content children
 * and output the maximum number.
 *
 * Example 1:
 *      Input: g = [1,2,3], s = [1,1]
 *      Output: 1
 *      Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 *      And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 *      You need to output 1.
 *
 * Example 2:
 *      Input: g = [1,2], s = [1,2,3]
 *      Output: 2
 *      Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 *      You have 3 cookies and their sizes are big enough to gratify all of the children,
 *      You need to output 2.
 *
 * Constraints:
 *      1 <= g.length <= 3 * 10^4
 *      0 <= s.length <= 3 * 10^4
 *      1 <= g[i], s[j] <= 2^31 - 1
 */
abstract class AssignCookies {

    abstract fun findContentChildren(g: IntArray, s: IntArray): Int

    @Test
    fun test1() {
        // Given
        val g = intArrayOf(1,2,3)
        val s = intArrayOf(1,1)

        // When
        val result = findContentChildren(g, s)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        // Given
        val g = intArrayOf(1,2)
        val s = intArrayOf(1,2, 3)

        // When
        val result = findContentChildren(g, s)

        // Then
        assertEquals(2, result)
    }
}

class AssignCookiesImpl: AssignCookies() {

    /**
     * Initial thoughts
     *
     * ie. g = 1, 2, 3  s = 2, 1
     * Brute force:
     * It is about the number of cookies to satisfy the maximum number of children
     * Both g and s needs to be sorted
     *
     * g sorted from bigger to smaller g = 3, 2, 1
     * s sorted from smaller to bigger s = 1, 2
     *
     * for each element of g (3, 2, 1), find the smallest number possible of s that
     * can satisfy him
     *
     * If so, mark the cookie as -1 and increase the result
     *
     * Complexity:
     * - Time: O(n^2)
     * - Space: O(1)
     */
    override fun findContentChildren(g: IntArray, s: IntArray): Int {
        // 1. Prepare the data
        g.sortDescending()
        s.sort()
        var happyChild = 0

        // 2. Loop
        g.forEach { children ->
            s.forEachIndexed { index, cookie ->
                if (cookie >= children) {
                    s[index] = -1
                    happyChild++
                    return@forEach
                }
            }
        }

        // 3. Return result
        return happyChild
    }
}

class AssignCookiesOptim: AssignCookies() {

    /**
     * Initial thoughts
     *
     * Use two pointers
     */
    override fun findContentChildren(g: IntArray, s: IntArray): Int {
        Arrays.sort(g)
        Arrays.sort(s)
        var contentChildren = 0
        var cookieIndex = 0
        while (cookieIndex < s.size && contentChildren < g.size) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++
            }
            cookieIndex++
        }
        return contentChildren
    }
}