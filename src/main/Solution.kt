class Solution {

    fun solution(word: String, pages: Array<String>): Int {
        val webPages: List<WebPage> = pages.map { parsePage(it) }

        val searchEngine = SearchEngine()
        webPages.forEach { searchEngine.register(it) }

        val topScorePage = searchEngine.search(word)
        return webPages.indexOf(topScorePage)
    }

    private fun parsePage(html: String): WebPage {
        return HtmlParser().parse(html)
    }
}
