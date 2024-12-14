package model

data class Pos(val x: Int, val y: Int) {
    operator fun times(i: Int): Pos = Pos(x * i, y * i)
}