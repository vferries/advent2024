import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day13Test : ShouldSpec({
    val sample = """Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279""".lines()

    should("verify part1 on sample list") {
        Day13.part1(sample) shouldBe "480"
    }

    should("verify part2 on real input list") {
        Day13.part2(readInput("Day13")) shouldBe "105620095782547"
    }
})
