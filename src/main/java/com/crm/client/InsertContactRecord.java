/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.client;

import com.crm.models.ContactInfo;
import com.crm.models.ContactNote;
import com.crm.models.Employee;
import com.crm.models.Stock;
import com.crm.security.User;
import com.crm.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author kelvin
 */
@WebServlet(name = "InsertContactRecord", urlPatterns = {"/InsertContactRecord"})
public class InsertContactRecord extends HttpServlet {

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
        String action = request.getParameter("action");
        try {
            List result = null;
            if (action.equals("getStock"))
                result = getStock(request.getParameter("term"));
            else if (action.equals("getContactPerson"))
                result = getContactPerson(request.getParameter("clientId"));
            else
                insertContactRecord(request.getParameter("contactPerson"), request.getParameter("stockNumber"), request.getParameter("date"), request.getParameter("type"), request.getParameter("price"), request.getParameter("memo"));
            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(result));

//            Criteria criteria = session.createCriteria(Transaction.class);
//            criteria.addOrder(Order.desc("txId"));
//            criteria.setMaxResults(1);
//
//            List<Transaction> list = criteria.list();
//            long maxId = list.get(0).getTxId();
//            Transaction transactionInfo = new Transaction();
//            transactionInfo.setTxId(maxId + 1);
//            transactionInfo.setClientId(new Client(Long.parseLong(clientId)));
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                transactionInfo.setTransDate(format.parse(date));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            transactionInfo.setEmpId((new Employee(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId())));
//            transactionInfo.setEstId(new Estate(estId));
//            transactionInfo.setUnitId(new Unit(unitId));
//            if (type.equals("sell")) {
//                transactionInfo.setSell(new BigDecimal(price));
//            } else {
//                transactionInfo.setRent(new BigDecimal(price));
//            }
//            transactionInfo.setCommission(new BigDecimal(commission));
//            session.save(transactionInfo);

        } finally {
            out.close();
        }
    }
    
    protected List getContactPerson(String clientId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT contactInfo.ctInfoId, contactInfo.engName FROM ContactInfo as contactInfo WHERE contactInfo.clientId = :clientId");
        query.setString("clientId", clientId);
        List result = query.list();
        session.close();
        return result;
    }
    
    protected List getStock(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT stock.stockId FROM Stock as stock WHERE UPPER(stock.stockId) LIKE UPPER(:keyword) AND stock.empId = :empId");
        query.setString("keyword", keyword + '%');
        query.setString("empId", ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
        List result = query.list();
        session.close();
        return result;
    }
    
    protected String insertContactRecord(String contactPerson, String stockNumber, String date, String type, String price, String memo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(ContactNote.class);
        criteria.addOrder(Order.desc("ctNoteId"));
        criteria.setMaxResults(1);

        List<ContactNote> list = criteria.list();
        long maxId;
        if (list.isEmpty())
            maxId = 0;
        else
            maxId = list.get(0).getCtNoteId();
        ContactNote contactNote = new ContactNote();
        contactNote.setCtNoteId(maxId + 1);
        contactNote.setEmpId((new Employee(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId())));
        contactNote.setCtInfoId(new ContactInfo(Long.parseLong(contactPerson)));
        System.out.println(stockNumber);
        contactNote.setStockId(new Stock(stockNumber));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            contactNote.setCtDate(format.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        contactNote.setCtType(type);
        contactNote.setInchPrice(new BigDecimal(price));
        contactNote.setMemo(memo);
        session.save(contactNote);
        trans.commit();
        session.close();
        return "success";
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
