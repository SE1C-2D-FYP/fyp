/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.OneToOne;
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
@Table(name = "CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClientId", query = "SELECT c FROM Client c WHERE c.clientId = :clientId"),
    @NamedQuery(name = "Client.findByClientName", query = "SELECT c FROM Client c WHERE c.clientName = :clientName"),
    @NamedQuery(name = "Client.findByAddress", query = "SELECT c FROM Client c WHERE c.address = :address"),
    @NamedQuery(name = "Client.findByPhoneNo", query = "SELECT c FROM Client c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "Client.findByMphoneNo", query = "SELECT c FROM Client c WHERE c.mphoneNo = :mphoneNo"),
    @NamedQuery(name = "Client.findByFaxNo", query = "SELECT c FROM Client c WHERE c.faxNo = :faxNo"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Size(max = 100)
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE_NO")
    private Long phoneNo;
    @Column(name = "MPHONE_NO")
    private Long mphoneNo;
    @Column(name = "FAX_NO")
    private BigInteger faxNo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private PersonalClient personalClient;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private CompanyClient companyClient;
    @OneToMany(mappedBy = "clientId")
    private Collection<ActivityNote> activityNoteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Collection<StockViewing> stockViewingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Collection<ContactInfo> contactInfoCollection;

    public Client() {
    }

    public Client(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getMphoneNo() {
        return mphoneNo;
    }

    public void setMphoneNo(Long mphoneNo) {
        this.mphoneNo = mphoneNo;
    }

    public BigInteger getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(BigInteger faxNo) {
        this.faxNo = faxNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
@JsonIgnore
    public PersonalClient getPersonalClient() {
        return personalClient;
    }

    public void setPersonalClient(PersonalClient personalClient) {
        this.personalClient = personalClient;
    }
@JsonIgnore
    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    public CompanyClient getCompanyClient() {
        return companyClient;
    }

    public void setCompanyClient(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ActivityNote> getActivityNoteCollection() {
        return activityNoteCollection;
    }

    public void setActivityNoteCollection(Collection<ActivityNote> activityNoteCollection) {
        this.activityNoteCollection = activityNoteCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StockViewing> getStockViewingCollection() {
        return stockViewingCollection;
    }

    public void setStockViewingCollection(Collection<StockViewing> stockViewingCollection) {
        this.stockViewingCollection = stockViewingCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ContactInfo> getContactInfoCollection() {
        return contactInfoCollection;
    }

    public void setContactInfoCollection(Collection<ContactInfo> contactInfoCollection) {
        this.contactInfoCollection = contactInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Client[ clientId=" + clientId + " ]";
    }
    
}
