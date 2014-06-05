
package com.crm.controller;

import com.crm.client.DataTableObject;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kelvin
 */
@Controller
@RequestMapping(value = "/PropertyController")
public class PropertyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @RequestMapping(value = "PropertyList", method = RequestMethod.GET)
    @ResponseBody
    public String getPropertyList()throws IOException {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT stock.stockId, region.engName, district.engName, estate.engName, building.engName, unit.floor, unit.flat, stock.txType, stock.price FROM Stock AS stock, Region AS region, District AS district, Estate AS estate, Building AS building, Unit AS unit WHERE stock.estId = estate.estId AND estate.distId = district.distId AND district.regionId = region.regionId AND stock.unitId = unit.unitId AND unit.bldgId = building.bldgId AND stock.empId = :empId");
            query.setString("empId", ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
            List queryResult = query.list();
            DataTableObject dtObj = new DataTableObject();
            dtObj.setAaData(queryResult);
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(dtObj);
            session.close();
            return result;
    }
    
    @RequestMapping(value = "Property/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getPropertyDetail(@PathVariable String id) throws IOException {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT region.engName, district.engName, estate.engName, building.engName, unit.floor, unit.flat, unit.sittingRoom, unit.room, unit.bathroom, unit.balcony, unit.grossArea, unit.netArea, unitView.engName, orientation.engTitle FROM Stock AS stock, Region AS region, District AS district, Estate AS estate, Building AS building, Unit AS unit, Orientation AS orientation, UnitView AS unitView WHERE stock.estId = estate.estId AND estate.distId = district.distId AND district.regionId = region.regionId AND stock.unitId = unit.unitId AND unit.bldgId = building.bldgId AND stock.orientationId = orientation.orientationId AND stock.unitViewId = unitView.unitViewId AND stock.stockId = :id");
        query.setString("id", id);
       
        List queryResult = query.list();
        
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(queryResult);
        session.close();
        return result;
        
    }
    
    @RequestMapping(value = "PropertyForPublic/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getPropertyPublicDetail(@PathVariable String id) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT region.engName, district.engName, estate.engName, building.engName, unit.floor, unit.flat, unit.sittingRoom, unit.room, unit.bathroom, unit.balcony, unit.grossArea, unit.netArea, unitView.engName, orientation.engTitle FROM Stock AS stock, Region AS region, District AS district, Estate AS estate, Building AS building, Unit AS unit, Orientation AS orientation, UnitView AS unitView WHERE stock.estId = estate.estId AND estate.distId = district.distId AND district.regionId = region.regionId AND stock.unitId = unit.unitId AND unit.bldgId = building.bldgId AND stock.orientationId = orientation.orientationId AND stock.unitViewId = unitView.unitViewId AND stock.stockId = :id");
        query.setString("id", id);
        
        List queryResult = query.list();
        for (int i=0; i<((Object[])queryResult.get(0)).length; i++) {
            if (((Object[])queryResult.get(0))[i] == null)
                ((Object[])queryResult.get(0))[i] = "-----";
        }
        String webHtml = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                + "<title>Property Detail</title>"
                + "<script src=\"../../static/js/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js\"></script>"
                + "<script src=\"../../static/js/bootstrap.min.js\" type=\"text/javascript\"></script>"
                + "<script src=\"../../static/js/panorama/jquery.transit.js\"></script>"
                + "<script src=\"../../static/js/panorama/jquery.leanorama.js\"></script>"
                + "<script src=\"../../static/js/panorama/jquery.leanorama.hotspot.js\"></script>"
                + "<script src=\"../../static/js/panorama/jquery.leanorama.controlbar.js\"></script>"
                + "<script src=\"../../static/js/panorama/jquery.leanorama.infobox.js\"></script>"
                + "<script src=\"../../static/public/panorama/example/tour.js\"></script>"
                + "<link rel=\"stylesheet\" href=\"../../static/css/panorama/index.css\">"
                + "<link rel=\"stylesheet\" href=\"../../static/css/panorama/leanorama.css\">"
                + "<link rel=\"stylesheet\" href=\"../../static/css/panorama/leanorama.hotspot.css\">"
                + "<link rel=\"stylesheet\" href=\"../../static/css/panorama/leanorama.controlbar.css\">"
                + "<link rel=\"stylesheet\" href=\"../../static/css/panorama/leanorama.infobox.css\">"
                + "</head>"
                + "<body>"
                + "<div class=\"pull-left\">"
                + "<table id=\"property_detail_table\" style=\"width: 400px;\">"
                + "<tr>"
                + "<td>Address: </td>"
                + "<td id=\"property_detail_table_address\">"
                + ((Object[])queryResult.get(0))[0] + ", " + ((Object[])queryResult.get(0))[1] + ", " + ((Object[])queryResult.get(0))[2] + ", " + ((Object[])queryResult.get(0))[3] + ", " + ((Object[])queryResult.get(0))[4] + "/F, FLAT " + ((Object[])queryResult.get(0))[5]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Sitting Room: </td>"
                + "<td id=\"property_detail_table_sittingRoom\">"
                + ((Object[])queryResult.get(0))[6]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Room: </td>"
                + "<td id=\"property_detail_table_room\">"
                + ((Object[])queryResult.get(0))[7]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Bathroom: </td>"
                + "<td id=\"property_detail_table_bathroom\">"
                + ((Object[])queryResult.get(0))[8]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Balcony: </td>"
                + "<td id=\"property_detail_table_balcony\">"
                + ((Object[])queryResult.get(0))[9]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Gross Area: </td>"
                + "<td id=\"property_detail_table_grossArea\">"
                + ((Object[])queryResult.get(0))[10]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Net Area: </td>"
                + "<td id=\"property_detail_table_netArea\">"
                + ((Object[])queryResult.get(0))[11]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>View: </td>"
                + "<td id=\"property_detail_table_view\">"
                + ((Object[])queryResult.get(0))[12]
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Orientation: </td>"
                + "<td id=\"property_detail_table_orientation\">"
                + ((Object[])queryResult.get(0))[13]
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</div>"
                + "<div id=\"pano-outer\" class=\"col-md-4\" >"
                + "<div id=\"pano\" class=\"col-md-8\"></div>"
                + "</div>"
                + "</body>"
                + "</html>";
        session.close();
        return webHtml;
    }

}
