/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "ACTIVITY_NOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityNote.findAll", query = "SELECT a FROM ActivityNote a"),
    @NamedQuery(name = "ActivityNote.findByANId", query = "SELECT a FROM ActivityNote a WHERE a.aNId = :aNId"),
    @NamedQuery(name = "ActivityNote.findByANTitle", query = "SELECT a FROM ActivityNote a WHERE a.aNTitle = :aNTitle"),
    @NamedQuery(name = "ActivityNote.findByANContent", query = "SELECT a FROM ActivityNote a WHERE a.aNContent = :aNContent"),
    @NamedQuery(name = "ActivityNote.findByANType", query = "SELECT a FROM ActivityNote a WHERE a.aNType = :aNType"),
    @NamedQuery(name = "ActivityNote.findByStartDate", query = "SELECT a FROM ActivityNote a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "ActivityNote.findByEndDate", query = "SELECT a FROM ActivityNote a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "ActivityNote.findByCreateDate", query = "SELECT a FROM ActivityNote a WHERE a.createDate = :createDate"),
    @NamedQuery(name = "ActivityNote.findByUpdatedDate", query = "SELECT a FROM ActivityNote a WHERE a.updatedDate = :updatedDate")})
public class ActivityNote implements Serializable {
//    @SequenceGenerator(name="EL_SEQ", sequenceName="EL_SEQ",allocationSize=1)

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G1")
    @SequenceGenerator(name = "G1", sequenceName = "seq_activity_note",allocationSize=0)
    @Basic(optional = false)
    @NotNull
    @Column(name = "A_N_ID")
    private Long aNId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "A_N_TITLE")
    private String aNTitle;
    @Size(max = 255)
    @Column(name = "A_N_CONTENT")
    private String aNContent;
    @Size(max = 50)
    @Column(name = "A_N_TYPE")
    private String aNType;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Employee empId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client clientId;

    public ActivityNote() {
    }

    public ActivityNote(Long aNId) {
        this.aNId = aNId;
    }

    public ActivityNote(Long aNId, String aNTitle) {
        this.aNId = aNId;
        this.aNTitle = aNTitle;
    }

    public Long getANId() {
        return aNId;
    }

    public void setANId(Long aNId) {
        this.aNId = aNId;
    }

    public String getANTitle() {
        return aNTitle;
    }

    public void setANTitle(String aNTitle) {
        this.aNTitle = aNTitle;
    }

    public String getANContent() {
        return aNContent;
    }

    public void setANContent(String aNContent) {
        this.aNContent = aNContent;
    }

    public String getANType() {
        return aNType;
    }

    public void setANType(String aNType) {
        this.aNType = aNType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aNId != null ? aNId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityNote)) {
            return false;
        }
        ActivityNote other = (ActivityNote) object;
        if ((this.aNId == null && other.aNId != null) || (this.aNId != null && !this.aNId.equals(other.aNId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.ActivityNote[ aNId=" + aNId + " ]";
    }
}
