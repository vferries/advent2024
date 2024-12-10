data class Equation(val result: Long, val operands: List<Long>) {
    fun solvable(): Boolean = solvable(operands.first(), 1)

    fun solvableWithConcat(): Boolean = solvableWithConcat(operands.first(), 1)

    private fun solvable(current: Long, index: Int): Boolean = when {
        current > result -> { false }
        index == operands.size -> {
            current == result
        }
        else -> {
            val elemAtIndex = operands[index]
            solvable(current + elemAtIndex, index + 1) ||
                    solvable(current * elemAtIndex, index + 1)
        }
    }

    private fun solvableWithConcat(current: Long, index: Int): Boolean = when {
        current > result -> {
            false
        }

        index == operands.size -> {
            current == result
        }

        else -> {
            val elemAtIndex = operands[index]
            solvableWithConcat(current + elemAtIndex, index + 1) ||
                    solvableWithConcat(current * elemAtIndex, index + 1) ||
                    solvableWithConcat((current.toString() + elemAtIndex.toString()).toLong(), index + 1)
        }
    }
}

object Day07 : Day() {
    override fun p1(input: List<String>): String = parseEquations(input)
        .filter(Equation::solvable)
        .sumOf(Equation::result)
        .toString()

    private fun parseEquations(input: List<String>) = input.map { line ->
        val (result, operands) = line.split(": ")
        Equation(result.toLong(), operands.split(" ").map(String::toLong))
    }

    override fun p2(input: List<String>): String = parseEquations(input)
        .filter(Equation::solvableWithConcat)
        .sumOf(Equation::result)
        .toString()
}

fun main() {
    val input = readInput("Day07")
    Day07.part1(input).println()
    Day07.part2(input).println()
}
