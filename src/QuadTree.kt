class QuadTree {

    fun countElements(): IntArray {
        return QuadTreeElementCounter().visit(this)
    }

    companion object {
        fun of(matrix: Array<IntArray>): QuadTree {
            return QuadTreeMatrixParser().parse(matrix)
        }

        fun create(value: Int): QuadTree {
            TODO("Not yet implemented")
        }

        fun create(q0: QuadTree, q1: QuadTree, q2: QuadTree, q3: QuadTree): QuadTree {
            TODO("Not yet implemented")
        }
    }
}
