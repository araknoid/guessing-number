package com.araknoid.talk

import kotlin.random.Random

fun main() {
    game { Random.nextInt(5) + 1 }
}

fun game(numberToGuess: () -> Int) {
    println("What is your name?")

    val name = readLine()
    println("""Hello, $name, welcome to the game!""")

    var exec = true

    while (exec) {
        val num = numberToGuess()

        println("Dear $name, please guess a number from 1 to 5:")

        val guess = readLine()?.toInt()

        if (guess == num) println("You guessed right, $name!")
        else println("You guessed wrong, $name! The number was: $num")

        println("Do you want to continue, $name?")

        val answer = readLine()
        when {
            answer == "y" -> exec = true
            answer == "n" -> exec = false
        }
    }
}