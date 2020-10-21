import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class QuadTreeTest {

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
