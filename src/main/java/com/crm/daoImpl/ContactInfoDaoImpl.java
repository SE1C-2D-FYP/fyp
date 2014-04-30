/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.ContactInfoDao;
import com.crm.models.ContactInfo;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class ContactInfoDaoImpl implements ContactInfoDao {
        Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllContactInfo() {
        return null;
    }

    @Override
    public ContactInfo findByContactInfoId(Long ctInfoId) {
        return null;
    }

    @Override
    public Long addContactInfo(ContactInfo contactInfo) {
        return null;
    }

    @Override
    public void updateContactInfo(ContactInfo contactInfo) {
    }

    @Override
    public void delContactInfo(Long ctInfoId) {
    }            
}
