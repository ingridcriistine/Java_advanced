package com.desktopapp;

import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Context {
    private EntityManagerFactory emf;
    private EntityManager em;

    public Context() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void begin() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public <T> List<T> find(Class<T> entytyClass, String query, Object... values) {
        EntityManager em = emf.createEntityManager();
        List<T> mensagens = null;
        try {
            var queryObj = em.createQuery(query, entytyClass);
            for (Integer i = 0; i < values.length; i++) {
                queryObj = queryObj.setParameter("arg" + i.toString(), values[i]);
            }
            mensagens = queryObj.getResultList();
        } finally {
            em.close();
        }
        return mensagens;
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        EntityManager em = emf.createEntityManager();
        List<T> mensagens = null;
        try {
            var queryObj = em.createQuery("select m from Mensagem m", entityClass);
            mensagens = queryObj.getResultList();
        } finally {
            em.close();
        }
        return mensagens;
    }

    public <T> TypedQuery<T> createQuery(Class<T> entityClass, String query) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(query, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        EntityManager em = emf.createEntityManager();
        T user = null;
        try {
            user = em.find(entityClass, primaryKey);
        } finally {
            em.close();
        }
        return user;
    }

    public void persist(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.persist(object);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public void commit() {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            em = null;
        }
    }

    public <T> void update(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.merge(object);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        } 
    }

    public <T> void delete(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.remove(em.contains(object) ? object : em.merge(object));
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        } 
    }
}