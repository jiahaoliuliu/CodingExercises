package math.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such
 * that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 * Example 1:
 *      Input: n = 2
 *      Output: 1
 *      Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 * Example 2:
 *      Input: n = 3
 *      Output: 2
 *      Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 *
 * Example 3:
 *      Input: n = 4
 *      Output: 3
 *      Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 * Constraints:
 *      0 <= n <= 30
 */
class FibonacciNumber {

    class FibonacciRecursion {
        /**
         * Using recursion
         * Complexity:
         *   Time: O(phi^n), where phi is 1.618
         *   Space: O(1)
         */
        private fun fib(n: Int): Int {
            if (n <= 0 ) return 0
            if (n == 1) return 1
            return fib(n - 1) + fib(n - 2)
        }

        @Test
        fun test1() {
            // Given
            val n = 2

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test2() {
            // Given
            val n = 3

            // When
            val result = fib(n)

            // Then
            assertEquals(2, result)
        }

        @Test
        fun test3() {
            // Given
            val n = 4

            // When
            val result = fib(n)

            // Then
            assertEquals(3, result)
        }

        @Test
        fun test4() {
            // Given
            val n = 1

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test5() {
            // Given
            val n = 0

            // When
            val result = fib(n)

            // Then
            assertEquals(0, result)
        }
    }

    /**
     * Using memoization
     * Complexity:
     *  Time: number of subproblems * cost of a subproblem
     *      - number of subproblems: f(0), f(1), ..., f(n-1), f(n) = n + 1
     *      - cost of a subproblem: O(1)
     *      (n + 1) * O(1) = O(n)
     *  Space: Space complexity of recursive function + |keys| * size of key/value pair
     *      - S(n) = O(n) + (n + 1) * O(1) = O(n)
     *
     *  Number of the call stack: f(n), f(n -1), ..., f(1) = O(n)
     */
    class FibonacciMemoization {
        private fun fib(n: Int, lookup: HashMap<Int, Int> = HashMap()): Int {
            if (lookup.containsKey(n)) {
                return lookup[n]!!
            }

            if (n == 0) return 0
            if (n == 1) return 1
            lookup[n] = fib(n - 1, lookup) + fib(n - 2, lookup)
            return lookup[n]!!
        }

        @Test
        fun test1() {
            // Given
            val n = 2

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test2() {
            // Given
            val n = 3

            // When
            val result = fib(n)

            // Then
            assertEquals(2, result)
        }

        @Test
        fun test3() {
            // Given
            val n = 4

            // When
            val result = fib(n)

            // Then
            assertEquals(3, result)
        }

        @Test
        fun test4() {
            // Given
            val n = 1

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test5() {
            // Given
            val n = 0

            // When
            val result = fib(n)

            // Then
            assertEquals(0, result)
        }
    }

    /**
     * Using Tabulation
     *
     * We solve first smallest problem, then we solve bigger and bigger problems
     * until we reach to the problem we want to solve
     *
     * Complexity:
     *  - Time: O(n)
     *  - Space: O(n)
     */
    class FibonacciTabulation {
        private fun fib(n: Int): Int {
            val cache = IntArray(n + 1)

            // Base cases
            cache[0] = 0

            if (n > 0) cache[1] = 1

            (2 .. n).forEach {
                cache[it] = cache[it - 1] + cache[it - 2]
            }

            return cache[n]
        }

        @Test
        fun test1() {
            // Given
            val n = 2

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test2() {
            // Given
            val n = 3

            // When
            val result = fib(n)

            // Then
            assertEquals(2, result)
        }

        @Test
        fun test3() {
            // Given
            val n = 4

            // When
            val result = fib(n)

            // Then
            assertEquals(3, result)
        }

        @Test
        fun test4() {
            // Given
            val n = 1

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test5() {
            // Given
            val n = 0

            // When
            val result = fib(n)

            // Then
            assertEquals(0, result)
        }
    }

    /**
     * Using Tabulation
     *
     * We solve first smallest problem, then we solve bigger and bigger problems
     * until we reach to the problem we want to solve
     *
     * We optimize the space complexity by only storing the values we need
     *
     * Complexity:
     *  - Time: O(n)
     *  - Space: O(1)
     */
    class FibonacciTabulationOptimized {
        private fun fib(n: Int): Int {
            if (n == 0) return 0

            var beforePrevious = 0
            var previous = 1
            var current = 1

            repeat((n + 1) - 2) {
                current = beforePrevious + previous
                beforePrevious = previous
                previous = current
            }

            return current
        }

        @Test
        fun test1() {
            // Given
            val n = 2

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test2() {
            // Given
            val n = 3

            // When
            val result = fib(n)

            // Then
            assertEquals(2, result)
        }

        @Test
        fun test3() {
            // Given
            val n = 4

            // When
            val result = fib(n)

            // Then
            assertEquals(3, result)
        }

        @Test
        fun test4() {
            // Given
            val n = 1

            // When
            val result = fib(n)

            // Then
            assertEquals(1, result)
        }

        @Test
        fun test5() {
            // Given
            val n = 0

            // When
            val result = fib(n)

            // Then
            assertEquals(0, result)
        }
    }
}