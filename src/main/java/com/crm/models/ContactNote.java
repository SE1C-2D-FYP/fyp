/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "CONTACT_NOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactNote.findAll", query = "SELECT c FROM ContactNote c"),
    @NamedQuery(name = "ContactNote.findByCtNoteId", query = "SELECT c FROM ContactNote c WHERE c.ctNoteId = :ctNoteId"),
    @NamedQuery(name = "ContactNote.findByCtDate", query = "SELECT c FROM ContactNote c WHERE c.ctDate = :ctDate"),
    @NamedQuery(name = "ContactNote.findByCtTime", query = "SELECT c FROM ContactNote c WHERE c.ctTime = :ctTime"),
    @NamedQuery(name = "ContactNote.findByCtType", query = "SELECT c FROM ContactNote c WHERE c.ctType = :ctType"),
    @NamedQuery(name = "ContactNote.findByInchPrice", query = "SELECT c FROM ContactNote c WHERE c.inchPrice = :inchPrice"),
    @NamedQuery(name = "ContactNote.findByMemo", query = "SELECT c FROM ContactNote c WHERE c.memo = :memo")})
public class ContactNote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CT_NOTE_ID")
    private Long ctNoteId;
    @Column(name = "CT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctDate;
    @Column(name = "CT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctTime;
    @Size(max = 20)
    @Column(name = "CT_TYPE")
    private String ctType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "INCH_PRICE")
    private BigDecimal inchPrice;
    @Size(max = 255)
    @Column(name = "MEMO")
    private String memo;
    @JoinColumn(name = "STOCK_ID", referencedColumnName = "STOCK_ID")
    @ManyToOne(optional = false)
    private Stock stockId;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @JoinColumn(name = "CT_INFO_ID", referencedColumnName = "CT_INFO_ID")
    @ManyToOne(optional = false)
    private ContactInfo ctInfoId;

    public ContactNote() {
    }

    public ContactNote(Long ctNoteId) {
        this.ctNoteId = ctNoteId;
    }

    public Long getCtNoteId() {
        return ctNoteId;
    }

    public void setCtNoteId(Long ctNoteId) {
        this.ctNoteId = ctNoteId;
    }

    public Date getCtDate() {
        return ctDate;
    }

    public void setCtDate(Date ctDate) {
        this.ctDate = ctDate;
    }

    public Date getCtTime() {
        return ctTime;
    }

    public void setCtTime(Date ctTime) {
        this.ctTime = ctTime;
    }

    public String getCtType() {
        return ctType;
    }

    public void setCtType(String ctType) {
        this.ctType = ctType;
    }

    public BigDecimal getInchPrice() {
        return inchPrice;
    }

    public void setInchPrice(BigDecimal inchPrice) {
        this.inchPrice = inchPrice;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Stock getStockId() {
        return stockId;
    }

    public void setStockId(Stock stockId) {
        this.stockId = stockId;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    public ContactInfo getCtInfoId() {
        return ctInfoId;
    }

    public void setCtInfoId(ContactInfo ctInfoId) {
        this.ctInfoId = ctInfoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctNoteId != null ? ctNoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactNote)) {
            return false;
        }
        ContactNote other = (ContactNote) object;
        if ((this.ctNoteId == null && other.ctNoteId != null) || (this.ctNoteId != null && !this.ctNoteId.equals(other.ctNoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.ContactNote[ ctNoteId=" + ctNoteId + " ]";
    }
    
}
