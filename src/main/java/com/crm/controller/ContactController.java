/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.dao.ClientDao;
import com.crm.dao.ContactInfoDao;
import com.crm.dao.EmployeeDao;
import com.crm.daoImpl.ClientDaoImpl;
import com.crm.daoImpl.ContactInfoDaoImpl;
import com.crm.daoImpl.EmployeeDaoImpl;
import org.springframework.ui.ModelMap;

/**
 *
 * @author Cliff
 */
public class ContactController {

    private EmployeeDao employeeDao;
    private ClientDao clientDao;
    private ContactInfoDao contactInfoDao;

    public ContactController() {
//        employeeDao = new EmployeeDaoImpl();
//        clientDao = new ClientDaoImpl();
//        contactInfoDao = new ContactInfoDaoImpl();
    }

    public String showClient(ModelMap map) {
        return null;
    }

    public String addClient(ModelMap map) {
        return null;
    }

    public String addCompanyClient(ModelMap map) {
        return null;
    }

    public String addContact(ModelMap map) {
        return null;
    }

    public String updateContact(ModelMap map) {
        return null;
    }

    public String deleteContact(ModelMap map) {
        return null;
    }
}
