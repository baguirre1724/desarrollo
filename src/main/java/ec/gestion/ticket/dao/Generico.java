/*
 * Generico.java 
 * Puntonet
 * Todos los derechos reservados. All rights reserved.
 * 30/04/2014
 * Copyright (C) Puntonet
 */
package ec.gestion.ticket.dao;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 *
 * @param <T> Entidad sobre la que actua el DAO
 * @author Byron Aguirre
 */
public abstract class Generico<T> {

    /**
     * Clase generica del DAO.
     */
    private Class<T> entityClass;

    /**
     * Constructor por parámetros.
     *
     * @param entityClass Class de la entidad
     */
    public Generico(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Manager de persistencia.
     * @return 
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Persiste o crea una entidad.
     *
     * @param entity 
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    
   
    /**
     * Actualiza una entidad.
     *
     * @param entity 
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Elimina una entidad.
     *
     * @param entity 
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Recupera una entidad dado el valor de su @Id.
     *
     * @param id valor del identificador de la entidad
     * @return Entidad recuperada de la bbdd
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    /**
     * Recupera todas las entidades de la bbdd.
     *
     * @return Lista con todas las entidades de la bbdd
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     * Recupera una lista de entidades dado un rango.
     *
     * @param range valor del rango
     * @return Entidades recuperadas de la bbdd
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    /**
     * Cuenta el numero de entidades que existe en la bbdd.
     *
     * @return 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
