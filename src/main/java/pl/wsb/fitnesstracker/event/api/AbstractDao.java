package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entity manager providing the abstract for Data Access Object
 */

@Transactional
public abstract class AbstractDao {

    @PersistenceContext
    protected EntityManager entityManager;

}