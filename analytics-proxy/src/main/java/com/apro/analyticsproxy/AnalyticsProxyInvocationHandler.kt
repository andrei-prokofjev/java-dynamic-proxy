package com.apro.analyticsproxy

import android.annotation.SuppressLint
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class AnalyticsProxyInvocationHandler(
    private val analyticsTacker: AnalyticsTacker
) : InvocationHandler {

    @SuppressLint("NewApi")
    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
        val eventName = method.annotations.firstNotNullOf { it as? EventName }
        val analyticsEventName = eventName.value
        if (method.parameterCount == 0) {
            analyticsTacker.trackEvent(analyticsEventName)
        } else {
            checkNotNull(args)
            val annotations = method.parameterAnnotations
            val analyticsParamNames = annotations.map { paramAnnotations ->
                paramAnnotations.firstNotNullOf { it as? Param }.value
            }

           val params =  buildMap {
                repeat(method.parameterCount) { index ->
                    put(analyticsParamNames[index], args[index])

                }
            }

            analyticsTacker.trackEvent(analyticsEventName, params)
        }

        return Unit
    }
}

interface AnalyticsTacker {
    fun trackEvent(eventName: String, params: Map<String, Any?>? = null)
}