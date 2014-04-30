/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "PERSONAL_CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalClient.findAll", query = "SELECT p FROM PersonalClient p"),
    @NamedQuery(name = "PersonalClient.findByClientclientId", query = "SELECT p FROM PersonalClient p WHERE p.clientclientId = :clientclientId")})
public class PersonalClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENTCLIENT_ID")
    private Long clientclientId;
    @JoinColumn(name = "CLIENTCLIENT_ID", referencedColumnName = "CLIENT_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;

    public PersonalClient() {
    }

    public PersonalClient(Long clientclientId) {
        this.clientclientId = clientclientId;
    }

    public Long getClientclientId() {
        return clientclientId;
    }

    public void setClientclientId(Long clientclientId) {
        this.clientclientId = clientclientId;
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
        hash += (clientclientId != null ? clientclientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalClient)) {
            return false;
        }
        PersonalClient other = (PersonalClient) object;
        if ((this.clientclientId == null && other.clientclientId != null) || (this.clientclientId != null && !this.clientclientId.equals(other.clientclientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.PersonalClient[ clientclientId=" + clientclientId + " ]";
    }
    
}
