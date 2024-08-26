package com.example.data.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val rSupportDispatchers: RSupportDispatchers)

enum class RSupportDispatchers {
    DEFAULT,
    IO,
}
