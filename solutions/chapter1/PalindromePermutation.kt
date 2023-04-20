//Class: PalindromePermutation
//Algorithm:
//1. Create a frequency map of characters in the input string by iterating over each character and keeping count of its occurrences.
//2. Iterate over the frequency map and keep track of the number of characters with an odd count.
//3. If the number of characters with odd count is greater than 1, return false as it cannot be a permutation of a palindrome. Otherwise, return true.

Time complexity: O(n)
Space complexity: O(n)

//Code:


class PalindromePermutation {
    fun isPermutationOfPalindrome(input: String): Boolean {
        val frequencyMap = mutableMapOf < Char, Int > ()

        for (char in input) {
            frequencyMap[char] = (frequencyMap[char] ?: 0) + 1
        }

        var oddCount = 0
        for (count in frequencyMap.values) {
            if (count % 2 != 0) {
                oddCount++
            }
        }

        return oddCount <= 1
    }
}


//Class: PalindromePermutationTest
//Algorithm:
//1. Test for a string that is a permutation of a palindrome.
//2. Test for a string that is not a permutation of a palindrome.
//3. Test for an empty string.
//4. Test for a single character string.

//Code:


class PalindromePermutationTest {
    private val palindromePermutation = PalindromePermutation()

    @ Test
    fun `is permutation of palindrome`() {
        assertTrue(palindromePermutation.isPermutationOfPalindrome("taco cat"))
        assertTrue(palindromePermutation.isPermutationOfPalindrome("atco eta"))
    }

    @ Test
    fun `is not permutation of palindrome`() {
        assertFalse(palindromePermutation.isPermutationOfPalindrome("hello world"))
        assertFalse(palindromePermutation.isPermutationOfPalindrome("banana"))
    }

    @ Test
    fun `empty string`() {
        assertTrue(palindromePermutation.isPermutationOfPalindrome(""))
    }

    @ Test
    fun `single character string`() {
        assertTrue(palindromePermutation.isPermutationOfPalindrome("a"))
    }
}
