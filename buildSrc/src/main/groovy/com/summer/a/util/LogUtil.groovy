package com.summer.a.util

class LogUtil {

    public static boolean canPrint = false

    static void log(Object text){
        if(!canPrint){
            return
        }
        println(text)
    }
}