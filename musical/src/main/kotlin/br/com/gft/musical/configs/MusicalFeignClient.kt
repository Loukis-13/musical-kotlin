package br.com.gft.musical.configs

import br.com.gft.musical.entities.Artista
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Component
@FeignClient(name = "musicalKafkaProducer", url = "musical-kafka:8082/v1")
interface MusicalFeignClient {
    @PostMapping("/artista", consumes = ["application/json"])
    fun returnArtista(@RequestBody artista: Artista): ResponseEntity<Artista>
}
