//:


class PermutationsWithoutDups {
    // Time complexity: O(n!), where n is the length of the string
    // Space complexity: O(n)

    fun findPermutations(str: String): List < String > {
        if (str.isEmpty()) return emptyList()

        val permutations = mutableListOf < String > ()
        val usedChars = BooleanArray(str.length)

        findPermutationsHelper(str, usedChars, "", permutations)

        return permutations
    }

    private fun findPermutationsHelper(str: String, usedChars: BooleanArray, curr: String, permutations: MutableList < String >) {
        if (curr.length == str.length) {
            permutations.add(curr)
            return
        }

        for (i in 0 until str.length) {
            if (!usedChars[i]) {
                usedChars[i] = true
                findPermutationsHelper(str, usedChars, curr + str[i], permutations)
                usedChars[i] = false
            }
        }
    }
}


class PermutationsWithoutDupsTest {

    private val permutationsWithoutDups = PermutationsWithoutDups()

    @ Test
    fun `empty string`() {
        val result = permutationsWithoutDups.findPermutations("")
        assertEquals(emptyList(), result)
    }

    @ Test
    fun `single character string`() {
        val result = permutationsWithoutDups.findPermutations("a")
        assertEquals(listOf("a"), result)
    }

    @ Test
    fun `two character string`() {
        val result = permutationsWithoutDups.findPermutations("ab")
        assertEquals(listOf("ab", "ba"), result)
    }

    @ Test
    fun `three character string`() {
        val result = permutationsWithoutDups.findPermutations("abc")
        assertEquals(listOf("abc", "acb", "bac", "bca", "cab", "cba"), result)
    }

}
