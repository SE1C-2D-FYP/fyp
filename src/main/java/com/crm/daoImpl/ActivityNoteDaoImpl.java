/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.models.ActivityNote;
import com.crm.models.CompanyClient;
import com.crm.models.Employee;
import com.crm.models.Title;
import com.crm.util.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Cliff
 */
public class ActivityNoteDaoImpl {

    Session session = null;

//    public ActivityNoteDaoImpl() {
//        this.session = HibernateUtil.getSessionFactory().openSession();
//    }
//    public void addActivityNote(ActivityNote activityNote) {
//        session.beginTransaction();
//
//        session.save(activityNote);
//
//        session.getTransaction().commit();
//        Transaction tx = null;
//        try {
//           tx = session.beginTransaction();
//           session.save(activityNote);
//           
//           tx.commit();
//        }
//        catch (Exception e) {
//           if (tx!=null) tx.rollback();
//           e.printStackTrace(); 
//        }finally {
//           session.close();
//        }
//
//    }
    public Long addActivityNote(ActivityNote activityNote) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long aNId = null;
        try {
            tx = session.beginTransaction();
            aNId = (Long) session.save(activityNote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return aNId;
    }
    /* Method to UPDATE an ActivityNote from the records */

    public void updateActivityNote(ActivityNote activityNote) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(activityNote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an ActivityNote from the records */
    public void delActivityNote(Long aNId) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//        Query query = session.getNamedQuery("ActivityNote.findByANId").setString("aNId", "56");
//            List<?> list = query.list();
//            ActivityNote a = (ActivityNote) list.get(0);
//            Long val = new Long("47");
//            Long val2 = new Long(47);
            Query query2 = session.getNamedQuery("ActivityNote.findByANId").setLong("aNId", aNId);
            List<?> list2 = query2.list();
            ActivityNote activityNote = (ActivityNote) list2.get(0);
//            ActivityNote activityNote = (ActivityNote) session.get(ActivityNote.class, Long.valueOf("63"));

//            session.delete(a);

            session.delete(activityNote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ActivityNote findByANId(Long aNId) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ActivityNote activityNote = null;
        try {
            Query query2 = session.getNamedQuery("ActivityNote.findByANId").setLong("aNId", aNId);
            List<?> list2 = query2.list();
            activityNote = (ActivityNote) list2.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return activityNote;
        }

    }

    public List findAll() {
//        Language language =  (Language) session.get(Language.class, 1); 
        Session session = null;
        List<?> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("ActivityNote.findAll");
            list = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public List findByEmpId() {
//        Language language =  (Language) session.get(Language.class, 1); 

        Query query = session.getNamedQuery("Employee.findByEmpId").setString("empId", "H0000011");

        List<?> list = query.list();

        return list;
    }

    public Employee findEmp() {
        String empId = "H0000011";
        Session session = null;
        Employee emp = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            emp = (Employee) session.get(Employee.class, empId);
            Hibernate.initialize(emp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emp;
    }

    public List findTodayRecord(String EMP_ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = this.session.createQuery("from ActivityNote p where p.empId=:Employee AND (p.startDate BETWEEN trunc(sysdate) AND trunc(sysdate+1))")
                .setParameter("Employee", EMP_ID);
        List<?> list = query.list();

        return list;
    }

    public List findTodayRecordBySearch(String EMP_ID, String title, String status, Date start, Date end) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ActivityNote.class);
//        criteria.add(Expression.eq("empId", EMP_ID));
        if (title != null) {
            criteria.add(Expression.like("aNTitle", "%" + title + "%"));
        }
        if (status != null) {
            criteria.add(Restrictions.eq("aNType", status));
        }
        if (start != null && end != null) {

            criteria.add(Expression.ge("startDate", start));
            Date endDate = new Date(end.getTime() + (24 * 60 * 60 * 1000));
            criteria.add(Expression.le("startDate", endDate));

        }
        Date now = DateUtils.truncate(new Date(), Calendar.DATE);
        Date now1 = new Date(now.getTime() + (24 * 60 * 60 * 1000));
        criteria.add(Expression.le("startDate", now1));
        criteria.add(Expression.ge("startDate", now));
        criteria.addOrder(Order.asc("startDate"));
        return criteria.list();
    }
//    public List findTodayRecordByTitle(String EMP_ID) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND (p.startDate BETWEEN trunc(sysdate) AND trunc(sysdate+1))")
//                .setParameter("Employee", EMP_ID);
//        List<?> list = query.list();
//
//        return list;
//    }
//
//    public List findTodayRecordByStatus(String EMP_ID) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND (p.startDate BETWEEN trunc(sysdate) AND trunc(sysdate+1))")
//                .setParameter("Employee", EMP_ID);
//        List<?> list = query.list();
//
//        return list;
//    }
//
//    public List findTodayRecordByTitleStatus(String EMP_ID, String title, String status) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND p.aNTitle = :aNTitle AND p.aNType = :aNType AND (p.startDate BETWEEN trunc(sysdate) AND trunc(sysdate+1))")
//                .setParameter("Employee", EMP_ID).setParameter("aNTitle", title).setParameter("aNType", status);
//        List<?> list = query.list();
//
//        return list;
//    }

    public List findPastRecord(String EMP_ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND p.startDate < trunc(sysdate) ")
                .setParameter("Employee", EMP_ID);
        List<?> list = query.list();

        return list;
    }

    public List findPastRecordBySearch(String EMP_ID, String title, String status, Date start, Date end) {
        session = HibernateUtil.getSessionFactory().openSession();
//        List<?> list = session.createCriteria(ActivityNote.class).list();
        Criteria criteria = session.createCriteria(ActivityNote.class);
//        criteria.add(Expression.eq("empId", EMP_ID));
        if (title != null) {
            criteria.add(Expression.like("aNTitle", "%" + title + "%"));
        }
        if (status != null) {
            criteria.add(Restrictions.eq("aNType", status));
        }
        if (start != null && end != null) {
//            criteria.add(Restrictions.between("start",start,end));
            criteria.add(Expression.ge("startDate", start));
            Date endDate = new Date(end.getTime() + (24 * 60 * 60 * 1000));
            criteria.add(Expression.le("startDate", endDate));
            Date now = DateUtils.truncate(new Date(), Calendar.DATE);
            criteria.add(Expression.le("startDate", new Date()));

            criteria.addOrder(Order.desc("startDate"));
//            String a = endDate.toString();
//            Date date = end;
//
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//            cal.add(Calendar.DATE, 1); // add 10 days  
//
//            date = cal.getTime();
//            String b = date.toString();
        }
//        List list = criteria.list();
        return criteria.list();
//        
//        session = HibernateUtil.getSessionFactory().openSession();
//        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND p.aNTitle like :aNTitle AND p.aNType = :aNType AND p.startDate < trunc(sysdate) ")
//                .setParameter("Employee", EMP_ID).setParameter("aNTitle", title).setParameter("aNType", status);
//        List<?> list = query.list();
//
//        return list;
    }

    public List findFutureRecord(String EMP_ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee AND p.startDate >= trunc(sysdate+1)")
                .setParameter("Employee", EMP_ID);
        List<?> list = query.list();

        return list;
    }

    public List findFutureRecordBySearch(String EMP_ID, String title, String status, Date start, Date end) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ActivityNote.class);
//        criteria.add(Expression.eq("empId", EMP_ID));
        if (title != null) {
            criteria.add(Expression.like("aNTitle", "%" + title + "%"));
        }
        if (status != null) {
            criteria.add(Restrictions.eq("aNType", status));
        }
        if (start != null && end != null) {

            criteria.add(Expression.ge("startDate", start));
            Date endDate = new Date(end.getTime() + (24 * 60 * 60 * 1000));
            criteria.add(Expression.le("startDate", endDate));

        }
        Date tmr = new Date(DateUtils.truncate(new Date(), Calendar.DATE).getTime() + (24 * 60 * 60 * 1000));
        criteria.add(Expression.ge("startDate", tmr));
        criteria.addOrder(Order.asc("startDate"));
        return criteria.list();
    }

    public List findActivityNoteByEmpID(String EMP_ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = this.session.createQuery("select p from ActivityNote as p where p.empId=:Employee")
                .setParameter("Employee", EMP_ID);
        List<?> list = query.list();

        return list;
    }
//    select * from "FYP_ORCL".Activity_Note  where EMP_ID= 'H0000011' AND (START_DATE BETWEEN trunc(sysdate-1) AND trunc(sysdate+2));
}
