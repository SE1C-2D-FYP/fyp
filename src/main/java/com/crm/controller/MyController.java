/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.models.Employee;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Cliff
 */

@Controller
public class MyController {

    @RequestMapping(value = "/showactivity", method = RequestMethod.GET)
    public String activity(Model model) {

        return "pages/activity";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        return "mainpages/index";
    }


}