/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "TRANSACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTxId", query = "SELECT t FROM Transaction t WHERE t.txId = :txId"),
    @NamedQuery(name = "Transaction.findBySell", query = "SELECT t FROM Transaction t WHERE t.sell = :sell"),
    @NamedQuery(name = "Transaction.findByRent", query = "SELECT t FROM Transaction t WHERE t.rent = :rent"),
    @NamedQuery(name = "Transaction.findByAgreement", query = "SELECT t FROM Transaction t WHERE t.agreement = :agreement"),
    @NamedQuery(name = "Transaction.findByInstDate", query = "SELECT t FROM Transaction t WHERE t.instDate = :instDate"),
    @NamedQuery(name = "Transaction.findByAgreementDate", query = "SELECT t FROM Transaction t WHERE t.agreementDate = :agreementDate"),
    @NamedQuery(name = "Transaction.findByTransDate", query = "SELECT t FROM Transaction t WHERE t.transDate = :transDate"),
    @NamedQuery(name = "Transaction.findByCommission", query = "SELECT t FROM Transaction t WHERE t.commission = :commission"),
    @NamedQuery(name = "Transaction.findByClientId", query = "SELECT t FROM Transaction t WHERE t.clientId = :clientId")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TX_ID")
    private Long txId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SELL")
    private BigDecimal sell;
    @Column(name = "RENT")
    private BigDecimal rent;
    @Size(max = 255)
    @Column(name = "AGREEMENT")
    private String agreement;
    @Column(name = "INST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date instDate;
    @Column(name = "AGREEMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreementDate;
    @Column(name = "TRANS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "COMMISSION")
    private BigDecimal commission;
    @JoinColumn(name = "UNIT_ID", referencedColumnName = "UNIT_ID")
    @ManyToOne(optional = false)
    private Unit unitId;
    @JoinColumn(name = "EST_ID", referencedColumnName = "EST_ID")
    @ManyToOne(optional = false)
    private Estate estId;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false)
    private Employee empId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(optional = false)
    private Client clientId;

    public Transaction() {
    }

    public Transaction(Long txId) {
        this.txId = txId;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Date getInstDate() {
        return instDate;
    }

    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }

    public Date getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(Date agreementDate) {
        this.agreementDate = agreementDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
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
    
    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (txId != null ? txId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.txId == null && other.txId != null) || (this.txId != null && !this.txId.equals(other.txId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Transaction[ txId=" + txId + " ]";
    }

}
