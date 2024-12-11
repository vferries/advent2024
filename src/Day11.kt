import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

object Day11 : Day() {
    override fun p1(input: List<String>): String {
        val stones = input[0].split(" ").map { it.toBigInteger() }
        return blink(25, stones).size.toString()
    }

    override fun p2(input: List<String>): String {
        val stones = input[0].split(" ").map { it.toBigInteger() }

        return stones.sumOf { blink(75, it, mutableMapOf()) }.toString()
    }

    fun blink(blinkCount: Int, list: List<BigInteger>): List<BigInteger> {
        var newList = list
        repeat(blinkCount) {
            newList = newList.flatMap {
                when {
                    it == ZERO -> listOf(ONE)
                    it.toString().length % 2 == 0 -> {
                        val str = it.toString()
                        listOf(str.take(str.length / 2).toBigInteger(), str.drop(str.length / 2).toBigInteger())
                    }

                    else -> listOf(it * 2024.toBigInteger())
                }
            }
        }
        return newList
    }

    fun blink(blinkCount: Int, stone: BigInteger, memo: MutableMap<Pair<BigInteger, Int>, BigInteger>): BigInteger {
        if (memo.contains(stone to blinkCount)) {
            return memo.getValue(stone to blinkCount)
        }
        val result = when {
            memo.contains(stone to blinkCount) -> memo.getValue(stone to blinkCount)
            blinkCount == 0 -> ONE
            stone == ZERO -> blink(blinkCount - 1, ONE, memo)
            stone.toString().length % 2 == 0 -> {
                val str = stone.toString()
                blink(blinkCount - 1, str.take(str.length / 2).toBigInteger(), memo) + blink(
                    blinkCount - 1,
                    str.drop(str.length / 2).toBigInteger(), memo
                )
            }
            else -> blink(blinkCount - 1, stone * 2024.toBigInteger(), memo)
        }
        memo[stone to blinkCount] = result
        return result
    }
}

fun main() {
    val input = readInput("Day11")
    Day11.part1(input).println()
    Day11.part2(input).println()
}
