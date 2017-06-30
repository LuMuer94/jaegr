package com.jaegr.daos;

import javax.persistence.EntityManager;

/**
 * Created by Leon on 09.06.2017.
 */
public abstract class BaseDAO {
    protected final EntityManager entityManager;

    protected BaseDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
