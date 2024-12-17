import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day17Test : ShouldSpec({
    val sample = """Register A: 729
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0""".lines()

    val sample2 = """Register A: 2024
Register B: 0
Register C: 0

Program: 0,3,5,4,3,0""".lines()

    should("verify part1 on sample list") {
        Day17.part1(sample) shouldBe "4,6,3,5,6,3,5,2,1,0"
    }

    should("verify part2 on sample list") {
        Day17.part2(sample2) shouldBe "117440"
    }
})
