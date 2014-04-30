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
@Table(name = "DISTRICT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "District.findAll", query = "SELECT d FROM District d"),
    @NamedQuery(name = "District.findByDistId", query = "SELECT d FROM District d WHERE d.distId = :distId"),
    @NamedQuery(name = "District.findByEngName", query = "SELECT d FROM District d WHERE d.engName = :engName"),
    @NamedQuery(name = "District.findByChiName", query = "SELECT d FROM District d WHERE d.chiName = :chiName")})
public class District implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIST_ID")
    private Long distId;
    @Size(max = 50)
    @Column(name = "ENG_NAME")
    private String engName;
    @Size(max = 50)
    @Column(name = "CHI_NAME")
    private String chiName;
    @JoinColumn(name = "REGION_ID", referencedColumnName = "REGION_ID")
    @ManyToOne(optional = false)
    private Region regionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distId")
    private Collection<Estate> estateCollection;

    public District() {
    }

    public District(Long distId) {
        this.distId = distId;
    }

    public Long getDistId() {
        return distId;
    }

    public void setDistId(Long distId) {
        this.distId = distId;
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

    public Region getRegionId() {
        return regionId;
    }

    public void setRegionId(Region regionId) {
        this.regionId = regionId;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Estate> getEstateCollection() {
        return estateCollection;
    }

    public void setEstateCollection(Collection<Estate> estateCollection) {
        this.estateCollection = estateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (distId != null ? distId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.distId == null && other.distId != null) || (this.distId != null && !this.distId.equals(other.distId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.District[ distId=" + distId + " ]";
    }
    
}
