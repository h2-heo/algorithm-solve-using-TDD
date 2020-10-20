import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RectTest {

    @Test
    fun `create whole size of matrix`() {
        val result = Rect(0, 0, 4, 4)

        val matrix = arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
        )

        assertEquals(result, Rect.wholeOf(matrix))
    }

    @Test
    fun `split into four uniform rects`() {
        val result = listOf(
                Rect(2, 2, 3, 3),
                Rect(2, 3, 3, 4),
                Rect(3, 2, 4, 3),
                Rect(3, 3, 4, 4),
        )

        val rect = Rect(2, 2, 4, 4)

        assertEquals(result, rect.splitQuad())
    }
}