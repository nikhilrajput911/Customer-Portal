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
@Table(name = "NG_CP_suppliergroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierGroup.findAll", query = "SELECT s FROM SupplierGroup s"),
    @NamedQuery(name = "SupplierGroup.findBySgid", query = "SELECT s FROM SupplierGroup s WHERE s.sgid = :sgid"),
    @NamedQuery(name = "SupplierGroup.findBySgroupname", query = "SELECT s FROM SupplierGroup s WHERE s.sgroupname = :sgroupname"),
    @NamedQuery(name = "SupplierGroup.findByCreationdate", query = "SELECT s FROM SupplierGroup s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierGroup.findByCreatedby", query = "SELECT s FROM SupplierGroup s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierGroup.findByUpdatedate", query = "SELECT s FROM SupplierGroup s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierGroup.findByGroupId", query = "SELECT s FROM SupplierGroup s WHERE s.bpaasGroupsGid.gid = :gid"),
    @NamedQuery(name = "SupplierGroup.findByGroupIdAndCustomerId", query = "SELECT s FROM SupplierGroup s WHERE s.bpaasGroupsGid.gid = :gid and s.bpaasCustomersubuserId.id = :custId"),
    @NamedQuery(name = "SupplierGroup.findByUpdatedby", query = "SELECT s FROM SupplierGroup s WHERE s.updatedby = :updatedby")})
public class SupplierGroup implements Serializable {
    @JoinColumn(name = "NG_CP_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sgid")
    private Integer sgid;
    @Size(max = 30)
    @Column(name = "sgroupname")
    private String sgroupname;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
    @JoinColumn(name = "NG_CP_groups_gid", referencedColumnName = "gid")
    @ManyToOne(optional = false)
    private Groups bpaasGroupsGid;
//    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private SupplierUser bpaasSupplieruserId;

    public SupplierGroup() {
    }

    public SupplierGroup(Integer sgid) {
        this.sgid = sgid;
    }

    public Integer getSgid() {
        return sgid;
    }

    public void setSgid(Integer sgid) {
        this.sgid = sgid;
    }

    public String getSgroupname() {
        return sgroupname;
    }

    public void setSgroupname(String sgroupname) {
        this.sgroupname = sgroupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Groups getBpaasGroupsGid() {
        return bpaasGroupsGid;
    }

    public void setBpaasGroupsGid(Groups bpaasGroupsGid) {
        this.bpaasGroupsGid = bpaasGroupsGid;
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
        hash += (sgid != null ? sgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierGroup)) {
            return false;
        }
        SupplierGroup other = (SupplierGroup) object;
        if ((this.sgid == null && other.sgid != null) || (this.sgid != null && !this.sgid.equals(other.sgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierGroup[ sgid=" + sgid + " ]";
    }

    public CustomerSubUser getBpaasCustomersubuserId() {
        return bpaasCustomersubuserId;
    }

    public void setBpaasCustomersubuserId(CustomerSubUser bpaasCustomersubuserId) {
        this.bpaasCustomersubuserId = bpaasCustomersubuserId;
    }
    
}
