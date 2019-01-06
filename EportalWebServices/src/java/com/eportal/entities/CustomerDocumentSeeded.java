/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "NG_CP_documentseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerDocumentSeeded.findAll", query = "SELECT c FROM CustomerDocumentSeeded c"),
    @NamedQuery(name = "CustomerDocumentSeeded.findByDocid", query = "SELECT c FROM CustomerDocumentSeeded c WHERE c.docid = :docid"),
    @NamedQuery(name = "CustomerDocumentSeeded.findByDocumentname", query = "SELECT c FROM CustomerDocumentSeeded c WHERE c.documentname = :documentname")})
public class CustomerDocumentSeeded implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docid")
    private Integer docid;
    @Size(max = 50)
    @Column(name = "documentname")
    private String documentname;
    @Size(max = 250)
    @Column(name = "documentAlias")
    private String documentAlias;

    public String getDocumentAlias() {
        return documentAlias;
    }

    public void setDocumentAlias(String documentAlias) {
        this.documentAlias = documentAlias;
    }
    
    public CustomerDocumentSeeded() {
    }

    public CustomerDocumentSeeded(Integer docid) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docid != null ? docid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerDocumentSeeded)) {
            return false;
        }
        CustomerDocumentSeeded other = (CustomerDocumentSeeded) object;
        if ((this.docid == null && other.docid != null) || (this.docid != null && !this.docid.equals(other.docid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerDocumentSeeded[ docid=" + docid + " ]";
    }
    
}
