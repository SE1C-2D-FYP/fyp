/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.dao.ClientDao;
import com.crm.dao.ContactInfoDao;
import com.crm.dao.ContactNoteDao;
import com.crm.dao.EmployeeDao;
import com.crm.daoImpl.ClientDaoImpl;
import com.crm.daoImpl.ContactInfoDaoImpl;
import com.crm.daoImpl.ContactNoteDaoImpl;
import com.crm.daoImpl.EmployeeDaoImpl;
import org.springframework.ui.ModelMap;

/**
 *
 * @author Cliff
 */
public class ContactRecordController {

    private EmployeeDao employeeDao;
    private ClientDao clientDao;
    private ContactNoteDao contactNoteDao;
    private ContactInfoDao contactInfoDao;

    public ContactRecordController() {
//        employeeDao = new EmployeeDaoImpl();
//        clientDao = new ClientDaoImpl();
//        contactNoteDao = new ContactNoteDaoImpl();
//        contactInfoDao = new ContactInfoDaoImpl();
    }

    public String showContactRecord(ModelMap map) {
        return null;
    }

    public String addContactRecord(ModelMap map) {
        return null;
    }

    public String addCompanyContactRecord(ModelMap map) {
        return null;
    }

    public String updateContactRecord(ModelMap map) {
        return null;
    }

    public String deleteContactRecord(ModelMap map) {
        return null;
    }
}
