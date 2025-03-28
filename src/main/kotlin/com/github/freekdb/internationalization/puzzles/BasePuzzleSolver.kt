package com.github.freekdb.internationalization.puzzles

import java.io.File

abstract class BasePuzzleSolver(protected val verbose: Boolean) {
    private val puzzleInputDirectory = "com/github/freekdb/internationalization/puzzles/input/"

    internal abstract val inputLinesExamples: List<String>

    fun solvePuzzle(inputId: String, description: String) {
        val startTime = System.currentTimeMillis()

        val inputLines = getInputLines(inputId)

        if (verbose) {
            println()
            println("Input lines:")
            inputLines.forEach { println(it) }
            println()
        }

        val result = solvePuzzle(inputLines)

        println()
        println("$description: $result.")

        println()
        val duration = (System.currentTimeMillis() - startTime) / 1000
        println("The program ran for $duration seconds.")
    }

    internal abstract fun solvePuzzle(inputLines: List<String>): Long

    private fun getInputLines(inputId: String): List<String> {
        val validInputIds = (1..inputLinesExamples.size).map { "example $it" } + listOf("puzzle input")
        if (inputId !in validInputIds)
            throw RuntimeException("Unexpected input ID '$inputId'.")

        return if (inputId == "puzzle input") {
            val dayNumber = javaClass.name.takeLast(2)
            val filepath = "${puzzleInputDirectory}puzzle-$dayNumber.txt"

            File(ClassLoader.getSystemResource(filepath).file).readLines()
        } else {
            inputLinesExamples[inputId.substringAfter(" ").toInt() - 1]
                .split("\n")
                .dropWhile { it.isBlank() }
                .dropLastWhile { it.isBlank() }
        }
    }
}
