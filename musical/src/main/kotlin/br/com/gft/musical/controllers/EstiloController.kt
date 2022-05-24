package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.services.MusicalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("estilo")
class EstiloController(val estiloService: MusicalService<Estilo>) {
    @GetMapping
    fun findAll(): ResponseEntity<List<Estilo>> {
        return ResponseEntity.ok(estiloService.getAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Estilo> {
        return ResponseEntity.ok(estiloService.searchEntity(id))
    }

    @PostMapping
    fun save(@RequestBody estilo: Estilo): ResponseEntity<Estilo> {
        return ResponseEntity.status(HttpStatus.CREATED).body(estiloService.saveEntity(estilo))
    }

    @PutMapping("/{id}")
    fun update(@RequestBody estilo: Estilo, @PathVariable id: Int): ResponseEntity<Estilo> {
        return ResponseEntity.ok(estiloService.updateEntity(id, estilo))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        estiloService.deleteEntity(id)
        return ResponseEntity.noContent().build()
    }
}
