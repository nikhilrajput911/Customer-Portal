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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RaphelTudu
 */
@Entity
@Table(name = "bpaas_usersecurityquestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSecurityQuestion.findAll", query = "SELECT u FROM UserSecurityQuestion u"),
    @NamedQuery(name = "UserSecurityQuestion.findByUsrsecid", query = "SELECT u FROM UserSecurityQuestion u WHERE u.usrsecid = :usrsecid"),
    @NamedQuery(name = "UserSecurityQuestion.findByUpdatedate", query = "SELECT u FROM UserSecurityQuestion u WHERE u.updatedate = :updatedate"),
    @NamedQuery(name = "UserSecurityQuestion.findByUpdatedby", query = "SELECT u FROM UserSecurityQuestion u WHERE u.updatedby = :updatedby"),
    @NamedQuery(name = "UserSecurityQuestion.findByCreationdate", query = "SELECT u FROM UserSecurityQuestion u WHERE u.creationdate = :creationdate"),
    @NamedQuery(name = "UserSecurityQuestion.findByCreatedby", query = "SELECT u FROM UserSecurityQuestion u WHERE u.createdby = :createdby"),
    @NamedQuery(name = "UserSecurityQuestion.findByAf1", query = "SELECT u FROM UserSecurityQuestion u WHERE u.af1 = :af1"),
    @NamedQuery(name = "UserSecurityQuestion.findByAf2", query = "SELECT u FROM UserSecurityQuestion u WHERE u.af2 = :af2"),
    @NamedQuery(name = "UserSecurityQuestion.findByAf3", query = "SELECT u FROM UserSecurityQuestion u WHERE u.af3 = :af3"),
    @NamedQuery(name = "UserSecurityQuestion.findByAf4", query = "SELECT u FROM UserSecurityQuestion u WHERE u.af4 = :af4"),
    @NamedQuery(name = "UserSecurityQuestion.findByAf5", query = "SELECT u FROM UserSecurityQuestion u WHERE u.af5 = :af5")})
public class UserSecurityQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usrsecid")
    private Integer usrsecid;
    @Lob
    @Size(max = 65535)
    @Column(name = "securityquestion1")
    private String securityquestion1;
    @Lob
    @Size(max = 65535)
    @Column(name = "securityanswer1")
    private String securityanswer1;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "securityquestion2")
    private String securityquestion2;
    @Lob
    @Size(max = 65535)
    @Column(name = "securityquestion3")
    private String securityquestion3;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer2")
    private String answer2;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer3")
    private String answer3;
    @Column(name = "UserDetail_userid")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public UserSecurityQuestion() {
    }

    public UserSecurityQuestion(Integer usrsecid) {
        this.usrsecid = usrsecid;
    }

    public Integer getUsrsecid() {
        return usrsecid;
    }

    public void setUsrsecid(Integer usrsecid) {
        this.usrsecid = usrsecid;
    }

    public String getSecurityquestion1() {
        return securityquestion1;
    }

    public void setSecurityquestion1(String securityquestion1) {
        this.securityquestion1 = securityquestion1;
    }

    public String getSecurityanswer1() {
        return securityanswer1;
    }

    public void setSecurityanswer1(String securityanswer1) {
        this.securityanswer1 = securityanswer1;
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

    public String getSecurityquestion2() {
        return securityquestion2;
    }

    public void setSecurityquestion2(String securityquestion2) {
        this.securityquestion2 = securityquestion2;
    }

    public String getSecurityquestion3() {
        return securityquestion3;
    }

    public void setSecurityquestion3(String securityquestion3) {
        this.securityquestion3 = securityquestion3;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrsecid != null ? usrsecid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSecurityQuestion)) {
            return false;
        }
        UserSecurityQuestion other = (UserSecurityQuestion) object;
        if ((this.usrsecid == null && other.usrsecid != null) || (this.usrsecid != null && !this.usrsecid.equals(other.usrsecid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.UserSecurityQuestion[ usrsecid=" + usrsecid + " ]";
    }
    
}
