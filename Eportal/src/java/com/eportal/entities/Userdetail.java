/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
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
 * @author RaphelTudu
 */
@Entity
@Table(name = "bpaas_userdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdetail.findAll", query = "SELECT u FROM Userdetail u"),
    @NamedQuery(name = "Userdetail.findByUserid", query = "SELECT u FROM Userdetail u WHERE u.userid = :userid"),
    @NamedQuery(name = "Userdetail.findByFirstname", query = "SELECT u FROM Userdetail u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userdetail.findByLastname", query = "SELECT u FROM Userdetail u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Userdetail.findByUsername", query = "SELECT u FROM Userdetail u WHERE u.username = :username and u.password = :password"),
    @NamedQuery(name = "Userdetail.findByPassword", query = "SELECT u FROM Userdetail u WHERE u.password = :password"),
    @NamedQuery(name = "Userdetail.findByConfirmpassword", query = "SELECT u FROM Userdetail u WHERE u.confirmpassword = :confirmpassword"),
    @NamedQuery(name = "Userdetail.findByCountry", query = "SELECT u FROM Userdetail u WHERE u.country = :country"),
    @NamedQuery(name = "Userdetail.findByWorkemailid", query = "SELECT u FROM Userdetail u WHERE u.workemailid = :workemailid"),
    @NamedQuery(name = "Userdetail.findByCreationdate", query = "SELECT u FROM Userdetail u WHERE u.creationdate = :creationdate"),
    @NamedQuery(name = "Userdetail.findByUpdatedate", query = "SELECT u FROM Userdetail u WHERE u.updatedate = :updatedate"),
    @NamedQuery(name = "Userdetail.findByUsertype", query = "SELECT u FROM Userdetail u WHERE u.usertype = :usertype"),
    @NamedQuery(name = "Userdetail.findByUserstatus", query = "SELECT u FROM Userdetail u WHERE u.userstatus = :userstatus"),
    @NamedQuery(name = "Userdetail.findByUserroleSystemaccess", query = "SELECT u FROM Userdetail u WHERE u.userroleSystemaccess = :userroleSystemaccess"),
    @NamedQuery(name = "Userdetail.findByUpdatedby", query = "SELECT u FROM Userdetail u WHERE u.updatedby = :updatedby"),
    @NamedQuery(name = "Userdetail.findByCreatedby", query = "SELECT u FROM Userdetail u WHERE u.createdby = :createdby"),
    @NamedQuery(name = "Userdetail.findBySupervisorname", query = "SELECT u FROM Userdetail u WHERE u.supervisorname = :supervisorname"),
    @NamedQuery(name = "Userdetail.findBySupervisoremailid", query = "SELECT u FROM Userdetail u WHERE u.supervisoremailid = :supervisoremailid"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield1", query = "SELECT u FROM Userdetail u WHERE u.additionalfield1 = :additionalfield1"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield2", query = "SELECT u FROM Userdetail u WHERE u.additionalfield2 = :additionalfield2"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield3", query = "SELECT u FROM Userdetail u WHERE u.additionalfield3 = :additionalfield3"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield4", query = "SELECT u FROM Userdetail u WHERE u.additionalfield4 = :additionalfield4"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield5", query = "SELECT u FROM Userdetail u WHERE u.additionalfield5 = :additionalfield5"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield6", query = "SELECT u FROM Userdetail u WHERE u.additionalfield6 = :additionalfield6"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield7", query = "SELECT u FROM Userdetail u WHERE u.additionalfield7 = :additionalfield7"),
    @NamedQuery(name = "Userdetail.findByAdditionalfield8", query = "SELECT u FROM Userdetail u WHERE u.additionalfield8 = :additionalfield8")})
public class Userdetail implements Serializable {
    @Size(max = 50)
    @Column(name = "enabled")
    private String enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasUserDetailuserid")
    private Collection<QmAnswer> qmAnswerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasUserDetailuserid")
    private Collection<WorkOrderSelectedApprover> workOrderSelectedApproverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetailuserid")
    private Collection<WorkOrderRfqHeader> workOrderRfqHeaderCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Size(max = 20)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 20)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 30)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 30)
    @Column(name = "confirmpassword")
    private String confirmpassword;
    @Size(max = 30)
    @Column(name = "country")
    private String country;
    @Size(max = 50)
    @Column(name = "workemailid")
    private String workemailid;
    @Lob
    @Size(max = 65535)
    @Column(name = "contactnumber")
    private String contactnumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "address")
    private String address;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 20)
    @Column(name = "usertype")
    private String usertype;
    @Size(max = 10)
    @Column(name = "userstatus")
    private String userstatus;
    @Size(max = 20)
    @Column(name = "userrole_systemaccess")
    private String userroleSystemaccess;
    @Size(max = 30)
    @Column(name = "updatedby")
    private String updatedby;
    @Size(max = 30)
    @Column(name = "createdby")
    private String createdby;
    @Size(max = 30)
    @Column(name = "supervisorname")
    private String supervisorname;
    @Size(max = 50)
    @Column(name = "supervisoremailid")
    private String supervisoremailid;
    @Column(name = "additionalfield1")
    private Integer additionalfield1;
    @Column(name = "additionalfield2")
    private Integer additionalfield2;
    @Column(name = "additionalfield3")
    private Integer additionalfield3;
    @Column(name = "additionalfield4")
    private Integer additionalfield4;
    @Column(name = "additionalfield5")
    private Integer additionalfield5;
    @Column(name = "additionalfield6")
    private Integer additionalfield6;
    @Column(name = "additionalfield7")
    private Integer additionalfield7;
    @Column(name = "additionalfield8")
    private Integer additionalfield8;

    public Userdetail() {
    }

    public Userdetail(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWorkemailid() {
        return workemailid;
    }

    public void setWorkemailid(String workemailid) {
        this.workemailid = workemailid;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public String getUserroleSystemaccess() {
        return userroleSystemaccess;
    }

    public void setUserroleSystemaccess(String userroleSystemaccess) {
        this.userroleSystemaccess = userroleSystemaccess;
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

    public String getSupervisorname() {
        return supervisorname;
    }

    public void setSupervisorname(String supervisorname) {
        this.supervisorname = supervisorname;
    }

    public String getSupervisoremailid() {
        return supervisoremailid;
    }

    public void setSupervisoremailid(String supervisoremailid) {
        this.supervisoremailid = supervisoremailid;
    }

    public Integer getAdditionalfield1() {
        return additionalfield1;
    }

    public void setAdditionalfield1(Integer additionalfield1) {
        this.additionalfield1 = additionalfield1;
    }

    public Integer getAdditionalfield2() {
        return additionalfield2;
    }

    public void setAdditionalfield2(Integer additionalfield2) {
        this.additionalfield2 = additionalfield2;
    }

    public Integer getAdditionalfield3() {
        return additionalfield3;
    }

    public void setAdditionalfield3(Integer additionalfield3) {
        this.additionalfield3 = additionalfield3;
    }

    public Integer getAdditionalfield4() {
        return additionalfield4;
    }

    public void setAdditionalfield4(Integer additionalfield4) {
        this.additionalfield4 = additionalfield4;
    }

    public Integer getAdditionalfield5() {
        return additionalfield5;
    }

    public void setAdditionalfield5(Integer additionalfield5) {
        this.additionalfield5 = additionalfield5;
    }

    public Integer getAdditionalfield6() {
        return additionalfield6;
    }

    public void setAdditionalfield6(Integer additionalfield6) {
        this.additionalfield6 = additionalfield6;
    }

    public Integer getAdditionalfield7() {
        return additionalfield7;
    }

    public void setAdditionalfield7(Integer additionalfield7) {
        this.additionalfield7 = additionalfield7;
    }

    public Integer getAdditionalfield8() {
        return additionalfield8;
    }

    public void setAdditionalfield8(Integer additionalfield8) {
        this.additionalfield8 = additionalfield8;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdetail)) {
            return false;
        }
        Userdetail other = (Userdetail) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.Userdetail[ userid=" + userid + " ]";
    }

    @XmlTransient
    public Collection<WorkOrderRfqHeader> getWorkOrderRfqHeaderCollection() {
        return workOrderRfqHeaderCollection;
    }

    public void setWorkOrderRfqHeaderCollection(Collection<WorkOrderRfqHeader> workOrderRfqHeaderCollection) {
        this.workOrderRfqHeaderCollection = workOrderRfqHeaderCollection;
    }

    @XmlTransient
    public Collection<WorkOrderSelectedApprover> getWorkOrderSelectedApproverCollection() {
        return workOrderSelectedApproverCollection;
    }

    public void setWorkOrderSelectedApproverCollection(Collection<WorkOrderSelectedApprover> workOrderSelectedApproverCollection) {
        this.workOrderSelectedApproverCollection = workOrderSelectedApproverCollection;
    }

    @XmlTransient
    public Collection<QmAnswer> getQmAnswerCollection() {
        return qmAnswerCollection;
    }

    public void setQmAnswerCollection(Collection<QmAnswer> qmAnswerCollection) {
        this.qmAnswerCollection = qmAnswerCollection;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    
}
