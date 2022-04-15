package com.apro.javadynamicproxy

import com.apro.analyticsproxy.EventName
import com.apro.analyticsproxy.Param

interface AppAnalytics {

    @EventName("App Start")
    fun trackAppStart()

    @EventName("Click count")
    fun trackClickCount(@Param("count") count: Int)
}