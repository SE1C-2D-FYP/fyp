/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "STOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByStockId", query = "SELECT s FROM Stock s WHERE s.stockId = :stockId"),
    @NamedQuery(name = "Stock.findByTxType", query = "SELECT s FROM Stock s WHERE s.txType = :txType"),
    @NamedQuery(name = "Stock.findByPrice", query = "SELECT s FROM Stock s WHERE s.price = :price"),
    @NamedQuery(name = "Stock.findByRent", query = "SELECT s FROM Stock s WHERE s.rent = :rent"),
    @NamedQuery(name = "Stock.findByWithKey", query = "SELECT s FROM Stock s WHERE s.withKey = :withKey"),
    @NamedQuery(name = "Stock.findByPostDate", query = "SELECT s FROM Stock s WHERE s.postDate = :postDate")})
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STOCK_ID")
    private String stockId;
    @Column(name = "TX_TYPE")
    private Character txType;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "RENT")
    private Long rent;
    @Size(max = 50)
    @Column(name = "WITH_KEY")
    private String withKey;
    @Column(name = "POST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @JoinColumn(name = "UNIT_VIEW_ID", referencedColumnName = "UNIT_VIEW_ID")
    @ManyToOne(optional = false)
    private UnitView unitViewId;
    @JoinColumn(name = "UNIT_ID", referencedColumnName = "UNIT_ID")
    @ManyToOne(optional = false)
    private Unit unitId;
    @JoinColumn(name = "ORIENTATION_ID", referencedColumnName = "ORIENTATION_ID")
    @ManyToOne(optional = false)
    private Orientation orientationId;
    @JoinColumn(name = "EST_ID", referencedColumnName = "EST_ID")
    @ManyToOne(optional = false)
    private Estate estId;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockId")
    private Collection<StockViewing> stockViewingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockId")
    private Collection<ContactNote> contactNoteCollection;

    public Stock() {
    }

    public Stock(String stockId) {
        this.stockId = stockId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Character getTxType() {
        return txType;
    }

    public void setTxType(Character txType) {
        this.txType = txType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }

    public String getWithKey() {
        return withKey;
    }

    public void setWithKey(String withKey) {
        this.withKey = withKey;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public UnitView getUnitViewId() {
        return unitViewId;
    }

    public void setUnitViewId(UnitView unitViewId) {
        this.unitViewId = unitViewId;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    public Orientation getOrientationId() {
        return orientationId;
    }

    public void setOrientationId(Orientation orientationId) {
        this.orientationId = orientationId;
    }

    public Estate getEstId() {
        return estId;
    }

    public void setEstId(Estate estId) {
        this.estId = estId;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    @XmlTransient
    public Collection<StockViewing> getStockViewingCollection() {
        return stockViewingCollection;
    }

    public void setStockViewingCollection(Collection<StockViewing> stockViewingCollection) {
        this.stockViewingCollection = stockViewingCollection;
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
        hash += (stockId != null ? stockId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.stockId == null && other.stockId != null) || (this.stockId != null && !this.stockId.equals(other.stockId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Stock[ stockId=" + stockId + " ]";
    }
    
}
