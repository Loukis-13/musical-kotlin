package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Musica
import br.com.gft.musical.repositories.MusicalRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MusicaServiceImpl(val musicaRespository: MusicalRepository<Musica>): MusicalService<Musica> {
    override fun getAll() = ResponseEntity.ok(musicaRespository.findAll())

    override fun searchEntity(id: Int) = ResponseEntity.ok(musicaRespository.findById(id))

    override fun updateEntity(id: Int, entity: Musica) = ResponseEntity.ok(musicaRespository.update(id, entity))

    override fun saveEntity(entity: Musica) =
        ResponseEntity.status(HttpStatus.CREATED).body(musicaRespository.save(entity))

    override fun deleteEntity(id: Int): ResponseEntity<Void> =
        musicaRespository.deleteById(id).run { ResponseEntity.noContent().build() }
}