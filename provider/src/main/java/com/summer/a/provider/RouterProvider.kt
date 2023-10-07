package com.summer.a.provider

class RouterProvider {

    companion object Instance {
        var map: MutableMap<String, String> = mutableMapOf()
        fun put(key: String, value: String) {
            map.put(key, value)
        }

        fun get(key: String): String? {
            return map.get(key)
        }
    }
}