package br.com.gft.musical.repositories.impl

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.repositories.MusicalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class EstiloRepository(@Autowired val entityManager: EntityManager): MusicalRepository<Estilo> {
    @Transactional
    override fun save(entity: Estilo) = entityManager.persist(entity).run { entity }

    @Transactional
    override fun update(id: Int, entity: Estilo): Estilo {
        findById(id)
        entity.id = id
        return entityManager.merge(entity)
    }

    override fun findAll(): List<Estilo> =
        entityManager.createQuery("SELECT e FROM Estilo e", Estilo::class.java).resultList

    override fun findById(id: Int): Estilo =
        entityManager.find(Estilo::class.java, id) ?: throw NoSuchElementException("Estilo não encontrado")

    @Transactional
    override fun deleteById(id: Int) = entityManager.remove(findById(id))
}