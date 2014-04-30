/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.ContactInfo;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface ContactInfoDao {

    Long addContactInfo(ContactInfo contactInfo);

    void delContactInfo(Long ctInfoId);

    List findAllContactInfo();

    ContactInfo findByContactInfoId(Long ctInfoId);

    void updateContactInfo(ContactInfo contactInfo);
    
}
