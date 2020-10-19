import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class TriangleTest {
    @Test
    fun testCreationSuccessWhenTriangularInput() {
        assertDoesNotThrow {
            Triangle.create(arrayOf(
                    intArrayOf(0)
            ))
        }
        assertDoesNotThrow {
            Triangle.create(arrayOf(
                    intArrayOf(0),
                    intArrayOf(0, 0)
            ))
        }
        assertDoesNotThrow {
            Triangle.create(arrayOf(
                    intArrayOf(0),
                    intArrayOf(0, 0),
                    intArrayOf(0, 0, 0)
            ))
        }
    }

    @Test
    fun testCreationFailWhenNotTriangularInput() {
        assertThrows<IllegalArgumentException> {
            Triangle.create(arrayOf(
                    intArrayOf(0, 0)
            ))
        }
        assertThrows<IllegalArgumentException> {
            Triangle.create(arrayOf(
                    intArrayOf(0),
                    intArrayOf(0, 0),
                    intArrayOf(0, 0)
            ))
        }
        assertThrows<IllegalArgumentException> {
            Triangle.create(arrayOf(
                    intArrayOf(0, 0),
                    intArrayOf(0)
            ))
        }
    }

    @Test
    fun testZeroes() {
        val triangle = Triangle.zeros(3)
        val result = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 3),
                intArrayOf(4, 5, 6)
        ))
        assertEquals(triangle, result)
    }

    @Test
    fun testTopIsAlwaysZeroZero() {
        assertEquals(Triangle.zeros(0).top, Point(0, 0))
        assertEquals(Triangle.zeros(1).top, Point(0, 0))
        assertEquals(Triangle.zeros(2).top, Point(0, 0))
    }

    @Test
    fun testToArray() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9, 10)
        ))
        val result = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertArrayEquals(triangle.toArray(), result)
    }

    @Test
    fun testSetValueAtPoint() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9, 10)
        ))

        triangle.setValueAt(Point(1, 1), 33)
        triangle.setValueAt(Point(2, 1), 55)
        triangle.setValueAt(Point(3, 0), 77)

        val result = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 33),
                intArrayOf(4, 55, 6),
                intArrayOf(77, 8, 9, 10)
        ))

        assertEquals(triangle, result)
    }

    @Test
    fun testGetValueAtPoint() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 3),
                intArrayOf(4, 5, 6)
        ))

        assertEquals(triangle.getValueAt(Point(0, 0)), 1)
        assertEquals(triangle.getValueAt(Point(1, 0)), 2)
        assertEquals(triangle.getValueAt(Point(1, 1)), 3)
        assertEquals(triangle.getValueAt(Point(2, 0)), 4)
        assertEquals(triangle.getValueAt(Point(2, 1)), 5)
        assertEquals(triangle.getValueAt(Point(2, 2)), 6)
    }

    @Test
    fun testContainsValidPoint() {
        val triangle = Triangle.zeros(3)

        assertTrue(triangle.contains(Point(0, 0)))
        assertTrue(triangle.contains(Point(1, 0)))
        assertTrue(triangle.contains(Point(1, 1)))
        assertTrue(triangle.contains(Point(2, 0)))
        assertTrue(triangle.contains(Point(2, 1)))
        assertTrue(triangle.contains(Point(2, 2)))
    }

    @Test
    fun testContainsInvalidPoint() {
        val triangle = Triangle.zeros(3)

        assertFalse(triangle.contains(Point(-1, -1)))
        assertFalse(triangle.contains(Point(-1, 0)))
        assertFalse(triangle.contains(Point(0, -1)))
        assertFalse(triangle.contains(Point(0, 1)))
        assertFalse(triangle.contains(Point(3, 0)))
        assertFalse(triangle.contains(Point(2, 3)))
    }
}