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
}