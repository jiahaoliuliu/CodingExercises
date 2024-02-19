package tree.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode

/**
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all
 * nodes with a value in the inclusive range [low, high].
 *
 * Example 1:
 *      Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 *      Output: 32
 *      Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 *
 * Example 2:
 *      Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 *      Output: 23
 *      Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [1, 2 * 10^4].
 *      1 <= Node.val <= 105
 *      1 <= low <= high <= 105
 *      All Node.val are unique.
 */
class RangeSumOfBST {

    private fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        // Corner case
        if (root == null) {
            return 0
        }

        // We can use breath first or depth first
        // Using depth first
        // 1. Init the variables
        var sum = 0
        val stack = ArrayDeque<TreeNode>()
        stack.addFirst(root)

        // 2. Loop
        while (stack.isNotEmpty()) {
            val node = stack.removeFirst()
            if (node.`val` in low..high) {
                sum += node.`val`
            }

            node.left?.let {
                stack.addFirst(it)
            }

            node.right?.let {
                stack.addFirst(it)
            }
        }

        // 3. return result
        return sum
    }

    @Test
    fun test1() {
        // Given
        val root = TreeNode(10)
        val node1 = TreeNode(5)
        val node2 = TreeNode(15)
        val node11 = TreeNode(3)
        val node12 = TreeNode(7)
        val node21 = null
        val node22 = TreeNode(18)

        root.left = node1
        root.right = node2

        node1.left = node11
        node1.right = node12

        node2.left = node21
        node2.right = node22

        val low = 7
        val high = 15

        // When
        val result = rangeSumBST(root, low, high)

        // Then
        assertEquals(32, result)
    }

    @Test
    fun test2() {
        // Given
        val root = TreeNode(10)
        val node1 = TreeNode(5)
        val node2 = TreeNode(15)
        val node11 = TreeNode(3)
        val node12 = TreeNode(7)
        val node21 = TreeNode(13)
        val node22 = TreeNode(18)
        val node111 = TreeNode(1)
        val node112 = null
        val node121 = TreeNode(6)
        val node122 = null

        root.left = node1
        root.right = node2

        node1.left = node11
        node1.right = node12

        node2.left = node21
        node2.right = node22

        node11.left = node111
        node11.right = node112

        node12.left = node121
        node12.right = node122

        val low = 6
        val high = 10

        // When
        val result = rangeSumBST(root, low, high)

        // Then
        assertEquals(23, result)
    }

    private fun rangeSumBSTOptimized(root: TreeNode?, low: Int, high: Int): Int {
        var sum = 0
        root?.let {
            if (it.`val` in low..high) {
                sum += it.`val`
            }

            // If the value is bigger than low, then the left might be eligible
            // for the sum
            if (it.`val` > low) {
                sum += rangeSumBSTOptimized(root.left, low, high)
            }

            // If the value is smaller than high, then the right might be
            // eligible for the sum
            if (it.`val` < high) {
                sum += rangeSumBSTOptimized(root.right, low, high)
            }
        }

        // 3. return result
        return sum
    }

    @Test
    fun test1Optimized() {
        // Given
        val root = TreeNode(10)
        val node1 = TreeNode(5)
        val node2 = TreeNode(15)
        val node11 = TreeNode(3)
        val node12 = TreeNode(7)
        val node21 = null
        val node22 = TreeNode(18)

        root.left = node1
        root.right = node2

        node1.left = node11
        node1.right = node12

        node2.left = node21
        node2.right = node22

        val low = 7
        val high = 15

        // When
        val result = rangeSumBSTOptimized(root, low, high)

        // Then
        assertEquals(32, result)
    }

    @Test
    fun test2Optimized() {
        // Given
        val root = TreeNode(10)
        val node1 = TreeNode(5)
        val node2 = TreeNode(15)
        val node11 = TreeNode(3)
        val node12 = TreeNode(7)
        val node21 = TreeNode(13)
        val node22 = TreeNode(18)
        val node111 = TreeNode(1)
        val node112 = null
        val node121 = TreeNode(6)
        val node122 = null

        root.left = node1
        root.right = node2

        node1.left = node11
        node1.right = node12

        node2.left = node21
        node2.right = node22

        node11.left = node111
        node11.right = node112

        node12.left = node121
        node12.right = node122

        val low = 6
        val high = 10

        // When
        val result = rangeSumBSTOptimized(root, low, high)

        // Then
        assertEquals(23, result)
    }

}