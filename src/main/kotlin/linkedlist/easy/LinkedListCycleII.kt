package linkedlist.easy

import linkedlist.ListNode
import linkedlist.toLinkedList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Linked list cycle II
 *
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle,
 * return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the node that
 * tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not
 * passed as a parameter.
 *
 * Do not modify the linked list.
 * Example 1:
 *      Input: head = [3,2,0,-4], pos = 1
 *      Output: tail connects to node index 1
 *      Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 *      Input: head = [1,2], pos = 0
 *      Output: tail connects to node index 0
 *      Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 *      Input: head = [1], pos = -1
 *      Output: no cycle
 *      Explanation: There is no cycle in the linked list.
 *
 * Constraints:
 *      The number of the nodes in the list is in the range [0, 10^4].
 *      -10^5 <= Node.val <= 10^5
 *      pos is -1 or a valid index in the linked-list.
 */
abstract class LinkedListCycleII {

    abstract fun detectCycle(head: ListNode?): ListNode?

    @Test
    fun testCornerCase_1Node() {
        // Given
        val list = listOf(1)
        val head = list.toLinkedList()

        // When
        val result = detectCycle(head)

        // Then
        assertNull(result)
    }

    @Test
    fun testNormal1() {
        // Given
        val list = listOf(1, 2)
        val head = list.toLinkedList()
        // Create cycle
        head?.next?.next = head

        // When
        val result = detectCycle(head)

        // Then
        assertEquals(head?.next?.next, result)
    }

    @Test
    fun testNormal2() {
        // Given
        val list = listOf(1, 2, 3, 4)
        val head = list.toLinkedList()
        // Create cycle
        head?.next?.next?.next?.next = head?.next
        // [1] -> [2] -> [3] -> [4]---|  // cycle length = 3
        //         ^-------------------
        // When
        val result = detectCycle(head)

        // Then
        assertEquals(head?.next?.next?.next?.next, result)
    }
}

class LinkedListCycleIIImpl: LinkedListCycleII() {

    /**
     * Initial thoughts
     *
     * 1. Use fast and slow pointer to know if there is any cycle
     *
     * 2.1 If there is any cycle, we can detect the cycle length
     * [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> [2]
     * cycle length = 5
     *
     * If we advance from head 5 positions = [5]
     * Length = head + cycle length
     *
     * 2.2 Whatever left is the length of the head. We need to advance head times to find the head of the cycle
     *
     * 2.3 Once we know where the cycle starts and how many items is in the cycle, the algo advances
     * cycleLength times to find the tail
     *
     */
    override fun detectCycle(head: ListNode?): ListNode? {
        // 1. Init the variables
        var slow = head
        var fast = head

        // 2. Loop
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            // If they met at some point
            if (slow == fast) {
                // 2.1 Calculate the cycle
                var cycleLength = 0
                do {
                    slow = slow?.next
                    cycleLength++
                } while (slow != fast)

                // 2.2 Detects the start of the cycle
                var cycleAdvancedIterator = head
                var cycleLengthForIterator = cycleLength
                while (cycleLengthForIterator-- > 0) {
                    cycleAdvancedIterator = cycleAdvancedIterator?.next
                }

                var iterator = head
                while( iterator != cycleAdvancedIterator) {
                    iterator = iterator?.next
                    cycleAdvancedIterator = cycleAdvancedIterator?.next
                }

                // 2.3 Advance from iterator the length of the cycle to find the tail
                while (cycleLength-- > 0) {
                    iterator = iterator?.next
                }

                return iterator
            }
        }

        // 3. Return the default value
        return null
    }
}

class LinkedListCycleIIOptimImpl: LinkedListCycleII() {

    /**
     * https://leetcode.com/problems/linked-list-cycle-ii/discuss/4757653/Explicacion-y-Resolucion-Del-Ejercicio-En-Espanol-Java-or-Python-or-PHP-or-JavaScript-or-C%2B%2B
     * https://www.youtube.com/watch?v=rFb9wCzdZQw
     *
     * 1. Use fast and slow pointer to know if there is any cycle
     *
     * 2. From the position where the pointer meets, restart slow pointer
     * and advance slow pointer and fast pointer at the same time
     *
     * 3. Once they meet, that's the end of the cycle
     *
     */
    override fun detectCycle(head: ListNode?): ListNode? {
        // 1. Init the variables
        var slow = head
        var fast = head

        // 2. Loop
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            // If they met at some point
            if (slow == fast) {
                slow = head

                while (slow != fast) {
                    slow = slow?.next
                    fast = fast?.next
                }

                return slow
            }
        }

        // 3. Return the default value
        return null
    }

}