package tree.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode
import tree.toBinaryTree
import kotlin.math.min

/**
 * Lowest Common Ancestor of a Binary Tree
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *      Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *      Output: 3
 *      Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *      Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *      Output: 5
 *      Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA
 *      definition.
 *
 * Example 3:
 *      Input: root = [1,2], p = 1, q = 2
 *      Output: 1
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [2, 10^5].
 *      -10^9 <= Node.val <= 10^9
 *      All Node.val are unique.
 *      p != q
 *      p and q will exist in the tree.
 */
abstract class LowestCommonAncestorOfABinaryTree {

    abstract fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode?

    @Test
    fun test1() {
        // Given
        val list = listOf(3,5,1,6,2,0,8,null,null,7,4)
        val root = list.toBinaryTree()
        val p = root?.left
        val q = root?.right

        // When
        val result = lowestCommonAncestor(root, p, q)

        // Then
        assertEquals(3, result?.`val`)
    }

    @Test
    fun test2() {
        // Given
        val list = listOf(3,5,1,6,2,0,8,null,null,7,4)
        val root = list.toBinaryTree()
        val p = root?.left
        val q = root?.left?.right?.right

        // When
        val result = lowestCommonAncestor(root, p, q)

        // Then
        assertEquals(5, result?.`val`)
    }

    @Test
    fun test3() {
        // Given
        val list = listOf(1, 2)
        val root = list.toBinaryTree()
        val p = root
        val q = root?.left

        // When
        val result = lowestCommonAncestor(root, p, q)

        // Then
        assertEquals(1, result?.`val`)
    }
}

class LowestCommonAncestorOfABinaryTreeSol1: LowestCommonAncestorOfABinaryTree() {

    /**
     * Complexity:
     * - Time: O(n)
     * - Space: O(h)
     */
    override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null

        val pathNumber1 = mutableListOf<TreeNode>()
        val pathNumber2 = mutableListOf<TreeNode>()

        // If the path from root to p or the path from root to q doesn't exist
        // then there is not common ancestor
        if (!getPath(root, p, pathNumber1) || !getPath(root, q, pathNumber2)) {
            return null
        }

        // Iterate through them until the value is not equal
        val minLength = min(pathNumber1.size, pathNumber2.size)
        var i = 0
        while (i < minLength) {
            if (pathNumber1[i].`val` == pathNumber2[i].`val`) {
                i++
            } else {
                break
            }
        }

        // Then return the value right before
        return pathNumber1[i - 1]
    }

    private fun getPath(root: TreeNode?, num: TreeNode, path: MutableList<TreeNode>): Boolean {
        if (root == null) return false
        path.add(root)
        if (root.`val` == num.`val`) {
            return true
        }

        if (getPath(root.left, num, path) || getPath(root.right, num, path)) {
            return true
        }
        path.removeLast()
        return false
    }
}

class LowestCommonAncestorOfABinaryTreeOptim: LowestCommonAncestorOfABinaryTree() {

    /**
     * Complexity
     * - Time: O(n)
     * - Space: O(h)
     */
    override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null

        // If root contains any of those values, return root
        if (root.`val` == p.`val` || root.`val` == q.`val`) return root

        // Check if both left and right contains any of those values
        // If both of them contains the value, the propagate root
        val leftLCA = lowestCommonAncestor(root.left, p, q)
        val rightLCA = lowestCommonAncestor(root.right, p, q)

        if (leftLCA != null && rightLCA != null) {
            return root
        }

        return leftLCA ?: rightLCA
    }
}

class LowestCommonAncestorOfABinaryTreeEPI: LowestCommonAncestorOfABinaryTree() {
    data class Status(val numTargetNodes: Int, val ancestor: TreeNode?)

    override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (p == null || q == null) return null
        return lcaHelper(root, p, q, ).ancestor
    }

    private fun lcaHelper(root: TreeNode?, p: TreeNode, q: TreeNode): Status {
        if (root == null) return Status(0, null)

        // Check the left node if it contains all the targets
        val leftStatus = lcaHelper(root.left, p, q)
        if (leftStatus.numTargetNodes == 2) {
            return leftStatus
        }

        // check the right node if it contains all the targets
        val rightStatus = lcaHelper(root.right, p, q)
        if (rightStatus.numTargetNodes == 2) {
            return rightStatus
        }

        val currentNodeMatch = if (root.`val` == p.`val` || root.`val` == q.`val`) 1 else 0
        val numTargetNodes = leftStatus.numTargetNodes + rightStatus.numTargetNodes +
                currentNodeMatch

        return Status(numTargetNodes, if (numTargetNodes == 2) root else null)
    }
}