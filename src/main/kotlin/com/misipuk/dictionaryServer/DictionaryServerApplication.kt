package com.misipuk.dictionaryServer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DictionaryServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(DictionaryServerApplication::class.java, *args)
}
