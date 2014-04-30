/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.Employee;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface EmployeeDao {

    Integer addEmployee(Employee emp);

    void delEmployee(String EmployeeID);

    List findAllEmp();

    Employee findByEmpId(String empId);

    /* Method to UPDATE salary for an employee */
    void updateEmployee(Employee emp);
    /* Method to DELETE an employee from the records */
    
}
