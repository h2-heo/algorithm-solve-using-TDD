class TriangularSnailSearcher(val triangle: Triangle, startPoint: Point, startDirection: Direction) {
    private var curPoint = startPoint
    private var curDirection = startDirection

    fun hasNext(): Boolean {
        return triangle.contains(curPoint) && triangle.getValueAt(curPoint) == 0
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
        val nextPoint = curPoint.moveTo(curDirection)
        if (triangle.contains(nextPoint) && triangle.getValueAt(nextPoint) == 0) {
            curPoint = nextPoint
            return
        }

        curDirection = curDirection.next()
        curPoint = curPoint.moveTo(curDirection)
    }

}
