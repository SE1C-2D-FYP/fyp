package com.crm;


import com.crm.models.Client;
import com.crm.models.Employee;
import com.crm.security.User;
import com.crm.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
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

@WebServlet(name = "InsertClient", urlPatterns = {"/InsertClient"})
public class InsertClient extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Session session = HibernateUtil.getSessionFactory().openSession();

        PrintWriter out = response.getWriter();

        session.beginTransaction();

        Criteria criteria = session.createCriteria(Client.class);
        criteria.addOrder(Order.desc("clientId"));
        criteria.setMaxResults(1);

        List<Client> list = criteria.list();
        long maxId = list.get(0).getClientId();

        Client c = new Client();

        c.setClientId(maxId + 1);
        c.setEmpId(new Employee(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId()));
        c.setClientName(request.getParameter("clientName"));
        c.setAddress(request.getParameter("address"));
        c.setPhoneNo(Long.parseLong(request.getParameter("phone")));
        c.setMphoneNo(Long.parseLong(request.getParameter("mphone")));
        c.setFaxNo(new BigInteger(request.getParameter("fax")));
        c.setEmail(request.getParameter("email"));

        BaseDAO<Client> base = new BaseDAO<Client>();
        base.create(c);

        out.print("New Client [" + c.getClientName() + "] has been created.");

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
