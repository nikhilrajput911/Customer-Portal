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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ng_cp_customer_mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerMail.findAll", query = "SELECT c FROM CustomerMail c"),
    @NamedQuery(name = "CustomerMail.findById", query = "SELECT c FROM CustomerMail c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerMail.findByMailComposedOn", query = "SELECT c FROM CustomerMail c WHERE c.mailComposedOn = :mailComposedOn"),
    @NamedQuery(name = "CustomerMail.findByMailSentBy", query = "SELECT c FROM CustomerMail c WHERE c.mailSentBy = :mailSentBy"),
    @NamedQuery(name = "CustomerMail.findByFROMAddress", query = "SELECT c FROM CustomerMail c WHERE c.fROMAddress = :fROMAddress"),
    @NamedQuery(name = "CustomerMail.findByTOAddress", query = "SELECT c FROM CustomerMail c WHERE c.tOAddress = :tOAddress"),
    @NamedQuery(name = "CustomerMail.findByCc", query = "SELECT c FROM CustomerMail c WHERE c.cc = :cc"),
    @NamedQuery(name = "CustomerMail.findBySubject", query = "SELECT c FROM CustomerMail c WHERE c.subject = :subject"),
    @NamedQuery(name = "CustomerMail.findByMailBody", query = "SELECT c FROM CustomerMail c WHERE c.mailBody = :mailBody"),
    @NamedQuery(name = "CustomerMail.findByDocumentIndex", query = "SELECT c FROM CustomerMail c WHERE c.documentIndex = :documentIndex"),
    @NamedQuery(name = "CustomerMail.findByDocumentName", query = "SELECT c FROM CustomerMail c WHERE c.documentName = :documentName"),
    @NamedQuery(name = "CustomerMail.findByMailSentOn", query = "SELECT c FROM CustomerMail c WHERE c.mailSentOn = :mailSentOn"),
    @NamedQuery(name = "CustomerMail.findByMailSentFlag", query = "SELECT c FROM CustomerMail c WHERE c.mailSentFlag = :mailSentFlag")})
public class CustomerMail implements Serializable {
    @Size(max = 200)
    @Column(name = "CustomerName")
    private String customerName;
    @Size(max = 200)
    @Column(name = "ProjectName")
    private String projectName;
    @Size(max = 50)
    @Column(name = "MailType")
    private String mailType;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "MailComposedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mailComposedOn;
    @Size(max = 250)
    @Column(name = "MailSentBy")
    private String mailSentBy;
    @Size(max = 250)
    @Column(name = "FROM_Address")
    private String fROMAddress;
    @Size(max = 250)
    @Column(name = "TO_Address")
    private String tOAddress;
    @Size(max = 250)
    @Column(name = "CC")
    private String cc;
    @Size(max = 1000)
    @Column(name = "Subject")
    private String subject;
    @Size(max = 2000)
    @Column(name = "MailBody")
    private String mailBody;
    @Size(max = 500)
    @Column(name = "DocumentIndex")
    private String documentIndex;
    @Size(max = 1000)
    @Column(name = "DocumentName")
    private String documentName;
    @Column(name = "MailSentOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mailSentOn;
    @Size(max = 2)
    @Column(name = "MailSentFlag")
    private String mailSentFlag;

    public CustomerMail() {
    }

    public CustomerMail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMailComposedOn() {
        return mailComposedOn;
    }

    public void setMailComposedOn(Date mailComposedOn) {
        this.mailComposedOn = mailComposedOn;
    }

    public String getMailSentBy() {
        return mailSentBy;
    }

    public void setMailSentBy(String mailSentBy) {
        this.mailSentBy = mailSentBy;
    }

    public String getFROMAddress() {
        return fROMAddress;
    }

    public void setFROMAddress(String fROMAddress) {
        this.fROMAddress = fROMAddress;
    }

    public String getTOAddress() {
        return tOAddress;
    }

    public void setTOAddress(String tOAddress) {
        this.tOAddress = tOAddress;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getDocumentIndex() {
        return documentIndex;
    }

    public void setDocumentIndex(String documentIndex) {
        this.documentIndex = documentIndex;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Date getMailSentOn() {
        return mailSentOn;
    }

    public void setMailSentOn(Date mailSentOn) {
        this.mailSentOn = mailSentOn;
    }

    public String getMailSentFlag() {
        return mailSentFlag;
    }

    public void setMailSentFlag(String mailSentFlag) {
        this.mailSentFlag = mailSentFlag;
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
        if (!(object instanceof CustomerMail)) {
            return false;
        }
        CustomerMail other = (CustomerMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerMail[ id=" + id + " ]";
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
}
