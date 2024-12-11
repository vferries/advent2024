import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day11Test : ShouldSpec({
    val sample = """0 1 10 99 999""".lines()

    should("evolve one step on sample list") {
        Day11.blink(1, listOf(0, 1, 10, 99, 999).map(Int::toBigInteger)) shouldBe listOf(1, 2024, 1, 0, 9, 9, 2021976).map(Int::toBigInteger)
    }

    should("evolve six steps on sample list") {
        Day11.blink(6, listOf(125, 17).map(Int::toBigInteger)).size shouldBe 22
    }

    should("evolve twenty five steps on sample list") {
        Day11.blink(25, listOf(125, 17).map(Int::toBigInteger)).size shouldBe 55312
    }

    should("verify part1 on sample list") {
        Day11.part1(sample) shouldBe ""
    }

    should("verify part2 on sample list") {
        Day11.part2(sample) shouldBe ""
    }
})
