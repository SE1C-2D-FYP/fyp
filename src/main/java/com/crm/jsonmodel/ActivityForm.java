/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.jsonmodel;

import java.util.List;

/**
 *
 * @author Cliff
 */
public class ActivityForm {
    
    private List todayRecords;
    private List pastRecords;
    private List futureRecords;

    public ActivityForm() {
    }

    public ActivityForm(List todayRecords, List pastRecords, List futureRecords) {
        this.todayRecords = todayRecords;
        this.pastRecords = pastRecords;
        this.futureRecords = futureRecords;
    }

    public List getTodayRecords() {
        return todayRecords;
    }

    public void setTodayRecords(List todayRecords) {
        this.todayRecords = todayRecords;
    }

    public List getPastRecords() {
        return pastRecords;
    }

    public void setPastRecords(List pastRecords) {
        this.pastRecords = pastRecords;
    }

    public List getFutureRecords() {
        return futureRecords;
    }

    public void setFutureRecords(List futureRecords) {
        this.futureRecords = futureRecords;
    }
    
    
}
