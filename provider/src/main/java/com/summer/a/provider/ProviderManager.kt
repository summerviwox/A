package com.summer.a.provider

import java.util.ServiceLoader

/**
 * 多模块组件化解耦管理
 */
class ProviderManager {

    companion object{
        private var map:MutableMap<Class<*>,Any> = mutableMapOf()
        fun <T> getProvider(t:Class<T>):T?{
            return map[t] as? T
        }
        fun <T> addProvider(t:Class<T>,any: Any){
            map.put(t,any)
        }
    }
}