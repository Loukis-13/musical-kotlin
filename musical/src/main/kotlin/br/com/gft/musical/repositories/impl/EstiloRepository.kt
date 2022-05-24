package br.com.gft.musical.repositories.impl

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.repositories.MusicalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class EstiloRepository(@Autowired val entityManager: EntityManager): MusicalRepository<Estilo> {
    @Transactional
    override fun save(entity: Estilo): Estilo {
        entityManager.persist(entity)
        return entity
    }

    @Transactional
    override fun update(id: Int, entity: Estilo): Estilo {
        findById(id).orElseThrow()
        entity.id = id
        return entityManager.merge(entity)
    }

    override fun findAll(): List<Estilo> {
        return entityManager.createQuery("SELECT e FROM Estilo e", Estilo::class.java).resultList
    }

    override fun findById(id: Int): Optional<Estilo> {
        return Optional.ofNullable(entityManager.find(Estilo::class.java, id))
    }

    @Transactional
    override fun deleteById(id: Int) {
        entityManager.remove(findById(id).orElseThrow())
    }
}