/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "bpaas_supplieruserbankdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierUserBankDetail.findAll", query = "SELECT s FROM SupplierUserBankDetail s"),
    @NamedQuery(name = "SupplierUserBankDetail.findById", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierUserBankDetail.findByBankname", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bankname = :bankname"),
    @NamedQuery(name = "SupplierUserBankDetail.findByBankaccountnumber", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bankaccountnumber = :bankaccountnumber"),
    @NamedQuery(name = "SupplierUserBankDetail.findByBankaccounttype", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bankaccounttype = :bankaccounttype"),
    @NamedQuery(name = "SupplierUserBankDetail.findByVendornameAsperbankrecord", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.vendornameAsperbankrecord = :vendornameAsperbankrecord"),
    @NamedQuery(name = "SupplierUserBankDetail.findByBankbranchname", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bankbranchname = :bankbranchname"),
    @NamedQuery(name = "SupplierUserBankDetail.findByBankaddress", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bankaddress = :bankaddress"),
    @NamedQuery(name = "SupplierUserBankDetail.findByPincode", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.pincode = :pincode"),
    @NamedQuery(name = "SupplierUserBankDetail.findByIfsccode", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.ifsccode = :ifsccode"),
    @NamedQuery(name = "SupplierUserBankDetail.findByMicrnumber", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.micrnumber = :micrnumber"),
    @NamedQuery(name = "SupplierUserBankDetail.findByUpdatedate", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierUserBankDetail.findByUpdatedby", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierUserBankDetail.findByCreationdate", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierUserBankDetail.findByCreatedby", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierUserBankDetail.findByAf1", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierUserBankDetail.findByAf2", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierUserBankDetail.findByAf3", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierUserBankDetail.findByAf4", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierUserBankDetail.findBySupplierId", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.bPaasSupplierUserTableid.id = :id"),
    @NamedQuery(name = "SupplierUserBankDetail.findByAf5", query = "SELECT s FROM SupplierUserBankDetail s WHERE s.af5 = :af5")})
public class SupplierUserBankDetail implements Serializable {
    @Column(name = "pincode")
    private Integer pincode;
    @Column(name = "micrnumber")
    private Integer micrnumber;
    @Column(name = "bankaccountnumber")
    private Integer bankaccountnumber;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "bankname")
    private String bankname;
    @Size(max = 30)
    @Column(name = "bankaccounttype")
    private String bankaccounttype;
    @Size(max = 30)
    @Column(name = "vendorname_asperbankrecord")
    private String vendornameAsperbankrecord;
    @Size(max = 30)
    @Column(name = "bankbranchname")
    private String bankbranchname;
    @Size(max = 50)
    @Column(name = "bankaddress")
    private String bankaddress;
    @Size(max = 30)
    @Column(name = "ifsccode")
    private String ifsccode;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 30)
    @Column(name = "updatedby")
    private String updatedby;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 30)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "AF1")
    private Integer af1;
    @Column(name = "AF2")
    private Integer af2;
    @Column(name = "AF3")
    private Integer af3;
    @Column(name = "AF4")
    private Integer af4;
    @Column(name = "AF5")
    private Integer af5;
    @JoinColumn(name = "BPaas_SupplierUserTable_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SupplierUser bPaasSupplierUserTableid;

    public SupplierUserBankDetail() {
    }

    public SupplierUserBankDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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

    public Integer getAf1() {
        return af1;
    }

    public void setAf1(Integer af1) {
        this.af1 = af1;
    }

    public Integer getAf2() {
        return af2;
    }

    public void setAf2(Integer af2) {
        this.af2 = af2;
    }

    public Integer getAf3() {
        return af3;
    }

    public void setAf3(Integer af3) {
        this.af3 = af3;
    }

    public Integer getAf4() {
        return af4;
    }

    public void setAf4(Integer af4) {
        this.af4 = af4;
    }

    public Integer getAf5() {
        return af5;
    }

    public void setAf5(Integer af5) {
        this.af5 = af5;
    }

    public SupplierUser getBPaasSupplierUserTableid() {
        return bPaasSupplierUserTableid;
    }

    public void setBPaasSupplierUserTableid(SupplierUser bPaasSupplierUserTableid) {
        this.bPaasSupplierUserTableid = bPaasSupplierUserTableid;
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
        if (!(object instanceof SupplierUserBankDetail)) {
            return false;
        }
        SupplierUserBankDetail other = (SupplierUserBankDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierUserBankDetail[ id=" + id + " ]";
    }

    public Integer getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(Integer bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Integer getMicrnumber() {
        return micrnumber;
    }

    public void setMicrnumber(Integer micrnumber) {
        this.micrnumber = micrnumber;
    }

    
}
