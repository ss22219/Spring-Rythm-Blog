package com.mvc.repository;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRepository<T> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
    }

    private Session session;

    public List<T> find(String hql) {
        return getSession().createQuery(hql).list();
    }

    public List<T> find(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
        }
        return query.list();
    }

    public T get(Class classe, int id) {
        return (T) getSession().get(classe, id);
    }

    public void update(T object) {
        getSession().saveOrUpdate(object);
    }

    public void save(T object) {
        getSession().merge(object);
        getSession().flush();
    }

    public Session getSession() {
        if (session == null) {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void delete(T object) {
        getSession().delete(object);
    }
}
