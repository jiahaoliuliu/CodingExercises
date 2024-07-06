package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * LRU cache
 * Least Recently Used Cache
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *      LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *      int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *      void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value
 *      pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently
 *      used key.
 *      The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 *      Input
 *      ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *      [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 *      Output
 *      [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 *      LRUCache lRUCache = new LRUCache(2);
 *      lRUCache.put(1, 1); // cache is {1=1}
 *      lRUCache.put(2, 2); // cache is {1=1, 2=2}
 *      lRUCache.get(1);    // return 1
 *      lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 *      lRUCache.get(2);    // returns -1 (not found)
 *      lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 *      lRUCache.get(1);    // return -1 (not found)
 *      lRUCache.get(3);    // return 3
 *      lRUCache.get(4);    // return 4
 *
 * Constraints:
 *      1 <= capacity <= 3000
 *      0 <= key <= 10^4
 *      0 <= value <= 10^5
 *      At most 2 * 10^5 calls will be made to get and put.
 */
class LRUCacheTODO  {

    /**
     * Initial thought
     * We need a structure that keeps the order of insertion
     *  -> But when an existing data gets updated, the move it to the end of the queue
     *
     *
     *  - value: key of put
     * - A hashMap to store the current value
     *  - key: key
     *  - value: value
     *
     *  Best solution: Use a linkedHashMap or a double Linked list for it
     *  -> One pointer to the tail and one pointer to the head
     *  -> For put, use a hashMap
     *      - key: key
     *      - value: Certain node
     */
    class LRUCache(private val capacity: Int) {
        val queue = ArrayDeque<Int>()
        val data = HashMap<Int, Int>()

        /**
         * Get the data directly from the hash
         * This count as a usage
         */
        fun get(key: Int): Int {
            // Update the usage
            if (data.containsKey(key)) {
                queue.remove(key)
                queue.addLast(key)
            }

            return data.getOrDefault(key, -1)
        }

        fun put(key: Int, value: Int) {
            // if the data already contains the key
            if (data.containsKey(key)) {
                data[key] = value
                queue.remove(key)
                queue.addLast(key)
                return
            }

            // If the queue is already full, remove the first added
            if (queue.size == capacity) {
                val keyToBeRemoved = queue.removeFirst()
                data.remove(keyToBeRemoved)
            }

            // Insert the element
            data[key] = value
            queue.addLast(key)
        }
    }

    @Test
    fun test1() {
        // Given
        val lRUCache = LRUCache(2)

        // When, Then
        lRUCache.put(1, 1)
        // cache is {1=1}

        lRUCache.put(2, 2)
        // cache is {1=1, 2=2}

        assertEquals(1, lRUCache.get(1))

        // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.put(3, 3)

        assertEquals(-1, lRUCache.get(2))
        // return -1 (not found)

        lRUCache.put(4, 4)
        // LRU key was 1, evicts key 1, cache is {4=4, 3=3}

        assertEquals(-1, lRUCache.get(1))
        // return -1 (not found)

        assertEquals(3, lRUCache.get(3))

        assertEquals(4, lRUCache.get(4))
    }
}