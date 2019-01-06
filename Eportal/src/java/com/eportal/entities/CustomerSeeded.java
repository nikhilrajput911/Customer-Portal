/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "NG_CP_customerseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSeeded.findAll", query = "SELECT c FROM CustomerSeeded c"),
    @NamedQuery(name = "CustomerSeeded.findByCid", query = "SELECT c FROM CustomerSeeded c WHERE c.cid = :cid"),
    @NamedQuery(name = "CustomerSeeded.findByCustomername", query = "SELECT c FROM CustomerSeeded c WHERE c.customername = :customername"),
    @NamedQuery(name = "CustomerSeeded.findByCustomercode", query = "SELECT c FROM CustomerSeeded c WHERE c.customercode = :customercode")})
public class CustomerSeeded implements Serializable {
    @Lob
    @Column(name = "profilepic")
    private byte[] profilepic;
//    
//    @OneToMany(mappedBy = "ngCpCustomerseededId")
//    private Collection<CustomerAuditReport> customerAuditReportCollection1;
    @Size(max = 50)
    @Column(name = "AccountGroupType")
    private String accountGroupType;
    @OneToMany(mappedBy = "bpaasCustomerseededCid")
    private Collection<ProjectSeeded> projectSeededCollection;
    @Size(max = 30)
    @Column(name = "customer_blockstatus")
    private String customerBlockstatus;
    @Size(max = 20)
    @Column(name = "customertype")
    private String customertype;
    @Size(max = 50)
    @Column(name = "emailid")
    private String emailid;
    @Size(max = 250)
    @Column(name = "addressline3")
    private String addressline3;
    @Size(max = 250)
    @Column(name = "addressline2")
    private String addressline2;
    @Size(max = 250)
    @Column(name = "addressline1")
    private String addressline1;
    @Size(max = 250)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 250)
    @Column(name = "firstname")
    private String firstname;
    @OneToMany(mappedBy = "bpaasCustomerseededCid")
    private Collection<CustomerSubUser> customerSubUserCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Size(max = 250)
    @Column(name = "customername")
    private String customername;
    @Size(max = 100)
    @Column(name = "customercode")
    private String customercode;

    public CustomerSeeded() {
    }

    public CustomerSeeded(Integer cid) {
        this.cid = cid;
    }

    public String getCustomerBlockstatus() {
        return customerBlockstatus;
    }

    public void setCustomerBlockstatus(String customerBlockstatus) {
        this.customerBlockstatus = customerBlockstatus;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }
    
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSeeded)) {
            return false;
        }
        CustomerSeeded other = (CustomerSeeded) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerSeeded[ cid=" + cid + " ]";
    }

    @XmlTransient
    public Collection<CustomerSubUser> getCustomerSubUserCollection() {
        return customerSubUserCollection;
    }

    public void setCustomerSubUserCollection(Collection<CustomerSubUser> customerSubUserCollection) {
        this.customerSubUserCollection = customerSubUserCollection;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlTransient
    public Collection<ProjectSeeded> getProjectSeededCollection() {
        return projectSeededCollection;
    }

    public void setProjectSeededCollection(Collection<ProjectSeeded> projectSeededCollection) {
        this.projectSeededCollection = projectSeededCollection;
    }
    
    public String getAccountGroupType() {
        return accountGroupType;
    }

    public void setAccountGroupType(String accountGroupType) {
        this.accountGroupType = accountGroupType;
    }

    public byte[] getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic = profilepic;
    }

//
//    @XmlTransient
//    public Collection<CustomerAuditReport> getCustomerAuditReportCollection1() {
//        return customerAuditReportCollection1;
//    }
//
//    public void setCustomerAuditReportCollection1(Collection<CustomerAuditReport> customerAuditReportCollection1) {
//        this.customerAuditReportCollection1 = customerAuditReportCollection1;
//    }

}
