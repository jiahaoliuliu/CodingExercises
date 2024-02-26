package linkedlist.easy

import linkedlist.ListNode
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Convert Binary Number in a Linked List to Integer
 *
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked
 * list is either 0 or 1. The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 * The most significant bit is at the head of the linked list.
 *
 * Example 1:
 *      Input: head = [1,0,1]
 *      Output: 5
 *      Explanation: (101) in base 2 = (5) in base 10
 *
 * Example 2:
 *      Input: head = [0]
 *      Output: 0
 *
 * Constraints:
 *      The Linked List is not empty.
 *      Number of nodes will not exceed 30.
 *      Each node's value is either 0 or 1.
 */
abstract class ConvertBinaryNumberInALinkedListToInteger {

    abstract fun getDecimalValue(head: ListNode?): Int

    @Test
    fun test1() {
        // Given
        val head = ListNode(1)
        val second = ListNode(0)
        head.next= second

        val third = ListNode(1)
        second.next = third

        // When
        val result = getDecimalValue(head)

        // Then
        assertEquals(5, result)
    }

    @Test
    fun test2() {
        // Given
        val head = ListNode(0)

        // When
        val result = getDecimalValue(head)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test3() {
        // Given
        val head = ListNode(1)
        val second = ListNode(0)
        head.next= second

        val third = ListNode(0)
        second.next = third

        // When
        val result = getDecimalValue(head)

        // Then
        assertEquals(4, result)
    }
}

class ConvertBinaryNumberInALinkedListToIntegerImpl: ConvertBinaryNumberInALinkedListToInteger() {

    /**
     * Initial thoughts
     * 101 = 1 * 2^2 + 0 * 2^1 + 1 * 2^0
     *     = 4 + 0 + 1
     * Iteratively
     * - 1: 1
     * - 0: 1 * 2 + 0 = 2
     * - 1: 2 * 2 + 1 = 5
     * The general rule is
     *
     * Example 2: 1101 ->
     * - 1: 0 * 2 + 1 = 1
     * - 1: 1*2 + 1 = 3
     * - 0: 3*2 + 0 = 6
     * - 1: 6 * 2 + 1 = 13
     *
     * Result(-1) = 0
     * Result(n) = result(n -1) * 2 + currentNumber
     *
     */
    override fun getDecimalValue(head: ListNode?): Int {
        // 1. Init the variable
        var result = 0
        var cur = head

        // 2. Loop
        while (cur != null) {
            result = result * 2 + cur.`val`
            cur = cur.next
        }

        // 3. Return the result
        return result
    }
}