import model.GridPos

object Day10 : Day() {
    override fun p1(input: List<String>): String {
        var total = 0
        for (row in input.indices) {
            for (col in input[row].indices) {
                if (input[row][col] == '0') {
                    total += reachableEnds(input, row, col).size
                }
            }
        }
        return total.toString()
    }

    private fun reachableEnds(input: List<String>, row: Int, col: Int): Set<GridPos> =
        if (input[row][col] == '9') {
            setOf(GridPos(row, col))
        } else {
            GridPos(row, col).neighbors()
                .filter { it.inside(input) }
                .filter { (rowN, colN) ->
                    input[rowN][colN].digitToInt() == input[row][col].digitToInt() + 1
                }.flatMap { reachableEnds(input, it.row, it.col) }.toSet()
        }

    override fun p2(input: List<String>): String {
        var total = 0
        for (row in input.indices) {
            for (col in input[row].indices) {
                if (input[row][col] == '0') {
                    total += ratings(input, row, col)
                }
            }
        }
        return total.toString()
    }

    private fun ratings(input: List<String>, row: Int, col: Int): Int =
        if (input[row][col] == '9') {
            1
        } else {
            GridPos(row, col).neighbors()
                .filter { it.inside(input) }
                .filter { (rowN, colN) ->
                    input[rowN][colN].digitToInt() == input[row][col].digitToInt() + 1
                }.sumOf { ratings(input, it.row, it.col) }
        }

}

fun main() {
    val input = readInput("Day10")
    Day10.part1(input).println()
    Day10.part2(input).println()
}
