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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ng_cp_customernotification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerNotification.findAll", query = "SELECT c FROM CustomerNotification c"),
    @NamedQuery(name = "CustomerNotification.findById", query = "SELECT c FROM CustomerNotification c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerNotification.findByStatus", query = "SELECT c FROM CustomerNotification c WHERE c.readstatus = :readstatus and c.commentby = 'Customer' order by c.commentdate desc"),
    @NamedQuery(name = "CustomerNotification.findByStatusAndCustomerId", query = "SELECT c FROM CustomerNotification c WHERE c.readstatus = 'true' and c.bpaasCustomersubuserId.id = :id and c.commentby = 'Admin' order by c.commentdate desc"),
    @NamedQuery(name = "CustomerNotification.findByCustomerUserId", query = "SELECT c FROM CustomerNotification c WHERE c.bpaasCustomersubuserId.id = :id order by c.commentdate desc"),
    @NamedQuery(name = "CustomerNotification.findByCustomerUserIdAndNoUpdateProfile", query = "SELECT c FROM CustomerNotification c WHERE c.bpaasCustomersubuserId.id = :id and c.isprofileupdate != 'Y' order by c.commentdate desc"),
    @NamedQuery(name = "CustomerNotification.findByCommentby", query = "SELECT c FROM CustomerNotification c WHERE c.commentby = :commentby")})
public class CustomerNotification implements Serializable {
    @Lob
    @Column(name = "attachment")
    private byte[] attachment;
    @JoinColumn(name = "ng_cp_customersubuser_admin", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser ngCpCustomersubuserAdmin;
    @Size(max = 30)
    @Column(name = "isprofileupdate")
    private String isprofileupdate;
    @Column(name = "readstatus")
    private String readstatus;
    @JoinColumn(name = "NG_CP_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;
    @Size(max = 30)
    @Column(name = "attachmentname")
    private String attachmentname;
    @Column(name = "commentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "notification")
    private String notification;
    @Size(max = 30)
    @Column(name = "commentby")
    private String commentby;
//    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
//    @ManyToOne
//    private SupplierUser bpaasSupplieruserId;

    public CustomerNotification() {
    }

    public CustomerNotification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getCommentby() {
        return commentby;
    }

    public void setCommentby(String commentby) {
        this.commentby = commentby;
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
        if (!(object instanceof CustomerNotification)) {
            return false;
        }
        CustomerNotification other = (CustomerNotification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerNotification[ id=" + id + " ]";
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }


    public String getAttachmentname() {
        return attachmentname;
    }

    public void setAttachmentname(String attachmentname) {
        this.attachmentname = attachmentname;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public CustomerSubUser getBpaasCustomersubuserId() {
        return bpaasCustomersubuserId;
    }

    public void setBpaasCustomersubuserId(CustomerSubUser bpaasCustomersubuserId) {
        this.bpaasCustomersubuserId = bpaasCustomersubuserId;
    }
    
    public String getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(String readstatus) {
        this.readstatus = readstatus;
    }
    
    public String getIsprofileupdate() {
        return isprofileupdate;
    }

    public void setIsprofileupdate(String isprofileupdate) {
        this.isprofileupdate = isprofileupdate;
    }
    
    public CustomerSubUser getNgCpCustomersubuserAdmin() {
        return ngCpCustomersubuserAdmin;
    }

    public void setNgCpCustomersubuserAdmin(CustomerSubUser ngCpCustomersubuserAdmin) {
        this.ngCpCustomersubuserAdmin = ngCpCustomersubuserAdmin;
    }
}
