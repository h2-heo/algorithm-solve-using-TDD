import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PointTest {
    @Test
    fun testMoveToLeftDown() {
        var point = Point(0, 0)

        point = point.moveTo(Direction.LEFT_DOWN)
        assertEquals(point, Point(1, 0))

        point = point.moveTo(Direction.LEFT_DOWN)
        assertEquals(point, Point(2, 0))
    }

    @Test
    fun testMoveToRight() {
        var point = Point(2, 0)

        point = point.moveTo(Direction.RIGHT)
        assertEquals(point, Point(2, 1))

        point = point.moveTo(Direction.RIGHT)
        assertEquals(point, Point(2, 2))
    }

    @Test
    fun testMoveToLeftUp() {
        var point = Point(2, 2)

        point = point.moveTo(Direction.LEFT_UP)
        assertEquals(point, Point(1, 1))

        point = point.moveTo(Direction.LEFT_UP)
        assertEquals(point, Point(0, 0))
    }
}