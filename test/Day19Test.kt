import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day19Test : ShouldSpec({
    val sample = """r, wr, b, g, bwu, rb, gb, br

brwrr
bggr
gbbr
rrbgbr
ubwu
bwurrg
brgr
bbrgwb""".lines()

    should("verify part1 on sample list") {
        Day19.part1(sample) shouldBe "6"
    }

    should("verify part2 on sample list") {
        Day19.part2(sample) shouldBe "16"
    }
})
