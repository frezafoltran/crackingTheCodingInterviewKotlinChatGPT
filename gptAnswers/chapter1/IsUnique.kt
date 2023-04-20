class IsUnique {
    // time complexity: O(n)
    // space complexity: O(1) (constant, since we are only checking for 128 possible characters)

    fun isUnique(str: String): Boolean {
        if(str.length > 128) return false // since the string can't have more than 128 unique characters
        val charSet = BooleanArray(128) // assume ASCII characterset
        for (i in str.indices) {
            val charIndex = str[i].toInt()
            if(charSet[charIndex]) return false
            charSet[charIndex] = true
        }
        return true
    }
}

class IsUniqueTest {

    @Test
    fun `unique characters`() {
        val uniqueString = "abcdefghijklmnopqrstuvwxyz"
        assertTrue(IsUnique().isUnique(uniqueString))
    }

    @Test
    fun `non-unique characters`() {
        val nonUniqueString = "aabbcdefghijklmnopqrstuvwxyz"
        assertFalse(IsUnique().isUnique(nonUniqueString))
    }

    @Test
    fun `empty string`() {
        assertTrue(IsUnique().isUnique(""))
    }

    @Test
    fun `string with more than 128 characters`() {
        val longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+"
        assertFalse(IsUnique().isUnique(longString))
    }
}