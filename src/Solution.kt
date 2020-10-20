class Solution {

    fun solution(arr: Array<IntArray>): IntArray {
        val tree = QuadTree.of(arr)
        return tree.countElements()
    }
}
