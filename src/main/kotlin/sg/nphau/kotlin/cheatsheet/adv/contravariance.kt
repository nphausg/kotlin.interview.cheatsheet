/**
 * Created by nphau on 09/12/2021, 00:30
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/12/2021, 15:54
 */
package sg.nphau.kotlin.cheatsheet.adv

class Source<out T>(private val t: T) {
    fun produceT(): T {
        return t
    }
}

class Sink<in T> {
    fun consume(t: T) {

    }
}

fun main(args: Array<String>) {
    val strSource: Source<String> = Source("Producer")
    val anySource: Source<Any> = strSource  // out-> covariance
    anySource.produceT()

    val anySink: Sink<Any> = Sink()
    val strSink: Sink<String> = anySink     // in -> contravariance
    strSink.consume("Consumer")
}