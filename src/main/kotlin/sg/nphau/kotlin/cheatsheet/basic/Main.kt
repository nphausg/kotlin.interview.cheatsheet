/**
 * Created by nphau on 08/04/2022, 00:30
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 08/04/2022, 15:54
 */
package sg.nphau.kotlin.cheatsheet.basic

import java.lang.Exception
import kotlin.math.absoluteValue
import kotlin.random.Random

fun main(args: Array<String>) {
    // For-Loop Expression
    printArgs(args)

    // When Expression
    print("Bit: ${getRandomBit()}")
    print("Color: ${getRandomColor()}")

    // Elvis Operator
    printListWithNulls()
}

fun getRandom(from: Int, until: Int) = Random.nextInt(from, until)

// https://kotlinlang.org/docs/basic-syntax.html#using-a-for-loop
fun printArgs(args: Array<String>) {
    for (name in args)
        println("Hello, $name!")

    for (i in args.indices)
        print(args[i])

    for ((index, value) in args.withIndex()) {
        println("the element at $index is $value")
    }
}

// region When Expression: http://kotlinlang.org/docs/reference/control-flow.html#when-expression
fun getRandomBit() = when (getRandom(0, 2)) {
    1 -> Bit.ONE
    else -> Bit.ZERO
}

fun getRandomColor() = when (getRandom(1, 4)) {
    1 -> Color.RED
    2 -> Color.GREEN
    else -> Color.BLUE
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}
// endregion

// region Elvis Operator: http://kotlinlang.org/docs/reference/null-safety.html#null-safety
fun printListWithNulls() {
    val listWithNulls: List<String?> = listOf("-5", "8", "13s", null)
    for (item in listWithNulls) {
        // // prints Kotlin and ignores null
        item?.let {
            println("Origin: $it - Parse: ${parseInt(item)?.absoluteValue ?: -1}")
        }
    }
}

fun parseInt(str: String): Int? {
    try {
        return Integer.parseInt(str)
    } catch (e: Exception) {
        println("One of the arguments isn't Int")
    }
    return null
}
// endregion