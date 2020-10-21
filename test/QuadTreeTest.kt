import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `when creating by value, only values 0 and 1 are allowed`() {
        assertDoesNotThrow { QuadTree.create(0) }
        assertDoesNotThrow { QuadTree.create(1) }
        assertThrows<IllegalArgumentException> { QuadTree.create(-1) }
        assertThrows<IllegalArgumentException> { QuadTree.create(2) }
    }

    @Test
    fun `when created by value, can access value`() {
        assertEquals(0, QuadTree.create(0).value)
        assertEquals(1, QuadTree.create(1).value)
    }

    @Test
    fun `when created by value, should be leaf`() {
        val tree = QuadTree.create(0)

        assertTrue(tree.isLeaf())
    }

    @Test
    fun `when created by value, should not have child`() {
        val tree = QuadTree.create(0)

        assertEquals(0, tree.children.size)
    }

    @Test
    fun `when created by subtrees, should not be leaf`() {
        val tree = QuadTree.create(
                QuadTree.create(0), QuadTree.create(1),
                QuadTree.create(1), QuadTree.create(0),
        )

        assertFalse(tree.isLeaf())
    }

    @Test
    fun `when created by subtrees, can access them`() {
        val q0 = QuadTree.create(0)
        val q1 = QuadTree.create(0)
        val q2 = QuadTree.create(0)
        val q3 = QuadTree.create(0)
        val tree = QuadTree.create(
                q0, q1,
                q2, q3,
        )

        assertEquals(q0, tree.children[0])
        assertEquals(q1, tree.children[1])
        assertEquals(q2, tree.children[2])
        assertEquals(q3, tree.children[3])
    }
}
