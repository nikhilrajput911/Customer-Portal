/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ng_cp_customer_automail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAutoMail.findAll", query = "SELECT c FROM CustomerAutoMail c"),
    @NamedQuery(name = "CustomerAutoMail.findById", query = "SELECT c FROM CustomerAutoMail c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerAutoMail.findByCustomerId", query = "SELECT c FROM CustomerAutoMail c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "CustomerAutoMail.findByCustomerName", query = "SELECT c FROM CustomerAutoMail c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "CustomerAutoMail.findByUserName", query = "SELECT c FROM CustomerAutoMail c WHERE c.userName = :userName"),
    @NamedQuery(name = "CustomerAutoMail.findByUserMailId", query = "SELECT c FROM CustomerAutoMail c WHERE c.userMailId = :userMailId"),
    @NamedQuery(name = "CustomerAutoMail.findByInvoice", query = "SELECT c FROM CustomerAutoMail c WHERE c.invoice = :invoice"),
    @NamedQuery(name = "CustomerAutoMail.findBySignedDO", query = "SELECT c FROM CustomerAutoMail c WHERE c.signedDO = :signedDO"),
    @NamedQuery(name = "CustomerAutoMail.findByUnSignedDO", query = "SELECT c FROM CustomerAutoMail c WHERE c.unSignedDO = :unSignedDO"),
    @NamedQuery(name = "CustomerAutoMail.findByDebit", query = "SELECT c FROM CustomerAutoMail c WHERE c.debit = :debit"),
    @NamedQuery(name = "CustomerAutoMail.findByCredit", query = "SELECT c FROM CustomerAutoMail c WHERE c.credit = :credit"),
    @NamedQuery(name = "CustomerAutoMail.findByARListing", query = "SELECT c FROM CustomerAutoMail c WHERE c.aRListing = :aRListing"),
    @NamedQuery(name = "CustomerAutoMail.findBySoa", query = "SELECT c FROM CustomerAutoMail c WHERE c.soa = :soa")})
public class CustomerAutoMail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "CustomerId")
    private String customerId;
    @Size(max = 500)
    @Column(name = "CustomerName")
    private String customerName;
    @Size(max = 250)
    @Column(name = "UserName")
    private String userName;
    @Size(max = 500)
    @Column(name = "UserMailId")
    private String userMailId;
    @Size(max = 2)
    @Column(name = "Invoice")
    private String invoice;
    @Size(max = 2)
    @Column(name = "SignedDO")
    private String signedDO;
    @Size(max = 2)
    @Column(name = "UnSignedDO")
    private String unSignedDO;
    @Size(max = 2)
    @Column(name = "Debit")
    private String debit;
    @Size(max = 2)
    @Column(name = "Credit")
    private String credit;
    @Size(max = 2)
    @Column(name = "ARListing")
    private String aRListing;
    @Size(max = 2)
    @Column(name = "SOA")
    private String soa;

    public CustomerAutoMail() {
    }

    public CustomerAutoMail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMailId() {
        return userMailId;
    }

    public void setUserMailId(String userMailId) {
        this.userMailId = userMailId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSignedDO() {
        return signedDO;
    }

    public void setSignedDO(String signedDO) {
        this.signedDO = signedDO;
    }

    public String getUnSignedDO() {
        return unSignedDO;
    }

    public void setUnSignedDO(String unSignedDO) {
        this.unSignedDO = unSignedDO;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getARListing() {
        return aRListing;
    }

    public void setARListing(String aRListing) {
        this.aRListing = aRListing;
    }

    public String getSoa() {
        return soa;
    }

    public void setSoa(String soa) {
        this.soa = soa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAutoMail)) {
            return false;
        }
        CustomerAutoMail other = (CustomerAutoMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerAutoMail[ id=" + id + " ]";
    }
    
}
