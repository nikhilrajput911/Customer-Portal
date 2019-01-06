/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "bpaas_supplieruser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierUser.findAll", query = "SELECT s FROM SupplierUser s order by s.creationdate desc"),
    @NamedQuery(name = "SupplierUser.findById", query = "SELECT s FROM SupplierUser s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierUser.findByCompanyname", query = "SELECT s FROM SupplierUser s WHERE s.companyname = :companyname"),
    @NamedQuery(name = "SupplierUser.findByTypeofbusiness", query = "SELECT s FROM SupplierUser s WHERE s.typeofbusiness = :typeofbusiness"),
    @NamedQuery(name = "SupplierUser.findByRegisteredaddress", query = "SELECT s FROM SupplierUser s WHERE s.registeredaddress = :registeredaddress"),
    @NamedQuery(name = "SupplierUser.findByOwnername", query = "SELECT s FROM SupplierUser s WHERE s.ownername = :ownername"),
    @NamedQuery(name = "SupplierUser.findByOwnermobilenumber", query = "SELECT s FROM SupplierUser s WHERE s.ownermobilenumber = :ownermobilenumber"),
    @NamedQuery(name = "SupplierUser.findByOwneremailid", query = "SELECT s FROM SupplierUser s WHERE s.owneremailid = :owneremailid"),
    @NamedQuery(name = "SupplierUser.findByStaffstrength", query = "SELECT s FROM SupplierUser s WHERE s.staffstrength = :staffstrength"),
    @NamedQuery(name = "SupplierUser.findByCompanypannumber", query = "SELECT s FROM SupplierUser s WHERE s.companypannumber = :companypannumber"),
    @NamedQuery(name = "SupplierUser.findByPfregnumber", query = "SELECT s FROM SupplierUser s WHERE s.pfregnumber = :pfregnumber"),
    @NamedQuery(name = "SupplierUser.findByUsername", query = "SELECT s FROM SupplierUser s WHERE s.username = :username and s.password = :password"),
    @NamedQuery(name = "SupplierUser.findByPassword", query = "SELECT s FROM SupplierUser s WHERE s.password = :password"),
    @NamedQuery(name = "SupplierUser.findByConfirmpassword", query = "SELECT s FROM SupplierUser s WHERE s.confirmpassword = :confirmpassword"),
    @NamedQuery(name = "SupplierUser.findBySupplierstatus", query = "SELECT s FROM SupplierUser s WHERE s.supplierstatus = :supplierstatus order by s.creationdate desc"),
    @NamedQuery(name = "SupplierUser.findByCreationdate", query = "SELECT s FROM SupplierUser s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierUser.findByUpdatedate", query = "SELECT s FROM SupplierUser s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierUser.findByUpdatedby", query = "SELECT s FROM SupplierUser s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierUser.findByCreatedby", query = "SELECT s FROM SupplierUser s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierUser.findByAf1", query = "SELECT s FROM SupplierUser s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierUser.findByAf2", query = "SELECT s FROM SupplierUser s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierUser.findByAf3", query = "SELECT s FROM SupplierUser s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierUser.findByAf4", query = "SELECT s FROM SupplierUser s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierUser.findByAf5", query = "SELECT s FROM SupplierUser s WHERE s.af5 = :af5"),
    @NamedQuery(name = "SupplierUser.findByGstnumber", query = "SELECT s FROM SupplierUser s WHERE s.gstnumber = :gstnumber"),
    @NamedQuery(name = "SupplierUser.findBySpocemailid", query = "SELECT s FROM SupplierUser s WHERE s.spocemailid = :spocemailid"),
    @NamedQuery(name = "SupplierUser.findBySpocname", query = "SELECT s FROM SupplierUser s WHERE s.spocname = :spocname"),
    @NamedQuery(name = "SupplierUser.findByGstState", query = "SELECT s FROM SupplierUser s WHERE s.gstState = :gstState")})
public class SupplierUser implements Serializable {
//    @OneToMany(mappedBy = "bpaasSupplieruserId")
//    private Collection<CustomerNotification> customerNotificationCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpaasSupplieruserId")
//    private Collection<SupplierGroup> supplierGroupCollection;
//    @OneToMany(mappedBy = "bpaasSupplieruserId")
//    private Collection<CustomerSubUser> customerSubUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpaasSupplieruserId")
    private Collection<CustomerDocument> customerDocumentCollection;
    @Size(max = 40)
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "bpaasSupplieruserId")
    private Collection<SupplierUserRegContactDetails> supplierUserRegContactDetailsCollection;
    @OneToMany(mappedBy = "bpaasSupplieruserId")
    private Collection<SupplierUserAttachment> supplierUserAttachmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasSupplierUserTableid")
    private Collection<SupplierSelection> supplierSelectionCollection;
    @Size(max = 15)
    @Column(name = "ownermobilenumber")
    private String ownermobilenumber;
    @OneToMany(mappedBy = "bpaasSupplieruserId")
    private Collection<SupplierBankDetailsNew> supplierBankDetailsNewCollection;
    @Size(max = 50)
    @Column(name = "staffstrength")
    private String staffstrength;
    @Size(max = 25)
    @Column(name = "gstnumber")
    private String gstnumber;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "companyname")
    private String companyname;
    @Size(max = 50)
    @Column(name = "typeofbusiness")
    private String typeofbusiness;
    @Size(max = 100)
    @Column(name = "registeredaddress")
    private String registeredaddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "website")
    private String website;
    @Size(max = 30)
    @Column(name = "ownername")
    private String ownername;
    @Size(max = 50)
    @Column(name = "owneremailid")
    private String owneremailid;
    @Size(max = 12)
    @Column(name = "companypannumber")
    private String companypannumber;
    @Size(max = 20)
    @Column(name = "pfregnumber")
    private String pfregnumber;
    @Size(max = 30)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 100)
    @Column(name = "confirmpassword")
    private String confirmpassword;
    @Size(max = 20)
    @Column(name = "supplierstatus")
    private String supplierstatus;
    @Lob
    @Size(max = 65535)
    @Column(name = "moreemailaddress")
    private String moreemailaddress;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 30)
    @Column(name = "updatedby")
    private String updatedby;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "forgotpassword")
    private String forgotpassword;
    @Size(max = 50)
    @Column(name = "spocemailid")
    private String spocemailid;
    @Size(max = 30)
    @Column(name = "spocname")
    private String spocname;
    @Size(max = 50)
    @Column(name = "gst_state")
    private String gstState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasSupplierUserTableid")
    private Collection<SupplierUserBankDetail> supplierUserBankDetailCollection;

    public SupplierUser() {
    }

    public SupplierUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTypeofbusiness() {
        return typeofbusiness;
    }

    public void setTypeofbusiness(String typeofbusiness) {
        this.typeofbusiness = typeofbusiness;
    }

    public String getRegisteredaddress() {
        return registeredaddress;
    }

    public void setRegisteredaddress(String registeredaddress) {
        this.registeredaddress = registeredaddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }


    public String getOwneremailid() {
        return owneremailid;
    }

    public void setOwneremailid(String owneremailid) {
        this.owneremailid = owneremailid;
    }

    public String getCompanypannumber() {
        return companypannumber;
    }

    public void setCompanypannumber(String companypannumber) {
        this.companypannumber = companypannumber;
    }

    public String getPfregnumber() {
        return pfregnumber;
    }

    public void setPfregnumber(String pfregnumber) {
        this.pfregnumber = pfregnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(String supplierstatus) {
        this.supplierstatus = supplierstatus;
    }

    public String getMoreemailaddress() {
        return moreemailaddress;
    }

    public void setMoreemailaddress(String moreemailaddress) {
        this.moreemailaddress = moreemailaddress;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
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


    public String getForgotpassword() {
        return forgotpassword;
    }

    public void setForgotpassword(String forgotpassword) {
        this.forgotpassword = forgotpassword;
    }

    public String getSpocemailid() {
        return spocemailid;
    }

    public void setSpocemailid(String spocemailid) {
        this.spocemailid = spocemailid;
    }

    public String getSpocname() {
        return spocname;
    }

    public void setSpocname(String spocname) {
        this.spocname = spocname;
    }

    public String getGstState() {
        return gstState;
    }

    public void setGstState(String gstState) {
        this.gstState = gstState;
    }

    @XmlTransient
    public Collection<SupplierUserBankDetail> getSupplierUserBankDetailCollection() {
        return supplierUserBankDetailCollection;
    }

    public void setSupplierUserBankDetailCollection(Collection<SupplierUserBankDetail> supplierUserBankDetailCollection) {
        this.supplierUserBankDetailCollection = supplierUserBankDetailCollection;
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
        if (!(object instanceof SupplierUser)) {
            return false;
        }
        SupplierUser other = (SupplierUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierUser[ id=" + id + " ]";
    }


    public String getGstnumber() {
        return gstnumber;
    }

    public void setGstnumber(String gstnumber) {
        this.gstnumber = gstnumber;
    }

    public String getStaffstrength() {
        return staffstrength;
    }

    public void setStaffstrength(String staffstrength) {
        this.staffstrength = staffstrength;
    }

    @XmlTransient
    public Collection<SupplierBankDetailsNew> getSupplierBankDetailsNewCollection() {
        return supplierBankDetailsNewCollection;
    }

    public void setSupplierBankDetailsNewCollection(Collection<SupplierBankDetailsNew> supplierBankDetailsNewCollection) {
        this.supplierBankDetailsNewCollection = supplierBankDetailsNewCollection;
    }

    public String getOwnermobilenumber() {
        return ownermobilenumber;
    }

    public void setOwnermobilenumber(String ownermobilenumber) {
        this.ownermobilenumber = ownermobilenumber;
    }

    @XmlTransient
    public Collection<SupplierSelection> getSupplierSelectionCollection() {
        return supplierSelectionCollection;
    }

    public void setSupplierSelectionCollection(Collection<SupplierSelection> supplierSelectionCollection) {
        this.supplierSelectionCollection = supplierSelectionCollection;
    }

    @XmlTransient
    public Collection<SupplierUserAttachment> getSupplierUserAttachmentCollection() {
        return supplierUserAttachmentCollection;
    }

    public void setSupplierUserAttachmentCollection(Collection<SupplierUserAttachment> supplierUserAttachmentCollection) {
        this.supplierUserAttachmentCollection = supplierUserAttachmentCollection;
    }

    @XmlTransient
    public Collection<SupplierUserRegContactDetails> getSupplierUserRegContactDetailsCollection() {
        return supplierUserRegContactDetailsCollection;
    }

    public void setSupplierUserRegContactDetailsCollection(Collection<SupplierUserRegContactDetails> supplierUserRegContactDetailsCollection) {
        this.supplierUserRegContactDetailsCollection = supplierUserRegContactDetailsCollection;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    public Collection<CustomerDocument> getCustomerDocumentCollection() {
        return customerDocumentCollection;
    }

    public void setCustomerDocumentCollection(Collection<CustomerDocument> customerDocumentCollection) {
        this.customerDocumentCollection = customerDocumentCollection;
    }

//    @XmlTransient
//    public Collection<CustomerSubUser> getCustomerSubUserCollection() {
//        return customerSubUserCollection;
//    }
//
//    public void setCustomerSubUserCollection(Collection<CustomerSubUser> customerSubUserCollection) {
//        this.customerSubUserCollection = customerSubUserCollection;
//    }

//    @XmlTransient
//    public Collection<SupplierGroup> getSupplierGroupCollection() {
//        return supplierGroupCollection;
//    }
//
//    public void setSupplierGroupCollection(Collection<SupplierGroup> supplierGroupCollection) {
//        this.supplierGroupCollection = supplierGroupCollection;
//    }

//    @XmlTransient
//    public Collection<CustomerNotification> getCustomerNotificationCollection() {
//        return customerNotificationCollection;
//    }
//
//    public void setCustomerNotificationCollection(Collection<CustomerNotification> customerNotificationCollection) {
//        this.customerNotificationCollection = customerNotificationCollection;
//    }
    
}
