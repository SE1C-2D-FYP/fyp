/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.dao.ClientDao;
import com.crm.dao.EmployeeDao;
import com.crm.dao.TransactionDao;
import com.crm.daoImpl.ClientDaoImpl;
import com.crm.daoImpl.EmployeeDaoImpl;
import com.crm.daoImpl.TransactionDaoImpl;
import org.springframework.ui.ModelMap;

/**
 *
 * @author Cliff
 */
public class TransactionController {
    private EmployeeDao employeeDao;
    private ClientDao clientDao;
    private TransactionDao transactionDao;

    public TransactionController() {
//        employeeDao = new EmployeeDaoImpl();
//        clientDao = new ClientDaoImpl();
//        transactionDao = new TransactionDaoImpl();
    }
    
    
    public String showTransaction(ModelMap map) {
        return null;
    }

    public String showTransactions(ModelMap map) {
        return null;
    }

    public String addTransaction(ModelMap map) {
        return null;
    }

    public String addCompanyClient(ModelMap map) {
        return null;
    }

    public String addContact(ModelMap map) {
        return null;
    }

    public String updateTransaction(ModelMap map) {
        return null;
    }

    public String deleteTransaction(ModelMap map) {
        return null;
    }
}
