package com.summer.a.provider

import kotlin.reflect.KClass

annotation class ProviderAnno(val value: KClass<*>)
annotation class RouteAnno(val value: String)
