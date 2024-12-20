object Day19 : Day() {
    val memo = mutableMapOf<Pair<String, List<String>>, Long>()

    override fun p1(input: List<String>): String {
        val towels = input[0].split(", ")
        val patterns = input.drop(2)
        return patterns.count { countFeasible(it, towels) > 0 }.toString()
    }

    override fun p2(input: List<String>): String {
        val towels = input[0].split(", ")
        val patterns = input.drop(2)
        return patterns.sumOf {
            countFeasible(it, towels)
        }.toString()
    }

    private fun countFeasible(pattern: String, towels: List<String>): Long {
        if (memo.contains(pattern to towels)) {
            return memo.getValue(pattern to towels)
        }
        if (pattern.isEmpty()) {
            return 1L
        }
        val result = towels
            .filter { pattern.startsWith(it) }
            .sumOf { countFeasible(pattern.substringAfter(it), towels) }
        memo[pattern to towels] = result
        return result
    }
}

fun main() {
    val input = readInput("Day19")
    Day19.part1(input).println()
    Day19.part2(input).println()
}
