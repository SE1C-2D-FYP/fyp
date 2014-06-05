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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "ESTATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estate.findAll", query = "SELECT e FROM Estate e"),
    @NamedQuery(name = "Estate.findByEstId", query = "SELECT e FROM Estate e WHERE e.estId = :estId"),
    @NamedQuery(name = "Estate.findByEngName", query = "SELECT e FROM Estate e WHERE e.engName = :engName"),
    @NamedQuery(name = "Estate.findByChiName", query = "SELECT e FROM Estate e WHERE e.chiName = :chiName")})
public class Estate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EST_ID")
    private String estId;
    @Size(max = 100)
    @Column(name = "ENG_NAME")
    private String engName;
    @Size(max = 100)
    @Column(name = "CHI_NAME")
    private String chiName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estId")
    private Collection<Building> buildingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estId")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estId")
    private Collection<Stock> stockCollection;
    @JoinColumn(name = "DIST_ID", referencedColumnName = "DIST_ID")
    @ManyToOne(optional = false)
    private District distId;

    public Estate() {
    }

    public Estate(String estId) {
        this.estId = estId;
    }

    public String getEstId() {
        return estId;
    }

    public void setEstId(String estId) {
        this.estId = estId;
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
    @JsonIgnore
    public Collection<Building> getBuildingCollection() {
        return buildingCollection;
    }

    public void setBuildingCollection(Collection<Building> buildingCollection) {
        this.buildingCollection = buildingCollection;
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

    public District getDistId() {
        return distId;
    }

    public void setDistId(District distId) {
        this.distId = distId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estate)) {
            return false;
        }
        Estate other = (Estate) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Estate[ estId=" + estId + " ]";
    }
    
}
