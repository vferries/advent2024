import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day07Test : ShouldSpec({
    val sample = """190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20""".lines()

    should("verify part1 on sample list") {
        Day07.part1(sample) shouldBe "3749"
    }

    should("verify part2 on sample list") {
        Day07.part2(sample) shouldBe "11387"
    }

    should("check simple equation") {
        Equation(7290L, listOf(6L, 8L, 6L, 15L)).solvableWithConcat() shouldBe true
    }
})
