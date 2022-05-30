package br.com.gft.musical.services

import br.com.gft.musical.entities.Artista
import org.springframework.http.ResponseEntity

interface MusicalService<T> {
    fun getAll(): ResponseEntity<List<T>>
    fun saveEntity(entity: T): ResponseEntity<T>
    fun searchEntity(id: Int): ResponseEntity<T>
    fun updateEntity(id: Int, entity: T): ResponseEntity<T>
    fun deleteEntity(id: Int): ResponseEntity<Void>
}