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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "BUILDING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Building.findAll", query = "SELECT b FROM Building b"),
    @NamedQuery(name = "Building.findByBldgId", query = "SELECT b FROM Building b WHERE b.bldgId = :bldgId"),
    @NamedQuery(name = "Building.findByEngName", query = "SELECT b FROM Building b WHERE b.engName = :engName"),
    @NamedQuery(name = "Building.findByChiName", query = "SELECT b FROM Building b WHERE b.chiName = :chiName"),
    @NamedQuery(name = "Building.findByFirstOpDate", query = "SELECT b FROM Building b WHERE b.firstOpDate = :firstOpDate"),
    @NamedQuery(name = "Building.findByBlockNo", query = "SELECT b FROM Building b WHERE b.blockNo = :blockNo")})
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "BLDG_ID")
    private String bldgId;
    @Size(max = 100)
    @Column(name = "ENG_NAME")
    private String engName;
    @Size(max = 100)
    @Column(name = "CHI_NAME")
    private String chiName;
    @Column(name = "FIRST_OP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstOpDate;
    @Column(name = "BLOCK_NO")
    private Long blockNo;
    @JoinColumn(name = "EST_ID", referencedColumnName = "EST_ID")
    @ManyToOne(optional = false)
    private Estate estId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bldgId")
    private Collection<Unit> unitCollection;

    public Building() {
    }

    public Building(String bldgId) {
        this.bldgId = bldgId;
    }

    public String getBldgId() {
        return bldgId;
    }

    public void setBldgId(String bldgId) {
        this.bldgId = bldgId;
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

    public Date getFirstOpDate() {
        return firstOpDate;
    }

    public void setFirstOpDate(Date firstOpDate) {
        this.firstOpDate = firstOpDate;
    }

    public Long getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(Long blockNo) {
        this.blockNo = blockNo;
    }

    public Estate getEstId() {
        return estId;
    }

    public void setEstId(Estate estId) {
        this.estId = estId;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Unit> getUnitCollection() {
        return unitCollection;
    }

    public void setUnitCollection(Collection<Unit> unitCollection) {
        this.unitCollection = unitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bldgId != null ? bldgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Building)) {
            return false;
        }
        Building other = (Building) object;
        if ((this.bldgId == null && other.bldgId != null) || (this.bldgId != null && !this.bldgId.equals(other.bldgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Building[ bldgId=" + bldgId + " ]";
    }
    
}
