package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Musica
import br.com.gft.musical.services.MusicalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("musica")
class MusicaController(val musicaService: MusicalService<Musica>) {
    @GetMapping
    @Operation(
        tags = ["Musica"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success")
        ]
    )
    fun findAll() = musicaService.getAll()

    @GetMapping("/{id}")
    @Operation(
        tags = ["Musica"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun findById(@PathVariable id: Int) = musicaService.searchEntity(id)

    @PostMapping
    @Operation(
        tags = ["Musica"],
        responses = [
            ApiResponse(responseCode = "201", description = "Created entity")
        ]
    )
    fun save(@RequestBody musica: Musica) = musicaService.saveEntity(musica)

    @PutMapping("/{id}")
    @Operation(
        tags = ["Musica"],
        responses = [
            ApiResponse(responseCode = "200", description = "Update success"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun update(@RequestBody musica: Musica, @PathVariable id: Int) = musicaService.updateEntity(id, musica)

    @DeleteMapping("/{id}")
    @Operation(
        tags = ["Musica"],
        responses = [
            ApiResponse(responseCode = "204", description = "No content"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun delete(@PathVariable id: Int) = musicaService.deleteEntity(id)
}
