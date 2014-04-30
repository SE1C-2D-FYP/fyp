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
@Table(name = "STOCK_VIEWING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockViewing.findAll", query = "SELECT s FROM StockViewing s"),
    @NamedQuery(name = "StockViewing.findByStockViewingId", query = "SELECT s FROM StockViewing s WHERE s.stockViewingId = :stockViewingId"),
    @NamedQuery(name = "StockViewing.findByViewingDate", query = "SELECT s FROM StockViewing s WHERE s.viewingDate = :viewingDate"),
    @NamedQuery(name = "StockViewing.findByViewingTime", query = "SELECT s FROM StockViewing s WHERE s.viewingTime = :viewingTime"),
    @NamedQuery(name = "StockViewing.findByViewingNote", query = "SELECT s FROM StockViewing s WHERE s.viewingNote = :viewingNote")})
public class StockViewing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_VIEWING_ID")
    private Long stockViewingId;
    @Column(name = "VIEWING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewingDate;
    @Column(name = "VIEWING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewingTime;
    @Size(max = 255)
    @Column(name = "VIEWING_NOTE")
    private String viewingNote;
    @JoinColumn(name = "STOCK_ID", referencedColumnName = "STOCK_ID")
    @ManyToOne(optional = false)
    private Stock stockId;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(optional = false)
    private Client clientId;

    public StockViewing() {
    }

    public StockViewing(Long stockViewingId) {
        this.stockViewingId = stockViewingId;
    }

    public Long getStockViewingId() {
        return stockViewingId;
    }

    public void setStockViewingId(Long stockViewingId) {
        this.stockViewingId = stockViewingId;
    }

    public Date getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(Date viewingDate) {
        this.viewingDate = viewingDate;
    }

    public Date getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(Date viewingTime) {
        this.viewingTime = viewingTime;
    }

    public String getViewingNote() {
        return viewingNote;
    }

    public void setViewingNote(String viewingNote) {
        this.viewingNote = viewingNote;
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

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockViewingId != null ? stockViewingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockViewing)) {
            return false;
        }
        StockViewing other = (StockViewing) object;
        if ((this.stockViewingId == null && other.stockViewingId != null) || (this.stockViewingId != null && !this.stockViewingId.equals(other.stockViewingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.StockViewing[ stockViewingId=" + stockViewingId + " ]";
    }
    
}
