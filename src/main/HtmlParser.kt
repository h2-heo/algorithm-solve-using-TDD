class HtmlParser {

    fun parse(html: String): WebPage {
        val (head, body) = getHeadBody(html)
        val url = getUrl(head)
        val links = getLinks(body)
        val words = getWords(body)
        return WebPage(url, links, words)
    }

    fun getHeadBody(html: String): Pair<String, String> {
        val regex = """^<html(?:\s.*)?>\s*(<head>(?:.|\s)*</head>)\s*(<body>(?:(.|\s)*)</body>)\s*</html>$""".toRegex()
        val matchResult = regex.find(html)
        val (head, body) = matchResult!!.destructured
        return Pair(head, body)
    }

    fun getUrl(headHtml: String): String {
        val regex = """<meta property="og:url" content="(.+?)"/>""".toRegex()
        val matchResult = regex.find(headHtml)
        return matchResult!!.groupValues[1]
    }

    fun getLinks(bodyHtml: String): List<String> {
        val regex = """<a href="(.+?)">""".toRegex()
        return regex.findAll(bodyHtml).map { it.groupValues[1] }.toList()
    }

    fun getWords(bodyHtml: String): List<String> {
        val bodyText = getBodyText(bodyHtml)

        val regex = """[A-Za-z]+""".toRegex()
        return regex.findAll(bodyText).map { it.value }.toList()
    }

    private fun getBodyText(bodyHtml: String): String {
        val regex = """<.*?>""".toRegex()
        return regex.replace(bodyHtml, " ")
    }
}
