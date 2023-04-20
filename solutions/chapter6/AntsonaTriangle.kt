class AntsOnTriangle {

    // time complexity: O(1)
    // space complexity: O(1)
    fun collisionProbabilityOnTriangle(): Double {
        return 2.0/3.0
    }

    // time complexity: O(n)
    // space complexity: O(1)
    fun collisionProbabilityOnPolygon(n: Int): Double {
        return 1 - Math.pow(2.0, -n)
    }
}


class AntsOnTriangleTest {

    @ Test
    fun `collision probability on triangle`() {
        val antsOnTriangle = AntsOnTriangle()
        assertEquals(2.0/3.0, antsOnTriangle.collisionProbabilityOnTriangle())
    }

    @ Test
    fun `collision probability on polygon`() {
        val antsOnTriangle = AntsOnTriangle()
        assertEquals(1.0/2.0, antsOnTriangle.collisionProbabilityOnPolygon(2))
        assertEquals(3.0/4.0, antsOnTriangle.collisionProbabilityOnPolygon(3))
        assertEquals(7.0/8.0, antsOnTriangle.collisionProbabilityOnPolygon(4))
    }
}
