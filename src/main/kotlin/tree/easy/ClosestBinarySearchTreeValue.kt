package tree.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode
import kotlin.math.abs
import kotlin.math.min

/**
 * Closest Binary Search Tree Value
 *
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the
 * target. If there are multiple answers, print the smallest.
 *
 * Example 1:
 *      Input: root = [4,2,5,1,3], target = 3.714286
 *      Output: 4
 *
 * Example 2:
 *      Input: root = [1], target = 4.428571
 *      Output: 1
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [1, 10^4].
 *      0 <= Node.val <= 10^9
 *      -10^9 <= target <= 10^9
 */
class ClosestBinarySearchTreeValue {

    /**
     * Initial thoughts
     *
     * Iterate through the tree
     * - Check if the number is the closest one
     *
     */
    private fun closestValue(root: TreeNode?, target: Double): Int {
        // Init the value
        var closestValue = Int.MAX_VALUE
        var closestDistance = Double.MAX_VALUE

        root?.let {
            // Init the value
            val stack = ArrayDeque<TreeNode>()
            stack.addFirst(root)

            // Loop through the tree
            while (stack.isNotEmpty()) {
                val node = stack.removeFirst()
                val distance = abs(target - node.`val`)
                if (distance < closestDistance) {
                    closestDistance = distance
                    closestValue = node.`val`
                } else if (distance == closestDistance) {
                    closestDistance = distance
                    closestValue = min(closestValue, node.`val`)
                }

                node.left?.let {
                    stack.addFirst(it)
                }

                node.right?.let {
                    stack.addFirst(it)
                }
            }
        }

        return closestValue
    }

    @Test
    fun test1() {
        // Given
        val root = TreeNode(4)
        val node1 = TreeNode(2)
        val node2 = TreeNode(5)

        val node11 = TreeNode(1)
        val node12 = TreeNode(3)

        root.left = node1
        root.right = node2

        node1.left = node11
        node1.right = node12

        val target = 3.714286

        // When
        val result = closestValue(root, target)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test2() {
        // Given
        val root = TreeNode(1)
        val target = 4.428571

        // When
        val result = closestValue(root, target)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test3() {
        // Given
        val root = TreeNode(1)

        val node11 = null
        val node12 = TreeNode(2)

        root.left = node11
        root.right = node12

        val target = 3.428571

        // When
        val result = closestValue(root, target)

        // Then
        assertEquals(2, result)
    }
}