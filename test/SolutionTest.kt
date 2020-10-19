import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SolutionTest {
    @Test
    fun testSolutionWhen1() {
        val n = 1
        val answer = intArrayOf(1)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }

    @Test
    fun testSolutionWhen2() {
        val n = 2
        val answer = intArrayOf(1, 2, 3)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }

    @Test
    fun testSolutionWhen3() {
        val n = 3
        val answer = intArrayOf(1, 2, 6, 3, 4, 5)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }

    @Test
    fun testSolutionWhen4() {
        val n = 4
        val answer = intArrayOf(1, 2, 9, 3, 10, 8, 4, 5, 6, 7)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }

    @Test
    fun testSolutionWhen5() {
        val n = 5
        val answer = intArrayOf(1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }

    @Test
    fun testSolutionWhen6() {
        val n = 6
        val answer = intArrayOf(1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11)
        val solution = Solution()
        assertArrayEquals(solution.solution(n), answer)
    }
}