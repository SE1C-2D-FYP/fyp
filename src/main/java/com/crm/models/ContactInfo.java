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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "CONTACT_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactInfo.findAll", query = "SELECT c FROM ContactInfo c"),
    @NamedQuery(name = "ContactInfo.findByCtInfoId", query = "SELECT c FROM ContactInfo c WHERE c.ctInfoId = :ctInfoId"),
    @NamedQuery(name = "ContactInfo.findByChiName", query = "SELECT c FROM ContactInfo c WHERE c.chiName = :chiName"),
    @NamedQuery(name = "ContactInfo.findByEngName", query = "SELECT c FROM ContactInfo c WHERE c.engName = :engName"),
    @NamedQuery(name = "ContactInfo.findByPhoneNum", query = "SELECT c FROM ContactInfo c WHERE c.phoneNum = :phoneNum"),
    @NamedQuery(name = "ContactInfo.findByPhoneType", query = "SELECT c FROM ContactInfo c WHERE c.phoneType = :phoneType")})
public class ContactInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CT_INFO_ID")
    private Long ctInfoId;
    @Size(max = 50)
    @Column(name = "CHI_NAME")
    private String chiName;
    @Size(max = 50)
    @Column(name = "ENG_NAME")
    private String engName;
    @Column(name = "PHONE_NUM")
    private Long phoneNum;
    @Column(name = "PHONE_TYPE")
    private String phoneType;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(optional = false)
    private Client clientId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ctInfoId")
    private Collection<ContactNote> contactNoteCollection;

    public ContactInfo() {
    }

    public ContactInfo(Long ctInfoId) {
        this.ctInfoId = ctInfoId;
    }

    public Long getCtInfoId() {
        return ctInfoId;
    }

    public void setCtInfoId(Long ctInfoId) {
        this.ctInfoId = ctInfoId;
    }

    public String getChiName() {
        return chiName;
    }

    public void setChiName(String chiName) {
        this.chiName = chiName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        hash += (ctInfoId != null ? ctInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactInfo)) {
            return false;
        }
        ContactInfo other = (ContactInfo) object;
        if ((this.ctInfoId == null && other.ctInfoId != null) || (this.ctInfoId != null && !this.ctInfoId.equals(other.ctInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.ContactInfo[ ctInfoId=" + ctInfoId + " ]";
    }
    
}
