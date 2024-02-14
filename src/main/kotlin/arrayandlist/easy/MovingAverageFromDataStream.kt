package arrayandlist.easy

import org.intellij.lang.annotations.JdkConstants.FontStyle
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Moving Average from Data Stream
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 * Example 1:
 *      Input ["MovingAverage", "next", "next", "next", "next"]
 *            [[3], [1], [10], [3], [5]]
 *      Output
 *          [null, 1.0, 5.5, 4.66667, 6.0]
 *
 *      Explanation
 *          MovingAverage movingAverage = new MovingAverage(3);
 *          movingAverage.next(1); // return 1.0 = 1 / 1
 *          movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 *          movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 *          movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 * Constraints:
 *      1 <= size <= 1000
 *      -10^5 <= val <= 10^5
 *      At most 104 calls will be made to next.
 */
class MovingAverageFromDataStream {

    /**
     * Initial thoughts
     * We can create an int array of fixed size {size}, init all the values as zero
     * Start an index as zero
     * Start a sum as zero
     * Everytime next is called, we add 1 to the value and module it to the size, so
     * the array will be circular
     *
     * At the same time, everytime a new value is added, the old value will be extracted
     * Then we calculate the average by dividing the sum to the windows size
     *
     * 1. Init the values
     * index = 0
     * sum = 0
     * number Of elements = 0
     *
     * 2. When next is called
     *  initial value of index is 0
     *      Index will be always pointing to the next value to be replaced
     *
     * 2.1. Update sum
     * sum += value
     * sum -= array[index]
     * array[index] = value
     *
     * 2.2 Update the index
     * index++
     * index = index rem window size
     *
     * 2.3 Update the number of elements
     * if (number of elements < 3)
     *  number of elements ++
     *
     * 3. Show result
     * return sum / number of elements
     */
    class MovingAverage(private val size: Int) {
        // 1. init the value
        private val circularArray = IntArray(size)
        private var index = 0
        private var sum = 0
        private var numberOfElements = 0

        fun next(`val`: Int): Double {
            // 2.1 Update the sum
            sum += `val`
            sum -= circularArray[index]
            circularArray[index] = `val`

            // 2.2 Update the index
            index++
            index %= size

            // 2.3 Update the number of elements
            // Increase it until it reaches to window size
            if (numberOfElements < size) {
                numberOfElements++
            }

            // 3. Return the value
            return sum / numberOfElements.toDouble()
        }
    }

    @Test
    fun test1() {
        // Given
        val windowSize = 3

        // When
        val movingAverage = MovingAverage(windowSize)

        // Then
        assertEquals(1.0, movingAverage.next(1), 0.5)
        assertEquals(5.5, movingAverage.next(10), 0.5)
        assertEquals(4.6667, movingAverage.next(3), 0.5)
        assertEquals(6.0, movingAverage.next(5), 0.5)
    }

    @Test
    fun test2() {
        // Given
        val windowSize = 2

        // When
        val movingAverage = MovingAverage(windowSize)

        // Then
        assertEquals(-50.0, movingAverage.next(-50), 0.5)
        assertEquals(25.0, movingAverage.next(100), 0.5)
        assertEquals(75.0, movingAverage.next(50), 0.5)
    }

    /**
     * Optimization: We can use a queue for this
     *
     */
    class MovingAverageOptimized(private val size: Int) {
        private var sum = 0
        private val myQueue = ArrayDeque<Int>()

        fun next(`val`: Int): Double {
            sum += `val`
            myQueue.addLast(`val`)
            if (myQueue.size > size) {
                sum -= myQueue.removeFirst()
            }

            return sum / myQueue.size.toDouble()
        }
    }

    @Test
    fun test1Optimized() {
        // Given
        val windowSize = 3

        // When
        val movingAverage = MovingAverageOptimized(windowSize)

        // Then
        assertEquals(1.0, movingAverage.next(1), 0.5)
        assertEquals(5.5, movingAverage.next(10), 0.5)
        assertEquals(4.6667, movingAverage.next(3), 0.5)
        assertEquals(6.0, movingAverage.next(5), 0.5)
    }

    @Test
    fun test2Optimized() {
        // Given
        val windowSize = 2

        // When
        val movingAverage = MovingAverageOptimized(windowSize)

        // Then
        assertEquals(-50.0, movingAverage.next(-50), 0.5)
        assertEquals(25.0, movingAverage.next(100), 0.5)
        assertEquals(75.0, movingAverage.next(50), 0.5)
    }

}