/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.security;

import com.crm.models.Account;
import com.crm.models.Employee;
import com.crm.models.Title;
import com.crm.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author comon
 */
public class UserDao {
    public User loadUserByUsername(final String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("username", username));
        List<Account> accountList = criteria.list();
        if (accountList.isEmpty()) {
            session.close();
            return null;
        }
        Employee emp = (Employee) session.load(Employee.class, accountList.get(0).getEmpId());
        Title title = (Title) session.load(Title.class, ((Title)emp.getTitleId()).getTitleId());
        User user = new User();
        user.setUsername(username);
        user.setPassword(accountList.get(0).getPassword());
        user.setEmail(accountList.get(0).getEmail());
        user.setFirstName(emp.getEngOtherName());
        user.setLastName(emp.getEngSurname());
        user.setEmpId(emp.getEmpId());
        Role role = new Role();
        role.setName(title.getEngTitle());
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setAuthorities(roles);
        session.close();
        return user;
    }
}
