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
 * @author RaphelTudu
 */
@Entity
@Table(name = "bpaas_workorderselectedapprover")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkOrderSelectedApprover.findAll", query = "SELECT w FROM WorkOrderSelectedApprover w"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findById", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.id = :id"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByApprovername1", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.approvername1 = :approvername1"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUsername", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE (w.username1 = :username or w.username2 = :username or w.username3 = :username or w.username4 = :username or w.username5 = :username) Order By w.creationdate desc"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByEmailid1", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.emailid1 = :emailid1"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUpdatedate", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.updatedate = :updatedate"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUpdatedby", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.updatedby = :updatedby"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByCreationdate", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.creationdate = :creationdate"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByCreatedby", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.createdby = :createdby"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByHeaderId", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAf1", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.af1 = :af1"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAf2", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.af2 = :af2"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAf3", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.af3 = :af3"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAf4", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.af4 = :af4"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAF5t", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.aF5t = :aF5t"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByApprovername2", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.approvername2 = :approvername2"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByEmail2", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.email2 = :email2"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByApprovername3", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.approvername3 = :approvername3"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByEmail3", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.email3 = :email3"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByApprovername4", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.approvername4 = :approvername4"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByEmail4", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.email4 = :email4"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByApprovername5", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.approvername5 = :approvername5"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByEmail5", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.email5 = :email5"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUsername2", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.username2 = :username2"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUsername3", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.username3 = :username3"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAssignedToUser", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.assignedTo = :assignedToUsername order by w.bPaasWorkOrderRFQHeaderRFQID desc"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByAssignedTo", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.assignedTo = :assignedTo or (w.username1 = :assignedTo or w.username2 = :assignedTo or w.username3 = :assignedTo or w.username4 = :assignedTo or w.username5 = :assignedTo) Order By w.updatedate desc"),
    @NamedQuery(name = "WorkOrderSelectedApprover.findByUsername5", query = "SELECT w FROM WorkOrderSelectedApprover w WHERE w.username5 = :username5")})
public class WorkOrderSelectedApprover implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpaasWorkorderselectedapproverId")
    private Collection<WorkOrderComment> workOrderCommentCollection;
    @Size(max = 100)
    @Column(name = "revisionsequence")
    private String revisionsequence;
    @Size(max = 100)
    @Column(name = "approvalsequence")
    private String approvalsequence;
    @Size(max = 50)
    @Column(name = "assigned_to")
    private String assignedTo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "approvername1")
    private String approvername1;
    @Size(max = 30)
    @Column(name = "username1")
    private String username1;
    @Size(max = 50)
    @Column(name = "emailid1")
    private String emailid1;
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
    @Column(name = "AF5t")
    private Integer aF5t;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment1")
    private String comment1;
    @Size(max = 50)
    @Column(name = "approvername2")
    private String approvername2;
    @Size(max = 100)
    @Column(name = "email2")
    private String email2;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment2")
    private String comment2;
    @Size(max = 50)
    @Column(name = "approvername3")
    private String approvername3;
    @Size(max = 100)
    @Column(name = "email3")
    private String email3;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment3")
    private String comment3;
    @Size(max = 50)
    @Column(name = "approvername4")
    private String approvername4;
    @Size(max = 100)
    @Column(name = "email4")
    private String email4;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment4")
    private String comment4;
    @Size(max = 50)
    @Column(name = "approvername5")
    private String approvername5;
    @Size(max = 100)
    @Column(name = "email5")
    private String email5;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment5")
    private String comment5;
    @Size(max = 50)
    @Column(name = "username2")
    private String username2;
    @Size(max = 50)
    @Column(name = "username3")
    private String username3;
    @Size(max = 50)
    @Column(name = "username4")
    private String username4;
    @Size(max = 50)
    @Column(name = "username5")
    private String username5;
    @JoinColumn(name = "BPaas_WorkOrderRFQHeader_RFQID", referencedColumnName = "RFQID")
    @ManyToOne(optional = false)
    private WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID;
    @JoinColumn(name = "BPaas_UserDetail_userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Userdetail bPaasUserDetailuserid;

    public WorkOrderSelectedApprover() {
    }

    public WorkOrderSelectedApprover(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovername1() {
        return approvername1;
    }

    public void setApprovername1(String approvername1) {
        this.approvername1 = approvername1;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getEmailid1() {
        return emailid1;
    }

    public void setEmailid1(String emailid1) {
        this.emailid1 = emailid1;
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

    public Integer getAF5t() {
        return aF5t;
    }

    public void setAF5t(Integer aF5t) {
        this.aF5t = aF5t;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getApprovername2() {
        return approvername2;
    }

    public void setApprovername2(String approvername2) {
        this.approvername2 = approvername2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getApprovername3() {
        return approvername3;
    }

    public void setApprovername3(String approvername3) {
        this.approvername3 = approvername3;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getApprovername4() {
        return approvername4;
    }

    public void setApprovername4(String approvername4) {
        this.approvername4 = approvername4;
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getApprovername5() {
        return approvername5;
    }

    public void setApprovername5(String approvername5) {
        this.approvername5 = approvername5;
    }

    public String getEmail5() {
        return email5;
    }

    public void setEmail5(String email5) {
        this.email5 = email5;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getUsername3() {
        return username3;
    }

    public void setUsername3(String username3) {
        this.username3 = username3;
    }

    public String getUsername4() {
        return username4;
    }

    public void setUsername4(String username4) {
        this.username4 = username4;
    }

    public String getUsername5() {
        return username5;
    }

    public void setUsername5(String username5) {
        this.username5 = username5;
    }

    public WorkOrderRfqHeader getBPaasWorkOrderRFQHeaderRFQID() {
        return bPaasWorkOrderRFQHeaderRFQID;
    }

    public void setBPaasWorkOrderRFQHeaderRFQID(WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID) {
        this.bPaasWorkOrderRFQHeaderRFQID = bPaasWorkOrderRFQHeaderRFQID;
    }

    public Userdetail getBPaasUserDetailuserid() {
        return bPaasUserDetailuserid;
    }

    public void setBPaasUserDetailuserid(Userdetail bPaasUserDetailuserid) {
        this.bPaasUserDetailuserid = bPaasUserDetailuserid;
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
        if (!(object instanceof WorkOrderSelectedApprover)) {
            return false;
        }
        WorkOrderSelectedApprover other = (WorkOrderSelectedApprover) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.WorkOrderSelectedApprover[ id=" + id + " ]";
    }

    public String getApprovalsequence() {
        return approvalsequence;
    }

    public void setApprovalsequence(String approvalsequence) {
        this.approvalsequence = approvalsequence;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getRevisionsequence() {
        return revisionsequence;
    }

    public void setRevisionsequence(String revisionsequence) {
        this.revisionsequence = revisionsequence;
    }

    @XmlTransient
    public Collection<WorkOrderComment> getWorkOrderCommentCollection() {
        return workOrderCommentCollection;
    }

    public void setWorkOrderCommentCollection(Collection<WorkOrderComment> workOrderCommentCollection) {
        this.workOrderCommentCollection = workOrderCommentCollection;
    }
    
}
