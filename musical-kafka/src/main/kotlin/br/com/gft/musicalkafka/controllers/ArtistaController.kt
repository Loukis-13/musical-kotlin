package br.com.gft.musicalkafka.controllers

import br.com.gft.musicalkafka.entities.Artista
import br.com.gft.musicalkafka.services.ArtistaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("artista")
class ArtistaController(@Autowired val artistaService: ArtistaService) {
    @PostMapping
    fun returnArtista(@RequestBody artista: Artista): ResponseEntity<Artista> {
        artistaService.sendArtistaToKafka(artista)
        return ResponseEntity.ok(artista)
    }
}
