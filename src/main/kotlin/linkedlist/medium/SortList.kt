package linkedlist.medium

import linkedlist.ListNode
import linkedlist.checkValues
import linkedlist.toLinkedList
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Comparator
import java.util.PriorityQueue

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Example 1:
 *      Input: head = [4,2,1,3]
 *      Output: [1,2,3,4]
 *
 * Example 2:
 *      Input: head = [-1,5,3,4,0]
 *      Output: [-1,0,3,4,5]
 *
 * Example 3:
 *      Input: head = []
 *      Output: []
 *
 * Constraints:
 *      The number of nodes in the list is in the range [0, 5 * 10^4].
 *      -10^5 <= Node.val <= 10^5
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 */
abstract class SortList {
    abstract fun sortList(head: ListNode?): ListNode?

    @Test
    fun test1() {
        // Given
        val list = listOf(4,2,1,3)
        val head = list.toLinkedList()

        // When
        val result = sortList(head)

        // Then
        assertTrue(result.checkValues(listOf(1, 2, 3, 4)))
    }

    @Test
    fun test2() {
        // Given
        val list = listOf(-1,5,3,4,0)
        val head = list.toLinkedList()

        // When
        val result = sortList(head)

        // Then
        assertTrue(result.checkValues(listOf(-1,0,3,4,5)))
    }

    @Test
    fun test3() {
        // Given
        val list = listOf<Int>()
        val head = list.toLinkedList()

        // When
        val result = sortList(head)

        // Then
        assertTrue(result.checkValues(listOf()))
    }
}

class SortListImpl: SortList() {

    /**
     * Initial thoughts
     *
     * We can use a min heap to sort the items and pop it out one by one
     *
     */
    override fun sortList(head: ListNode?): ListNode? {
        // 1. Init the value
        val listNodeComparator : Comparator<ListNode> = compareBy { it.`val` }
        val minHeap = PriorityQueue(listNodeComparator)
        var myHead = head

        // 2. Add the values
        while(myHead != null) {
            val tmp = myHead
            myHead = myHead.next
            // Reset tmp head
            tmp.next = null
            minHeap.add(tmp)
        }

        // 3. Extract the items one by one
        var resultHead: ListNode? = null
        if (minHeap.isNotEmpty()) {
            resultHead = minHeap.remove()
        }

        var iterator = resultHead

        while(minHeap.isNotEmpty()) {
            iterator?.next = minHeap.remove()
            iterator = iterator?.next
        }

        return resultHead
    }
}

class SortListBubbleSort: SortList() {

    /**
     * Using bubble sort for to sort the elements from the back to the front
     * The biggest element will be moved to the back
     *
     * i = 0
     * while j < len(array)
     *  j = 0
     *  while (j + 1) < len(array)
     *      if (arr[j] > arr[j + 1])
     *          arr[j], arr[j+1] = arr[j+1], arr[j]
     *      j += 1
     *  i += 1
     */
    override fun sortList(head: ListNode?): ListNode? {
        var i = head
        while (head != null && i != null) {
            var j = head
            while (j?.next != null) {
                if (j.`val` > j.next!!.`val`) {
                    val tmp = j.`val`
                    j.`val` = j.next!!.`val`
                    j.next!!.`val` = tmp
                }
                j = j.next
            }
            i = i.next
        }

        return head
    }
}

class SortListMergeSort: SortList() {

    /**
     * This sort has two parts
     * 1. Split the linked list into two parts
     * 2. Repeat the steps 1 recursively until both list have one single element
     *
     * 3. Once they have one single element, merge both sorted list into one single list
     * 4. Repeat the steps until there is only one list left
     *
     */
    override fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        // Use the fast and slow pointers to split the list into halfv
        var beforeSlowPointer: ListNode? = null
        var slowPointer = head
        var fastPointer = head

        while (fastPointer?.next != null) {
            beforeSlowPointer = slowPointer
            slowPointer = slowPointer?.next
            fastPointer = fastPointer.next?.next
        }

        // Rest the pointer before slow pointer
        beforeSlowPointer?.next = null

        val left = sortList(head)
        val right = sortList(slowPointer)

        return merge(left, right)
    }

    private fun merge(head1: ListNode?, head2: ListNode?): ListNode? {
        val sentinel = ListNode(0)
        var iterator = sentinel

        var currentList1 = head1
        var currentList2 = head2

        while(currentList1 != null && currentList2 != null) {
            if (currentList1.`val` > currentList2.`val`) {
                iterator.next = currentList2
                iterator = currentList2
                currentList2 = currentList2.next
            } else {
                iterator.next = currentList1
                iterator = currentList1
                currentList1 = currentList1.next
            }
        }

        if (currentList1 == null) {
            iterator.next = currentList2
        }

        if (currentList2 == null) {
            iterator.next = currentList1
        }

        return sentinel.next
    }
}

