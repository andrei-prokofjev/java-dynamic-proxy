package com.apro.javadynamicproxy

import com.apro.analyticsproxy.EventName
import com.apro.analyticsproxy.Param

interface AppAnalytics {

    @EventName(value = "App Start")
    fun trackAppStart()

    @EventName(value = "Click count")
    fun trackClickCount(@Param("count") count: Int)
}