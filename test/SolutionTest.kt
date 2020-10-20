import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun `test example case #1`() {
        val result = intArrayOf(4, 9)

        val arr = arrayOf(
                intArrayOf(1, 1, 0, 0),
                intArrayOf(1, 0, 0, 0),
                intArrayOf(1, 0, 0, 1),
                intArrayOf(1, 1, 1, 1),
        )

        assertArrayEquals(result, Solution().solution(arr))
    }

    @Test
    fun `test example case #2`() {
        val result = intArrayOf(10, 15)

        val arr = arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
                intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
        )

        assertArrayEquals(result, Solution().solution(arr))
    }
}
