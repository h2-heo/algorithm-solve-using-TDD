import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuadTreeElementCounterTest {

    private val counter = QuadTreeElementCounter()

    @Test
    fun `test visiting leaf 0`() {
        val result = intArrayOf(1, 0)

        val tree = QuadTree.create(0)

        assertArrayEquals(result, counter.visit(tree))
    }

    @Test
    fun `test visiting leaf 1`() {
        val result = intArrayOf(0, 1)

        val tree = QuadTree.create(1)

        assertArrayEquals(result, counter.visit(tree))
    }

    @Test
    fun `should count values for all descendants`() {
        val result = intArrayOf(3, 4)

        val q0 = QuadTree.create(0)
        val q1 = QuadTree.create(1)
        val q2 = QuadTree.create(
                QuadTree.create(1), QuadTree.create(1),
                QuadTree.create(1), QuadTree.create(0)
        )
        val q3 = QuadTree.create(0)
        val tree = QuadTree.create(
                q0, q1,
                q2, q3,
        )

        assertArrayEquals(result, counter.visit(tree))
    }
}
