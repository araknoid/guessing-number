package com.araknoid.talk

import arrow.core.Failure
import arrow.core.Success
import arrow.core.Try
import arrow.effects.IO
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

    askForNumber(name)
        .map { it.findOutGameResult(num) }
        .flatMap { printGameResult(it, name) }
        .unsafeRunSync()

    askToContinue(name, numberToGuess).unsafeRunSync()
}

sealed class GameResult
object Win : GameResult()
data class Loss(val numberToGuess: Int) : GameResult()

fun Try<Int>.findOutGameResult(numberToGuess: Int) =
    when (this) {
        is Failure -> Loss(numberToGuess)
        is Success -> if (value == numberToGuess) Win else Loss(numberToGuess)
    }

fun printGameResult(gameResult: GameResult, name: String) =
    when (gameResult) {
        is Win -> putStrLn("You guessed right, $name!")
        is Loss -> putStrLn("You guessed wrong, $name! The number was: ${gameResult.numberToGuess}")
    }


private fun askForNumber(name: String): IO<Try<Int>> {
    return putStrLn("Dear $name, please guess a number from 1 to 5:")
        .flatMap { getStrLn() }
        .map { it.safeToInt() }
}

private fun askToContinue(name: String, numberToGuess: () -> Int): IO<Unit> {
    return putStrLn("Do you want to continue, $name?")
        .flatMap { getStrLn() }
        .map {
            when {
                it == "n" -> Unit
                else -> gameLoop(numberToGuess, name)
            }
        }
}

fun String.safeToInt() = Try { this.toInt() }

fun putStrLn(input: String): IO<Unit> = IO { println(input) }
fun getStrLn(): IO<String> = IO { readLine() as String }
