class Parens {

    // time complexity: O(2 ^ n), where n is the number of pairs of parentheses
    // space complexity: O(n)

    fun generateParenthesis(n: Int): List < String > {
        val result = mutableListOf < String > ()
        generate("", n, n, result)
        return result
    }

    private fun generate(str: String, left: Int, right: Int, result: MutableList < String > ) {
        if (left == 0 & & right == 0) {
            result.add(str)
            return
        }
        if (left > 0) {
            generate("$str(", left - 1, right, result)
        }
        if (right > left) {
            generate("$str)", left, right - 1, result)
        }
    }
}


class ParensTest {

    @ Test
    fun `generate valid parentheses combinations for n = 1`() {
        val expected = listOf("()")
        val actual = Parens().generateParenthesis(1)
        assertEquals(expected, actual)
    }

    @ Test
    fun `generate valid parentheses combinations for n = 2`() {
        val expected = listOf("(())", "()()")
        val actual = Parens().generateParenthesis(2)
        assertEquals(expected, actual)
    }

    @ Test
    fun `generate valid parentheses combinations for n = 3`() {
        val expected = listOf("((()))", "(()())", "(())()", "()(())", "()()()")
        val actual = Parens().generateParenthesis(3)
        assertEquals(expected, actual)
    }

    @ Test
    fun `generate valid parentheses combinations for n = 4`() {
        val expected = listOf("(((())))", "((()()))", "((())())", "((()))()", "(()(()))",
                              "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())",
                              "()(())()", "()()(())", "()()()()")
        val actual = Parens().generateParenthesis(4)
        assertEquals(expected, actual)
    }
}
