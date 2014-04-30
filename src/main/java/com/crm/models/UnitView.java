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

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "UNIT_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnitView.findAll", query = "SELECT u FROM UnitView u"),
    @NamedQuery(name = "UnitView.findByUnitViewId", query = "SELECT u FROM UnitView u WHERE u.unitViewId = :unitViewId"),
    @NamedQuery(name = "UnitView.findByEngName", query = "SELECT u FROM UnitView u WHERE u.engName = :engName"),
    @NamedQuery(name = "UnitView.findByChiName", query = "SELECT u FROM UnitView u WHERE u.chiName = :chiName")})
public class UnitView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UNIT_VIEW_ID")
    private String unitViewId;
    @Size(max = 50)
    @Column(name = "ENG_NAME")
    private String engName;
    @Size(max = 50)
    @Column(name = "CHI_NAME")
    private String chiName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitViewId")
    private Collection<Stock> stockCollection;

    public UnitView() {
    }

    public UnitView(String unitViewId) {
        this.unitViewId = unitViewId;
    }

    public String getUnitViewId() {
        return unitViewId;
    }

    public void setUnitViewId(String unitViewId) {
        this.unitViewId = unitViewId;
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

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitViewId != null ? unitViewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnitView)) {
            return false;
        }
        UnitView other = (UnitView) object;
        if ((this.unitViewId == null && other.unitViewId != null) || (this.unitViewId != null && !this.unitViewId.equals(other.unitViewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.UnitView[ unitViewId=" + unitViewId + " ]";
    }
    
}
