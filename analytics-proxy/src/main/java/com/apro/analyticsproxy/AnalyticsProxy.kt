package com.apro.analyticsproxy

import java.lang.reflect.Proxy
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull

class AnalyticsProxy private constructor(private val analyticsTacker: AnalyticsTacker) {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> create(clazz: Class<T>): T {
        return Proxy.newProxyInstance(
            clazz.classLoader,
            arrayOf(clazz),
            AnalyticsProxyInvocationHandler(analyticsTacker)
        ) as T
    }

    class Builder {
        private var analyticsTacker: AnalyticsTacker by notNull()

        fun analyticsTracker(analyticsTacker: AnalyticsTacker): Builder {
            this.analyticsTacker = analyticsTacker
            return this
        }

        fun build(): AnalyticsProxy {
            return AnalyticsProxy(analyticsTacker)
        }
    }

}

inline fun <reified T : Any> AnalyticsProxy.create(): T = create(T::class.java)

