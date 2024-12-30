object Day24 : Day() {
    override fun p1(input: List<String>): String {
        val registers = parseRegistersMap(input)

        return registers.keys
            .asSequence()
            .filter { it.startsWith("z") }
            .sortedDescending()
            .map { registers.getValue(it)() }
            .map {
                if (it) {
                    1
                } else {
                    0
                }
            }
            .joinToString("")
            .toBigInteger(2)
            .toString()
    }

    private fun parseOperations(input: List<String>): List<Register> =
        input.takeLastWhile { it.isNotEmpty() }.map { operation ->
            val (r1, operator, r2, r) = """^(\w+) (AND|OR|XOR) (\w+) -> (\w+)$""".toRegex()
                .find(operation)!!.destructured
            Register(r1, r2, r, operator)
        }

    private fun parseRegistersMap(input: List<String>): Map<String, () -> Boolean> {
        val registers = mutableMapOf<String, () -> Boolean>()
        val initializations = input.takeWhile { it.isNotEmpty() }
        initializations.forEach { initialization ->
            val (register, initValue) = initialization.split(": ")
            registers[register] = { initValue == "1" }
        }

        parseOperations(input).forEach { operation ->
            val op = when (operation.operator) {
                "AND" -> Boolean::and
                "OR" -> Boolean::or
                "XOR" -> Boolean::xor
                else -> throw UnsupportedOperationException("Unsupported operator ${operation.operator}")
            }
            registers[operation.output] = { op(registers.getValue(operation.a)(), registers.getValue(operation.b)()) }
        }
        return registers
    }

    private fun inlineRegisters(input: List<String>): Map<String, () -> String> {
        val registers = mutableMapOf<String, () -> String>()
        val operations = input.takeLastWhile { it.isNotEmpty() }
        operations.forEach { operation ->
            val (r1, operator, r2, r) = """^(\w+) (AND|OR|XOR) (\w+) -> (\w+)$""".toRegex()
                .find(operation)!!.destructured
            fun inline(name: String): String = if (name.startsWith("x") || name.startsWith("y")) {
                name
            } else {
                "(${registers.getValue(name)()})"
            }
            registers[r] = { "${inline(r1)} $operator ${inline(r2)}" }
        }
        return registers
    }

    override fun p2(input: List<String>): String {
        val registers = parseOperations(input)
        //graphviz(registers).println()

        val faulty = mutableSetOf<Register>()
        // Si output z, alors XOR sauf z45 => pour prendre la retenue
        faulty.addAll(registers.filter { r ->
            r.output.startsWith("z") && r.operator != "XOR" && r.output != "z45"
        })
        // Si output non z et inputs non x et non y, OR ou AND uniquement
        faulty.addAll(registers.filter { r ->
            !r.output.startsWith("z") && !r.a.isXY() && !r.b.isXY() && r.operator == "XOR"
        })
        // Si inputs x et y et XOR, devrait être l'input d'une porte xor sauf 0 => xor de retenue encore
        faulty.addAll(registers.filter { r ->
            r.a.isXY() && r.b.isXY() && r.operator == "XOR" && r.output != "z00"
                    && registers.none { (it.a == r.output || it.b == r.output) && it.operator == "XOR" }
        })
        // Si inputs x et y et AND, l'output devrait être l'input d'un OR sauf 0 => retenue encore
        faulty.addAll(registers.filter { r ->
            r.a.isXY() && r.b.isXY() && r.operator == "AND" && r.output != "z00"
                    && registers.none { it.a == r.output || it.b == r.output && it.operator == "OR" }
        })

        return faulty.map { it.output }.sorted().joinToString(",")
    }

    private fun graphviz(registers: List<Register>): String {
        var visualization = "digraph{\n"
        for (register in registers) {
            visualization += "${register.output} -> ${register.a} [label=\"${register.operator}\"]\n"
            visualization += "${register.output} -> ${register.b} [label=\"${register.operator}\"]\n"
        }
        return "$visualization}\n"
    }
}

private fun String.isXY(): Boolean = this.startsWith("x") || this.startsWith("y")

data class Register(val a: String, val b: String, val output: String, val operator: String)

fun main() {
    val input = readInput("Day24")
    Day24.part1(input).println()
    Day24.part2(input).println()
}
