package br.com.gft.musicalkafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicalKafkaApplication

fun main(args: Array<String>) {
	runApplication<MusicalKafkaApplication>(*args)
}
