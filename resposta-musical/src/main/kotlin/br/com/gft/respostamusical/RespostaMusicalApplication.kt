package br.com.gft.respostamusical

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RespostaMusicalApplication

fun main(args: Array<String>) {
	runApplication<RespostaMusicalApplication>(*args)
}
