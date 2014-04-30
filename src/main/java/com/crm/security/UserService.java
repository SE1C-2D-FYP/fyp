/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author comon
 */
public class UserService implements UserDetailsService {
    private UserDao userDao = new UserDao();

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
}
