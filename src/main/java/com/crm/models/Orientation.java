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
@Table(name = "ORIENTATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orientation.findAll", query = "SELECT o FROM Orientation o"),
    @NamedQuery(name = "Orientation.findByOrientationId", query = "SELECT o FROM Orientation o WHERE o.orientationId = :orientationId"),
    @NamedQuery(name = "Orientation.findByEngTitle", query = "SELECT o FROM Orientation o WHERE o.engTitle = :engTitle"),
    @NamedQuery(name = "Orientation.findByChiTitle", query = "SELECT o FROM Orientation o WHERE o.chiTitle = :chiTitle")})
public class Orientation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORIENTATION_ID")
    private String orientationId;
    @Size(max = 50)
    @Column(name = "ENG_TITLE")
    private String engTitle;
    @Size(max = 50)
    @Column(name = "CHI_TITLE")
    private String chiTitle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orientationId")
    private Collection<Stock> stockCollection;

    public Orientation() {
    }

    public Orientation(String orientationId) {
        this.orientationId = orientationId;
    }

    public String getOrientationId() {
        return orientationId;
    }

    public void setOrientationId(String orientationId) {
        this.orientationId = orientationId;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public String getChiTitle() {
        return chiTitle;
    }

    public void setChiTitle(String chiTitle) {
        this.chiTitle = chiTitle;
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
        hash += (orientationId != null ? orientationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orientation)) {
            return false;
        }
        Orientation other = (Orientation) object;
        if ((this.orientationId == null && other.orientationId != null) || (this.orientationId != null && !this.orientationId.equals(other.orientationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Orientation[ orientationId=" + orientationId + " ]";
    }
    
}
