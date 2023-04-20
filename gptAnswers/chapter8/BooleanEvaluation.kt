class BooleanEvaluation {
    // time complexity: O(n^2)
    // space complexity: O(n^2)
    fun countEval(s: String, result: Boolean): Int {
        val memo = mutableMapOf<Pair<String, Boolean>, Int>()
        return evaluate(s, result, memo)
    }

    private fun evaluate(s: String, result: Boolean, memo: MutableMap<Pair<String, Boolean>, Int>): Int {
        if (s.length == 1) {
            return if (s.toBoolean() == result) 1 else 0
        }
        if (memo.containsKey(s to result)) {
            return memo[s to result]!!
        }
        var ways = 0
        for (i in 1 until s.length step 2) {
            val left = s.substring(0, i)
            val operator = s[i]
            val right = s.substring(i + 1)
            val leftTrue = evaluate(left, true, memo)
            val leftFalse = evaluate(left, false, memo)
            val rightTrue = evaluate(right, true, memo)
            val rightFalse = evaluate(right, false, memo)
            val total = (leftTrue + leftFalse) * (rightTrue + rightFalse)
            var totalTrue = 0
            when (operator) {
                '&' -> totalTrue = leftTrue * rightTrue
                'I' -> totalTrue = (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue)
                '/' -> totalTrue = (leftTrue * rightFalse) + (leftFalse * rightTrue)
            }
            ways += if (result) totalTrue else total - totalTrue
        }
        memo[s to result] = ways
        return ways
    }
}

class BooleanEvaluationTest {

    @Test
    fun `example input 1`() {
        val ev = BooleanEvaluation()
        assertEquals(2, ev.countEval("l/\\01011", false))
    }

    @Test
    fun `example input 2`() {
        val ev = BooleanEvaluation()
        assertEquals(10, ev.countEval("0&0&0&1/\\II0", true))
    }
}