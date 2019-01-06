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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "bpaas_usersecseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSecSeeded.findAll", query = "SELECT b FROM UserSecSeeded b"),
    @NamedQuery(name = "UserSecSeeded.findByUserssid", query = "SELECT b FROM UserSecSeeded b WHERE b.userssid = :userssid"),
    @NamedQuery(name = "UserSecSeeded.findBySecurityquestions", query = "SELECT b FROM UserSecSeeded b WHERE b.securityquestions = :securityquestions"),
    @NamedQuery(name = "UserSecSeeded.findByUpdatedate", query = "SELECT b FROM UserSecSeeded b WHERE b.updatedate = :updatedate"),
    @NamedQuery(name = "UserSecSeeded.findByUpdatedby", query = "SELECT b FROM UserSecSeeded b WHERE b.updatedby = :updatedby"),
    @NamedQuery(name = "UserSecSeeded.findByCreationdate", query = "SELECT b FROM UserSecSeeded b WHERE b.creationdate = :creationdate"),
    @NamedQuery(name = "UserSecSeeded.findByCreatedby", query = "SELECT b FROM UserSecSeeded b WHERE b.createdby = :createdby"),
    @NamedQuery(name = "UserSecSeeded.findByAf1", query = "SELECT b FROM UserSecSeeded b WHERE b.af1 = :af1"),
    @NamedQuery(name = "UserSecSeeded.findByAf2", query = "SELECT b FROM UserSecSeeded b WHERE b.af2 = :af2"),
    @NamedQuery(name = "UserSecSeeded.findByAf3", query = "SELECT b FROM UserSecSeeded b WHERE b.af3 = :af3"),
    @NamedQuery(name = "UserSecSeeded.findByAf4", query = "SELECT b FROM UserSecSeeded b WHERE b.af4 = :af4"),
    @NamedQuery(name = "UserSecSeeded.findByAf5", query = "SELECT b FROM UserSecSeeded b WHERE b.af5 = :af5"),
    @NamedQuery(name = "UserSecSeeded.findByStatus", query = "SELECT b FROM UserSecSeeded b WHERE b.status = :status")})
public class UserSecSeeded implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userssid")
    private Integer userssid;
    @Size(max = 100)
    @Column(name = "securityquestions")
    private String securityquestions;
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
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    public UserSecSeeded() {
    }

    public UserSecSeeded(Integer userssid) {
        this.userssid = userssid;
    }

    public Integer getUserssid() {
        return userssid;
    }

    public void setUserssid(Integer userssid) {
        this.userssid = userssid;
    }

    public String getSecurityquestions() {
        return securityquestions;
    }

    public void setSecurityquestions(String securityquestions) {
        this.securityquestions = securityquestions;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userssid != null ? userssid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSecSeeded)) {
            return false;
        }
        UserSecSeeded other = (UserSecSeeded) object;
        if ((this.userssid == null && other.userssid != null) || (this.userssid != null && !this.userssid.equals(other.userssid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.BpaasUsersecseeded[ userssid=" + userssid + " ]";
    }
    
}
