/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "COMPANY_CLIENT")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "CompanyClient.findAll", query = "SELECT c FROM CompanyClient c"),
//    @NamedQuery(name = "CompanyClient.findByCclientId", query = "SELECT c FROM CompanyClient c WHERE c.cclientId = :cclientId"),
//    @NamedQuery(name = "CompanyClient.findByWebsite", query = "SELECT c FROM CompanyClient c WHERE c.website = :website")})
public class CompanyClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCLIENT_ID")
    private Long cclientId;
    @Size(max = 100)
    @Column(name = "WEBSITE")
    private String website;
    @JoinColumn(name = "CCLIENT_ID", referencedColumnName = "CLIENT_ID", insertable = false, updatable = false)
    @OneToOne(optional = false,fetch = FetchType.LAZY)
    private Client client;

    public CompanyClient() {
    }

    public CompanyClient(Long cclientId) {
        this.cclientId = cclientId;
    }

    public Long getCclientId() {
        return cclientId;
    }

    public void setCclientId(Long cclientId) {
        this.cclientId = cclientId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cclientId != null ? cclientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyClient)) {
            return false;
        }
        CompanyClient other = (CompanyClient) object;
        if ((this.cclientId == null && other.cclientId != null) || (this.cclientId != null && !this.cclientId.equals(other.cclientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.CompanyClient[ cclientId=" + cclientId + " ]";
    }
    
}
