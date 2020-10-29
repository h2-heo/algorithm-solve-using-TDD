class FastPageMatcher : PageMatcher {

    private val pages = arrayListOf<WebPage>()

    private val linkedPagesOf = hashMapOf<WebPage, ArrayList<WebPage>>()
    private val linkedPagesOfUrl = hashMapOf<String, ArrayList<WebPage>>()
    private val baseScoresOf = hashMapOf<WebPage, Map<Long, Double>>()
    private val wordHashOf = hashMapOf<String, Long>()

    override fun add(page: WebPage) {
        cache(page)
        pages.add(page)
    }

    private fun cache(page: WebPage) {
        linkedPagesOf[page] = linkedPagesOfUrl.getOrPut(page.url, { arrayListOf() })

        page.links.forEach { targetUrl ->
            linkedPagesOfUrl.getOrPut(targetUrl, { arrayListOf() }).add(page)
        }
    }

    override fun addAll(pages: Iterable<WebPage>) {
        pages.forEach { add(it) }
    }

    override fun getMatchingScore(page: WebPage, word: String): Double {
        return getBaseScore(page, word) + getLinkScore(page, word)
    }

    override fun getBaseScore(page: WebPage, word: String): Double {
        val wordHash = word.wordHash
        val baseScores = page.getBaseScores()
        return baseScores.getOrDefault(wordHash, 0.0)
    }

    override fun getLinkScore(page: WebPage, word: String): Double {
        val linkedPages = linkedPagesOf.getValue(page)
        return linkedPages.sumByDouble { getBaseScore(it, word) / it.links.size }
    }

    private val String.wordHash: Long
        get() {
            if (this in wordHashOf) {
                return wordHashOf[this]!!
            }

            var hash = 0L
            for (c in this.chars()) {
                hash = hash shl 5
                hash = hash or charHashOf[c]
            }

            wordHashOf[this] = hash
            return hash
        }

    private val charHashOf: LongArray by lazy {
        val hashes = LongArray(128) { 0L }
        for (c in 'A'..'Z') {
            hashes[c.toInt()] = (c - 'A' + 1).toLong()
        }
        for (c in 'a'..'z') {
            hashes[c.toInt()] = (c - 'a' + 1).toLong()
        }
        hashes
    }

    private fun WebPage.getBaseScores(): Map<Long, Double> {
        if (this in baseScoresOf) {
            return baseScoresOf[this]!!
        }

        val wordHashes = this.words.map { it.wordHash }
        val baseScores = wordHashes
                .groupingBy { it }
                .eachCount()
                .mapValues { it.value.toDouble() }

        baseScoresOf[this] = baseScores
        return baseScores
    }
}
