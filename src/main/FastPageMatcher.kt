class FastPageMatcher: PageMatcher {

    private val pages = arrayListOf<WebPage>()

    override fun add(page: WebPage) {
        pages.add(page)
    }

    override fun addAll(pages: Iterable<WebPage>) {
        this.pages.addAll(pages)
    }

    override fun getMatchingScore(page: WebPage, word: String): Double {
        return getBaseScore(page, word) + getLinkScore(page, word)
    }

    override fun getBaseScore(page: WebPage, word: String): Double {
        return page.words.count { it.equals(word, ignoreCase = true) }.toDouble()
    }

    override fun getLinkScore(page: WebPage, word: String): Double {
        val linkedPages = getLinkedPages(page)
        return linkedPages.sumByDouble { getBaseScore(it, word) / it.links.size }
    }

    private fun getLinkedPages(page: WebPage): List<WebPage> {
        return pages.filter { it.links.contains(page.url) }
    }
}
