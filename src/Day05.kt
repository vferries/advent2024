typealias Rules = List<Rule>
typealias Rule = List<Int>
typealias Update = MutableList<Int>

object Day05 : Day() {
    override fun p1(input: List<String>): String {
        val (rules, updates) = extractParts(input)
        return updates.filter { update ->
            update.validateAllRules(rules)
        }.sumOf { update -> update[update.size / 2] }.toString()
    }

    override fun p2(input: List<String>): String {
        val (rules, updates) = extractParts(input)
        return updates.filter { update ->
            !update.validateAllRules(rules)
        }.map { it.toValidOrdering(rules) }.sumOf { update -> update[update.size / 2] }.toString()
    }

    private fun Update.validateAllRules(rules: Rules) = rules.all { isValid(it) }

    private fun Update.isValid(rule: Rule) =
        rule.first() !in this || rule.last() !in this || this.indexOf(rule.first()) < this.indexOf(rule.last())

    private fun extractParts(input: List<String>): Pair<Rules, List<Update>> {
        val rules = input.takeWhile { it.isNotBlank() }.map { it.split("|").map(String::toInt) }
        val updates = input.drop(rules.size + 1).map { it.split(",").map(String::toInt).toMutableList() }
        return Pair(rules, updates)
    }

    private fun Update.toValidOrdering(rules: Rules): Update {
        while (!this.validateAllRules(rules)) {
            val invalidRule = rules.first { rule -> !this.isValid(rule) }
            val firstIndex = this.indexOf(invalidRule.first())
            val lastIndex = this.indexOf(invalidRule.last())
            this.swap(firstIndex, lastIndex)
        }
        return this
    }
}

private fun Update.swap(firstIndex: Int, lastIndex: Int) {
    val tmp = this[firstIndex]
    this[firstIndex] = this[lastIndex]
    this[lastIndex] = tmp
}

fun main() {
    val input = readInput("Day05")
    Day05.part1(input).println()
    Day05.part2(input).println()
}
