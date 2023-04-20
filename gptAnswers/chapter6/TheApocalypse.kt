class Apocalypse:
    fun genderRatio(numFamilies: Int, numChildren: Int): Double {
        var numGirls = 0
        var numBoys = 0
        for (i in 0 until numFamilies) {
            var hasGirl = false
            var currentChildren = 0
            while (!hasGirl && currentChildren < numChildren) {
                val gender = (0..1).random() // 0 for boy, 1 for girl
                if (gender == 1) {
                    hasGirl = true
                    numGirls++
                } else {
                    numBoys++
                }
                currentChildren++
            }
        }
        return numGirls.toDouble() / (numGirls + numBoys)
    }
}

class ApocalypseTest {
    @Test
    fun `test gender ratio`() {
        val apocalypse = Apocalypse()
        val expected = 0.5 // Equal chance of boy or girl
        val result = apocalypse.genderRatio(100000, 2)
        assertEquals(expected, result, 0.01)
    }
}