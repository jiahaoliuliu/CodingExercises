package tree.easy

import tree.TreeNode

/**
 * Find mode in binary search tree
 *
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most
 * frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *      Input: root = [1,null,2,2]
 *      Output: [2]
 *
 * Example 2:
 *      Input: root = [0]
 *      Output: [0]
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [1, 10^4].
 *      -10^5 <= Node.val <= 10^5
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space
 * incurred due to recursion does not count).
 *
 */
class FindModeInBinarySearchTreeTODO {

    /**
     * Solution 1: DFS or BFS with frequency map
     * - Keep a value for the maximum frequency
     *
     * Then look into the frequency map for the values with that frequency
     *
     * Solution 2: Since it is a binary tree
     * where the values on the left could be less or equal than the root
     * and the value on the right could be equal or more than the root
     * 1. Going to the leftest part of the node recursively
     * 2. Add the value to an array
     * The array will have all the elements sorted from small to big
     *
     * 3. Use a counter for the max
     * - If any value == value on the left. Counter ++
     * - Use a variable to keep the max
     * - Use an array to store the values of the same max
     * - If new max appears, reset result array
     *
     * 4. Return result array
     */
    private fun findMode(root: TreeNode?): IntArray {
        return intArrayOf()
    }
}