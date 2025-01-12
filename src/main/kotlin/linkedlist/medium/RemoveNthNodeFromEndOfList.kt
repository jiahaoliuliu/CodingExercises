package linkedlist.medium

import linkedlist.ListNode
import linkedlist.checkValues
import linkedlist.toLinkedList
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * - Input: head = [1,2,3,4,5], n = 2
 * - Output: [1,2,3,5]
 *
 * Example 2:
 * - Input: head = [1], n = 1
 * - Output: []
 *
 * Example 3:
 * - Input: head = [1,2], n = 1
 * - Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is sz.
 * - 1 <= sz <= 30
 * - 0 <= Node.val <= 100
 * - 1 <= n <= sz
 *
 * Follow up: Could you do this in one pass?
 */
abstract class RemoveNthNodeFromEndOfList {

    abstract fun removeNthFromEnd(head: ListNode?, n: Int): ListNode?

    @ParameterizedTest(name = "After removing {1} from {0} it should be {2}")
    @MethodSource("getData")
    fun test(head: List<Int>, n: Int, expectedValue: List<Int>) {
        val result = removeNthFromEnd(head.toLinkedList(), n)
        assertTrue(result.checkValues(expectedValue))
    }

    companion object {
        @JvmStatic
        fun getData(): List<Array<Any?>> {
            return listOf(
                arrayOf(listOf(1, 2, 3, 4, 5), 2, listOf(1, 2, 3, 5)),
                arrayOf(listOf(1), 1, emptyList<Int>()),
                arrayOf(listOf(1, 2), 1, listOf(1)),
            )
        }
    }
}

class RemoveNthNodeFromEndOfListImpl: RemoveNthNodeFromEndOfList() {

    /*
     * Initial thoughts
     * We can have two pointers:
     * 1. Start the pointer. Move it N positions
     * 2. Start the second pointer pointing to the head
     * 3. Move both of the pointers until the first pointer reaches to the end of the linked list
     * 4. Remove the node pointed by the second pointer
     * 5. Return head
     *
     * Corner case:
     * - On the step 1. If it reaches to null. Then return head
     */
    override fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // n = 2 1 0 -1
        // p1
        //  |
        // [1] -> [2] -> [3] -> [4] -> [5]
        //  |
        // p2
        // 1. Start the pointer. Move it N positions

        // n = 1 0
        // p1
        //              |
        // [1] -> [2]
        //
        // p2
        var pointerToEnd = head
        var nthSteps = n
        while (nthSteps >= 0 && pointerToEnd != null) {
            pointerToEnd = pointerToEnd.next
            nthSteps--
        }

        // Corner case
        if (pointerToEnd == null && nthSteps != -1) {
            return if (nthSteps == 0) {
                // remove the current node
                head?.next
            } else {
                head
            }
        }

        // 2. Start the second pointer pointing to the head
        var pointerToRemoveNode = head

        // 3. Move both of the pointers until the first pointer reaches to the end of the linked list
        while (pointerToEnd != null) {
            pointerToRemoveNode = pointerToRemoveNode?.next
            pointerToEnd = pointerToEnd.next
        }

        // 4. Remove the next node pointed by the second pointer
        pointerToRemoveNode?.next = pointerToRemoveNode?.next?.next

        // 5. Return head
        return head
    }
}

class RemoveNthNodeFromEndOfListOptim: RemoveNthNodeFromEndOfList() {
    override fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 1. Create a dummy node as result
        val dummyNode = ListNode(-1)
        dummyNode.next = head

        // 2. Initialize the pointers
        var slowPointer: ListNode? = dummyNode
        var fastPointer = head

        // 3. Move the fast pointer n positions
        for (i in 0 until n) {
            fastPointer = fastPointer?.next
        }

        // 4. Moving the fast pointer and slow pointer at the same time
        while (fastPointer != null) {
            slowPointer = slowPointer?.next
            fastPointer = fastPointer.next
        }

        // 5. Remove the nth node
        slowPointer?.next = slowPointer?.next?.next
        return dummyNode.next
    }

}