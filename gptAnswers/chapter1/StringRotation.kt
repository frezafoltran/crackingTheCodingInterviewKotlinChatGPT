class StringRotation {

    fun isRotation(s1: String, s2: String): Boolean {
        if(s1.length != s2.length) return false
        val s1s1 = s1 + s1
        return isSubstring(s1s1, s2)
    }

    private fun isSubstring(s1: String, s2: String): Boolean {
        return s1.contains(s2)
    }
}

class StringRotationTest {

   @Test
   fun `is rotation`() {
        val s1 = "waterbottle"
        val s2 = "erbottlewat"
        val stringRotation = StringRotation()
        assertTrue(stringRotation.isRotation(s1, s2))
   }

   @Test
   fun `different length`() {
        val s1 = "water"
        val s2 = "erwat"
        val stringRotation = StringRotation()
        assertFalse(stringRotation.isRotation(s1, s2))
   }

   @Test
   fun `not rotation`() {
        val s1 = "waterbottle"
        val s2 = "rottlewat"
        val stringRotation = StringRotation()
        assertFalse(stringRotation.isRotation(s1, s2))
   }

}