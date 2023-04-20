import kotlin.test.assertEquals
import org.junit.Test
//Class with algorithm:


class Debugger {
    // O(1) time complexity

    fun explain(n: Int, n1: Int): String {
        return if ((n and n1) == 0) {
            "$n and $n1 have no common set bits."
        } else {
            "$n and $n1 have common set bits."
        }
    }
}


//Class with test:


class DebuggerTest {

    private val debugger = Debugger()

    @ Test
    fun `n and n1 have no common set bits`() {
        val result = debugger.explain(1, 2)
        assertEquals("1 and 2 have no common set bits.", result)
    }

    @ Test
    fun `n and n1 have common set bits`() {
        val result = debugger.explain(3, 2)
        assertEquals("3 and 2 have common set bits.", result)
    }
}
