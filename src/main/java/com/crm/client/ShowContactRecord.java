/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.client;

import com.crm.security.User;
import com.crm.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author comon
 */
@WebServlet(name = "ShowContactRecord", urlPatterns = {"/ShowContactRecord"})
public class ShowContactRecord extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String empId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId();
            String sqlString = "SELECT contact_note.ct_date, contact_note.ct_time, contact_info.eng_name, contact_info.phone_num, contact_note.stock_id, contact_note.inch_price FROM contact_note, contact_info, client WHERE contact_note.emp_id = '" + empId + "' AND contact_note.ct_info_id = contact_info.ct_info_id AND contact_info.client_id = client.client_id AND client.client_id = " + request.getParameter("clientId") + " ORDER BY contact_note.ct_date DESC";
            List result = session.createSQLQuery(sqlString).list();
            DataTableObject dataTableObject = new DataTableObject();
            dataTableObject.setAaData(result);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dataTableObject);
            out.print(json);
            System.out.println(json);
            session.close();
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
