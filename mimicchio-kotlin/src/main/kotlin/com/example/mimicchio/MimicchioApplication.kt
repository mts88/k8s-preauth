package com.example.mimicchio

import com.example.mimicchio.security.JwtConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration::class)
class MimicchioApplication

fun main(args: Array<String>) {
	runApplication<MimicchioApplication>(*args)
}
