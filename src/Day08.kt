object Day08 : Day() {
    override fun p1(input: List<String>): String {
        val antennas = parseAntennas(input)
        val antiNodes = mutableSetOf<GridPos>()
        for ((_, list) in antennas) {
            val combinations = list.possiblePairs()
            combinations.forEach { (a1, a2) ->
                val dr = a1.row - a2.row
                val dc = a1.col - a2.col
                antiNodes += GridPos(a1.row + dr, a1.col + dc)
                antiNodes += GridPos(a2.row - dr, a2.col - dc)
            }
        }
        return antiNodes.count { it.inside(input) }.toString()
    }

    private fun parseAntennas(input: List<String>): MutableMap<Char, MutableList<GridPos>> {
        val antennas = mutableMapOf<Char, MutableList<GridPos>>()
        for (row in input.indices) {
            for (col in input[row].indices) {
                val c = input[row][col]
                if (c != '.') {
                    antennas.getOrPut(c) { mutableListOf() }
                        .add(GridPos(row, col))
                }
            }
        }
        return antennas
    }

    override fun p2(input: List<String>): String {
        val antennas = parseAntennas(input)
        val antiNodes = mutableSetOf<GridPos>()
        for ((_, list) in antennas) {
            val combinations = list.possiblePairs()
            combinations.forEach { (a1, a2) ->
                val dr = a1.row - a2.row
                val dc = a1.col - a2.col
                // Do that while we are on grid
                var current = a1
                while (current.inside(input)) {
                    antiNodes += current
                    current = GridPos(current.row + dr, current.col + dc)
                }
                current = a2
                while (current.inside(input)) {
                    antiNodes += current
                    current = GridPos(current.row - dr, current.col - dc)
                }
            }
        }
        return antiNodes.size.toString()
    }
}

private fun <E> List<E>.possiblePairs(): List<Pair<E, E>> {
    return if (this.size <= 1) {
        listOf()
    } else {
        this.drop(1).map { Pair(this.first(), it) } + this.drop(1).possiblePairs()
    }
}

fun main() {
    val input = readInput("Day08")
    Day08.part1(input).println()
    Day08.part2(input).println()
}
