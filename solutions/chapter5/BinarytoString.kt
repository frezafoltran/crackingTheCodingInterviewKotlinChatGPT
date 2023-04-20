//Class with algorithm:


class BinarytoString {
    // time complexity: O(1)
    // space complexity: O(1)

    fun printBinary(num: Double): String {
        if (num >= 1 | | num <= 0){
            return "ERROR"
        }

        val binary = StringBuilder()
        var fraction = 0.5

        binary.append(".")
        while (num > 0){
            if (binary.length > 32){
                return "ERROR"
            }

            if (num >= fraction){
                binary.append(1)
                num -= fraction
            } else {
                binary.append(0)
            }
            fraction /= 2
        }

        return binary.toString()
    }
}


//Test class:


class BinarytoStringTest {

    @ Test
    fun `test printing binary for valid input`() {
        assertEquals(".101", BinarytoString().printBinary(0.625))
        assertEquals(".101011", BinarytoString().printBinary(0.4140625))
    }

    @ Test
    fun `test printing binary for invalid input`() {
        assertEquals("ERROR", BinarytoString().printBinary(1.5))
        assertEquals("ERROR", BinarytoString().printBinary(-0.1))
    }

}
