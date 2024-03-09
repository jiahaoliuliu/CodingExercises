package Graph.easy

import org.junit.Assert
import org.junit.Test

/**
 * Breadth first search
 *
 * Given an undirected graph of integers, represented by an adjacency list, and an integer root,
 * create a function that prints its values in breadth first search, by considering the vertex whose value
 * is root as the arbitrary node.
 *
 * Example 1:
 *      Input: graph = {"5" : [8, 1, 12], "8" : [5, 12, 14, 4], "12" : [5, 8, 14], "14" : [8, 12, 4],
 *      "4" : [8, 14], "1" : [5, 7], "7" : [1, 16], "16" : [7]}, root = 5
 *      Output: 5 8 1 12 14 4 7 16
 */
abstract class BreadthFirstSearch {

    abstract fun bfs(graph: Graph?, root: Int): IntArray

    @Test
    fun test1() {
        // Given
        val hashMap = HashMap<Int, ArrayList<Int>>()
        hashMap[5]  = arrayListOf(8, 1, 12)
        hashMap[8]  = arrayListOf(5, 12, 14, 4)
        hashMap[12] = arrayListOf(5, 8, 14)
        hashMap[14] = arrayListOf(8, 12, 4)
        hashMap[4]  = arrayListOf(8, 14)
        hashMap[1]  = arrayListOf(5, 7)
        hashMap[7]  = arrayListOf(1, 16)
        hashMap[16]  = arrayListOf(7)
        val graph = Graph(hashMap)
        val root = 5

        // When
        val result = bfs(graph, root)

        // Then
        Assert.assertEquals(8, result.size)
        Assert.assertTrue(intArrayOf(5, 8, 1, 12, 14, 4, 7, 16) contentEquals result)
    }
}

class BreadthFirstSearchIterative: BreadthFirstSearch() {

    /**
     * The same as the bfs for a binary tree, but this time with graph
     *
     * The algo should use a queue to store the nodes
     *
     * Complexity:
     * - Time: O(|V| + |E|) where |V| is the number of vertices and |E| is the number of nodes
     * - Space: O(|V|)
     */
    override fun bfs(graph: Graph?, root: Int): IntArray {
        // 0. Corner cases
        if (graph == null) return intArrayOf()

        // 1. Init the variables
        val visited = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()
        queue.addLast(root)

        // 2. Loop
        while(queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (!visited.contains(node)) {
                visited.add(node)
            }

            for (adjacent in graph.adjList[node]!!) {
                if (!visited.contains(adjacent)) {
                    queue.addLast(adjacent)
                }
            }
        }

        // 3. Return the value
        return visited.toIntArray()

    }

}