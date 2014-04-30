/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.ActivityNote;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface ActivityNoteDao {

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
    Long addActivityNote(ActivityNote activityNote);
    /* Method to UPDATE an ActivityNote from the records */

    /* Method to DELETE an ActivityNote from the records */
    void delActivityNote(String aNId);

    List findAll();

    List findByEmpId();

    List findFutureRecord(String EMP_ID);
    //    select * from "FYP_ORCL".Activity_Note  where EMP_ID= 'H0000011' AND (START_DATE BETWEEN trunc(sysdate-1) AND trunc(sysdate+2));

    List findPastRecord(String EMP_ID);

    List findTodayRecord(String EMP_ID);

    void updateActivityNote(ActivityNote activityNote);
    
}
