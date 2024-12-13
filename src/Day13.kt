import java.math.BigInteger
import java.math.BigInteger.ZERO
import kotlin.math.min

object Day13 : Day() {
    override fun p1(input: List<String>): String = parseMachines(input).sumOf(::bestCost).toString()

    private fun parseMachines(input: List<String>): List<Machine> {
        val machines = input.joinToString("\n").split("\n\n")
            .map { Machine(it) }
        return machines
    }

    private fun bestCost(machine: Machine): Int {
        var best = Integer.MAX_VALUE
        for (a in 0..100) {
            val pos = machine.buttonA * a
            val dx = machine.prize.x - pos.x
            val dy = machine.prize.y - pos.y
            val mx = dx / machine.buttonB.x
            val my = dy / machine.buttonB.y
            if (mx == my && mx * machine.buttonB.x == dx && my * machine.buttonB.y == dy) {
                val cost = 3 * a + mx
                best = min(best, cost)
            }
        }
        return if (best < Integer.MAX_VALUE) best else 0
    }

    private fun bestCostDistant(machine: Machine): BigInteger {
        val prizeX = BigInteger("10000000000000") + machine.prize.x.toBigInteger()
        val prizeY = BigInteger("10000000000000") + machine.prize.y.toBigInteger()
        val bax = machine.buttonA.x.toBigInteger()
        val bay = machine.buttonA.y.toBigInteger()
        val bbx = machine.buttonB.x.toBigInteger()
        val bby = machine.buttonB.y.toBigInteger()
        val b = (prizeY * bax - bay * prizeX) / (bby * bax - bbx * bay)
        val a = (prizeX - b * bbx) / bax
        return if (a >= ZERO && b >= ZERO && a * bax + b * bbx == prizeX && a * bay + b * bby == prizeY) {
            3.toBigInteger() * a + b
        } else {
            ZERO
        }
    }

    override fun p2(input: List<String>): String = parseMachines(input).sumOf(::bestCostDistant).toString()
}

class Machine(text: String) {
    val buttonA: Pos
    val buttonB: Pos
    val prize: Pos

    override fun toString(): String = "Machine { buttonA=$buttonA, buttonB=$buttonB, prize=$prize }"

    init {
        val lines = text.lines()
        val (ax, ay) = "Button A: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(lines[0])!!.destructured.toList()
            .map { it.toInt() }
        buttonA = Pos(ax, ay)
        val (bx, by) = "Button B: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(lines[1])!!.destructured.toList()
            .map { it.toInt() }
        buttonB = Pos(bx, by)
        val (px, py) = "Prize: X=(\\d+), Y=(\\d+)".toRegex().find(lines[2])!!.destructured.toList().map { it.toInt() }
        prize = Pos(px, py)
    }
}

data class Pos(val x: Int, val y: Int) {
    operator fun times(i: Int): Pos = Pos(x * i, y * i)
}

fun main() {
    val input = readInput("Day13")
    Day13.part1(input).println()
    Day13.part2(input).println()
}
