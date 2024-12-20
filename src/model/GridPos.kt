package model

import kotlin.math.abs

data class GridPos(val row: Int, val col: Int) {
    fun inside(map: List<String>): Boolean = this.row in map.indices && this.col in map[0].indices
    fun forward(orientation: Orientation): GridPos = when (orientation) {
        Orientation.NORTH -> GridPos(row - 1, col)
        Orientation.EAST -> GridPos(row, col + 1)
        Orientation.SOUTH -> GridPos(row + 1, col)
        Orientation.WEST -> GridPos(row, col - 1)
    }

    fun neighbors(): List<GridPos> = listOf(
        GridPos(row - 1, col),
        GridPos(row + 1, col),
        GridPos(row, col - 1),
        GridPos(row, col + 1)
    )

    operator fun plus(pos: GridPos): GridPos = GridPos(row + pos.row, col + pos.col)
    operator fun minus(pos: GridPos): GridPos = GridPos(row - pos.row, col - pos.col)
    fun manhattan(other: GridPos): Int = abs(row - other.row) + abs(col - other.col)
}