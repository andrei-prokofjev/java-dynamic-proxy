package com.apro.javadynamicproxy

import com.apro.analyticsproxy.AnalyticsProxy
import com.apro.analyticsproxy.AnalyticsTacker

fun main() {

    val analyticsProxy = AnalyticsProxy.Builder()
        .analyticsTracker(LogAnalyticsTacker())
        .build()

    val appAnalytics: AppAnalytics = analyticsProxy.create(AppAnalytics::class.java)


    appAnalytics.trackAppStart()
    appAnalytics.trackClickCount(44)

}

class LogAnalyticsTacker : AnalyticsTacker {
    override fun trackEvent(eventName: String, params: Map<String, Any?>?) {

        if (params.isNullOrEmpty()) {
            println(">>> $eventName ")
        } else {
            println(">>> $eventName($params)")
        }
    }
}