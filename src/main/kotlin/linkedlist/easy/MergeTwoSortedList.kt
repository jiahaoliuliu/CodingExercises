package linkedlist.easy

import linkedlist.ListNode
import linkedlist.checkValues
import linkedlist.toLinkedList
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Merge two sorted list
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the
 * first two lists.
 * Return the head of the merged linked list.
 * Example 1:
 *      Input: list1 = [1,2,4], list2 = [1,3,4]
 *      Output: [1,1,2,3,4,4]
 *
 * Example 2:
 *      Input: list1 = [], list2 = []
 *      Output: []
 *
 * Example 3:
 *      Input: list1 = [], list2 = [0]
 *      Output: [0]
 *
 * Constraints:
 *      The number of nodes in both lists is in the range [0, 50].
 *      -100 <= Node.val <= 100
 *      Both list1 and list2 are sorted in non-decreasing order.
 */
abstract class MergeTwoSortedList {
    abstract fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode?

    @Test
    fun testCornerCases_listsWithSameSize() {
        // Given
        val list1 = listOf(1, 2, 4)
        val head1 = list1.toLinkedList()

        val list2 = listOf(1, 3, 4)
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        assertTrue(result.checkValues(listOf(1,1,2,3,4,4)))
    }

    @Test
    fun testCornerCases_listsWithDifferentSize1() {
        // Given
        val list1 = listOf(1, 2, 4, 5)
        val head1 = list1.toLinkedList()

        val list2 = listOf(1, 3, 4)
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        assertTrue(result.checkValues(listOf(1,1,2,3,4,4,5)))
    }

    @Test
    fun testCornerCases_listsWithDifferentSize2() {
        // Given
        val list1 = listOf(1, 2, 4)
        val head1 = list1.toLinkedList()

        val list2 = listOf(1, 3, 4, 5)
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        assertTrue(result.checkValues(listOf(1,1,2,3,4,4,5)))
    }

    @Test
    fun testCornerCases_BothEmptyList() {
        // Given
        val list1 = listOf<Int>()
        val head1 = list1.toLinkedList()

        val list2 = listOf<Int>()
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        assertTrue(result.checkValues(listOf()))
    }

    @Test
    fun testCornerCases_List1Empty() {
        // Given
        val list1 = listOf<Int>()
        val head1 = list1.toLinkedList()

        val list2 = listOf(0, 1, 2, 3)
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        result.checkValues(listOf(0, 1, 2, 3))
    }

    @Test
    fun testCornerCases_List2Empty() {
        // Given
        val list1 = listOf<Int>(0, 1, 2, 3)
        val head1 = list1.toLinkedList()

        val list2 = listOf<Int>()
        val head2 = list2.toLinkedList()

        // When
        val result = mergeTwoLists(head1, head2)

        // Then
        result.checkValues(listOf(0, 1, 2, 3))
    }

}

class MergeTwoSortedListImpl : MergeTwoSortedList() {
    /**
     * Initial thoughts
     *
     * While list1 && list2 are not null
     * - if (list1 is null)
     *      append list2
     *      advance list2
     * - else if (list2 is null)
     *      append list1
     *      advance list1
     * - else
     *      get the value of list1
     *      get the value of list2
     *      if (list1(value) > list2(value)
     *          append list1
     *          advance list1
     *      else
     *          append list2
     *          advance list2
     *
     * return solution
     *
     * Small optimization
     *
     * While list1 && list2 are not null
     * // If list 1 is not null, then list 2 cannot be null
     * - if (list1 is null || list1.value < list2.value)
     *      append list2L
     *      advance list2
     * // If list 2 is not null, then list 1 cannot be null
     * - else if (list2 is null || list1.value >= list2.value)
     *      append list1
     *      advance list1
     * return solution
     *
     * A better solution
     * 1. create a while loop where both list1 and list 2 are not null
     *      append the items that is needed
     *      advance the corresponding list
     * 2. check if list1 is null
     *      append list 2 to the solution
     * 3. check if list 2 is null
     *      append list 1 to the solution
     * Since those are linked list, this solution will skip all the iteration from the moment when
     * one of the list is null
     */
    override fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        // 1. Init the values
        var head: ListNode? = null
        var current: ListNode? = null
        var next:ListNode? = null

        // Since the parameter of the function cannot be updated, the algo uses
        // two extra parameters
        var currentList1 = list1
        var currentList2 = list2

        while (currentList1 != null && currentList2 != null) {
            if (currentList1.`val` < currentList2.`val`) {
                next = currentList1
                currentList1 = currentList1.next
            } else { // current list 1 val >= currentList2.val
                next = currentList2
                currentList2 = currentList2.next
            }
            if (head == null) {
                head = next
            }

            // Update current
            if (current == null) {
                current = next
            } else {
                current.next = next
                current = current.next
            }
        }

        if (currentList1 == null) {
            next = currentList2
            if (head == null) {
                head = next
            }
            current?.next = next
        }

        if (currentList2 == null) {
            next = currentList1
            if (head == null) {
                head = next
            }
            current?.next = next
        }

        return head
    }
}

class MergeTwoSortedListOptimalImpl : MergeTwoSortedList() {
    /**
     * Algo
     *
     * 1. Create a dummy node
     * 2. Create a tail that is pointing to the dummy node
     *
     * 3. While (list1 has element and list 2 has element)
     *      if (list1.value < list2.value)
     *          tail.next = list1
     *          list1 = list1.next
     *      else
     *          tail.next = list2
     *          list2 = list2.next
     *      // advance tail
     *      tail = tail.next
     *
     * 4. If list 1 is empty
     *      tail.next = list1
     *
     * 5. If list 2 is empty
     *      tail.next = list2
     *
     * 6. return dummy.next
     *
     * The key is have a sentinel node, then have the tail pointing to it
     * Then work always with tail.next
     */
    override fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        // 1. Init the values
        val sentinel = ListNode(0)
        var tail:ListNode? = sentinel

        // Since the parameter of the function cannot be updated, the algo uses
        // two extra parameters
        var currentList1 = list1
        var currentList2 = list2

        while (currentList1 != null && currentList2 != null) {
            if (currentList1.`val` < currentList2.`val`) {
                tail?.next = currentList1
                currentList1 = currentList1.next
            } else { // current list 1 val >= currentList2.val
                tail?.next = currentList2
                currentList2 = currentList2.next
            }

            // Update current
            tail = tail?.next
        }

        if (currentList1 == null) {
            tail?.next = currentList2
        }

        if (currentList2 == null) {
            tail?.next = currentList1
        }

        return sentinel.next
    }
}