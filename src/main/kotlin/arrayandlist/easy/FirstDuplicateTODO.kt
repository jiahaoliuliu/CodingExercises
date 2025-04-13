package arrayandlist.easy

/**
 * First duplicate
 *
 *  given an array that contains numbers only in the range from 1 to a-length, find the first duplicate
 *  number for which the second occurrence has the minimal index. Hence, if there is more than one duplicate
 *  in your list you’ll return the number for which the second occurrence has the smaller index. If you can’t
 *  find any then return -1.
 *
 * Example 1
 *  For a =[2, 1, 3, 5, 3, 2], the output should be firstDuplicate(a)=3
 *  Explanation: there are 2 duplicate numbers 2 and 3, however, the second occurrence of number 3 is smaller
 *  than the second occurrence of number 2 duplicate
 *
 * Example 2
 * For a=[1,2,3,4,5], the output should be firstDuplicate(a)=-1
 * Indeed, there is no duplicate in this list!
 *
 * Check: Not in Leet Code
 */
class FirstDuplicateTODO {

    /**
     * Initial thought
     * 1. Init elements
     * Create an hashset of number seen
     *
     * 2. Loop
     * Per each element in the array
     * - If the number exists in the hash set
     *   -> return it
     * - Else
     *   -> add it to the hashset
     *
     * 3. return default data
     * return -1
     */
    private fun firstDuplicate(array: IntArray): Int {
        return -1
    }
}