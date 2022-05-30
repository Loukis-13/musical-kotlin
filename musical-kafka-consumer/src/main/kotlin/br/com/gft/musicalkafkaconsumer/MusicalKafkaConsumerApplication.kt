package br.com.gft.musicalkafkaconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicalKafkaConsumerApplication

fun main(args: Array<String>) {
	runApplication<MusicalKafkaConsumerApplication>(*args)
}
