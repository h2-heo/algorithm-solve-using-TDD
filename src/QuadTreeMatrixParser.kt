class QuadTreeMatrixParser {

    fun parse(matrix: Array<IntArray>): QuadTree {
        val wholeRect = Rect.wholeOf(matrix)
        return parse(matrix, wholeRect)
    }

    private fun parse(matrix: Array<IntArray>, rect: Rect): QuadTree {
        return if (matrix canCompressIn rect) {
            val value = matrix.getAnyValueIn(rect)
            QuadTree.create(value)
        } else {
            val (r0, r1, r2, r3) = rect.splitQuad()
            QuadTree.create(
                    parse(matrix, r0), parse(matrix, r1),
                    parse(matrix, r2), parse(matrix, r3),
            )
        }
    }
}

private fun Array<IntArray>.getAnyValueIn(rect: Rect): Int {
    val (y, x) = rect.points().first()
    return this[y][x]
}

private infix fun Array<IntArray>.canCompressIn(rect: Rect): Boolean {
    return this hasAllTheSameValueIn rect
}

private infix fun Array<IntArray>.hasAllTheSameValueIn(rect: Rect): Boolean {
    val v = this.getAnyValueIn(rect)
    return rect.points().all { (y, x) -> this[y][x] == v }
}
