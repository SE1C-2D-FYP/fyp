package com.crm.client;

import com.crm.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public class BaseDAO<T> {
    
    public void create(T object) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            
            session.getTransaction().rollback();
            
        } finally {
            
            session.close();
            
        }
      
    }
    
    public void update(T object) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            
            session.getTransaction().rollback();
            
        } finally {
            
            session.close();
            
        }
      
    }
    
    public void delete(T object) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            
            session.getTransaction().rollback();
            
        } finally {
            
            session.close();
            
        }
      
    }
    
    public T find(Class <? extends T> clazz, Serializable id) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            
            session.beginTransaction();
            return (T) session.get(clazz, id);
            
        } finally {
            
            session.getTransaction().commit();
            session.close();
            
        }
        
    }
    
    public List<T> list(String hql) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            
            session.beginTransaction();
            return session.createQuery(hql).list();
            
        } finally {
            
            session.getTransaction().commit();
            session.close();
            
        }
        
    }
    
}
