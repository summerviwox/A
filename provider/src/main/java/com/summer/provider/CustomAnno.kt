package com.summer.provider

import kotlin.reflect.KClass

annotation class ProviderAnno(val value: KClass<*>)
annotation class RouteAnno(val value: String)
