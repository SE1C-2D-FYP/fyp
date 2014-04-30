/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;


import com.crm.daoImpl.ActivityNoteDaoImpl;
import com.crm.daoImpl.TitleDaoImpl;
import com.crm.models.Title;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cliff
 */
@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
//        Stock stock = new Stock("a","5");
//        StockDaoImpl stockDAO = new StockDaoImpl();
//        stockDAO.save(stock);
//        Stock ret = stockDAO.findByStockCode("a");
//        TitleDao titleDao = new TitleDao();
//        Title title = new Title();
//        title =titleDao.findAll();
//        map.addAttribute("hello", "Hello Spring from Netbeans!!");
//        map.addAttribute("language", title);
        
//        ActivityNoteDao activityNoteDao = new ActivityNoteDao();
//        List list1 =  activityNoteDao.findAll();
//        map.addAttribute("language", list1);

        return "redirect:/index";
    }
}
