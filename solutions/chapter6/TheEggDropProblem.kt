class EggDropProblem {

    // time complexity: O(sqrt(N)), where N is the number of floors
    // space complexity: O(1)
    fun findBreakingFloor(): Int {
        var interval = 14
        var prevFloor = 0
        var egg1 = 0
        while (egg1 < 100) {
            prevFloor = egg1
            egg1 += interval
            if (egg1 >= 100) {
                egg1 = 99
            }
            var egg2 = prevFloor
            while (egg2 <= egg1) {
                if (egg2 == 99) {
                    return prevFloor + 1
                } else if (isEggBroken(egg2)) {
                    return egg2
                }
                egg2++
            }
            interval--
        }
        return 100
    }

    private fun isEggBroken(floor: Int): Boolean {
        return floor >= 85 // assume egg breaks if dropped from 85th floor and above
    }
}


class EggDropProblemTest {

    @ Test
    fun `findBreakingFloor test`() {
        assertEquals(85, EggDropProblem().findBreakingFloor())
    }
}
