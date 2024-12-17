import kotlin.math.pow

object Day17 : Day() {
    override fun p1(input: List<String>): String {
        val registers = parseRegisters(input)
        val program = parseProgram(input)

        val outputs = execute(program, registers)
        return outputs.joinToString(",")
    }

    private fun findQuines(
        current: Long,
        step: Int,
        program: List<Int>,
        initialRegisters: Map<String, Long>
    ): List<Long> {
        return (0..63).flatMap { delta ->
            val registers = HashMap(initialRegisters)
            val testedValue = current * 64 + delta
            registers["A"] = testedValue
            val result = execute(program, registers)
            when {
                program == result -> {
                    listOf(testedValue)
                }

                step == program.size -> {
                    listOf()
                }

                result == program.takeLast(step + 2) -> {
                    findQuines(testedValue, step + 2, program, initialRegisters)
                }

                else -> {
                    listOf()
                }
            }
        }
    }

    override fun p2(input: List<String>): String {
        val registers = parseRegisters(input)
        val program = parseProgram(input)

        return findQuines(0, 0, program, registers).min().toString()
    }

    private fun execute(
        program: List<Int>,
        registers: MutableMap<String, Long>
    ): MutableList<Int> {
        var instruction = 0
        val outputs = mutableListOf<Int>()
        while (instruction in 0..program.lastIndex) {
            val opCode = program[instruction]
            val operand = program[instruction + 1]

            when (opCode) {
                0 -> {
                    // adv
                    val numerator = registers.getValue("A")
                    val denominator = 2.toDouble().pow(combo(operand, registers).toInt()).toInt()
                    registers["A"] = numerator / denominator
                }

                1 -> {
                    // bxl
                    registers["B"] = registers.getValue("B").xor(operand.toLong())
                }

                2 -> {
                    // bst
                    registers["B"] = combo(operand, registers) % 8
                }

                3 -> {
                    // jnz
                    if (registers.getValue("A") != 0L) {
                        instruction = operand - 2
                    }
                }

                4 -> {
                    // bxc
                    registers["B"] = registers.getValue("B").xor(registers.getValue("C"))
                }

                5 -> {
                    // out
                    outputs += combo(operand, registers).mod(8)
                }

                6 -> {
                    // bdv
                    val numerator = registers.getValue("A")
                    val denominator = 2.toDouble().pow(combo(operand, registers).toDouble()).toLong()
                    registers["B"] = (numerator / denominator)

                }

                7 -> {
                    // cdv
                    val numerator = registers.getValue("A")
                    val denominator = 2.toDouble().pow(combo(operand, registers).toDouble()).toLong()
                    registers["C"] = numerator / denominator
                }

                else -> {
                    throw IllegalArgumentException("Invalid opcode: $opCode")
                }
            }
            instruction += 2
        }
        return outputs
    }

    private fun combo(operand: Int, registers: MutableMap<String, Long>): Long = when (operand) {
        0, 1, 2, 3 -> operand.toLong()
        4 -> registers.getValue("A")
        5 -> registers.getValue("B")
        6 -> registers.getValue("C")
        else -> {
            throw IllegalArgumentException("Invalid combo: $operand")
        }
    }

    private fun parseProgram(input: List<String>): List<Int> =
        input.last().substringAfter("Program: ").split(",").map(String::toInt)

    private fun parseRegisters(input: List<String>): MutableMap<String, Long> = input.take(3).associate {
        val (register, value) = "Register (.): (\\d+)".toRegex().find(it)!!.destructured
        register to value.toLong()
    }.toMutableMap()
}

fun main() {
    val input = readInput("Day17")
    Day17.part1(input).println()
    Day17.part2(input).println()
}
