package Graph.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


/**
 * Graph Depth first search
 *
 * Given an undirected graph of integers graph, represented by an adjacency list, and an integer root,
 * create a function that prints its values in depth first search, by considering the vertex whose value
 * is root as the arbitrary node.
 *
 * Example 1:
 *      Input: graph = {"5" : [8, 1, 12], "8" : [5, 12, 14, 4], "12" : [5, 8, 14], "14" : [8, 12, 4],
 *                      "4" : [8, 14], "1" : [5, 7], "7" : [1, 16], "16" : [7]}, root = 5
 *      Output: 5 8 12 14 4 1 7 16
 *
 */

abstract class DepthFirstSearch {

    abstract fun dfs(graph: Graph?, root: Int): IntArray

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
        val result = dfs(graph, root)

        // Then
        assertEquals(8, result.size)
        assertTrue(intArrayOf(5, 8, 12, 14, 4, 1, 7, 16) contentEquals result)
    }
}

class DepthFirstSearchRecursive: DepthFirstSearch() {

    /**
     * Initial thoughts
     *
     * Use an queue and a list of visited nodes for it
     * Complexity:
     * - Time: O(|V| + |E|) where |V| is the number of vertices and |E| is the number of nodes
     * - Space: O(|V|)
     */
    override fun dfs(graph: Graph?, root: Int): IntArray {
        // 0. Corner cases
        if (graph == null) return intArrayOf()
        val visited = mutableSetOf<Int>()

        return dfs(graph, root, visited)
    }

    private fun dfs(graph: Graph, root:Int, visited: MutableSet<Int>): IntArray {
        return if (visited.contains(root)) visited.toIntArray()
        else {
            visited.add(root)
            val neighbours = graph.adjList[root]!!
            for (i in 0 until neighbours.size) {
                dfs(graph, neighbours[i], visited)
            }
            visited.toIntArray()
        }
    }
}

class DepthFirstSearchIterative: DepthFirstSearch() {
    /**
     *
     * Depth first search
     *
     * Note the adjacent list is entered reversed. This is because it is a stack, so the
     * first adjacent node should be on the top of the stack
     *
     * Complexity:
     * - Time: O(|V| + |E|) where |V| is the number of vertices and |E| is the number of nodes
     * - Space: O(|V|)
     *
     */
    override fun dfs(graph: Graph?, root: Int): IntArray {
        // 0. Corner cases
        if (graph == null) return intArrayOf()

        // 1. Init the variables
        val visited = mutableListOf<Int>()
        val stack = ArrayDeque<Int>()
        stack.add(root)

        // 2. Loop
        while (stack.isNotEmpty()) {
            val node = stack.removeFirst()

            // Add the node to the visited if it was not visited
            if (!visited.contains(node)) {
                visited.add(node)
            }

            for (adjacent in graph.adjList[node]!!.reversed()) {
                if (!visited.contains(adjacent)) {
                    stack.addFirst(adjacent)
                }
            }
        }

        // 3. Return value
        return visited.toIntArray()
    }

}