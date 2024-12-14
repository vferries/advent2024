package model

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