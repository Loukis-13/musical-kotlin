package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.services.MusicalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("estilo")
class EstiloController(val estiloService: MusicalService<Estilo>) {
    @GetMapping
    @Operation(
        tags = ["Estilo"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success")
        ]
    )
    fun findAll() = estiloService.getAll()

    @GetMapping("/{id}")
    @Operation(
        tags = ["Estilo"],
        responses = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "404", description = "Not found")
        ]
    )
    fun findById(@PathVariable id: Int) = estiloService.searchEntity(id)

    @PostMapping
    @Operation(
        tags = ["Estilo"],
        responses = [
            ApiResponse(responseCode = "201", description = "Created entity")
        ]
    )
    fun save(@RequestBody estilo: Estilo) = estiloService.saveEntity(estilo)

    @PutMapping("/{id}")
    @Operation(
        tags = ["Estilo"],
        responses = [
            ApiResponse(responseCode = "200", description = "Update success"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun update(@RequestBody estilo: Estilo, @PathVariable id: Int) = estiloService.updateEntity(id, estilo)

    @DeleteMapping("/{id}")
    @Operation(
        tags = ["Estilo"],
        responses = [
            ApiResponse(responseCode = "204", description = "No content"),
            ApiResponse(responseCode = "404", description = "Not found"),
        ]
    )
    fun delete(@PathVariable id: Int) = estiloService.deleteEntity(id)
}
