import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day09Test : ShouldSpec({
    val sample = "2333133121414131402".lines()

    should("verify part1 on sample list") {
        Day09.part1(sample) shouldBe "1928"
    }

    should("verify part2 on sample list") {
        Day09.part2(sample) shouldBe "2858"
    }
})
