object Day03 : Day() {
    override fun p1(input: List<String>): Int {
        val line = input.joinToString("\n")
        val found = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(line)
        return found.sumOf {
            val (left, right) = it.destructured
            left.toInt() * right.toInt()
        }
    }

    override fun p2(input: List<String>): Int {
        val line = input.joinToString("\n")
        val found = """do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(line)
        return found.fold(Pair(0, true)) { (total, active), match ->
            when (match.value) {
                "do()" -> {
                    Pair(total, true)
                }

                "don't()" -> {
                    Pair(total, false)
                }

                else -> {
                    val (left, right) = match.destructured
                    Pair(
                        if (active) {
                            total + left.toInt() * right.toInt()
                        } else {
                            total
                        }, active
                    )
                }
            }
        }.first
    }
}

fun main() {
    val input = readInput("Day03")
    Day03.part1(input).println()
    Day03.part2(input).println()
}
