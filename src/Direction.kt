enum class Direction {
    LEFT_DOWN,
    RIGHT,
    LEFT_UP;

    fun next(): Direction {
        return when (this) {
            LEFT_DOWN -> RIGHT
            RIGHT -> LEFT_UP
            LEFT_UP -> LEFT_DOWN
        }
    }
}
