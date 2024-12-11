import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day10Test : ShouldSpec({
    val sample = """89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732""".lines()

    should("verify part1 on sample list") {
        Day10.part1(sample) shouldBe "36"
    }

    should("verify part2 on sample list") {
        Day10.part2(sample) shouldBe "81"
    }
})
