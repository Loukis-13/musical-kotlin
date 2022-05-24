package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.repositories.MusicalRepository
import br.com.gft.musical.services.MusicalService
import org.springframework.stereotype.Service

@Service
class EstiloServiceImpl(val estiloRespository: MusicalRepository<Estilo>): MusicalService<Estilo> {
    override fun getAll(): List<Estilo> {
        return estiloRespository.findAll()
    }

    override fun saveEntity(entity: Estilo): Estilo {
        return estiloRespository.save(entity)
    }

    override fun searchEntity(id: Int): Estilo {
        return estiloRespository.findById(id).orElseThrow()
    }

    override fun updateEntity(id: Int, entity: Estilo): Estilo {
        return estiloRespository.update(id, entity)
    }

    override fun deleteEntity(id: Int) {
        estiloRespository.deleteById(id)
    }
}