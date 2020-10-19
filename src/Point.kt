data class Point(val row: Int, val col: Int) {
    fun moveTo(direction: Direction): Point {
        return when (direction) {
            Direction.LEFT_DOWN -> Point(row + 1, col)
            Direction.RIGHT -> Point(row, col + 1)
            Direction.LEFT_UP -> Point(row - 1, col - 1)
        }
    }
}