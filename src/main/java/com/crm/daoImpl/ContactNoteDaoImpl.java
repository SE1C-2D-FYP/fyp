/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.ContactNoteDao;
import com.crm.models.ContactNote;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class ContactNoteDaoImpl implements ContactNoteDao {
    Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllContactNote() {
        return null;
    }

    @Override
    public ContactNote findByContactNoteId(Long ctNoteId) {
        return null;
    }

    @Override
    public Long addContactNote(ContactNote contactNote) {
        return null;
    }

    @Override
    public void updateContactNote(ContactNote contactNote) {
    }

    @Override
    public void delContactNote(Long ctNoteId) {
    }
}
