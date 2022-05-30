package br.com.gft.respostamusical.configs

import br.com.gft.respostamusical.models.Artista
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "resposta-musical", url = "musical:8081/v1", path = "/artista")
interface ArtistaFeignClient {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Artista>
}
