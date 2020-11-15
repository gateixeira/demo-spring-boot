package com.gateixeira.demospringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoSpringBootApplication

fun main(args: Array<String>) {
	runApplication<DemoSpringBootApplication>(*args)
}
