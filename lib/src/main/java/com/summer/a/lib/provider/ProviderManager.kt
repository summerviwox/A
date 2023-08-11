//package com.summer.a.lib.provider
//
//import java.util.ServiceLoader
//
///**
// * 多模块组件化解耦管理
// */
//class ProviderManager {
//
//    companion object{
//        private var map:MutableMap<Class<*>,Any> = mutableMapOf()
//        init {
//            ServiceLoader.load(LoginProvider::class.java).forEach {
//                it.javaClass.interfaces?.forEach {
//                        that->
//                    map.put(that,it)
//                }
//            }
//            ServiceLoader.load(Any::class.java).forEach {
//                it.javaClass.interfaces?.forEach {
//                    that->
//                    map.put(that,it)
//                }
//            }
//        }
//        fun <T> getProvider(t:Class<T>):T?{
//            return map[t] as? T
//        }
//    }
//}