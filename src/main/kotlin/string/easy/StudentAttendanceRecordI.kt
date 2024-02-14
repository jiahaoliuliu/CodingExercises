package string.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Student Attendance Record I
 *
 * You are given a string s representing an attendance record for a student where each character signifies
 * whether the student was absent, late, or present on that day. The record only contains the following three
 * characters:
 *  'A': Absent.
 *  'L': Late.
 *  'P': Present.
 *
 * The student is eligible for an attendance award if they meet both of the following criteria:
 *      The student was absent ('A') for strictly fewer than 2 days total.
 *      The student was never late ('L') for 3 or more consecutive days.
 * Return true if the student is eligible for an attendance award, or false otherwise.
 *
 * Example 1:
 *      Input: s = "PPALLP"
 *      Output: true
 *      Explanation: The student has fewer than 2 absences and was never late 3 or more consecutive days.
 *
 * Example 2:
 *      Input: s = "PPALLL"
 *      Output: false
 *      Explanation: The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.
 *
 * Constraints:
 *      1 <= s.length <= 1000
 *      s[i] is either 'A', 'L', or 'P'.
 */
class StudentAttendanceRecordI {
    /**
     * Initial thoughts
     * Going through the string and record the conditions
     *
     * for absent, the algo records the number. At moment when
     * it is more than 1, return false
     *
     * For late, we can check the previous 3 records, or
     * record it in a pair (number of late, last time when he was late)
     *
     * Small optimization:
     * - If it is L
     *  -> increase the count for L
     *  -> Check if it is bigger than 2
     * -> else
     *  Reset L
     */
    private fun checkRecord(s: String): Boolean {
        // 1. Init the data
        var wasAbsentBefore = false
        var numberOfConsecutiveLate = 0

        // 2. Loop
        for (i in s.indices) {
            if (s[i] == 'A') {
                if (wasAbsentBefore) {
                    return false
                } else {
                    wasAbsentBefore = true
                }
            }
            if (s[i] == 'L') {
                numberOfConsecutiveLate ++
                if (numberOfConsecutiveLate > 2) {
                    return false
                }
            } else {
                numberOfConsecutiveLate = 0
            }
        }

        // 3. default case
        return true
    }

    @Test
    fun test1() {
        // Given
        val studentRecord = "PPALLP"

        // When
        val result = checkRecord(studentRecord)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val studentRecord = "PPALLL"

        // When
        val result = checkRecord(studentRecord)

        // Then
        assertFalse(result)
    }
}