package br.com.gft.musical.services.impl

import br.com.gft.musical.configs.MusicalFeignClient
import br.com.gft.musical.entities.Artista
import br.com.gft.musical.repositories.impl.ArtistaRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.stereotype.Service

@Service
class ArtistaServiceImpl (
    val artistaRepository: ArtistaRepository,
    val musicalFeignClient: MusicalFeignClient,
) : MusicalService<Artista> {
    override fun getAll(): List<Artista> {
        return  artistaRepository.findAll()
    }

    override fun saveEntity(entity: Artista): Artista {
        return artistaRepository.save(entity)
    }

    override fun searchEntity(id: Int): Artista {
        return musicalFeignClient.returnArtista(artistaRepository.findById(id).orElseThrow()).body!!
    }

    override fun updateEntity(id: Int, entity: Artista): Artista {
        return artistaRepository.update(id, entity)
    }

    override fun deleteEntity(id: Int) {
        artistaRepository.deleteById(id)
    }
}