:

class CheckPermutation {
    // time complexity: O(n log n), where n is the length of the longest string
    // space complexity: O(n)

    fun isPermutation(str1: String, str2: String): Boolean {
        if(str1.length != str2.length) return false
        val sortedStr1 = str1.toCharArray().sorted().joinToString("")
        val sortedStr2 = str2.toCharArray().sorted().joinToString("")
        return sortedStr1 == sortedStr2
    }
}

class CheckPermutationTest {

    @Test
    fun `is permutation`() {
        assertTrue(CheckPermutation().isPermutation("abcd", "dcba"))
        assertTrue(CheckPermutation().isPermutation("abc", "bac"))
    }

    @Test
    fun `not permutation`() {
        assertFalse(CheckPermutation().isPermutation("abc", "def"))
        assertFalse(CheckPermutation().isPermutation("abcd", "abc"))
    }

}