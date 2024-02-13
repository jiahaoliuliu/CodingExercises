package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Maximum population year
 *
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years
 * of the ith person.
 *
 * The population of some year x is the number of people alive during that year. The ith person is counted in
 * year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted
 * in the year that they die.
 *
 * Return the earliest year with the maximum population.
 *
 * Example 1:
 *      Input: logs = [[1993,1999],[2000,2010]]
 *      Output: 1993
 *      Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
 *
 * Example 2:
 *      Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
 *      Output: 1960
 *      Explanation: The maximum population is 2, and it had happened in years 1960 and 1970.
 * The earlier year between them is 1960.
 *
 *
 * Constraints:
 *      1 <= logs.length <= 100
 *      1950 <= birth(i) < death(i) <= 2050
 *
 */
class MaximumPopulationYear {

    /**
     * Initial thoughts
     *
     * Since the year allowed is from 1950 to 2050, it is possible to build an array of 100 years
     * and add the years to there
     *
     * Then loop through the arrays and create a pair of (year, population)
     * Return the first year with the biggest population
     *
     */
    private fun maximumPopulation(logs: Array<IntArray>): Int {
        // 1. Going through the years to getter the population per year
        val population = IntArray(101)

        // 2. Loop through the list of logs
        for (log in logs) {
            for (i in log[0] until log[1]) { // Until is inclusive on the left and exclusive on the right
                population[i - 1950]++
            }
        }

        // 3. Getting the results
        var result = Pair(1950, 0)
        population.onEachIndexed { index, people ->
            if (people > result.second) {
                result = Pair(1950 + index, people)
            }
        }

        return result.first
    }

    @Test
    fun test1() {
        // Given
        val logs = arrayOf(intArrayOf(1993, 1999), intArrayOf(2000, 2010))

        // When
        val result = maximumPopulation(logs)

        // Then
        assertEquals(1993, result)
    }

    @Test
    fun test2() {
        // Given
        val logs = arrayOf(intArrayOf(1950, 1961), intArrayOf(1960, 1971), intArrayOf(1970, 1981))

        // When
        val result = maximumPopulation(logs)

        // Then
        assertEquals(1960, result)
    }

    /** Instead of recording the population itself, record the changes
     * on the population
     */
    private fun maximumPopulationOptimal(logs: Array<IntArray>): Int {
        // 1. Init the data
        val populationChanges = IntArray(101)
        for (log in logs) {
            populationChanges[log[0] - 1950]++
            populationChanges[log[1] - 1950]--
        }

        // 2. Checking the population changes
        var populationMax = 0
        var populationMaxYear = 1950
        var currentPopulation = 0
        for (index in populationChanges.indices) {
            currentPopulation += populationChanges[index]

            if (currentPopulation > populationMax) {
                populationMax = currentPopulation
                populationMaxYear = index + 1950
            }
        }

        return populationMaxYear
    }

    @Test
    fun test3() {
        // Given
        val logs = arrayOf(intArrayOf(1993, 1999), intArrayOf(2000, 2010))

        // When
        val result = maximumPopulationOptimal(logs)

        // Then
        assertEquals(1993, result)
    }

    @Test
    fun test4() {
        // Given
        val logs = arrayOf(intArrayOf(1950, 1961), intArrayOf(1960, 1971), intArrayOf(1970, 1981))

        // When
        val result = maximumPopulationOptimal(logs)

        // Then
        assertEquals(1960, result)
    }

}