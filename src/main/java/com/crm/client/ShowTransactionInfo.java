
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
@WebServlet(name = "ShowTransactionInfo", urlPatterns = {"/ShowTransactionInfo"})
public class ShowTransactionInfo extends HttpServlet {

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
            String empId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId();
            String sqlString = "SELECT TO_CHAR(transaction.trans_date, 'DD/MM/YYYY'), (estate.eng_name || ', ' || building.eng_name || ', ' || building.block_no || '' || unit.floor || '/F, Flat ' || unit.flat) AS unit_detail, unit.net_area, case when transaction.sell > 0 then 'sell' when transaction.rent > 0 then 'rent' end as transaction_type, case when transaction.sell > 0 then transaction.sell when transaction.rent > 0 then transaction.rent end as price, transaction.commission, transaction.agreement, transaction.tx_id FROM estate, building, unit, employee, transaction WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id = '" + empId + "' AND transaction.client_id = '" + request.getParameter("clientId") + "' AND unit.unit_id = transaction.unit_id AND unit.bldg_id = building.bldg_id AND building.est_id = estate.est_id ORDER BY transaction.trans_date DESC";
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
