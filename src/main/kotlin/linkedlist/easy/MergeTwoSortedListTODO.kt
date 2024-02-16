package linkedlist.easy

import linkedlist.ListNode

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
class MergeTwoSortedListTODO {

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
    private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        return null
    }
}