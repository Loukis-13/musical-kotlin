package br.com.gft.musical.services.impl

import br.com.gft.musical.configs.MusicalFeignClient
import br.com.gft.musical.entities.Artista
import br.com.gft.musical.repositories.impl.ArtistaRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ArtistaServiceImpl (
    val artistaRepository: ArtistaRepository,
    val musicalFeignClient: MusicalFeignClient,
) : MusicalService<Artista> {
    override fun getAll() = ResponseEntity.ok(artistaRepository.findAll())

    override fun saveEntity(entity: Artista) =
        artistaRepository.save(entity).run { ResponseEntity.status(HttpStatus.CREATED).body(this) }

    override fun searchEntity(id: Int) =
        ResponseEntity.ok(artistaRepository.findById(id))
//        ResponseEntity.ok(musicalFeignClient.returnArtista(artistaRepository.findById(id)).body!!)

    override fun updateEntity(id: Int, entity: Artista) = ResponseEntity.ok(artistaRepository.update(id, entity))

    override fun deleteEntity(id: Int): ResponseEntity<Void> =
        artistaRepository.deleteById(id).run { ResponseEntity.noContent().build() }
}