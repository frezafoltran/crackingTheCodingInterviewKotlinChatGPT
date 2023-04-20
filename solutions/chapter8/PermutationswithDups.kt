class PermutationsWithDups {
    // time complexity: O(n!), where n is the length of the string with duplicates
    // space complexity: O(n!)

    fun getPermutations(s: String): List < String > {
        val result = mutableListOf < String > ()
        val counts = getCharCounts(s)

        getPermutations("", s.length, counts, result)

        return result
    }

    private fun getPermutations(prefix: String, remaining: Int, counts: Map < Char, Int >, result: MutableList < String > ) {
        if (remaining == 0) {
            result.add(prefix)
            return
        }

        counts.forEach {char, count ->
                        if (count > 0) {
                            val newCounts = counts.toMutableMap()
                            newCounts[char] = count - 1
                            getPermutations(prefix + char, remaining - 1, newCounts, result)
                        }
                        }
    }

    private fun getCharCounts(s: String): Map < Char, Int > {
        val counts = mutableMapOf < Char, Int > ()

        s.forEach {char ->
                   counts[char] = counts.getOrDefault(char, 0) + 1
                   }

        return counts
    }
}


class PermutationsWithDupsTest {

    @ Test
    fun `getPermutations with duplicates`() {
        val expected = listOf("abc", "acb", "bac", "bca", "cab", "cba")
        val actual = PermutationsWithDups().getPermutations("abc")
        assertEquals(expected, actual)
    }

    @ Test
    fun `getPermutations with repeating characters`() {
        val expected = listOf("aabb", "abab", "abba", "baab", "baba", "bbaa")
        val actual = PermutationsWithDups().getPermutations("aabb")
        assertEquals(expected, actual)
    }

    @ Test
    fun `getPermutations with empty string`() {
        val expected = listOf("")
        val actual = PermutationsWithDups().getPermutations("")
        assertEquals(expected, actual)
    }

}
