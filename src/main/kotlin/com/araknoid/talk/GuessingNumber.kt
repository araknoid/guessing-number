package com.araknoid.talk

import arrow.core.Try
import kotlin.random.Random

fun main() {
    game { Random.nextInt(5) + 1 }
}

fun game(numberToGuess: () -> Int) {
    println("What is your name?")

    val name = readLine() as String
    println("""Hello, $name, welcome to the game!""")

    gameLoop(numberToGuess, name)
}

private fun gameLoop(numberToGuess: () -> Int, name: String) {
    val num = numberToGuess()

    println("Dear $name, please guess a number from 1 to 5:")

    (readLine() as String)
        .safeToInt()
        .fold(
            { println("You guessed wrong, $name! The number was: $num") },
            { guess ->
                if (guess == num) println("You guessed right, $name!")
                else println("You guessed wrong, $name! The number was: $num")
            }
        )

    println("Do you want to continue, $name?")

    val answer = readLine()
    when {
        answer == "n" -> Unit
        else -> gameLoop(numberToGuess, name)
    }
}

fun String.safeToInt() = Try { this.toInt() }