class QuadTree(
        val value: Int = -1,
) {

    private val _children = arrayListOf<QuadTree>()
    val children: List<QuadTree>
        get() = _children

    fun isLeaf(): Boolean {
        return children.isEmpty()
    }

    companion object {
        fun create(value: Int): QuadTree {
            require(value in 0..1)

            return QuadTree(value)
        }

        fun create(q0: QuadTree, q1: QuadTree, q2: QuadTree, q3: QuadTree): QuadTree {
            val instance = QuadTree()
            instance._children += listOf(q0, q1, q2, q3)
            return instance
        }
    }
}
