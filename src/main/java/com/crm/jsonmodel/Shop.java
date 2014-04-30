/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.jsonmodel;

import java.util.ArrayList;

/**
 *
 * @author Cliff
 */
public class Shop {

    String name;
    String staffName[];
    ArrayList list;

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStaffName() {
        return staffName;
    }

    public void setStaffName(String[] staffName) {
        this.staffName = staffName;
    }
    
    
}
