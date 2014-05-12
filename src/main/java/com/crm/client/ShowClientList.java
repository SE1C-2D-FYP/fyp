package com.crm.client;

import com.crm.models.Client;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet(name = "ShowClientList", urlPatterns = {"/ShowClientList"})
public class ShowClientList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Session session = HibernateUtil.getSessionFactory().openSession();

        PrintWriter out = response.getWriter();

        session.beginTransaction();

        Criteria criteria = session.createCriteria(Client.class);
        Restrictions.eq("empId", ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
        List client = criteria.list();

        DataTableObject dataTableObject = new DataTableObject();
        dataTableObject.setAaData(client);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataTableObject);
        out.print(json);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
