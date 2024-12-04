import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day02Test : ShouldSpec({
    should("verify part1 on sample list") {
        Day02.part1(
            """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
        ) shouldBe "2"
    }

    should("verify part2 on sample list") {
        Day02.part2(
            """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
        ) shouldBe "4"
    }
}) {
}