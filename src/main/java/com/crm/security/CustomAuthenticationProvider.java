/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.security;

import java.util.Collection;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author comon
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserService userService = new UserService();
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        User user = userService.loadUserByUsername(username);
        
        if(user == null)
            throw new BadCredentialsException("Invalid Username");
        if(!password.equals(user.getPassword()))
            throw new BadCredentialsException("Invalid Password");
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
