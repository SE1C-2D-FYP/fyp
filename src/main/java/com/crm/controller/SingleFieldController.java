package com.crm.controller;

import com.crm.dao.EmployeeDao;
import com.crm.daoImpl.ActivityNoteDaoImpl;
import com.crm.daoImpl.EmployeeDaoImpl;
import com.crm.jsonModelMapping.ActivityManager;
import com.crm.jsonmodel.ActivityForm;
import com.crm.jsonmodel.ActivityNoteJson;
import com.crm.jsonmodel.SearchActivityNoteJson;
import com.crm.models.ActivityNote;
import com.crm.models.Employee;
import com.crm.security.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/activityNote")
public class SingleFieldController {

    //get list of activity json
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ActivityForm getActivityListByJSON() {

        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        ActivityManager am = new ActivityManager(activityNoteDaoImpl);
        return am.getActivityForm(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    ActivityNoteJson getActivityByJSON(@PathVariable Long id) {

        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        ActivityManager am = new ActivityManager(activityNoteDaoImpl);
        return am.getActivityNoteJson(id);

    }

    @RequestMapping(value = "deleteActivityId={id}", method = RequestMethod.GET)
    public @ResponseBody
    String deleteActivityByJson(@PathVariable Long id) {
        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        activityNoteDaoImpl.delActivityNote(id);
        ActivityNote activity = activityNoteDaoImpl.findByANId(id);
        String returnMsg = "";
        if (activity == null) {
            returnMsg = "Delete successful!";
        } else {
            returnMsg = "Delete unsuccessful!";
        }
        return returnMsg;

    }

    @RequestMapping(value = "createActivity", method = RequestMethod.POST)
    public @ResponseBody
    String createActivityByJson(@RequestBody ActivityNoteJson activityNoteJson) {
        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        ActivityManager am = new ActivityManager(activityNoteDaoImpl);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.findByEmpId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
        am.insertActivityNote(activityNoteJson,employee);
        return "Record Created!";
    }
    
    @RequestMapping(value = "searchActivity", method = RequestMethod.POST)
    public @ResponseBody
    ActivityForm searchActivityByJson(@RequestBody SearchActivityNoteJson searchActivityNoteJson) {
        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        ActivityManager am = new ActivityManager(activityNoteDaoImpl);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.findByEmpId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId());
        return am.getActivityFormBySearch(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId(), searchActivityNoteJson);        
    }
    
    @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateActivityByJson(@RequestBody ActivityNoteJson activityNoteJson) {
        ActivityNoteDaoImpl activityNoteDaoImpl = new ActivityNoteDaoImpl();
        ActivityManager am = new ActivityManager(activityNoteDaoImpl);
        am.updateActivityNote(activityNoteJson);
        return "Record Updated!";


    }






}
