class DrawLine {

    fun drawline(screen: ByteArray, width: Int, xl: Int, x2: Int, y: Int) {
        val startOffset = y * (width / 8) + (xl / 8)
        val endOffset = y * (width / 8) + (x2 / 8)

        val startMask = 0xFF shr(xl % 8)
        val endMask = 0xFF ushr(x2 % 8 + 1)

       if (startOffset == endOffset) {// case 1: xl and x2 are in the same byte
                                       screen[startOffset] = (startMask and endMask).toByte()
                                       } else {
           screen[startOffset] = startMask.toByte() // case 2: xl and x2 are in different bytes
           screen[endOffset] = endMask.toByte()

           // fill in the bytes between start and end offsets
           for (i in startOffset + 1 until endOffset) {
               screen[i] = 0xFF.toByte()
           }
        }
    }
}


class DrawLineTest {

    @ Test
    fun `draw line`() {
        val screen = ByteArray(16).apply {
            fill(0b11111111.toByte()) // fill screen with white
        }

        val expectedScreen = ByteArray(16).apply {
            fill(0b11100000.toByte(), 2, 4) // draw line from x = 2 to x = 4
        }

        DrawLine().drawline(screen, 24, 18, 30, 1) // draw line from (18, 1) to(30, 1)
        assertArrayEquals(expectedScreen, screen)
    }
}
