import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuadTreeTest {

    @Test
    fun `test countElements() when only leaf 0`() {
        val result = intArrayOf(1, 0)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(0),
        ))

        assertArrayEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() when only leaf 1`() {
        val result = intArrayOf(0, 1)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(1),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #1`() {
        val result = intArrayOf(1, 0)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 0),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #2`() {
        val result = intArrayOf(0, 1)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #3`() {
        val result = intArrayOf(1, 3)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 1),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #4`() {
        val result = intArrayOf(0, 1)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #5`() {
        val result = intArrayOf(1, 3)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(0, 0, 1, 1),
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
        ))

        assertEquals(result, tree.countElements())
    }

    @Test
    fun `test countElements() #6`() {
        val result = intArrayOf(7, 6)

        val tree = QuadTree.of(arrayOf(
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 0, 1, 1),
                intArrayOf(0, 1, 1, 0),
                intArrayOf(0, 1, 1, 0),
        ))

        assertEquals(result, tree.countElements())
    }
}
