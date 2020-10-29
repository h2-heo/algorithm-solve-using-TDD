internal class FastPageMatcherTest: PageMatcherTest() {

    override fun createPageMatcher(): PageMatcher {
        return FastPageMatcher()
    }
}
