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
@Table(name = "bpaas_supplierselection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierSelection.findAll", query = "SELECT s FROM SupplierSelection s"),
    @NamedQuery(name = "SupplierSelection.findById", query = "SELECT s FROM SupplierSelection s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierSelection.findByUpdatedate", query = "SELECT s FROM SupplierSelection s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierSelection.findByUpdatedby", query = "SELECT s FROM SupplierSelection s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierSelection.findByCreationdate", query = "SELECT s FROM SupplierSelection s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierSelection.findByCreatedby", query = "SELECT s FROM SupplierSelection s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierSelection.findByAf1", query = "SELECT s FROM SupplierSelection s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierSelection.findByAf2", query = "SELECT s FROM SupplierSelection s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierSelection.findByAf3", query = "SELECT s FROM SupplierSelection s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierSelection.findByCreatedByAndRfpId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfpId and s.createdby = :createdby order by s.updatedate"),
    @NamedQuery(name = "SupplierSelection.findByRfqId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid"),
    @NamedQuery(name = "SupplierSelection.findBySupplierIdAndRfpId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid and s.bPaasSupplierUserTableid.id = :supplierId"),
    @NamedQuery(name = "SupplierSelection.findBySupplierId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasSupplierUserTableid.id = :id order by s.bPaasWorkOrderRFQHeaderRFQID desc"),
    @NamedQuery(name = "SupplierSelection.findBySupplierStatusAndSupplierId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasSupplierUserTableid.id = :id and s.supplierWFstatus = :status order by s.updatedate desc"),
    @NamedQuery(name = "SupplierSelection.findBySupplierStatusAndRfqId", query = "SELECT s FROM SupplierSelection s WHERE s.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid and s.supplierWFstatus = :status order by s.updatedate desc"),
    @NamedQuery(name = "SupplierSelection.findBySupplierWFstatus", query = "SELECT s FROM SupplierSelection s WHERE s.supplierWFstatus = :supplierWFstatus order by s.updatedate desc")})
public class SupplierSelection implements Serializable {
    @OneToMany(mappedBy = "bPaasSupplierSelectionid")
    private Collection<QmQuestion> qmQuestionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasSupplierSelectionid")
    private Collection<UserWf> userWfCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasSupplierSelectionTableid")
    private Collection<SupplierHeader> supplierHeaderCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Size(max = 30)
    @Column(name = "supplierWFstatus")
    private String supplierWFstatus;
    @JoinColumn(name = "BPaas_SupplierUserTable_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SupplierUser bPaasSupplierUserTableid;
    @JoinColumn(name = "BPaas_WorkOrderRFQHeader_RFQID", referencedColumnName = "RFQID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID;

    public SupplierSelection() {
    }

    public SupplierSelection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSupplierWFstatus() {
        return supplierWFstatus;
    }

    public void setSupplierWFstatus(String supplierWFstatus) {
        this.supplierWFstatus = supplierWFstatus;
    }

    public SupplierUser getBPaasSupplierUserTableid() {
        return bPaasSupplierUserTableid;
    }

    public void setBPaasSupplierUserTableid(SupplierUser bPaasSupplierUserTableid) {
        this.bPaasSupplierUserTableid = bPaasSupplierUserTableid;
    }

    public WorkOrderRfqHeader getBPaasWorkOrderRFQHeaderRFQID() {
        return bPaasWorkOrderRFQHeaderRFQID;
    }

    public void setBPaasWorkOrderRFQHeaderRFQID(WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID) {
        this.bPaasWorkOrderRFQHeaderRFQID = bPaasWorkOrderRFQHeaderRFQID;
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
        if (!(object instanceof SupplierSelection)) {
            return false;
        }
        SupplierSelection other = (SupplierSelection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierSelection[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<SupplierHeader> getSupplierHeaderCollection() {
        return supplierHeaderCollection;
    }

    public void setSupplierHeaderCollection(Collection<SupplierHeader> supplierHeaderCollection) {
        this.supplierHeaderCollection = supplierHeaderCollection;
    }

    @XmlTransient
    public Collection<UserWf> getUserWfCollection() {
        return userWfCollection;
    }

    public void setUserWfCollection(Collection<UserWf> userWfCollection) {
        this.userWfCollection = userWfCollection;
    }

    @XmlTransient
    public Collection<QmQuestion> getQmQuestionCollection() {
        return qmQuestionCollection;
    }

    public void setQmQuestionCollection(Collection<QmQuestion> qmQuestionCollection) {
        this.qmQuestionCollection = qmQuestionCollection;
    }
    
}
