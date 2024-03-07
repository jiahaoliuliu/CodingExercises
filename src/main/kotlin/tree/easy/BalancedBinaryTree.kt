package tree.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import tree.TreeNode
import tree.toBinaryTree
import java.lang.Math.max
import kotlin.math.abs

/**
 * Balanced binary tree
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 *  A binary tree is considered as balanced if its left and right subtree are balanced, and the difference between
 *  their heights is at most 1
 *
 * Example 1:
 *      Input: root = [3,9,20,null,null,15,7]
 *      Output: true
 *
 * Example 2:
 *      Input: root = [1,2,2,3,3,null,null,4,4]
 *      Output: false
 *
 * Example 3:
 *      Input: root = []
 *      Output: true
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [0, 5000].
 *      -10^4 <= Node.val <= 10^4
 */
abstract class BalancedBinaryTree {
    abstract fun isBalanced(root: TreeNode?): Boolean

    @Test
    fun test1() {
        // Given
        val list = listOf(3,9,20,null,null,15,7)
        val root = list.toBinaryTree()

        // When
        val result = isBalanced(root)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val list = listOf(1,2,2,3,3,null,null,4,4)
        val root = list.toBinaryTree()

        // When
        val result = isBalanced(root)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val list = listOf<Int?>()
        val root = list.toBinaryTree()

        // When
        val result = isBalanced(root)

        // Then
        assertTrue(result)
    }
}

class BalancedBinaryTreeImpl: BalancedBinaryTree() {

    /**
     * The algorithm, at the same time it calculates if the left and right tree are balanced
     * it needs to calculate the height of the left tree and right tree recursively
     *
     * Corner case
     * if the tree is null, then tree is balanced
     *
     * How to return the height of the tree at the same check if it is balanced?
     *
     * Complexity:
     * - Time:
     *  - Best case scenario (Balanced tree): T(0) = 1, T(n) = 2T(n/2) + n = O(n log n)
     *  - Worse case scenario (Skew): O(n)
     * - Space:
     *  - Worse case scenario (Skew): O(h)
     *
     */
    override fun isBalanced(root: TreeNode?): Boolean {
        // Corner case
        if (root == null) return true

        val leftHeight = calculateHeight(root.left)
        val rightHeight = calculateHeight(root.right)

        return abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)
    }

    private fun calculateHeight(root: TreeNode?): Int {
        if (root == null) return 0

        return 1 + kotlin.math.max(calculateHeight(root.left), calculateHeight(root.right))
    }
}

class BalancedBinaryTreeOptim: BalancedBinaryTree() {

    /**
     * At the same time it is calculating the height of the tree, if any of the subtree is unbalanced
     * return -1, as the height of a tree will never be -1
     *
     */
    override fun isBalanced(root: TreeNode?): Boolean {
        return calculateHeight(root) != -1
    }

    private fun calculateHeight(root: TreeNode?): Int {
        if (root == null) return 0

        val leftHeight = calculateHeight(root.left)
        val rightHeight = calculateHeight(root.right)

        if (leftHeight == -1 || rightHeight == -1) return -1
        if (abs(leftHeight - rightHeight) > 1) return -1

        return 1 + kotlin.math.max(calculateHeight(root.left), calculateHeight(root.right))
    }
}