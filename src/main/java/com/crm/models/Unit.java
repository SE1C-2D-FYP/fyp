/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "UNIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u"),
    @NamedQuery(name = "Unit.findByUnitId", query = "SELECT u FROM Unit u WHERE u.unitId = :unitId"),
    @NamedQuery(name = "Unit.findByFlat", query = "SELECT u FROM Unit u WHERE u.flat = :flat"),
    @NamedQuery(name = "Unit.findByFloor", query = "SELECT u FROM Unit u WHERE u.floor = :floor"),
    @NamedQuery(name = "Unit.findByRoom", query = "SELECT u FROM Unit u WHERE u.room = :room"),
    @NamedQuery(name = "Unit.findBySittingRoom", query = "SELECT u FROM Unit u WHERE u.sittingRoom = :sittingRoom"),
    @NamedQuery(name = "Unit.findByBathroom", query = "SELECT u FROM Unit u WHERE u.bathroom = :bathroom"),
    @NamedQuery(name = "Unit.findByBalcony", query = "SELECT u FROM Unit u WHERE u.balcony = :balcony"),
    @NamedQuery(name = "Unit.findByGrossArea", query = "SELECT u FROM Unit u WHERE u.grossArea = :grossArea"),
    @NamedQuery(name = "Unit.findByNetArea", query = "SELECT u FROM Unit u WHERE u.netArea = :netArea")})
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UNIT_ID")
    private String unitId;
    @Size(max = 10)
    @Column(name = "FLAT")
    private String flat;
    @Column(name = "FLOOR")
    private Integer floor;
    @Column(name = "ROOM")
    private Integer room;
    @Column(name = "SITTING_ROOM")
    private Integer sittingRoom;
    @Column(name = "BATHROOM")
    private Integer bathroom;
    @Column(name = "BALCONY")
    private Integer balcony;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GROSS_AREA")
    private BigDecimal grossArea;
    @Column(name = "NET_AREA")
    private BigDecimal netArea;
    @JoinColumn(name = "BLDG_ID", referencedColumnName = "BLDG_ID")
    @ManyToOne(optional = false)
    private Building bldgId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitId")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitId")
    private Collection<Stock> stockCollection;

    public Unit() {
    }

    public Unit(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getSittingRoom() {
        return sittingRoom;
    }

    public void setSittingRoom(Integer sittingRoom) {
        this.sittingRoom = sittingRoom;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
    }

    public Integer getBalcony() {
        return balcony;
    }

    public void setBalcony(Integer balcony) {
        this.balcony = balcony;
    }

    public BigDecimal getGrossArea() {
        return grossArea;
    }

    public void setGrossArea(BigDecimal grossArea) {
        this.grossArea = grossArea;
    }

    public BigDecimal getNetArea() {
        return netArea;
    }

    public void setNetArea(BigDecimal netArea) {
        this.netArea = netArea;
    }

    public Building getBldgId() {
        return bldgId;
    }

    public void setBldgId(Building bldgId) {
        this.bldgId = bldgId;
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
    @JsonIgnore
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitId != null ? unitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.unitId == null && other.unitId != null) || (this.unitId != null && !this.unitId.equals(other.unitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Unit[ unitId=" + unitId + " ]";
    }
    
}
