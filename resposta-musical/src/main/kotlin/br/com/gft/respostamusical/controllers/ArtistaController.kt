package br.com.gft.respostamusical.controllers

import br.com.gft.respostamusical.models.Artista
import br.com.gft.respostamusical.services.ArtistaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("artista")
class ArtistaController(val artistaService: ArtistaService) {
    @GetMapping("/{artistaId}")
    fun getArtista(@PathVariable artistaId: Int): ResponseEntity<Artista> {
        return ResponseEntity.ok(artistaService.getArtista(artistaId))
    }
}