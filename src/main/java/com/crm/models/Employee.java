/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpId", query = "SELECT e FROM Employee e WHERE e.empId = :empId"),
    @NamedQuery(name = "Employee.findByEngSurname", query = "SELECT e FROM Employee e WHERE e.engSurname = :engSurname"),
    @NamedQuery(name = "Employee.findByEngOtherName", query = "SELECT e FROM Employee e WHERE e.engOtherName = :engOtherName"),
    @NamedQuery(name = "Employee.findByChiSurname", query = "SELECT e FROM Employee e WHERE e.chiSurname = :chiSurname"),
    @NamedQuery(name = "Employee.findByChiOtherName", query = "SELECT e FROM Employee e WHERE e.chiOtherName = :chiOtherName"),
    @NamedQuery(name = "Employee.findByNickname", query = "SELECT e FROM Employee e WHERE e.nickname = :nickname")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EMP_ID")
    private String empId;
    @Size(max = 50)
    @Column(name = "ENG_SURNAME")
    private String engSurname;
    @Size(max = 50)
    @Column(name = "ENG_OTHER_NAME")
    private String engOtherName;
    @Size(max = 50)
    @Column(name = "CHI_SURNAME")
    private String chiSurname;
    @Size(max = 50)
    @Column(name = "CHI_OTHER_NAME")
    private String chiOtherName;
    @Size(max = 50)
    @Column(name = "NICKNAME")
    private String nickname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<Client> clientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<ActivityNote> activityNoteCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<StockViewing> stockViewingCollection;
    @JoinColumn(name = "TITLE_ID", referencedColumnName = "TITLE_ID")
    @ManyToOne(optional = false)
    private Title titleId;
    @OneToMany(mappedBy = "supervisorId")
    private Collection<Employee> employeeCollection;
    @JoinColumn(name = "SUPERVISOR_ID", referencedColumnName = "EMP_ID")
    @ManyToOne
    private Employee supervisorId;
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "DEPT_ID")
    @ManyToOne(optional = false)
    private Department deptId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<ContactInfo> contactInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
    private Collection<ContactNote> contactNoteCollection;

    public Employee() {
    }

    public Employee(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEngSurname() {
        return engSurname;
    }

    public void setEngSurname(String engSurname) {
        this.engSurname = engSurname;
    }

    public String getEngOtherName() {
        return engOtherName;
    }

    public void setEngOtherName(String engOtherName) {
        this.engOtherName = engOtherName;
    }

    public String getChiSurname() {
        return chiSurname;
    }

    public void setChiSurname(String chiSurname) {
        this.chiSurname = chiSurname;
    }

    public String getChiOtherName() {
        return chiOtherName;
    }

    public void setChiOtherName(String chiOtherName) {
        this.chiOtherName = chiOtherName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ActivityNote> getActivityNoteCollection() {
        return activityNoteCollection;
    }

    public void setActivityNoteCollection(Collection<ActivityNote> activityNoteCollection) {
        this.activityNoteCollection = activityNoteCollection;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlTransient
    public Collection<StockViewing> getStockViewingCollection() {
        return stockViewingCollection;
    }

    public void setStockViewingCollection(Collection<StockViewing> stockViewingCollection) {
        this.stockViewingCollection = stockViewingCollection;
    }

    public Title getTitleId() {
        return titleId;
    }

    public void setTitleId(Title titleId) {
        this.titleId = titleId;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public Employee getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Employee supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Department getDeptId() {
        return deptId;
    }

    public void setDeptId(Department deptId) {
        this.deptId = deptId;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ContactInfo> getContactInfoCollection() {
        return contactInfoCollection;
    }

    public void setContactInfoCollection(Collection<ContactInfo> contactInfoCollection) {
        this.contactInfoCollection = contactInfoCollection;
    }

    @XmlTransient
    public Collection<ContactNote> getContactNoteCollection() {
        return contactNoteCollection;
    }

    public void setContactNoteCollection(Collection<ContactNote> contactNoteCollection) {
        this.contactNoteCollection = contactNoteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Employee[ empId=" + empId + " ]";
    }
    
}
