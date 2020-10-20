data class Rect(
        val top: Int,
        val left: Int,
        val bottom: Int,
        val right: Int,
) {

    fun splitQuad(): List<Rect> {
        TODO("Not yet implemented")
    }

    companion object {
        fun wholeOf(matrix: Array<IntArray>): Rect {
            TODO("Not yet implemented")
        }
    }
}
