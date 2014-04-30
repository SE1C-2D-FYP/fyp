/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.jsonModelMapping;

import com.crm.daoImpl.ActivityNoteDaoImpl;
import com.crm.jsonmodel.ActivityForm;
import com.crm.jsonmodel.ActivityNoteJson;
import com.crm.jsonmodel.SearchActivityNoteJson;
import com.crm.models.ActivityNote;
import com.crm.models.Employee;
import com.crm.models.Title;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cliff
 */
public class ActivityManager {

    private ActivityNoteDaoImpl activityNoteDaoImpl;
    private ActivityNoteJson activityNoteJson;

    public ActivityManager(ActivityNoteDaoImpl activityNoteDaoImpl) {
        this.activityNoteDaoImpl = activityNoteDaoImpl;

    }

    public void insertActivityNote(ActivityNoteJson activityNoteJson, Employee employee) {
        ActivityNoteJson activityJson = activityNoteJson;
        ActivityNote activity = activityNoteDaoImpl.findByANId(activityJson.getId());
        if (activity == null) {
            activity = new ActivityNote();
            activity.setANTitle(activityJson.getTitle());
            String a = activityJson.getContent();
            activity.setANContent(activityJson.getContent());
            activity.setANType(activityJson.getType());
            Date dd = activityJson.getStart();
            activity.setStartDate(dd);
            activity.setEndDate(activityJson.getEnd());
            Date date = new Date();
            activity.setCreateDate(date);
            activity.setUpdatedDate(date);
            activity.setEmpId(employee);
        }
        Long activityId = activityNoteDaoImpl.addActivityNote(activity);
    }

    public void updateActivityNote(ActivityNoteJson activityNoteJson) {
        ActivityNoteJson activityJson = activityNoteJson;
        ActivityNote activity = activityNoteDaoImpl.findByANId(activityJson.getId());
        if (activity != null) {
            activity.setANTitle(activityJson.getTitle());
            activity.setANContent(activityJson.getContent());
            activity.setANType(activityJson.getType());
            activity.setStartDate(activityJson.getStart());
            activity.setEndDate(activityJson.getEnd());
            activity.setUpdatedDate(new Date());
        }
        activityNoteDaoImpl.updateActivityNote(activity);
    }

    public ActivityForm getActivityForm(String EMP_ID) {

        ActivityForm activityForm = new ActivityForm();
        activityForm.setTodayRecords(getTodayRecords(EMP_ID));
        activityForm.setPastRecords(getPastRecords(EMP_ID));
        activityForm.setFutureRecords(getFutureRecords(EMP_ID));

        return activityForm;

    }
        public ActivityForm getActivityFormBySearch(String EMP_ID,SearchActivityNoteJson a) {

        ActivityForm activityForm = new ActivityForm();
        activityForm.setTodayRecords(getTodayRecordsBySearch(EMP_ID,a.getTitle(),a.getType(),a.getStart(),a.getEnd()));
        activityForm.setPastRecords(getPastRecordsBySearch(EMP_ID,a.getTitle(),a.getType(),a.getStart(),a.getEnd()));
        activityForm.setFutureRecords(getFutureRecordsBySearch(EMP_ID,a.getTitle(),a.getType(),a.getStart(),a.getEnd()));

        return activityForm;

    }

    private List getTodayRecords(String EMP_ID) {

        List<ActivityNote> temp = activityNoteDaoImpl.findTodayRecord(EMP_ID);
        List todayRecords = new ArrayList();
//        for(int i=0; i<=temp.size();i++){
//            ActivityNote activityNote = new ActivityNote();
//                    activityNote.setId(Long.MIN_VALUE);
//                    (com.crm.models.ActivityNote)temp.get(i).;
//        }
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            todayRecords.add(activityNoteJson);
        }

        return todayRecords;

    }
    public List getTodayRecordsBySearch(String EMP_ID, String title, String status,Date start, Date end) {
        List<ActivityNote> temp = activityNoteDaoImpl.findTodayRecordBySearch(EMP_ID, title, status,start,end);
        List todayRecords = new ArrayList();
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            todayRecords.add(activityNoteJson);
        }

        return todayRecords;
    }
    private List getPastRecords(String EMP_ID) {
        List<ActivityNote> temp = activityNoteDaoImpl.findPastRecord(EMP_ID);
        List pastRecords = new ArrayList();
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            pastRecords.add(activityNoteJson);
        }

        return pastRecords;
    }

    public List getPastRecordsBySearch(String EMP_ID, String title, String status,Date start, Date end) {
        List<ActivityNote> temp = activityNoteDaoImpl.findPastRecordBySearch(EMP_ID, title, status,start,end);
        List pastRecords = new ArrayList();
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            pastRecords.add(activityNoteJson);
        }

        return pastRecords;
    }

    private List getFutureRecords(String EMP_ID) {
        List<ActivityNote> temp = activityNoteDaoImpl.findFutureRecord(EMP_ID);
        List futureRecords = new ArrayList();
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            futureRecords.add(activityNoteJson);
        }

        return futureRecords;

    }
    public List getFutureRecordsBySearch(String EMP_ID, String title, String status,Date start, Date end) {
        List<ActivityNote> temp = activityNoteDaoImpl.findFutureRecordBySearch(EMP_ID, title, status,start,end);
        List futureRecords = new ArrayList();
        for (ActivityNote a : temp) {
            ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
            futureRecords.add(activityNoteJson);
        }

        return futureRecords;
    }
    public ActivityNoteJson getActivityNoteJson(Long aNId) {
        ActivityNote a = activityNoteDaoImpl.findByANId(aNId);
        ActivityNoteJson activityNoteJson = new ActivityNoteJson(a.getANId(), a.getANTitle(), a.getStartDate(), a.getEndDate(), a.getANContent(), a.getANType());
        return activityNoteJson;

    }
}
