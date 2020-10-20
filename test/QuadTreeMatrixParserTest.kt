import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuadTreeMatrixParserTest {

    private val parser = QuadTreeMatrixParser()

    @Test
    fun `parse leaf 0`() {
        val result = QuadTree.create(0)

        val matrix = arrayOf(
                intArrayOf(0),
        )

        assertEquals(result, parser.parse(matrix))
    }

    @Test
    fun `parse leaf 1`() {
        val result = QuadTree.create(1)

        val matrix = arrayOf(
                intArrayOf(1),
        )

        assertEquals(result, parser.parse(matrix))
    }

    @Test
    fun `if all values are 0, compress them into one`() {
        val result = QuadTree.create(0)

        val matrix = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 0),
        )

        assertEquals(result, parser.parse(matrix))
    }

    @Test
    fun `if all values are 1, compress them into one`() {
        val result = QuadTree.create(1)

        val matrix = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1),
        )

        assertEquals(result, parser.parse(matrix))
    }

    @Test
    fun `if the values are different, do not compress`() {
        val result = QuadTree.create(
                QuadTree.create(0), QuadTree.create(1),
                QuadTree.create(1), QuadTree.create(1)
        )

        val matrix = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 1),
        )

        assertEquals(result, parser.parse(matrix))
    }

    @Test
    fun `divide it into four places and compress it`() {
        val q0 = QuadTree.create(
                QuadTree.create(0), QuadTree.create(1),
                QuadTree.create(1), QuadTree.create(1)
        )
        val q1 = QuadTree.create(
                QuadTree.create(1), QuadTree.create(0),
                QuadTree.create(1), QuadTree.create(1)
        )
        val q2 = QuadTree.create(1)
        val q3 = QuadTree.create(0)
        val result = QuadTree.create(
                q0, q1,
                q2, q3
        )

        val matrix = arrayOf(
                intArrayOf(0, 1, 1, 0),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 0, 0),
                intArrayOf(1, 1, 0, 0),
        )

        assertEquals(result, parser.parse(matrix))
    }
}
