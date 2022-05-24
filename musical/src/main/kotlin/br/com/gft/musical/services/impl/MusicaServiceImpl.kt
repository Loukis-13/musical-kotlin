package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Musica
import br.com.gft.musical.repositories.MusicalRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.stereotype.Service

@Service
class MusicaServiceImpl(val musicaRespository: MusicalRepository<Musica>): MusicalService<Musica> {
    override fun getAll(): List<Musica> {
        return musicaRespository.findAll()
    }

    override fun saveEntity(entity: Musica): Musica {
        return musicaRespository.save(entity)
    }

    override fun searchEntity(id: Int): Musica {
        return musicaRespository.findById(id).orElseThrow()
    }

    override fun updateEntity(id: Int, entity: Musica): Musica {
        return musicaRespository.update(id, entity)
    }

    override fun deleteEntity(id: Int) {
        musicaRespository.deleteById(id)
    }
}