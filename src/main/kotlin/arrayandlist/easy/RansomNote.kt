package arrayandlist.easy

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Ransom Note
 * Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him
 * through his handwriting. He found a magazine and wants to know if he can cut out whole words from it
 * and use them to create an untraceable replica of his ransom note. The words in his note are case-sensitive
 * and he must use only whole words available in the magazine. He cannot use substrings or concatenation
 * to create the words he needs.

 * Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his ransom
 * note exactly using whole words from the magazine; otherwise, print No.
 * Example:
 *  managezine = "attack at dawn"  note = "Attack at dawn"
 * The magazine has all the right words, but there is a case mismatch. The answer is No.
 */
class RansomNote {

    /**
     * Initial thought
     * We need to find if the magazine contains all the words from note. So in the dictionary
     * we need to put all the words of note, and then check it with magazine.
     *
     * Note the words from note could be repeated. In this case, we just need one of them. So
     * we compare the occurrences and check if the magazine has at least one of the each
     * word required by note
     *
     */
    private fun checkMagazine(magazine: Array<String>, note: Array<String>): Boolean {
        // Corner case
        if (note.isEmpty()) {
            println("Yes")
            return true
        }

        // Index all the elements in the note with hashmap
        val dictionary = HashMap<String, Int>()

        note.forEach{
            var occurrences = dictionary.getOrDefault(it, 0)
            dictionary[it] = ++occurrences
        }

        // Extract the ocurrences in the magazine
        magazine.forEach {
            val occurrences = dictionary.get(it)
            // If the dictionary has it
            occurrences?.let { occurrencesNotNull ->
                dictionary[it] = occurrencesNotNull - 1
            }
        }

        dictionary.forEach {(_, v) ->
            if (v > 0) {
                return false
            }
        }

        return true
    }

    @Test
    fun test1() {
        // Given
        val magazine = arrayOf("give", "me", "one", "grand", "today", "night")
        val note = arrayOf("give", "one", "grand", "today")

        // When
        val result = checkMagazine(magazine, note)

        // Then
        assertTrue(result)
    }
    @Test
    fun test2() {
        // Given
        val magazine = arrayOf("ive", "got", "a", "lovely", "bunch", "of", "coconuts")
        val note = arrayOf("ive", "got", "some", "coconuts")

        // When
        val result = checkMagazine(magazine, note)

        // Then
        assertFalse(result)
    }
}