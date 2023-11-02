package com.summer.a.app

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
