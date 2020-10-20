class QuadTreeMatrixParser {

    fun parse(matrix: Array<IntArray>): QuadTree {
        val wholeRect = Rect.of(matrix)
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

private fun <T> Array<T>.getAnyValueIn(rect: Rect): Int {
    TODO("Not yet implemented")
}

private infix fun Array<IntArray>.canCompressIn(rect: Rect): Boolean {
    TODO("Not yet implemented")
}
