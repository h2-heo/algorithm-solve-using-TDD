data class Rect(
        val top: Int,
        val left: Int,
        val bottom: Int,
        val right: Int,
) {

    fun splitQuad(): List<Rect> {
        val midY = (top + bottom) / 2
        val midX = (left + right) / 2
        return listOf(
                Rect(top, left, midY, midX),
                Rect(top, midX, midY, right),
                Rect(midY, left, bottom, midX),
                Rect(midY, midX, bottom, right),
        )
    }

    fun points(): Sequence<Pair<Int, Int>> {
        return sequence {
            for (y in top until bottom) {
                for (x in left until right) {
                    yield(Pair(y, x))
                }
            }
        }
    }

    companion object {
        fun wholeOf(matrix: Array<IntArray>): Rect {
            val height = matrix.size
            val width = matrix[0].size
            return Rect(0, 0, height, width)
        }
    }
}
