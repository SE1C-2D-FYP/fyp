
package com.crm.client;

import com.crm.models.Client;
import com.crm.models.Employee;
import com.crm.models.Estate;
import com.crm.models.Transaction;
import com.crm.models.Unit;
import com.crm.security.User;
import com.crm.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
 * @author comon
 */
@WebServlet(name = "InsertTransactionInfo", urlPatterns = {"/InsertTransactionInfo"})
public class InsertTransactionInfo extends HttpServlet {

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
            List result = null;
            String action = request.getParameter("action");
            if (action != null && action.equals("getAddressEstate")) {
                result = getAddressEstate(request.getParameter("term"));
            } else if (action != null && action.equals("getAddressBlock")) {
                result = getAddressBlock(request.getParameter("term"), request.getParameter("estateId"));
            } else if (action != null && action.equals("getAddressFloor")) {
                result = getAddressFloor(request.getParameter("term"), request.getParameter("estateId"), request.getParameter("bldgId"));
            } else if (action != null && action.equals("getAddressFlat")) {
                result = getAddressFlat(request.getParameter("term"), request.getParameter("estateId"), request.getParameter("bldgId"), request.getParameter("floor"));
            } else {
                insertTransactionInfo(request.getParameter("clientId"), request.getParameter("transactionDate"), request.getParameter("autocompleteTransactionAddressEstate_hidden"), request.getParameter("autocompleteTransactionAddressFlat_hidden"), request.getParameter("transactionType"), request.getParameter("transactionPrice"), request.getParameter("transactionCommission"));
            }
            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(result));
        } finally {
            out.close();
        }
    }
    
    protected List getAddressEstate(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT estate.estId, estate.engName FROM Estate as estate WHERE UPPER(estate.engName) LIKE UPPER(:keyword) ORDER BY estate.engName");
        query.setString("keyword", '%'+keyword+'%');
        List result = query.list();
        session.close();
        return result;
    }
    
    protected List getAddressBlock(String keyword, String estateId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT building.bldgId, building.engName FROM Building as building, Estate as estate WHERE estate.estId = :estateId AND estate.estId = building.estId AND UPPER(building.engName) LIKE UPPER(:keyword) ORDER BY building.engName");
        query.setString("keyword", '%' + keyword + '%');
        query.setString("estateId", estateId);
        List result = query.list();
        session.close();
        return result;
    }
    
    protected List getAddressFloor(String keyword, String estateId, String bldgId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT DISTINCT unit.floor FROM Unit as unit, Estate as estate, Building as building WHERE estate.estId = :estateId AND building.bldgId = :bldgId AND unit.bldgId = building.bldgId AND estate.estId = building.estId AND CAST(unit.floor AS VARCHAR(9)) LIKE :keyword ORDER BY unit.floor");
        query.setString("keyword", keyword + '%');
        query.setString("estateId", estateId);
        query.setString("bldgId", bldgId);
        List result = query.list();
        session.close();
        return result;
    }
    
    protected List getAddressFlat(String keyword, String estateId, String bldgId, String floor) {
        System.out.println(keyword + estateId + bldgId + floor);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT unit.flat, unit.unitId FROM Unit as unit, Estate as estate, Building as building WHERE estate.estId = :estateId AND building.bldgId = :bldgId AND unit.bldgId = building.bldgId AND estate.estId = building.estId AND CAST(unit.floor AS VARCHAR(9)) = :floor AND UPPER(unit.flat) LIKE UPPER(:keyword) ORDER BY unit.flat");
        query.setString("keyword", '%' + keyword + '%');
        query.setString("estateId", estateId);
        query.setString("bldgId", bldgId);
        query.setString("floor", floor);
        List result = query.list();
        session.close();
        return result;
    }
    
    protected String insertTransactionInfo(String clientId, String date, String estId, String unitId, String type, String price, String commission) {
        System.out.println(clientId + date+ estId+ unitId+type+price+ commission);
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction trans = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Transaction.class);
        criteria.addOrder(Order.desc("txId"));
        criteria.setMaxResults(1);

        List<Transaction> list = criteria.list();
        long maxId = list.get(0).getTxId();
        Transaction transactionInfo = new Transaction();
        transactionInfo.setTxId(maxId + 1);
        transactionInfo.setClientId(new Client(Long.parseLong(clientId)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            transactionInfo.setTransDate(format.parse(date));
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
        transactionInfo.setEmpId((new Employee(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId())));
        transactionInfo.setEstId(new Estate(estId));
        transactionInfo.setUnitId(new Unit(unitId));
        if (type.equals("sell")) {
            transactionInfo.setSell(new BigDecimal(price));
        } else {
            transactionInfo.setRent(new BigDecimal(price));
        }
        transactionInfo.setCommission(new BigDecimal(commission));
        session.save(transactionInfo);
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
