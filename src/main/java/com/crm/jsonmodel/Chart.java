package com.crm.jsonmodel;

import com.crm.models.Employee;
import com.crm.util.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;

/**
 *
 * @author comon
 * 
 */
public class Chart {
    
    public String getPersonalSalesReportData(String empId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlString = "SELECT extract(year from trans_date) || '/' || extract(month from trans_date), SUM(sell) from transaction, employee WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id = '" + empId + "' GROUP BY extract(year from trans_date) || '/' || extract(month from trans_date)";
        List totalSalesAmountList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT extract(year from trans_date) || '/' || extract(month from trans_date), COUNT(*) from transaction, employee WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id = '" + empId + "' AND transaction.sell != 0 GROUP BY extract(year from trans_date) || '/' || extract(month from trans_date)";
        List totalSalesUnitList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT region.eng_name, COUNT(*) FROM transaction, employee, region, district, estate WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id = '" + empId + "' AND estate.est_id = transaction.est_id AND estate.dist_id = district.dist_id AND district.region_id = region.region_id GROUP BY region.eng_name";
        List salesByGeogList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT (estate.eng_name || ' ' || building.eng_name || ' ' || building.block_no || ' ' || unit.floor || ' ' || unit.flat) AS unit_detail, transaction.sell FROM estate, building, unit, employee, transaction WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id = '" + empId + "' AND unit.unit_id = transaction.unit_id AND unit.bldg_id = building.bldg_id AND building.est_id = estate.est_id AND transaction.sell != 0 AND ROWNUM <= 5 ORDER BY transaction.sell DESC";
        List topSalesUnitList = session.createSQLQuery(sqlString).list();
        ObjectMapper mapper = new ObjectMapper();
        HashMap jsonMap = new HashMap();
        String jsonString;
        try {
            String totalSalesAmountJson = mapper.writeValueAsString(totalSalesAmountList);
            String totalSalesUnitJson = mapper.writeValueAsString(totalSalesUnitList);
            String salesByGeogJson = mapper.writeValueAsString(salesByGeogList);
            String topSalesUnitJson = mapper.writeValueAsString(topSalesUnitList);
            jsonMap.put("totalSalesAmount", totalSalesAmountJson);
            jsonMap.put("totalSalesUnit", totalSalesUnitJson);
            jsonMap.put("salesByGeog", salesByGeogJson);
            jsonMap.put("topUnitSales", topSalesUnitJson);
            jsonString = mapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            jsonString = "error";
        }
        System.out.println(jsonString);
        session.close();
        return jsonString;
    }
    
    public String getTeamMember(String empId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlString = "SELECT emp_id, eng_surname, eng_other_name FROM employee WHERE supervisor_id = '" + empId + "'";
        List tempTeamMemberList = session.createSQLQuery(sqlString).list();
//        Iterator iterator = tempTeamMemberList.iterator();
//        List teamMemberList = new ArrayList();
//        while (iterator.hasNext()) {
//            Object[] row = (Object[]) iterator.next();
//            TeamMember teamMember = new TeamMember((String)row[0], (String)row[1], (String)row[2]);
//            teamMemberList.add(teamMember);
//        }
//        TeamMemberList memberList = new TeamMemberList(teamMemberList);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(tempTeamMemberList);
        } catch (Exception e) {
            jsonString = "No team member.";
        }
        System.out.println(jsonString);
        session.close();
        return jsonString;
    }
    
    public String getTeamSalesReportData(String empId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlString = "SELECT extract(year from trans_date) || '/' || extract(month from trans_date), SUM(sell) from transaction, employee WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "') GROUP BY extract(year from trans_date) || '/' || extract(month from trans_date)";
        List totalSalesAmountList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT extract(year from trans_date) || '/' || extract(month from trans_date), COUNT(*) from transaction, employee WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "')AND transaction.sell != 0 GROUP BY extract(year from trans_date) || '/' || extract(month from trans_date)";
        List totalSalesUnitList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT region.eng_name, COUNT(*) FROM transaction, employee, region, district, estate WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "') AND estate.est_id = transaction.est_id AND estate.dist_id = district.dist_id AND district.region_id = region.region_id GROUP BY region.eng_name";
        List salesByGeogList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT (estate.eng_name || ' ' || building.eng_name || ' ' || building.block_no || ' ' || unit.floor || ' ' || unit.flat) AS unit_detail, transaction.sell FROM estate, building, unit, employee, transaction WHERE transaction.emp_id = employee.emp_id AND transaction.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "') AND unit.unit_id = transaction.unit_id AND unit.bldg_id = building.bldg_id AND building.est_id = estate.est_id AND transaction.sell != 0 AND ROWNUM <= 5 ORDER BY transaction.sell DESC";
        List topSalesUnitList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT employee.emp_id, employee.eng_other_name || ' ' || employee.eng_surname, sum(sell) FROM employee, transaction WHERE employee.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "' ) AND extract(month from trans_date) BETWEEN extract(month from add_months(sysdate,-3)) AND extract(month from sysdate) AND transaction.sell != 0 GROUP BY employee.emp_id, employee.eng_other_name || ' ' || employee.eng_surname";
        List agentPerformanceSalesList = session.createSQLQuery(sqlString).list();
        sqlString = "SELECT employee.emp_id, employee.eng_other_name || ' ' || employee.eng_surname, COUNT(sell) FROM employee, transaction WHERE employee.emp_id IN (SELECT emp_id FROM employee WHERE supervisor_id = '" + empId + "' ) AND extract(month from trans_date) BETWEEN extract(month from add_months(sysdate,-3)) AND extract(month from sysdate) AND transaction.sell != 0 GROUP BY employee.emp_id, employee.eng_other_name || ' ' || employee.eng_surname";
        List agentPerformanceUnitList = session.createSQLQuery(sqlString).list();
        ObjectMapper mapper = new ObjectMapper();
        HashMap jsonMap = new HashMap();
        String jsonString;
        try {
            String totalSalesAmountJson = mapper.writeValueAsString(totalSalesAmountList);
            String totalSalesUnitJson = mapper.writeValueAsString(totalSalesUnitList);
            String salesByGeogJson = mapper.writeValueAsString(salesByGeogList);
            String topSalesUnitJson = mapper.writeValueAsString(topSalesUnitList);
            String agentPerformanceSalesJson = mapper.writeValueAsString(agentPerformanceSalesList);
            String agentPerformanceUnitJson = mapper.writeValueAsString(agentPerformanceUnitList);
            jsonMap.put("totalSalesAmount", totalSalesAmountJson);
            jsonMap.put("totalSalesUnit", totalSalesUnitJson);
            jsonMap.put("salesByGeog", salesByGeogJson);
            jsonMap.put("topUnitSales", topSalesUnitJson);
            jsonMap.put("agentPerformanceSales", agentPerformanceSalesJson);
            jsonMap.put("agentPerformanceUnit", agentPerformanceUnitJson);
            jsonString = mapper.writeValueAsString(jsonMap);
        } catch(Exception e) {
            jsonString = "No record";
        }
        session.close();
        return jsonString;
    }
    
    public String checkSupervisor(String selfEmpId, String empId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee target = (Employee)session.get(Employee.class, empId);
        if(target == null || target.getSupervisorId() == null || !target.getSupervisorId().getEmpId().equals(selfEmpId))
            return "error";
        else
            return getPersonalSalesReportData(empId);
    }
}
