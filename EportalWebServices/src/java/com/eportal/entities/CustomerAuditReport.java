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
@Table(name = "ng_cp_auditreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAuditReport.findAll", query = "SELECT c FROM CustomerAuditReport c"),
    @NamedQuery(name = "CustomerAuditReport.findById", query = "SELECT c FROM CustomerAuditReport c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerAuditReport.findByDocumentname", query = "SELECT c FROM CustomerAuditReport c WHERE c.documentname = :documentname"),
    @NamedQuery(name = "CustomerAuditReport.findByDocumenttype", query = "SELECT c FROM CustomerAuditReport c WHERE c.documenttype = :documenttype"),
    @NamedQuery(name = "CustomerAuditReport.findByDocumentindex", query = "SELECT c FROM CustomerAuditReport c WHERE c.documentindex = :documentindex"),
    @NamedQuery(name = "CustomerAuditReport.findByFromDateAndToDateAndCustomer", query = "SELECT c FROM CustomerAuditReport c WHERE c.downloaddate BETWEEN :fromDate AND :toDate and c.ngCpCustomerseededId.customername = :customername order by c.downloaddate desc"),
    @NamedQuery(name = "CustomerAuditReport.findByFromDateAndToDate", query = "SELECT c FROM CustomerAuditReport c WHERE c.downloaddate BETWEEN :fromDate AND :toDate order by c.downloaddate desc"),
    @NamedQuery(name = "CustomerAuditReport.findByDownloaddate", query = "SELECT c FROM CustomerAuditReport c WHERE c.downloaddate = :downloaddate")})
public class CustomerAuditReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "ng_cp_customerseeded_id", referencedColumnName = "cid")
    @ManyToOne
    private CustomerSeeded ngCpCustomerseededId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "Documentname")
    private String documentname;
    @Size(max = 100)
    @Column(name = "Documenttype")
    private String documenttype;
    @Size(max = 50)
    @Column(name = "Documentindex")
    private String documentindex;
    @Column(name = "Downloaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date downloaddate;
    @JoinColumn(name = "ng_cp_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;

    public CustomerAuditReport() {
    }

    public CustomerAuditReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getDocumentindex() {
        return documentindex;
    }

    public void setDocumentindex(String documentindex) {
        this.documentindex = documentindex;
    }

    public Date getDownloaddate() {
        return downloaddate;
    }

    public void setDownloaddate(Date downloaddate) {
        this.downloaddate = downloaddate;
    }

    public CustomerSubUser getBpaasCustomersubuserId() {
        return bpaasCustomersubuserId;
    }

    public void setBpaasCustomersubuserId(CustomerSubUser bpaasCustomersubuserId) {
        this.bpaasCustomersubuserId = bpaasCustomersubuserId;
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
        if (!(object instanceof CustomerAuditReport)) {
            return false;
        }
        CustomerAuditReport other = (CustomerAuditReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerAuditReport[ id=" + id + " ]";
    }
    
    public CustomerSeeded getNgCpCustomerseededId() {
        return ngCpCustomerseededId;
    }

    public void setNgCpCustomerseededId(CustomerSeeded ngCpCustomerseededId) {
        this.ngCpCustomerseededId = ngCpCustomerseededId;
    }
}
