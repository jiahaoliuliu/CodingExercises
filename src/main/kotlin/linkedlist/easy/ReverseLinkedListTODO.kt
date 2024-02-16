package linkedlist.easy

import linkedlist.ListNode

/**
 * Reverse linked list
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 *      Input: head = [1,2,3,4,5]
 *      Output: [5,4,3,2,1]
 *
 * Example 2:
 *      Input: head = [1,2]
 *      Output: [2,1]
 *
 * Example 3:
 *      Input: head = []
 *      Output: []
 *
 * Constraints:
 *      The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
class ReverseLinkedListTODO {

    /**
     * Initial though
     *
     * Have two pointer
     * - Prev node = 0
     * - Current node = current node
     * - Next node = current node.next
     *
     * while the next node is not null
     * -> Set the next node to next node
     * -> point the current node to the previous node
     * -> Set the previous node to the current node
     * -> Set the current node to next node
     */
    fun reverseList(head: ListNode?): ListNode? {
        return null
    }
}