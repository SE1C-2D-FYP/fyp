/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.security;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 *
 * @author comon
 */
public class CustomJdbcDaoImpl extends JdbcDaoImpl {
    public CustomJdbcDaoImpl() {
        super();
    }
}
