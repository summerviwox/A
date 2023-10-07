package com.summer.a.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Flow

interface Ia{
    fun abc(a:String){
        println("${a}")
    }
}

class R3(ia: Ia):Ia by ia{

}

class R5:Ia{
    override fun abc(a: String) {
        println("gogogoR5")
    }
}

fun main(){
    runBlocking{
        var a:String? = null

        a?.also { println(11111) }?:also { println(222222) }
    }
}

suspend fun mapa(a:Int):Int{
    delay(1000)
    return a
}

suspend fun e():String{
    delay(3000)
    return "5454"
}

suspend fun d(){
    repeat(100){
        println(it)
        delay(1000)
    }
}

suspend fun a(){
    println("a-start")
    delay(7000L)
    println("1.1")
    println("a-end")
}

suspend fun b(){
    println("b-start")
    coroutineScope {
        launch {
            delay(4000L)
            println("2.2")
        }
        delay(1000L)
        println("2.4")
    }
    println("b-end")
}

suspend fun c(){
    println("c")
}
