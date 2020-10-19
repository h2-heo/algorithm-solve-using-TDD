class TriangularSnailSearcher(val triangle: Triangle, startPoint: Point, startDirection: Direction) {
    private var curPoint = startPoint
    private var curDirection = startDirection

    fun hasNext(): Boolean {
        return triangle.getValueAt(curPoint) == 0
    }

    fun next(): Point {
        if (!hasNext()) {
            throw IllegalStateException()
        }

        val ret = curPoint
        moveToNext()
        return ret
    }

    private fun moveToNext() {
        var nextPoint = curPoint.moveTo(curDirection)
        if (triangle.contains(nextPoint) && triangle.getValueAt(nextPoint) == 0) {
            curPoint = nextPoint
            return
        }

        curDirection = curDirection.next()
        nextPoint = curPoint.moveTo(curDirection)
        if (triangle.contains(nextPoint) && triangle.getValueAt(nextPoint) == 0) {
            curPoint = nextPoint
            return
        }

        throw IllegalStateException()
    }

}
