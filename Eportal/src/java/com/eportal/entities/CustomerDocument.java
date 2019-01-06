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
@Table(name = "NG_CP_customerdocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerDocument.findAll", query = "SELECT c FROM CustomerDocument c"),
    @NamedQuery(name = "CustomerDocument.findByDocid", query = "SELECT c FROM CustomerDocument c WHERE c.docid = :docid"),
    @NamedQuery(name = "CustomerDocument.findByDocumentname", query = "SELECT c FROM CustomerDocument c WHERE c.documentname = :documentname"),
    @NamedQuery(name = "CustomerDocument.findByDocumenttype", query = "SELECT c FROM CustomerDocument c WHERE c.documenttype = :documenttype"),
    @NamedQuery(name = "CustomerDocument.findByUpdatedby", query = "SELECT c FROM CustomerDocument c WHERE c.updatedby = :updatedby"),
    @NamedQuery(name = "CustomerDocument.findByUpdatedate", query = "SELECT c FROM CustomerDocument c WHERE c.updatedate = :updatedate"),
    @NamedQuery(name = "CustomerDocument.findByCreatedby", query = "SELECT c FROM CustomerDocument c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "CustomerDocument.findByByDocType", query = "SELECT c FROM CustomerDocument c WHERE c.documenttype = :documenttype"),
    @NamedQuery(name = "CustomerDocument.findByCreationdate", query = "SELECT c FROM CustomerDocument c WHERE c.creationdate = :creationdate")})
public class CustomerDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docid")
    private Integer docid;
    @Size(max = 50)
    @Column(name = "documentname")
    private String documentname;
    @Size(max = 50)
    @Column(name = "documenttype")
    private String documenttype;
    @Lob
    @Column(name = "document")
    private byte[] document;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierUser bpaasSupplieruserId;

    public CustomerDocument() {
    }

    public CustomerDocument(Integer docid) {
        this.docid = docid;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
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

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public SupplierUser getBpaasSupplieruserId() {
        return bpaasSupplieruserId;
    }

    public void setBpaasSupplieruserId(SupplierUser bpaasSupplieruserId) {
        this.bpaasSupplieruserId = bpaasSupplieruserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docid != null ? docid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerDocument)) {
            return false;
        }
        CustomerDocument other = (CustomerDocument) object;
        if ((this.docid == null && other.docid != null) || (this.docid != null && !this.docid.equals(other.docid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerDocument[ docid=" + docid + " ]";
    }
    
}
