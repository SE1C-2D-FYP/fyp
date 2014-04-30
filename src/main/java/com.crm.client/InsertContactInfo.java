package com.crm;

import com.crm.models.Client;
import com.crm.models.ContactInfo;
import com.crm.models.Employee;
import com.crm.security.User;
import com.crm.util.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet(name = "InsertContactInfo", urlPatterns = {"/InsertContactInfo"})
public class InsertContactInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Session session = HibernateUtil.getSessionFactory().openSession();

        PrintWriter out = response.getWriter();

        session.beginTransaction();

        Criteria criteria = session.createCriteria(ContactInfo.class);
        criteria.addOrder(Order.desc("ctInfoId"));
        criteria.setMaxResults(1);

        List<ContactInfo> list = criteria.list();
        long maxId;
        try {
            maxId = list.get(0).getCtInfoId();
        } catch(IndexOutOfBoundsException e) {
            maxId = 0;
        }
        long clientId = Long.parseLong(request.getParameter("ctClientId"));

        ContactInfo ci = new ContactInfo();

        ci.setCtInfoId(maxId + 1);
        ci.setClientId(new Client(clientId));
        ci.setEmpId(new Employee(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId()));
        ci.setChiName(request.getParameter("chiName"));
        ci.setEngName(request.getParameter("engName"));
        ci.setPhoneNum(Long.parseLong(request.getParameter("phoneNo")));
        ci.setPhoneType(request.getParameter("phoneType"));

        BaseDAO<ContactInfo> base = new BaseDAO<ContactInfo>();
        base.create(ci);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ci);
        out.print(json);
        session.getTransaction().commit();
        session.close();
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
