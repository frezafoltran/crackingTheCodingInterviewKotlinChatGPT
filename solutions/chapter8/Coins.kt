class Coins {
    // dynamic programming
    // time complexity: O(n), where n is the target value
    // space complexity: O(n)

    fun calculateWays(n: Int): Int {
        val coins = intArrayOf(1, 5, 10, 25)
        val ways = IntArray(n + 1)
        ways[0] = 1

        for (coin in coins) {
            for (i in coin..n) {
                ways[i] += ways[i - coin]
            }
        }
        return ways[n]
    }
}


class CoinsTest {
    @ Test
    fun `example input`() {
        val coins = Coins()
        assertEquals(2, coins.calculateWays(5))
        assertEquals(4, coins.calculateWays(10))
        assertEquals(13, coins.calculateWays(25))
    }

    @ Test
    fun `edge cases`() {
        val coins = Coins()
        // minimum value
        assertEquals(1, coins.calculateWays(1))
        // largest value with exact number of quarters for simplicity
        assertEquals(1, coins.calculateWays(100))
        // largest value with random combination of coins
        assertEquals(242, coins.calculateWays(99))
    }
}
