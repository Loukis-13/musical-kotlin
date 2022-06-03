package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.repositories.MusicalRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EstiloServiceImpl(val estiloRespository: MusicalRepository<Estilo>) : MusicalService<Estilo> {
    override fun getAll() = ResponseEntity.ok(estiloRespository.findAll())

    override fun searchEntity(id: Int) = ResponseEntity.ok(estiloRespository.findById(id))

    override fun saveEntity(entity: Estilo) =
        ResponseEntity.status(HttpStatus.CREATED).body(estiloRespository.save(entity))

    override fun updateEntity(id: Int, entity: Estilo) = ResponseEntity.ok(estiloRespository.update(id, entity))

    override fun deleteEntity(id: Int): ResponseEntity<Void> =
        estiloRespository.deleteById(id).run { ResponseEntity.noContent().build() }
}