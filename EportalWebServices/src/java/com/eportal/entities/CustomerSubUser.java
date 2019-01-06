/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "NG_CP_customersubuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSubUser.findAll", query = "SELECT c FROM CustomerSubUser c"),
    @NamedQuery(name = "CustomerSubUser.findById", query = "SELECT c FROM CustomerSubUser c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerSubUser.findByFisrtname", query = "SELECT c FROM CustomerSubUser c WHERE c.fisrtname = :fisrtname"),
    @NamedQuery(name = "CustomerSubUser.findByLastname", query = "SELECT c FROM CustomerSubUser c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "CustomerSubUser.findByUsername", query = "SELECT c FROM CustomerSubUser c WHERE c.username = :username"),
    @NamedQuery(name = "CustomerSubUser.findByUsernameAsActive", query = "SELECT c FROM CustomerSubUser c WHERE c.username = :username and c.status = 'Active'"),
    @NamedQuery(name = "CustomerSubUser.findByPassword", query = "SELECT c FROM CustomerSubUser c WHERE c.password = :password"),
    @NamedQuery(name = "CustomerSubUser.findByConfirmpassword", query = "SELECT c FROM CustomerSubUser c WHERE c.confirmpassword = :confirmpassword"),
    @NamedQuery(name = "CustomerSubUser.findByStatus", query = "SELECT c FROM CustomerSubUser c WHERE c.status != :status and (c.usertype is null or c.usertype != 'AutoEmailUser') order by c.creationdate desc"),
    @NamedQuery(name = "CustomerSubUser.findByStatusAsActive", query = "SELECT c FROM CustomerSubUser c WHERE c.status = :status and (c.usertype is null or c.usertype != 'AutoEmailUser') order by c.creationdate desc"),
    @NamedQuery(name = "CustomerSubUser.findByPhonenumber", query = "SELECT c FROM CustomerSubUser c WHERE c.phonenumber = :phonenumber"),
    @NamedQuery(name = "CustomerSubUser.findByCountry", query = "SELECT c FROM CustomerSubUser c WHERE c.country = :country"),
    @NamedQuery(name = "CustomerSubUser.findByCreationdate", query = "SELECT c FROM CustomerSubUser c WHERE c.creationdate = :creationdate"),
    @NamedQuery(name = "CustomerSubUser.findByCreatedby", query = "SELECT c FROM CustomerSubUser c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "CustomerSubUser.findByUpdatedate", query = "SELECT c FROM CustomerSubUser c WHERE c.updatedate = :updatedate"),
    @NamedQuery(name = "CustomerSubUser.findByCustomerSeededId", query = "SELECT c FROM CustomerSubUser c WHERE c.bpaasCustomerseededCid.cid = :cid and c.status != 'Delete' and (c.usertype is null or c.usertype != 'AutoEmailUser')"),
    @NamedQuery(name = "CustomerSubUser.findByCustomerSeededIdForAutoMail", query = "SELECT c FROM CustomerSubUser c WHERE c.bpaasCustomerseededCid.cid = :cid and c.status != 'Delete' order by c.creationdate desc"),
    @NamedQuery(name = "CustomerSubUser.findByUpdatedby", query = "SELECT c FROM CustomerSubUser c WHERE c.updatedby = :updatedby")})
public class CustomerSubUser implements Serializable {

    @Size(max = 100)
    @Column(name = "usertype")
    private String usertype;
    @Size(max = 50)
    @Column(name = "is_personal_info_updated")
    private String isPersonalInfoUpdated;
    @Size(max = 250)
    @Column(name = "personalfirstname")
    private String personalfirstname;
    @Size(max = 250)
    @Column(name = "personallastname")
    private String personallastname;
    @Size(max = 250)
    @Column(name = "personalemailid")
    private String personalemailid;
    @Size(max = 50)
    @Column(name = "personalcontactnumber")
    private String personalcontactnumber;
    @Lob
    @Column(name = "profilepic")
    private byte[] profilepic;
    @Size(max = 100)
    @Column(name = "emailid")
    private String emailid;
    @Size(max = 100)
    @Column(name = "addressline1")
    private String addressline1;
    @Size(max = 100)
    @Column(name = "addressline2")
    private String addressline2;
    @Size(max = 100)
    @Column(name = "addressline3")
    private String addressline3;
    @Size(max = 30)
    @Column(name = "is_password_updated")
    private String isPasswordUpdated;
    @Size(max = 50)
    @Column(name = "userauth")
    private String userauth;

    @JsonIgnore
    @OneToMany(mappedBy = "bpaasCustomersubuserId")
    private Collection<CustomerNotification> customerNotificationCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "bpaasCustomersubuserId")
    private Collection<SupplierGroup> supplierGroupCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "bpaasCustomersubuserId", fetch = FetchType.EAGER)
    private Collection<CustomerProjectMapping> customerProjectMappingCollection;
    @JoinColumn(name = "NG_CP_customerseeded_cid", referencedColumnName = "cid")
    @ManyToOne
    private CustomerSeeded bpaasCustomerseededCid;
//    @JoinColumn(name = "bpaas_projectseeded_pid", referencedColumnName = "pid")
//    @ManyToOne
//    private ProjectSeeded bpaasProjectseededPid;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "fisrtname")
    private String fisrtname;
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 100)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 100)
    @Column(name = "confirmpassword")
    private String confirmpassword;
    @Size(max = 30)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Size(max = 100)
    @Column(name = "country")
    private String country;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
//    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
//    @ManyToOne
//    private SupplierUser bpaasSupplieruserId;

    public CustomerSubUser() {
    }

    public CustomerSubUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFisrtname() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname = fisrtname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

//    public SupplierUser getBpaasSupplieruserId() {
//        return bpaasSupplieruserId;
//    }
//
//    public void setBpaasSupplieruserId(SupplierUser bpaasSupplieruserId) {
//        this.bpaasSupplieruserId = bpaasSupplieruserId;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSubUser)) {
            return false;
        }
        CustomerSubUser other = (CustomerSubUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerSubUser[ id=" + id + " ]";
    }

    public CustomerSeeded getBpaasCustomerseededCid() {
        return bpaasCustomerseededCid;
    }

    public void setBpaasCustomerseededCid(CustomerSeeded bpaasCustomerseededCid) {
        this.bpaasCustomerseededCid = bpaasCustomerseededCid;
    }

//    public ProjectSeeded getBpaasProjectseededPid() {
//        return bpaasProjectseededPid;
//    }
//
//    public void setBpaasProjectseededPid(ProjectSeeded bpaasProjectseededPid) {
//        this.bpaasProjectseededPid = bpaasProjectseededPid;
//    }
    @XmlTransient
    public Collection<CustomerProjectMapping> getCustomerProjectMappingCollection() {
        return customerProjectMappingCollection;
    }

    public void setCustomerProjectMappingCollection(Collection<CustomerProjectMapping> customerProjectMappingCollection) {
        this.customerProjectMappingCollection = customerProjectMappingCollection;
    }

    @XmlTransient
    public Collection<SupplierGroup> getSupplierGroupCollection() {
        return supplierGroupCollection;
    }

    public void setSupplierGroupCollection(Collection<SupplierGroup> supplierGroupCollection) {
        this.supplierGroupCollection = supplierGroupCollection;
    }

    @XmlTransient
    public Collection<CustomerNotification> getCustomerNotificationCollection() {
        return customerNotificationCollection;
    }

    public void setCustomerNotificationCollection(Collection<CustomerNotification> customerNotificationCollection) {
        this.customerNotificationCollection = customerNotificationCollection;
    }

    public String getUserauth() {
        return userauth;
    }

    public void setUserauth(String userauth) {
        this.userauth = userauth;
    }

    public String getIsPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setIsPasswordUpdated(String isPasswordUpdated) {
        this.isPasswordUpdated = isPasswordUpdated;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public byte[] getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic = profilepic;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPersonalfirstname() {
        return personalfirstname;
    }

    public void setPersonalfirstname(String personalfirstname) {
        this.personalfirstname = personalfirstname;
    }

    public String getPersonallastname() {
        return personallastname;
    }

    public void setPersonallastname(String personallastname) {
        this.personallastname = personallastname;
    }

    public String getPersonalemailid() {
        return personalemailid;
    }

    public void setPersonalemailid(String personalemailid) {
        this.personalemailid = personalemailid;
    }

    public String getPersonalcontactnumber() {
        return personalcontactnumber;
    }

    public void setPersonalcontactnumber(String personalcontactnumber) {
        this.personalcontactnumber = personalcontactnumber;
    }

    public String getIsPersonalInfoUpdated() {
        return isPersonalInfoUpdated;
    }

    public void setIsPersonalInfoUpdated(String isPersonalInfoUpdated) {
        this.isPersonalInfoUpdated = isPersonalInfoUpdated;
    }
    
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
