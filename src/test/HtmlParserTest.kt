import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HtmlParserTest {

    @Test
    fun `test parse() #1`() {
        val html = """
            |<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            |<head>
            |  <meta charset="utf-8">
            |  <meta property="og:url" content="https://a.com"/>
            |</head>  
            |<body>
            |Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. 
            |<a href="https://b.com"> Link to b </a>
            |</body>
            |</html>
        """.trimMargin()
        val page = WebPage(
                url = "https://a.com",
                links = listOf("https://b.com"),
                words = listOf(
                        "Blind", "Lorem", "Blind", "ipsum", "dolor", "Blind", "test", "sit", "amet",
                        "consectetur", "adipiscing", "elit", "Link", "to", "b",
                ),
        )

        assertEquals(page, HtmlParser().parse(html))
    }

    @Test
    fun `test parse() #2`() {
        val html = "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>"
        val page = WebPage(
                url = "https://a.com",
                links = listOf("https://b.com"),
                words = listOf(
                        "Blind", "Lorem", "Blind", "ipsum", "dolor", "Blind", "test", "sit", "amet",
                        "consectetur", "adipiscing", "elit", "Link", "to", "b",
                ),
        )

        assertEquals(page, HtmlParser().parse(html))
    }

    @Test
    fun `test parse() #3`() {
        val html = """
            |<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            |<head>
            |  <meta charset="utf-8">
            |  <meta property="og:url" content="https://www.kakaocorp.com"/>
            |</head>  
            |<body>
            |con%    muzI92apeach&2<a href="https://hashcode.co.kr/tos"></a>
            |
            |    ^
            |</body>
            |</html>
        """.trimMargin()
        val page = WebPage(
                url = "https://www.kakaocorp.com",
                links = listOf("https://hashcode.co.kr/tos"),
                words = listOf("con", "muzI", "apeach"),
        )

        assertEquals(page, HtmlParser().parse(html))
    }

    @Test
    fun `test parse() #4`() {
        val html = "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a><a href=\"https://c.com\"> Link to c on same line </a>\n</body>\n</html>"
        val page = WebPage(
                url = "https://a.com",
                links = listOf("https://b.com", "https://c.com"),
                words = listOf(
                        "Blind", "Lorem", "Blind", "ipsum", "dolor", "Blind", "test", "sit", "amet",
                        "consectetur", "adipiscing", "elit", "Link", "to", "b",
                        "Link", "to", "c", "on", "same", "line",
                ),
        )

        assertEquals(page, HtmlParser().parse(html))
    }

    @Test
    fun `test getHeadBody()`() {
        val html = """
            |<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            |<head>
            |  <meta charset="utf-8">
            |  <meta property="og:url" content="https://a.com"/>
            |</head>  
            |<body>
            |Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. 
            |<a href="https://b.com"> Link to b </a>
            |</body>
            |</html>
        """.trimMargin()

        val head = """
            |<head>
            |  <meta charset="utf-8">
            |  <meta property="og:url" content="https://a.com"/>
            |</head>
        """.trimMargin()
        val body = """
            |<body>
            |Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. 
            |<a href="https://b.com"> Link to b </a>
            |</body>
        """.trimMargin()

        assertEquals(Pair(head, body), HtmlParser().getHeadBody(html))
    }

    @Test
    fun `test getUrl()`() {
        val head = """
            |<head>
            |  <meta charset="utf-8">
            |  <meta property="og:url" content="https://a.com"/>
            |</head>
        """.trimMargin()

        val url = "https://a.com"

        assertEquals(url, HtmlParser().getUrl(head))
    }

    @Test
    fun `test getLinks()`() {
        val body = """
            |<body>
            |Suspendisse potenti. Vivamus venenatis tellus non turpis bibendum, 
            |<a href="https://a.com"> Link to a </a>
            |blind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.
            |<a href="https://c.com"> Link to c </a>
            |</body>
        """.trimMargin()

        val links = listOf("https://a.com", "https://c.com")

        assertEquals(links, HtmlParser().getLinks(body))
    }

    @Test
    fun `test getWords()`() {
        val body = """
            |<body>
            |Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. 
            |<a href="https://b.com"> Link to b </a>
            |Haha@haha!!#1@!!    123 123 hohoho12312goo
            |</body>
        """.trimMargin()

        val words = listOf(
                "Blind", "Lorem", "Blind", "ipsum", "dolor", "Blind", "test", "sit", "amet",
                "consectetur", "adipiscing", "elit", "Link", "to", "b", "Haha", "haha", "hohoho", "goo",
        )

        assertEquals(words, HtmlParser().getWords(body))
    }
}