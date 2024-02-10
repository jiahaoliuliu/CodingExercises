package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * First Bad version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest
 * version of your product fails the quality check. Since each version is developed based on the previous version,
 * all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 *
 * Example 1:
 *      Input: n = 5, bad = 4
 *      Output: 4
 *      Explanation:
 *          call isBadVersion(3) -> false
 *          call isBadVersion(5) -> true
 *          call isBadVersion(4) -> true
 *          Then 4 is the first bad version.
 *
 * Example 2:
 *      Input: n = 1, bad = 1
 *      Output: 1
 *
 * Constraints:
 *      1 <= bad <= n <= 2^31 - 1
 */
class FirstBadVersionImpl(
    totalNumberOfVersions: Int,
    badVersion: Int): FirstBadVersion(totalNumberOfVersions, badVersion) {

    override fun firstBadVersion(n: Int): Int {
        return findBadVersion(1, n)
    }

    private fun findBadVersion(start: Int, end: Int): Int {
        // empty cases
        return if (start == end) {
            start
        } else {
            val middle = start + ((end - start) / 2)
            if (isBadVersion(middle)) {
                findBadVersion(start, middle)
            } else {
                findBadVersion(middle + 1, end)
            }
        }
    }

    override fun firstBadVersionOptimal(n: Int) : Int {
        var left = 0
        var right = n
        while (left <= right) {
            val version = left + ((right - left) / 2)
            if (isBadVersion(version)) right = version - 1
            else left = version + 1
        }
        return left
    }
}

class Testing() {
    @Test
    fun test1() {
        // Given
        val totalNumberOfVersions = 5
        val badVersion = 4
        val firstBadVersion = FirstBadVersionImpl(totalNumberOfVersions, badVersion)

        // When
        val result = firstBadVersion.check()
        val resultOptimal = firstBadVersion.checkOptimal()

        // Then
        assertEquals(badVersion, result)
        assertEquals(badVersion, resultOptimal)
    }

    @Test
    fun test2() {
        // Given
        val totalNumberOfVersions = 1
        val badVersion = 1
        val firstBadVersion = FirstBadVersionImpl(totalNumberOfVersions, badVersion)

        // When
        val result = firstBadVersion.check()
        val resultOptimal = firstBadVersion.checkOptimal()

        // Then
        assertEquals(badVersion, result)
        assertEquals(badVersion, resultOptimal)
    }
}

abstract class FirstBadVersion(private val totalNumberOfVersions: Int, private val badVersion: Int) {

    abstract fun firstBadVersion(n: Int): Int

    abstract fun firstBadVersionOptimal(n: Int): Int

    protected fun isBadVersion(version: Int): Boolean {
        return version == badVersion
    }

    fun check() = firstBadVersion(totalNumberOfVersions)

    fun checkOptimal() = firstBadVersionOptimal(totalNumberOfVersions)
}