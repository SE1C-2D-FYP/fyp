package com.crm.jsonmodel;

/**
 *
 * @author comon
 */
public class TeamMember {
    private String empId;
    private String surname;
    private String otherName;

    public TeamMember() {
    }

    public TeamMember(String empId, String surname, String otherName) {
        this.empId = empId;
        this.surname = surname;
        this.otherName = otherName;
    }

    public String getEmpId() {
        return empId;
    }

    public String getSurname() {
        return surname;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
    
    
}
