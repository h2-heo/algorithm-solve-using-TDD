class Solution {
    fun solution(n: Int): IntArray {
        return createTriangularSnailArray(n)
    }

    private fun createTriangularSnailArray(n: Int): IntArray {
        val triangle = Triangle.zeros(n)
        drawTriangularSnail(triangle)
        return triangle.toArray()
    }

    private fun drawTriangularSnail(triangle: Triangle) {
        val searcher = TriangularSnailSearcher(triangle, triangle.top, Direction.LEFT_DOWN)
        var i = 1
        while (searcher.hasNext()) {
            val currentPoint = searcher.next()
            triangle.setValueAt(currentPoint, i)
            i++
        }
    }
}