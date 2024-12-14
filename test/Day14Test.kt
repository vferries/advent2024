import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day14Test : ShouldSpec({
    val sample = """p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3""".lines()

    should("verify part1 on sample list") {
        Day14.safetyFactor(11, 7, sample) shouldBe 12
    }

    should("verify part2 on sample list") {
        Day14.part2(sample) shouldBe ""
    }
})
