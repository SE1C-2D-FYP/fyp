/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.controller;

import com.crm.security.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.jivesoftware.smack.BOSHConfiguration;
import org.jivesoftware.smack.BOSHConnection;
import org.jivesoftware.smack.XMPPException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author kelvin
 */
@WebServlet(name = "Prebind", urlPatterns = {"/Prebind"})
public class Prebind extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, XMPPException {
        response.setContentType("application/json");

        BOSHConfiguration conf = new BOSHConfiguration(false, "14.199.25.192", 7070, "/http-bind/", "14.199.25.192");
        BOSHConnection connection = new BOSHConnection(conf);
        connection.connect();
        System.out.println(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId().toLowerCase());
        System.out.println(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword());
        connection.login(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId().toLowerCase(), ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword(), "14.199.25.192");

        String sid = connection.getConnectionID();
        long rid = connection.getRid();
        String jids = connection.getUser();
        String jid = jids.split("/")[0];
        Map map = new HashMap();
        map.put("sid", sid);
        map.put("jid", jid);
        map.put("rid", rid);
        map.put("bosh_service_url", "http://14.199.25.192:7070/http-bind/");
        ObjectMapper mapper = new ObjectMapper();
        
        String json = mapper.writeValueAsString(map);

        PrintWriter out = response.getWriter();
        out.print(json);

        out.flush();
        //connection.disconnect();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (XMPPException ex) {
            Logger.getLogger(Prebind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (XMPPException ex) {
            Logger.getLogger(Prebind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
