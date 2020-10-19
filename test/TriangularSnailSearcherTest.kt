import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class TriangularSnailSearcherTest {
    @Test
    fun testHasNextWhenFirst() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(0),
                intArrayOf(0, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, triangle.top, Direction.LEFT_DOWN)
        assertTrue(searcher.hasNext())
    }

    @Test
    fun testHasNextWhenHasNext() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, Point(1, 1), Direction.LEFT_UP)
        assertTrue(searcher.hasNext())
    }

    @Test
    fun testHasNextWhenNoNext() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 3)
        ))
        val searcher = TriangularSnailSearcher(triangle, Point(1, 1), Direction.LEFT_UP)
        assertFalse(searcher.hasNext())
    }

    @Test
    fun testNextCanBeCycle() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(0),
                intArrayOf(0, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, triangle.top, Direction.LEFT_DOWN)
        assertEquals(searcher.next(), Point(0, 0))
        assertEquals(searcher.next(), Point(1, 0))
        assertEquals(searcher.next(), Point(1, 1))
        assertEquals(searcher.next(), Point(0, 0))
        assertEquals(searcher.next(), Point(1, 0))
        assertEquals(searcher.next(), Point(1, 1))
    }

    @Test
    fun testNextTurnsWhenMeetsNonZero() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(0, 0),
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, Point(1, 0), Direction.LEFT_DOWN)
        assertEquals(searcher.next(), Point(1, 0))
        assertEquals(searcher.next(), Point(2, 0))
        assertEquals(searcher.next(), Point(3, 0))
        assertEquals(searcher.next(), Point(3, 1))
        assertEquals(searcher.next(), Point(3, 2))
        assertEquals(searcher.next(), Point(3, 3))
        assertEquals(searcher.next(), Point(2, 2))
        assertEquals(searcher.next(), Point(1, 1))
        assertEquals(searcher.next(), Point(2, 1))
    }
}