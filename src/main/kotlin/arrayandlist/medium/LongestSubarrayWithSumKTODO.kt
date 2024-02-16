package arrayandlist.medium

/**
 * Longest subarray with sum K
 *
 * Given an array arr[] of size n containing integers. The problem is to find the length of the longest sub-array
 * having sum equal to the given value k.
 *
 * Example 1
 *      Input: arr[] = { 10, 5, 2, 7, 1, 9 }, k = 15
 *      Output: 4
 *      Explanation: The sub-array is {5, 2, 7, 1}.
 * Example 2
 *      Input: arr[] = {-5, 8, -14, 2, 4, 12}, k = -5
 *      Output: 5
 *      Explanation: The sub-array is {-5, 8, -14, 2, 4}
 */
class LongestSubarrayWithSumKTODO {

    /**
     * Initial thoughts
     *
     * Observation
     * - The elements in the array are not related
     * - Can we sort them?
     *   -> No. Because we are looking for subarray
     *
     * Brute force
     * For each element in the array
     *   Expand the subarray until the end of the array
     *      -> If the result is equal to k and it is bigger than actual result
     *          -> Update the result
     * Return result
     *
     * Time complexity: O(n^2)
     *
     * Better solution: We can store the data temporally to improve the result
     * - Given an array { 10, 5, 2, 7, 1, 9 } and k = 15
     * The post elements are added constantly
     *
     * How about store them and add them only once?
     * It does not improve the problem
     *
     * How about have windows size of n, check the result
     *  -> Then have windows size of n - 1, check the possible results
     *  -> reducing the windows size until it is 1?
     *
     *  Is it a DP problem?
     *  -> Subproblem: the size of the array can growth from 1 to n
     *  -> Are they overlapped: The sum is calculated constantly
     *  {10}
     *  {10} + 5
     *  {10, 5} + 2
     *  {10, 5, 2} + 7
     *  {10, 5, 2, 7} + 1
     *  {10, 5, 2, 7, 1} + 9
     *      {5}
     *      {5} + 2
     *      {5, 2} + 7
     *      {5, 2, 7} + 1
     *      {5, 2, 7, 1} + 9
     *         {2}
     *         {2} + 7
     *         {2, 7} + 1
     *         {2, 7, 1} + 9
     *            {7}
     *            {7} + 1
     *            {7, 1} + 9
     *               {1}
     *               {1} + 9
     *                    {9}
     *
     *  Have a windows that going through the array will help
     *  -> What is the condition to move the left pointer?
     *      -> the sum is smaller than k
     *  -> What is the condition to move the right pointer?
     *      -> The sum is bigger than k
     *
     *  Algo
     *  // 1. Init the values
     *  left = 0
     *  right = 0
     *  sum = 0
     *  maxLength = 0
     *
     *  // 2. Loop
     *  for (right in nums.index)
     *      // the left cursor moves according to the outside while
     *      // if (sum < k) -> This condition is guaranteed by the inner while loop
     *          sum += nums[right]
     *      //if (sum == k) -> This condition is checked again by the inner while loop
     *      //    maxLength = max(maxLength, right - left + 1)
     *
     *      while (sum >= k && left <= right)
     *          sum -= nums[left]
     *          left++
     *      if (sum == k)
     *          maxLength = max(maxLength, right - left + 1)
     *
     *  // 3. return the resul
     *  return maxLength
     */
    private fun longestSubarrayWithSumK(nums: IntArray, k: Int): Int {
        return 0
    }
}