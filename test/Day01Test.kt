import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day01Test : ShouldSpec({
    should("verify part1 on sample list") {
        Day01.part1(
            """3   4
4   3
2   5
1   3
3   9
3   3""".lines()
        ) shouldBe "11"
    }

    should("verify part2 on sample list") {
        Day01.part2(
            """3   4
4   3
2   5
1   3
3   9
3   3""".lines()
        ) shouldBe "31"
    }
})
