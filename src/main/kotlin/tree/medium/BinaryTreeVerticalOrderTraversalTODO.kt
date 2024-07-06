package tree.medium

import tree.TreeNode
import tree.easy.ClosestBinarySearchTreeValue

/**
 * Binary Tree Vertical Order Traversal
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example 1:
 *      Input: root = [3,9,20,null,null,15,7]
 *      Output: [[9],[3,15],[20],[7]]
 *
 * Example 2:
 *      Input: root = [3,9,8,4,0,1,7]
 *      Output: [[4],[9],[3,0,1],[8],[7]]
 *
 * Example 3:
 *      Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 *      Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [0, 100].
 *      -100 <= Node.val <= 100
 *
 * Check: This problem is in the paid version of Leet code
 */
class BinaryTreeVerticalOrderTraversalTODO {

    /**
     * Traverse the tree using a stack or a queue
     * each element of the data structure is
     * - an order count
     * - the node itself
     *
     * Starting from root, with the order count 0
     * - every time a left node is added, order count -= 1
     * - every time a right node is added, order count += 1
     *
     * Maintain at the same time a hashMap where
     * - key = order count
     * - value = list of nodes values with such order count
     *
     * How do I return them from left to right?
     * - By keeping two values
     * -> Smallest index
     * -> Biggest index
     * That will be updated everytime we visit a node
     */
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        return listOf(listOf())
    }
}