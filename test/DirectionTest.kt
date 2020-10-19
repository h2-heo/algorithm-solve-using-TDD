import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DirectionTest {
    @Test
    fun testNextOfLeftDownIsRight() {
        assertEquals(Direction.LEFT_DOWN.next(), Direction.RIGHT)
    }

    @Test
    fun testNextOfRightIsLeftUp() {
        assertEquals(Direction.RIGHT.next(), Direction.LEFT_UP)
    }

    @Test
    fun testNextOfLeftUpIsLeftDown() {
        assertEquals(Direction.LEFT_UP.next(), Direction.LEFT_DOWN)
    }
}