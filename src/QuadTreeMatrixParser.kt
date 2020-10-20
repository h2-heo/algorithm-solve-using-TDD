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
    val firstValue = this[rect.top][rect.left]
    return firstValue
}

private infix fun Array<IntArray>.canCompressIn(rect: Rect): Boolean {
    return this hasSameValue rect
}

private infix fun Array<IntArray>.hasSameValue(rect: Rect): Boolean {
    val v = this.getAnyValueIn(rect)
    for (y in rect.top until rect.bottom ) {
        for (x in rect.left until rect.right ) {
            if (this[y][x] != v) {
                return false
            }
        }
    }
    return true
}
