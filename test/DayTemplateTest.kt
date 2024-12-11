import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class DayTemplateTest : ShouldSpec({
    val sample = """""".lines()

    should("verify part1 on sample list") {
        DayTemplate.part1(sample) shouldBe ""
    }

    should("verify part2 on sample list") {
        DayTemplate.part2(sample) shouldBe ""
    }
})
