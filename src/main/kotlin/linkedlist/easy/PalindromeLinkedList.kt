package linkedlist.easy

import linkedlist.ListNode
import linkedlist.toLinkedList
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Palindrome linked list
 *
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 * Example 1:
 *      Input: head = [1,2,2,1]
 *      Output: true
 *
 * Example 2:
 *      Input: head = [1,2]
 *      Output: false
 *
 * Constraints:
 *      The number of nodes in the list is in the range [1, 10^5].
 *      0 <= Node.val <= 9
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 */
abstract class PalindromeLinkedList {

    abstract fun isPalindrome(head: ListNode?): Boolean

    @Test
    fun test1() {
        // Given
        val list = listOf(1,2,2,1)
        val head = list.toLinkedList()

        // When
        val result = isPalindrome(head)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val list = listOf(1,2)
        val head = list.toLinkedList()

        // When
        val result = isPalindrome(head)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val list = listOf(5, 4, 7, 7, 4, 5)
        val head = list.toLinkedList()

        // When
        val result = isPalindrome(head)

        // Then
        assertTrue(result)
    }

}

class PalindromeLinkedListImpl: PalindromeLinkedList() {

    /**
     * 1. Find the middle of the list
     * 2. Reverse the list
     * 3. Check the content from head and tail
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     */
    override fun isPalindrome(head: ListNode?): Boolean {
        // 1. Find the middle of the list
        var slowPointer = head
        var fastPointer = head

        while (fastPointer?.next != null ) {
            slowPointer = slowPointer?.next
            fastPointer = fastPointer.next?.next
        }

        // 2. Reverse linked list
        var tail = reverseLinkedList(slowPointer)

        var myHead = head

        // 3. Check
        while (tail != null) {
            if (myHead?.`val` != tail.`val`) {
                return false
            }
            myHead = myHead.next
            tail = tail.next
        }
        return true
    }

    /**
     * Reverse linked list
     */
    private fun reverseLinkedList(middle: ListNode?): ListNode? {
        // Corner case
        if (middle == null) return null

        if (middle.next == null) return middle

        // Init the variables
        var previous:ListNode? = null
        var current = middle

        // Iterate
        while (current != null) {
            val next = current.next
            current.next = previous
            previous = current
            current = next
        }

        return previous
    }

}