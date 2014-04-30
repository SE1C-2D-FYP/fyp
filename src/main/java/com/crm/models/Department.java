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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "DEPARTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByDeptId", query = "SELECT d FROM Department d WHERE d.deptId = :deptId"),
    @NamedQuery(name = "Department.findByDeptCode", query = "SELECT d FROM Department d WHERE d.deptCode = :deptCode"),
    @NamedQuery(name = "Department.findByEngName", query = "SELECT d FROM Department d WHERE d.engName = :engName"),
    @NamedQuery(name = "Department.findByChiName", query = "SELECT d FROM Department d WHERE d.chiName = :chiName"),
    @NamedQuery(name = "Department.findByPhoneNo", query = "SELECT d FROM Department d WHERE d.phoneNo = :phoneNo"),
    @NamedQuery(name = "Department.findByEmailAddr", query = "SELECT d FROM Department d WHERE d.emailAddr = :emailAddr")})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Size(max = 50)
    @Column(name = "DEPT_CODE")
    private String deptCode;
    @Size(max = 50)
    @Column(name = "ENG_NAME")
    private String engName;
    @Size(max = 50)
    @Column(name = "CHI_NAME")
    private String chiName;
    @Column(name = "PHONE_NO")
    private Integer phoneNo;
    @Size(max = 50)
    @Column(name = "EMAIL_ADDR")
    private String emailAddr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deptId")
    private Collection<Employee> employeeCollection;

    public Department() {
    }

    public Department(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getChiName() {
        return chiName;
    }

    public void setChiName(String chiName) {
        this.chiName = chiName;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptId != null ? deptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Department[ deptId=" + deptId + " ]";
    }
    
}
