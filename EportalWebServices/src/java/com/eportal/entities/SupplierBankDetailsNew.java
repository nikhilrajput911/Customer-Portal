/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author admin
 */
@Entity
@Table(name = "bpaas_supplierbankdetails_new")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierBankDetailsNew.findAll", query = "SELECT s FROM SupplierBankDetailsNew s"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankid", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankid = :bankid"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankname", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankname = :bankname"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankaccountnumber", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankaccountnumber = :bankaccountnumber"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByConfirmaccountnumber", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.confirmaccountnumber = :confirmaccountnumber"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankaccounttype", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankaccounttype = :bankaccounttype"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByVendornameAsperbankrecord", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.vendornameAsperbankrecord = :vendornameAsperbankrecord"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankbranchname", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankbranchname = :bankbranchname"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByBankaddress", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bankaddress = :bankaddress"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByPincode", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.pincode = :pincode"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByIfsccode", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.ifsccode = :ifsccode"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByMicrnumber", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.micrnumber = :micrnumber"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByUpdatdate", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.updatdate = :updatdate"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByUpdatedby", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByCreationdate", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierBankDetailsNew.findBySupplierId", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.bpaasSupplieruserId.id = :id"),
    @NamedQuery(name = "SupplierBankDetailsNew.findByCreatedby", query = "SELECT s FROM SupplierBankDetailsNew s WHERE s.createdby = :createdby")})
public class SupplierBankDetailsNew implements Serializable {
    @Size(max = 30)
    @Column(name = "currency")
    private String currency;
    @Size(max = 20)
    @Column(name = "bankaccountnumber")
    private String bankaccountnumber;
    @Size(max = 20)
    @Column(name = "confirmaccountnumber")
    private String confirmaccountnumber;
    @Size(max = 15)
    @Column(name = "pincode")
    private String pincode;
    @Size(max = 20)
    @Column(name = "micrnumber")
    private String micrnumber;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bankid")
    private Integer bankid;
    @Size(max = 100)
    @Column(name = "bankname")
    private String bankname;
    @Size(max = 30)
    @Column(name = "bankaccounttype")
    private String bankaccounttype;
    @Size(max = 50)
    @Column(name = "vendorname_asperbankrecord")
    private String vendornameAsperbankrecord;
    @Size(max = 50)
    @Column(name = "bankbranchname")
    private String bankbranchname;
    @Size(max = 100)
    @Column(name = "bankaddress")
    private String bankaddress;
    @Size(max = 20)
    @Column(name = "ifsccode")
    private String ifsccode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updatdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatdate;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierUser bpaasSupplieruserId;

    public SupplierBankDetailsNew() {
    }

    public SupplierBankDetailsNew(Integer bankid) {
        this.bankid = bankid;
    }

    public SupplierBankDetailsNew(Integer bankid, Date updatdate, Date creationdate) {
        this.bankid = bankid;
        this.updatdate = updatdate;
        this.creationdate = creationdate;
    }

    public Integer getBankid() {
        return bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }


    public String getBankaccounttype() {
        return bankaccounttype;
    }

    public void setBankaccounttype(String bankaccounttype) {
        this.bankaccounttype = bankaccounttype;
    }

    public String getVendornameAsperbankrecord() {
        return vendornameAsperbankrecord;
    }

    public void setVendornameAsperbankrecord(String vendornameAsperbankrecord) {
        this.vendornameAsperbankrecord = vendornameAsperbankrecord;
    }

    public String getBankbranchname() {
        return bankbranchname;
    }

    public void setBankbranchname(String bankbranchname) {
        this.bankbranchname = bankbranchname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }


    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }


    public Date getUpdatdate() {
        return updatdate;
    }

    public void setUpdatdate(Date updatdate) {
        this.updatdate = updatdate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public SupplierUser getBpaasSupplieruserId() {
        return bpaasSupplieruserId;
    }

    public void setBpaasSupplieruserId(SupplierUser bpaasSupplieruserId) {
        this.bpaasSupplieruserId = bpaasSupplieruserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankid != null ? bankid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierBankDetailsNew)) {
            return false;
        }
        SupplierBankDetailsNew other = (SupplierBankDetailsNew) object;
        if ((this.bankid == null && other.bankid != null) || (this.bankid != null && !this.bankid.equals(other.bankid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierBankDetailsNew[ bankid=" + bankid + " ]";
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }

    public String getConfirmaccountnumber() {
        return confirmaccountnumber;
    }

    public void setConfirmaccountnumber(String confirmaccountnumber) {
        this.confirmaccountnumber = confirmaccountnumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMicrnumber() {
        return micrnumber;
    }

    public void setMicrnumber(String micrnumber) {
        this.micrnumber = micrnumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
}
