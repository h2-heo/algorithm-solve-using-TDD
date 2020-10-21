class Solution {

    fun solution(arr: Array<IntArray>): IntArray {
        val tree = QuadTreeMatrixParser().parse(arr)
        return QuadTreeElementCounter().visit(tree)
    }
}
