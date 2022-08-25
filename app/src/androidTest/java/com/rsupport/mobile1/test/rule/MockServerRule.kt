package com.rsupport.mobile1.test.rule

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockServerRule(
    val server: MockWebServer = MockWebServer()
): TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        server.start(8080)
    }

    override fun finished(description: Description) {
        super.finished(description)
        server.shutdown()
    }
}