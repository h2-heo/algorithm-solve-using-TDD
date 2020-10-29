interface PageMatcher {

    fun add(page: WebPage)
    fun addAll(pages: Iterable<WebPage>)

    fun getMatchingScore(page: WebPage, word: String): Double
    fun getBaseScore(page: WebPage, word: String): Double
    fun getLinkScore(page: WebPage, word: String): Double
}
