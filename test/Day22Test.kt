import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day22Test : ShouldSpec({
    val sample = listOf("1", "10", "100","2024")

    should("verify part1 on sample list") {
        Day22.part1(sample) shouldBe "37327623"
    }

    should("verify part2 on sample list") {
        Day22.part2(sample) shouldBe "24"
    }
})
