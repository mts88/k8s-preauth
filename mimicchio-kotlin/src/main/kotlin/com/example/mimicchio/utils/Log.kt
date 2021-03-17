package com.example.mimicchio.utils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Log {
    companion object {
        inline var <reified T> T.log: Logger
            get() = LoggerFactory.getLogger(T::class.java)
            set(value) {}
    }
}

