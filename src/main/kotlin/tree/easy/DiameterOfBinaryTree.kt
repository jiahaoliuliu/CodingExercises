package tree.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode
import java.lang.Integer.max

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 *      Input: root = [1,2,3,4,5]
 *      Output: 3
 *      Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 *      Input: root = [1,2]
 *      Output: 1
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [1, 10^4].
 *      -100 <= Node.val <= 100
 */
class DiameterOfBinaryTree {

    /**
     * Initial thoughts
     *
     * A binary tree has 2 nodes: Left and right
     *
     * The diameter is the sum of the max length of the left node and the max length of
     * the right node
     *
     * The node with longest diameter does not have to be the root node
     * because the root node could have 2 deepest node on the left and 1 small node on the right
     * then the node with the longest diameter is the one on the left, which both on the
     * left and on the right it has the longest diameter
     *
     * Definition:
     * Diameter(node) = longestPath(node.left) + longestPath(node.right)
     *
     * We need to find the diameter of all the nodes, then calculate the longest one
     * - While we are calculating the diameters, we can use a variable to store the
     * value meanwhile
     *
     * If per node, we have the longest path on the left and on the right already recorded
     * then it would be easier
     *
     * LongestPath(node) = max(longestPath(node.left), longestPath(node.right))
     *
     * We can have a hashMap
     * - key: node
     * - value: longest path
     *
     * Going through the tree getting the longest path of the nodes
     * Use the hash map to cache the values
     *
     * 1. Init the value
     * longestPathMap
     * - Key = node
     * - Value = Longest path
     * maxDiameter = 0
     *
     * pathStack = Stack<Node, Path>
     *
     * pathStack.add(root)
     *
     * 2. Create longest path Map (recurrent value)
     * longestPath(node) = 1 + max(longestPath(node.left), longestPath(node.right))
     *
     * Base case
     * longestPath(null) = 0
     *
     */
    private fun diameterOfBinaryTree(root: TreeNode?): Int {
        // Corner case
        if (root == null) return 0

        // 1. Init variables
        var maxDiameter = 0
        val longestPathMap = HashMap<TreeNode, Int>()
        val stack = ArrayDeque<TreeNode>()
        stack.addFirst(root)

        // 2. Loop
        while (stack.isNotEmpty()) {
            val node = stack.removeFirst()
            val leftLongestPath = getLongestPath(node.left, longestPathMap)
            val rightLongestPath = getLongestPath(node.right, longestPathMap)
            val diameter = leftLongestPath + rightLongestPath
            maxDiameter = max(maxDiameter, diameter)

            node.left?.let {
                stack.addFirst(it)
            }

            node.right?.let {
                stack.addFirst(it)
            }
        }

        return maxDiameter
    }

    private fun getLongestPath(node: TreeNode?, longestPathMap: HashMap<TreeNode, Int>): Int {
        // Base case
        if (node == null) return 0

        if (longestPathMap.containsKey(node)) {
            return longestPathMap[node]!!
        }

        val longestPath = 1 + max(getLongestPath(node.left, longestPathMap), getLongestPath(node.right, longestPathMap))
        longestPathMap[node] = longestPath
        return longestPath
    }

    @Test
    fun test1() {
        // Given
        val root = TreeNode(1)

        val node11 = TreeNode(2)
        val node12 = TreeNode(3)

        val node111 = TreeNode(4)
        val node112 = TreeNode(5)

        root.left = node11
        root.right = node12

        node11.left = node111
        node11.right = node112

        // When
        val result = diameterOfBinaryTree(root)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        // Given
        val root = TreeNode(1)

        val node11 = TreeNode(2)

        root.left = node11

        // When
        val result = diameterOfBinaryTree(root)

        // Then
        assertEquals(1, result)
    }
}