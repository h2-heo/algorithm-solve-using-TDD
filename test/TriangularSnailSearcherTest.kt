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
        assertFalse(searcher.hasNext())
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
    fun testNext1() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(0)
        ))
        val searcher = TriangularSnailSearcher(triangle, triangle.top, Direction.LEFT_DOWN)
        assertEquals(searcher.next(), Point(0, 0))
        assertThrows<IllegalStateException> { searcher.next() }
    }

    @Test
    fun testNext2() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(0),
                intArrayOf(0, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, triangle.top, Direction.LEFT_DOWN)
        assertEquals(searcher.next(), Point(0, 0))
        assertEquals(searcher.next(), Point(1, 0))
        assertEquals(searcher.next(), Point(1, 1))
        assertThrows<IllegalStateException> { searcher.next() }
    }

    @Test
    fun testNext3() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(0, 0),
                intArrayOf(0, 0, 0)
        ))
        val searcher = TriangularSnailSearcher(triangle, Point(1, 0), Direction.LEFT_DOWN)
        assertEquals(searcher.next(), Point(1, 0))
        assertEquals(searcher.next(), Point(2, 0))
        assertEquals(searcher.next(), Point(2, 1))
        assertEquals(searcher.next(), Point(2, 2))
        assertEquals(searcher.next(), Point(1, 1))
        assertThrows<IllegalStateException> { searcher.next() }
    }

    @Test
    fun testNext4() {
        val triangle = Triangle.create(arrayOf(
                intArrayOf(1),
                intArrayOf(2, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(4, 5, 6, 7)
        ))
        val searcher = TriangularSnailSearcher(triangle, Point(2, 2), Direction.LEFT_UP)
        assertEquals(searcher.next(), Point(2, 2))
        assertEquals(searcher.next(), Point(1, 1))
        assertEquals(searcher.next(), Point(2, 1))
        assertThrows<IllegalStateException> { searcher.next() }
    }
}