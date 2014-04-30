/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.ContactNote;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface ContactNoteDao {

    Long addContactNote(ContactNote contactNote);

    void delContactNote(Long ctNoteId);

    List findAllContactNote();

    ContactNote findByContactNoteId(Long ctNoteId);

    void updateContactNote(ContactNote contactNote);
    
}
