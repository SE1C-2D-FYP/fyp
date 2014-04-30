/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.EmployeeDao;
import com.crm.models.Employee;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Cliff
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List findAllEmp() {
        Session session = null;
        List<?> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("Employee.findAll");
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

    @Override
    public Employee findByEmpId(String empId) {
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

    @Override
    public Integer addEmployee(Employee emp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Employee employee = emp;
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public void listEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
//            for (Iterator iterator =
//                    employees.iterator(); iterator.hasNext();) {
//                Employee employee = (Employee) iterator.next();
//                System.out.print("First Name: " + employee.getFirstName());
//                System.out.print("  Last Name: " + employee.getLastName());
//                System.out.println("  Salary: " + employee.getSalary());
//            }
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


    /* Method to UPDATE salary for an employee */
    @Override
    public void updateEmployee(Employee emp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            Employee employee =
//                    (Employee) session.get(Employee.class, emp.getEmpId());
//            employee.setSalary(salary);
//            session.update(employee);

            session.update(emp);
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
    /* Method to DELETE an employee from the records */

    @Override
    public void delEmployee(String EmployeeID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
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
    
//        public Long addTestId( ) {
//            
//            
//          Testid  testid = new Testid();
//          testid.setColumn1("test");
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        Long employeeID = null;
//        try {
//            tx = session.beginTransaction();
//            employeeID = (Long) session.save(testid);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return employeeID;
//    }
}
//public class TestHibernateNamedQuery 
//{
//    public static void main(String[] args) 
//    {
//        //Open the hibernate session
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        try
//        {
//            //Update record using named query
//            Query query = session.getNamedQuery(DepartmentEntity.UPDATE_DEPARTMENT_BY_ID)
//                                        .setInteger("id", 1)
//                                        .setString("name", "Finance");
//            query.executeUpdate();
//             
//            //Get named query instance
//            query = session.getNamedQuery(DepartmentEntity.GET_DEPARTMENT_BY_ID)
//                                        .setInteger("id", 1);
//            //Get all departments (instances of DepartmentEntity)
//            DepartmentEntity department = (DepartmentEntity) query.uniqueResult();
//            System.out.println(department.getName());
//        }
//        finally
//        {
//            session.getTransaction().commit();
//            HibernateUtil.shutdown();
//        }
//    }
//}
// 
//Output in console:
// 
//Hibernate: update DEPARTMENT set NAME=? where ID=?
//Hibernate: select department0_.ID as ID0_, department0_.NAME as NAME0_ from DEPARTMENT department0_ where department0_.ID=?
//Finance
