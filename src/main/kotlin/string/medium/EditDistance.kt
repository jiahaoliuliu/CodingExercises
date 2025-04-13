package string.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.min

class EditDistance {

    private fun distance(word1: String, word2: String): Int {
        val n = word1.length
        val m = word2.length
        val dp = Array(n+1) { IntArray(m+1) }

        for (j in 1 .. m) {
            dp[0][j] = j
        }

        for (i in 1 .. n) {
            dp[i][0] = i
        }

        for (i in 1 .. n) {
            for (j in 1 .. m) {
                if (word1[i-1] == word2[j-1]) {
                    dp[i][j]=dp[i - 1][j - 1]
                } else {
                    var min = min(dp[i - 1][j], dp[i][j-1])
                    min = min(min, dp[i-1][j-1])
                    dp[i][j] = 1 + min
                }
            }
        }
        return dp[n][m]
    }

    @Test
    fun test1() {
        // Given
        val word1 = "inside"
        val word2 = "index"

        // When
        val result = distance(word1, word2)

        // Then
        assertEquals(3, result)

    }
}