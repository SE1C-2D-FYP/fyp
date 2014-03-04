/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package salesReport.bean;
import org.json.*;

/**
 *
 * @author comon
 * 
 */
public class Chart {
    private static String exampleData = "{" +
                    "element: 'bar-chart'," +
                    "resize: true," +
                    "data: [" +
                        "{y: '2006', a: 100, b: 90}," +
                        "{y: '2007', a: 75, b: 65}," +
                        "{y: '2008', a: 50, b: 40}," +
                        "{y: '2009', a: 75, b: 65}," +
                        "{y: '2010', a: 50, b: 40}," +
                        "{y: '2011', a: 75, b: 65}," +
                        "{y: '2012', a: 100, b: 90}" +
                    "]," +
                    "barColors: ['#00a65a', '#f56954']," +
                    "xkey: 'y'," +
                    "ykeys: ['a', 'b']," +
                    "labels: ['CPU', 'DISK']," +
                    "hideHover: 'auto'}";
                
    public JSONObject getData(String chartType) throws JSONException {
        
        JSONObject json = new JSONObject(exampleData);
        return json;
    }            
}
