package tree.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode
import tree.toBinaryTree

/**
 * Tree breath first search II
 *
 * Given a binary tree of integers root, create a function that returns an array where each element
 * represents an array that contains the elements at the level i
 *
 */
abstract class TreeBreathFirstSearchII {
    abstract fun nodesOfSameLevel(root: TreeNode?): List<List<TreeNode>>

    /**
     *                            1
     *                        2        2
     *                      3   3
     *                    4  4
     *
     */
    @Test
    fun test1() {
        // Given
        val list = listOf(1,2,2,3,3,null,null,4,4)
        val root = list.toBinaryTree()

        // When
        val result = nodesOfSameLevel(root)

        // Then
        assertEquals(4, result.size)
        assertEquals(listOf(TreeNode(1)), result[0])
        assertEquals(listOf(TreeNode(2), TreeNode(2)), result[1])
        assertEquals(listOf(TreeNode(3), TreeNode(3)), result[2])
        assertEquals(listOf(TreeNode(4), TreeNode(4)), result[3])
    }

    @Test
    fun test2() {
        // Given
        val list = listOf(5, 10, 3, 4, 6, null, 7, null, 8, 9, 1, 2)

        val root = list.toBinaryTree()

        // When
        val result = nodesOfSameLevel(root)

        // then
        assertEquals(4, result.size)
        assertEquals(listOf(TreeNode(5)), result[0])
        assertEquals(listOf(TreeNode(10), TreeNode(3)), result[1])
        assertEquals(listOf(TreeNode(4), TreeNode(6), TreeNode(7)), result[2])
        assertEquals(listOf(TreeNode(8), TreeNode(9), TreeNode(1), TreeNode(2)), result[3])
    }
}

class TreeBreathFirstSearchIIImpl: TreeBreathFirstSearchII() {

    /**
     * Initial thoughts
     *
     * For nodes of the same level, we need to do breath first search
     * (using a queue)
     *
     * Before inserting the nodes in the queue, need to group them first
     *
     * Complexity:
     * - Time: O(n), where n is the list of all nodes
     * - Space: O(n)
     *
     */
    override fun nodesOfSameLevel(root: TreeNode?): List<List<TreeNode>> {
        // 1. init the variables
        val result = mutableListOf<List<TreeNode>>()
        if (root == null) return result
        var group: MutableList<TreeNode>
        var childNodes: ArrayDeque<TreeNode>
        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)

        // 2. Loop
        while (queue.isNotEmpty()) {
            group = mutableListOf()
            childNodes = ArrayDeque()
            while (queue.isNotEmpty()) {
                // Extract and group the nodes
                val currentNode = queue.removeFirst()
                group.add(currentNode)

                currentNode.left?.let {
                    childNodes.addLast(it)
                }

                currentNode.right?.let {
                    childNodes.addLast(it)
                }
            }

            result.add(group)
            queue.addAll(childNodes)
        }

        // 3. Return the result
        return result
    }
}