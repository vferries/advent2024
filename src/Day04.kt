object Day04 : Day() {
    private val XMAS = "XMAS".toRegex()

    override fun p1(input: List<String>): String {
        val lines = input
        val columns = lines[0].indices.map { i ->
            lines.map { line ->
                line[i]
            }.joinToString("")
        }
        return (lines + columns + diagonals(lines)).sumOf { line -> findXmasInLine(line) }.toString()
    }

    private fun diagonals(lines: List<String>): MutableList<String> {
        val diagonals = mutableListOf<String>()
        val reversed = lines.reversed()
        for (i in lines.indices) {
            var diagonal1 = ""
            var diagonal2 = ""
            for (j in 0..lines[0].lastIndex - i) {
                diagonal1 += lines[i + j][j]
                diagonal2 += reversed[i + j][j]
            }
            diagonals += diagonal1
            diagonals += diagonal2
        }
        for (i in 1..lines[0].lastIndex) {
            var diagonal1 = ""
            var diagonal2 = ""
            for (j in 0..lines.lastIndex - i) {
                diagonal1 += lines[j][i + j]
                diagonal2 += reversed[j][i + j]
            }
            diagonals += diagonal1
            diagonals += diagonal2
        }
        return diagonals
    }

    private fun findXmasInLine(line: String): Int {
        return XMAS.findAll(line).count() + XMAS.findAll(line.reversed()).count()
    }


    override fun p2(input: List<String>): String {
        var count = 0
        for (row in 1 until input[0].lastIndex) {
            for (col in 1 until input.lastIndex) {
                if (input[row][col] == 'A') {
                    val topLeft = input[row - 1][col - 1]
                    val topRight = input[row - 1][col + 1]
                    val bottomLeft = input[row + 1][col - 1]
                    val bottomRight = input[row + 1][col + 1]
                    if (topLeft in "SM" && topRight in "SM" && bottomRight in "SM" && bottomLeft in "SM" &&
                        topLeft != bottomRight && topRight != bottomLeft
                    ) {
                        count++
                    }
                }
            }
        }
        return count.toString()
    }
}

fun main() {
    val input = readInput("Day04")
    Day04.part1(input).println()
    Day04.part2(input).println()
}
