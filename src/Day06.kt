import Orientation.*

object Day06 : Day() {
    override fun p1(input: List<String>): String = visitAllPositions(input).size.toString()

    private fun visitAllPositions(input: List<String>): MutableSet<GridPos> {
        var guardPosition = GuardPos(parsePosition(input), NORTH)
        val visited = mutableSetOf<GridPos>()
        while (guardPosition.position.inside(input)) {
            visited += guardPosition.position
            guardPosition = guardPosition.nextPos(input)
        }
        return visited
    }

    private fun parsePosition(map: List<String>): GridPos {
        val row = map.indexOfFirst { '^' in it }
        val col = map[row].indexOf('^')
        return GridPos(row, col)
    }

    override fun p2(input: List<String>): String {
        val map = input.toMutableList()
        val initPosition = GuardPos(parsePosition(map), NORTH)
        var guardPosition: GuardPos
        var count = 0

        visitAllPositions(input).filter { it != initPosition.position }.forEach { p ->
            // Modify map
            map[p.row] = map[p.row].replaceRange(p.col..p.col, "#")

            val visited = mutableSetOf<GuardPos>()
            guardPosition = initPosition
            while (guardPosition.position.inside(map)) {
                if (visited.contains(guardPosition)) {
                    count++
                    break
                }
                visited += guardPosition
                guardPosition = guardPosition.nextPos(map)
            }

            // Restore map
            map[p.row] = map[p.row].replaceRange(p.col..p.col, ".")
        }

        return count.toString()
    }
}

data class GridPos(val row: Int, val col: Int) {
    fun inside(map: List<String>): Boolean = this.row in map.indices && this.col in map[0].indices
    fun forward(orientation: Orientation): GridPos = when (orientation) {
        NORTH -> GridPos(row - 1, col)
        EAST -> GridPos(row, col + 1)
        SOUTH -> GridPos(row + 1, col)
        WEST -> GridPos(row, col - 1)
    }

    companion object {
        fun allPositions(map: List<String>): List<GridPos> = map.flatMapIndexed { row, line ->
            line.indices.map { col ->
                GridPos(row, col)
            }
        }
    }
}

enum class Orientation {
    NORTH, EAST, SOUTH, WEST;

    fun right(): Orientation =
        when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
}

data class GuardPos(val position: GridPos, val orientation: Orientation) {
    fun nextPos(map: List<String>): GuardPos {
        val forward = position.forward(orientation)
        return if (!forward.inside(map) || map[forward.row][forward.col] != '#') {
            this.copy(position = forward)
        } else {
            GuardPos(position.forward(orientation.right()), orientation.right())
        }
    }
}

fun main() {
    val input = readInput("Day06")
    Day06.part1(input).println()
    Day06.part2(input).println()
}
