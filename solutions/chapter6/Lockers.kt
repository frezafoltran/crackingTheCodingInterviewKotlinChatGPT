class Lockers {
    // time complexity: O(n ^ 2), where n is the number of lockers(100 in this case)
    // space complexity: O(n), where n is the number of lockers
    fun countOpenLockers(): Int {
        val lockers = BooleanArray(100) {true}
        for (i in 2..100) {
            for (j in i - 1 until 100 step i) {
                lockers[j] = !lockers[j]
            }
        }
        return lockers.count {it}
    }
}


class LockersTest {

    @ Test
    fun `count open lockers`() {
        val lockers = Lockers()
        assertEquals(10, lockers.countOpenLockers())
    }
}
