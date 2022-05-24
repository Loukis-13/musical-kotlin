package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Artista
import br.com.gft.musical.services.MusicalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("artista")
class ArtistaController (val artistaService: MusicalService<Artista>) {
    @GetMapping
    @Operation(
        tags = ["Artista"],
        responses = [ApiResponse(responseCode = "200", description = "success")]
    )
    fun findAll(): ResponseEntity<List<Artista>> {
        return ResponseEntity.ok(artistaService.getAll())
    }

    @PostMapping
    @Operation(
        tags = ["Artista"],
        responses = [ApiResponse(responseCode = "201", description = "Created success")]
    )
    fun saveArtista(@RequestBody artista: Artista): ResponseEntity<Artista> {
        artistaService.saveEntity(artista)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(artista.id).toUri()
        return ResponseEntity.created(uri).body(artista)
    }

    @GetMapping(value = ["/{id}"])
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "200", description = "success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun findById(@PathVariable id: Int): ResponseEntity<Artista> {
        return ResponseEntity.ok(artistaService.searchEntity(id))
    }

    @PutMapping(value = ["/{id}"])
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "200", description = "Update success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun updateArtista(@PathVariable id: Int, @RequestBody artista: Artista): ResponseEntity<Artista> {
        return ResponseEntity.ok().body(artistaService.updateEntity(id, artista))
    }

    @DeleteMapping(value = ["/{id}"])
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "204", description = "Success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun deleteArtista(@PathVariable id: Int): ResponseEntity<Void> {
        artistaService.deleteEntity(id)
        return ResponseEntity.noContent().build()
    }
}