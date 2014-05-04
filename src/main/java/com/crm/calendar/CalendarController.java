
package com.crm.calendar;

import com.crm.daoImpl.ActivityNoteDaoImpl;
import com.crm.jsonModelMapping.ActivityManager;
import com.crm.jsonmodel.ActivityForm;
import com.crm.jsonmodel.ActivityNoteJson;
import com.crm.models.ActivityNote;
import com.crm.security.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author comon
 */
@WebServlet(name = "CalendarController", urlPatterns = {"/CalendarController"})
public class CalendarController extends HttpServlet {

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
        response.setContentType("application/json");
        String action = request.getParameter("action");
        String json = "";
        if (action.equals("getEvent")) {
            json = getEvent();
            System.out.println(json);
        }
    //    else if (action.equals("insertEvent"))
   //         insertEvent();
        else if (action.equals("updateEvent"))
            updateEvent(Long.parseLong(request.getParameter("eventId")), Integer.parseInt(request.getParameter("dayDelta")), Integer.parseInt(request.getParameter("minuteDelta")), request.getParameter("type"));
 //       else
//             deleteEvent();
        response.getWriter().write(json);
        response.getWriter().close();
    }
    
    protected String getEvent() throws IOException {
        ActivityManager actMgr = new ActivityManager(new ActivityNoteDaoImpl());
        ActivityForm actForm = actMgr.getActivityForm(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
        List<ActivityNoteJson> actList = actForm.getPastRecords();
        actList.addAll(actForm.getTodayRecords());
        actList.addAll(actForm.getFutureRecords());
        List<EventObject> eventList = new ArrayList<EventObject>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        for (ActivityNoteJson actNote : actList) {
            EventObject eventObject = null;
            eventObject = new EventObject(actNote.getId().toString(), actNote.getTitle(), formatter.format(actNote.getStart()), formatter.format(actNote.getEnd()), actNote.getContent(), true);
            eventList.add(eventObject);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(eventList);
    }
    
    protected void updateEvent(Long eventId, int dayDelta, int minuteDelta, String type) {
        System.out.println(eventId + "," + dayDelta + "," + minuteDelta);
        ActivityManager actMgr = new ActivityManager(new ActivityNoteDaoImpl());
        ActivityNoteJson actNote = actMgr.getActivityNoteJson(eventId);
        if (type.equals("stop")) {
            actNote.setStart(new Date(actNote.getStart().getTime() + (dayDelta * 86400 + minuteDelta * 60) * 1000));
            System.out.println(actNote.getStart());
            actNote.setEnd(new Date(actNote.getEnd().getTime() + (dayDelta * 86400 + minuteDelta * 60) * 1000));
        } else {
            actNote.setEnd(new Date(actNote.getEnd().getTime() + (dayDelta * 86400 + minuteDelta * 60) * 1000));
        }
        actMgr.updateActivityNote(actNote);
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
