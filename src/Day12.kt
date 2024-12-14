import model.GridPos
import model.Orientation
import model.Orientation.*

object Day12 : Day() {
    override fun p1(input: List<String>): String =
        findAllGroups(input).sumOf { it.size * perimeter(it, input) }.toString()

    private fun perimeter(group: Set<GridPos>, input: List<String>): Int {
        val c = input[group.first().row][group.first().col]
        return group.flatMap(GridPos::neighbors).count { !it.inside(input) || input[it.row][it.col] != c }
    }

    override fun p2(input: List<String>): String =
        findAllGroups(input).sumOf { it.size * sides(it, input) }.toString()

    private fun sides(group: Set<GridPos>, input: List<String>): Int {
        val c = input[group.first().row][group.first().col]
        val fences = group.flatMap { pos -> pos.neighborsWithOrientation().map { (n, o) -> Triple(pos, n, o) } }
            .filter { (_, neighbor, _) ->
                !neighbor.inside(input) || input[neighbor.row][neighbor.col] != c
            }
            .map { (pos, _, orientation) -> pos to orientation }
            .toMutableList()
        var count = 0
        while (fences.isNotEmpty()) {
            val (pos, orientation) = fences.removeAt(0)
            when (orientation) {
                NORTH, SOUTH -> {
                    // find horizontal neighbors
                    var leftMost = pos + GridPos(0, -1)
                    while (fences.contains(leftMost to orientation)) {
                        fences.remove(leftMost to orientation)
                        leftMost += GridPos(0, -1)
                    }
                    var rightMost = pos + GridPos(0, 1)
                    while (fences.contains(rightMost to orientation)) {
                        fences.remove(rightMost to orientation)
                        rightMost += GridPos(0, 1)
                    }
                }

                EAST, WEST -> {
                    // find vertical neighbors
                    var upper = pos + GridPos(-1, 0)
                    while (fences.contains(upper to orientation)) {
                        fences.remove(upper to orientation)
                        upper += GridPos(-1, 0)
                    }
                    var lower = pos + GridPos(1, 0)
                    while (fences.contains(lower to orientation)) {
                        fences.remove(lower to orientation)
                        lower += GridPos(1, 0)
                    }
                }
            }
            count++
        }
        return count
    }

    private fun findAllGroups(input: List<String>): List<Set<GridPos>> {
        val visited = mutableSetOf<GridPos>()
        val groups = mutableListOf<Set<GridPos>>()
        for (row in input.indices) {
            for (col in input[row].indices) {
                val pos = GridPos(row, col)
                if (visited.contains(pos)) {
                    continue
                }
                val c = input[row][col]
                val group = mutableSetOf<GridPos>()
                val toVisit = mutableSetOf(pos)
                while (toVisit.isNotEmpty()) {
                    val head = toVisit.first()
                    toVisit.remove(head)
                    group += head
                    visited.add(head)
                    toVisit.addAll(
                        head.neighbors().filter { it.inside(input) && it !in visited && input[it.row][it.col] == c }
                    )
                }
                groups += group
            }
        }
        return groups
    }
}

private fun GridPos.neighborsWithOrientation(): List<Pair<GridPos, Orientation>> = listOf(
    GridPos(row - 1, col) to NORTH,
    GridPos(row + 1, col) to SOUTH,
    GridPos(row, col - 1) to WEST,
    GridPos(row, col + 1) to EAST
)

fun main() {
    val input = readInput("Day12")
    Day12.part1(input).println()
    Day12.part2(input).println()
}
