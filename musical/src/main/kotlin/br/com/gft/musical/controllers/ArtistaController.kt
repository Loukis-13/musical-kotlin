package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Artista
import br.com.gft.musical.services.MusicalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("artista")
class ArtistaController(val artistaService: MusicalService<Artista>) {
    @GetMapping
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success")
        ]
    )
    fun findAll() = artistaService.getAll()

    @GetMapping("/{id}")
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun findById(@PathVariable id: Int) = artistaService.searchEntity(id)

    @PostMapping
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "201", description = "Created entity")
        ]
    )
    fun saveArtista(@RequestBody artista: Artista) = artistaService.saveEntity(artista)

    @PutMapping("/{id}")
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "200", description = "Update success"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun updateArtista(@PathVariable id: Int, @RequestBody artista: Artista) = artistaService.updateEntity(id, artista)

    @DeleteMapping("/{id}")
    @Operation(
        tags = ["Artista"],
        responses = [
            ApiResponse(responseCode = "204", description = "No content"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun deleteArtista(@PathVariable id: Int) = artistaService.deleteEntity(id)
}
