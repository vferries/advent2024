import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day21Test : ShouldSpec({
    val sample = listOf("029A", "980A", "179A", "456A", "379A")

    should("verify part1 on sample list") {
        Day21.part1(sample) shouldBe "126384"
    }

    should("verify part2 on sample list") {
        Day21.part2(sample) shouldBe ""
    }
})
