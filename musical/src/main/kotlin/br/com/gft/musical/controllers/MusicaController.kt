package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Musica
import br.com.gft.musical.services.MusicalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("musica")
class MusicaController(val musicaService: MusicalService<Musica>) {
    @GetMapping
    fun findAll(): ResponseEntity<List<Musica>> {
        return ResponseEntity.ok(musicaService.getAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Musica> {
        return ResponseEntity.ok(musicaService.searchEntity(id))
    }

    @PostMapping
    fun save(@RequestBody musica: Musica): ResponseEntity<Musica> {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicaService.saveEntity(musica))
    }

    @PutMapping("/{id}")
    fun update(@RequestBody musica: Musica, @PathVariable id: Int): ResponseEntity<Musica> {
        return ResponseEntity.ok(musicaService.updateEntity(id, musica))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        musicaService.deleteEntity(id)
        return ResponseEntity.noContent().build()
    }
}
