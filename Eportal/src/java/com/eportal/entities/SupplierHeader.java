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
import javax.persistence.FetchType;
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
 * @author admin
 */
@Entity
@Table(name = "bpaas_wosupplierheader")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierHeader.findAll", query = "SELECT s FROM SupplierHeader s"),
    @NamedQuery(name = "SupplierHeader.findById", query = "SELECT s FROM SupplierHeader s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierHeader.findByCreationdate", query = "SELECT s FROM SupplierHeader s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierHeader.findByCreatedby", query = "SELECT s FROM SupplierHeader s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierHeader.findByUpdatedby", query = "SELECT s FROM SupplierHeader s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierHeader.findByUpdatedate", query = "SELECT s FROM SupplierHeader s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierHeader.findByAf1", query = "SELECT s FROM SupplierHeader s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierHeader.findByAf2", query = "SELECT s FROM SupplierHeader s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierHeader.findByAf3", query = "SELECT s FROM SupplierHeader s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierHeader.findByAf4", query = "SELECT s FROM SupplierHeader s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierHeader.findByAf5", query = "SELECT s FROM SupplierHeader s WHERE s.af5 = :af5"),
    @NamedQuery(name = "SupplierHeader.findByAf6", query = "SELECT s FROM SupplierHeader s WHERE s.af6 = :af6"),
    @NamedQuery(name = "SupplierHeader.findBySupplierAndRfqId", query = "SELECT s FROM SupplierHeader s WHERE s.bpaasWorkorderrfqheaderRfqid.rfqid = :rfqid and s.bPaasSupplierSelectionTableid.bPaasSupplierUserTableid.id = :supplierId"),
    @NamedQuery(name = "SupplierHeader.findByWorkOrderRfqHeaderId", query = "SELECT s FROM SupplierHeader s WHERE s.bpaasWorkorderrfqheaderRfqid.rfqid = :rfqid"),
    @NamedQuery(name = "SupplierHeader.findByAf8", query = "SELECT s FROM SupplierHeader s WHERE s.af8 = :af8")})
public class SupplierHeader implements Serializable {
    @JoinColumn(name = "bpaas_workorderrfqheader_rfqid", referencedColumnName = "RFQID")
    @ManyToOne(optional = false)
    private WorkOrderRfqHeader bpaasWorkorderrfqheaderRfqid;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "notetobuyer")
    private String notetobuyer;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 30)
    @Column(name = "createdby")
    private String createdby;
    @Size(max = 30)
    @Column(name = "updatedby")
    private String updatedby;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
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
    @Column(name = "AF6")
    private Integer af6;
    @Column(name = "AF7")
    private Integer af7;
    @Column(name = "AF8")
    private Integer af8;
    @JoinColumn(name = "BPaas_SupplierSelectionTable_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SupplierSelection bPaasSupplierSelectionTableid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workOrderSupplierHeaderTableid", fetch = FetchType.EAGER)
    private Collection<SupplierLineitem> supplierLineitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWOSupplierHeaderid")
    private Collection<SupplierAddattachment> supplierAddattachmentCollection;

    public SupplierHeader() {
    }

    public SupplierHeader(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotetobuyer() {
        return notetobuyer;
    }

    public void setNotetobuyer(String notetobuyer) {
        this.notetobuyer = notetobuyer;
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

    public Integer getAf6() {
        return af6;
    }

    public void setAf6(Integer af6) {
        this.af6 = af6;
    }

    public Integer getAf7() {
        return af7;
    }

    public void setAf7(Integer af7) {
        this.af7 = af7;
    }

    public Integer getAf8() {
        return af8;
    }

    public void setAf8(Integer af8) {
        this.af8 = af8;
    }

    public SupplierSelection getBPaasSupplierSelectionTableid() {
        return bPaasSupplierSelectionTableid;
    }

    public void setBPaasSupplierSelectionTableid(SupplierSelection bPaasSupplierSelectionTableid) {
        this.bPaasSupplierSelectionTableid = bPaasSupplierSelectionTableid;
    }

    @XmlTransient
    public Collection<SupplierLineitem> getSupplierLineitemCollection() {
        return supplierLineitemCollection;
    }

    public void setSupplierLineitemCollection(Collection<SupplierLineitem> supplierLineitemCollection) {
        this.supplierLineitemCollection = supplierLineitemCollection;
    }

    @XmlTransient
    public Collection<SupplierAddattachment> getSupplierAddattachmentCollection() {
        return supplierAddattachmentCollection;
    }

    public void setSupplierAddattachmentCollection(Collection<SupplierAddattachment> supplierAddattachmentCollection) {
        this.supplierAddattachmentCollection = supplierAddattachmentCollection;
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
        if (!(object instanceof SupplierHeader)) {
            return false;
        }
        SupplierHeader other = (SupplierHeader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierHeader[ id=" + id + " ]";
    }

    public WorkOrderRfqHeader getBpaasWorkorderrfqheaderRfqid() {
        return bpaasWorkorderrfqheaderRfqid;
    }

    public void setBpaasWorkorderrfqheaderRfqid(WorkOrderRfqHeader bpaasWorkorderrfqheaderRfqid) {
        this.bpaasWorkorderrfqheaderRfqid = bpaasWorkorderrfqheaderRfqid;
    }
    
}
