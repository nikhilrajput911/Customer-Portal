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
import javax.persistence.Lob;
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
@Table(name = "bpaas_workordercomment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkOrderComment.findAll", query = "SELECT w FROM WorkOrderComment w"),
    @NamedQuery(name = "WorkOrderComment.findById", query = "SELECT w FROM WorkOrderComment w WHERE w.id = :id"),
    @NamedQuery(name = "WorkOrderComment.findByApprovername", query = "SELECT w FROM WorkOrderComment w WHERE w.approvername = :approvername"),
    @NamedQuery(name = "WorkOrderComment.findByAction", query = "SELECT w FROM WorkOrderComment w WHERE w.action = :action")})
public class WorkOrderComment implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "approvername")
    private String approvername;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment")
    private String comment;
    @Size(max = 100)
    @Column(name = "action")
    private String action;
    @JoinColumn(name = "bpaas_workorderselectedapprover_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkOrderSelectedApprover bpaasWorkorderselectedapproverId;

    public WorkOrderComment() {
    }

    public WorkOrderComment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovername() {
        return approvername;
    }

    public void setApprovername(String approvername) {
        this.approvername = approvername;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public WorkOrderSelectedApprover getBpaasWorkorderselectedapproverId() {
        return bpaasWorkorderselectedapproverId;
    }

    public void setBpaasWorkorderselectedapproverId(WorkOrderSelectedApprover bpaasWorkorderselectedapproverId) {
        this.bpaasWorkorderselectedapproverId = bpaasWorkorderselectedapproverId;
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
        if (!(object instanceof WorkOrderComment)) {
            return false;
        }
        WorkOrderComment other = (WorkOrderComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.WorkOrderComment[ id=" + id + " ]";
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }
    
}
