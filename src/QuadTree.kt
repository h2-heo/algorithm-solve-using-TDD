class QuadTree {

    fun countElements(): IntArray {
        return QuadTreeElementCounter().visit(this)
    }

    companion object {
        fun of(matrix: Array<IntArray>): QuadTree {
            return QuadTreeMatrixParser().parse(matrix)
        }
    }
}
