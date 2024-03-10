package tree.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import tree.TreeNode
import tree.toBinaryTree
import tree.toList

/**
 * Flatten Binary Tree to Linked List
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 * list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 * Example 1:
 *      Input: root = [1,2,5,3,4,null,6]
 *      Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 *      Input: root = []
 *      Output: []
 *
 * Example 3:
 *      Input: root = [0]
 *      Output: [0]
 *
 * Constraints:
 *      The number of nodes in the tree is in the range [0, 2000].
 *      -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
abstract class FlattenBinaryTreeToLinkedList {
    abstract fun flatten(root: TreeNode?): Unit

    @Test
    fun test1() {
        // Given
        val list = listOf(1,2,5,3,4,null,6)
        val head = list.toBinaryTree()

        // When
        flatten(head)

        // Then
        val result = head.toList()
        assertEquals(listOf(1,null,2,null,3,null,4,null,5,null,6), result)
    }

    @Test
    fun test2() {
        // Given
        val list = listOf<Int?>()
        val head = list.toBinaryTree()

        // When
        flatten(head)

        // Then
        val result = head.toList()
        assertEquals(listOf<Int?>(), result)
    }

    @Test
    fun test3() {
        // Given
        val list = listOf(0)
        val head = list.toBinaryTree()

        // When
        flatten(head)

        // Then
        val result = head.toList()
        assertEquals(listOf(0), result)
    }
}

class FlattenBinaryTreeToLinkedListImpl: FlattenBinaryTreeToLinkedList() {

    /**
     * Initial thoughts
     *
     * 1,2,5,3,4,null,6
     * to
     * 1,null,2,null,3,null,4,null,5,null,6
     *
     * In-order traversal
     * - Visit left node
     * - Visit root
     * - Visit right node
     *
     * Pre-order traversal
     * - Visit the root
     * - Visit the left node
     * - Visit the right node
     *
     * Post-order traversal
     * - Visit the left node
     * - Visit the right node
     * - Visit the root
     *
     * This is a pre-order traversal
     *
     * The result needs to put in place
     * - We can store temporally the values in a list
     * then at the end, append the content of the list to the root
     * (well the root's value does not change)
     *
     *
     *
     */
    override fun flatten(root: TreeNode?) {
        // 0. Corner cases
        if (root == null || (root.left == null && root.right == null)) return

        // 1. Init the values
        val nodesList = mutableListOf<TreeNode>()

        // 2. Call the helper method
        flatten(root, nodesList)

        // 3. Move all the nodes to root
        var currentNode = root
        for (i in 1 until nodesList.size) {
            currentNode?.let {
                it.left = null
                it.right = nodesList[i]
                currentNode = it.right
            }
        }
    }

    private fun flatten(node: TreeNode, nodesList: MutableList<TreeNode>) {
        // Pre-order. First the main node
        nodesList.add(node)

        // Then the left node
        node.left?.let {
            flatten(it, nodesList)
        }

        node.right?.let {
            flatten(it, nodesList)
        }
    }
}

class FlattenBinaryTreeToLinkedListOptim: FlattenBinaryTreeToLinkedList() {

    private var head: TreeNode? = null
    /**
     * Complexity:
     * - Time: O(n)
     * - Space: O(h)
     */
    override fun flatten(root: TreeNode?) {
        if (root == null) return
        flatten(root.right)
        flatten(root.left)
        root.left = null
        root.right = head
        head = root
    }
}