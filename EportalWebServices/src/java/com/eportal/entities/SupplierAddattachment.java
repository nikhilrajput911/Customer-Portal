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
@Table(name = "bpaas_supplier_addattachment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierAddattachment.findAll", query = "SELECT s FROM SupplierAddattachment s"),
    @NamedQuery(name = "SupplierAddattachment.findById", query = "SELECT s FROM SupplierAddattachment s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierAddattachment.findByName", query = "SELECT s FROM SupplierAddattachment s WHERE s.name = :name"),
    @NamedQuery(name = "SupplierAddattachment.findByAf1", query = "SELECT s FROM SupplierAddattachment s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierAddattachment.findByAf2", query = "SELECT s FROM SupplierAddattachment s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierAddattachment.findByAf3", query = "SELECT s FROM SupplierAddattachment s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierAddattachment.findByAf4", query = "SELECT s FROM SupplierAddattachment s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierAddattachment.findBySupplierHeaderId", query = "SELECT s FROM SupplierAddattachment s WHERE s.bPaasWOSupplierHeaderid.id = :id"),
    @NamedQuery(name = "SupplierAddattachment.findByAf5", query = "SELECT s FROM SupplierAddattachment s WHERE s.af5 = :af5")})
public class SupplierAddattachment implements Serializable {
    @Lob
    @Column(name = "addattachment")
    private byte[] addattachment;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
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
    @JoinColumn(name = "BPaas_WOSupplierHeader_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SupplierHeader bPaasWOSupplierHeaderid;

    public SupplierAddattachment() {
    }

    public SupplierAddattachment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public SupplierHeader getBPaasWOSupplierHeaderid() {
        return bPaasWOSupplierHeaderid;
    }

    public void setBPaasWOSupplierHeaderid(SupplierHeader bPaasWOSupplierHeaderid) {
        this.bPaasWOSupplierHeaderid = bPaasWOSupplierHeaderid;
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
        if (!(object instanceof SupplierAddattachment)) {
            return false;
        }
        SupplierAddattachment other = (SupplierAddattachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierAddattachment[ id=" + id + " ]";
    }

    public byte[] getAddattachment() {
        return addattachment;
    }

    public void setAddattachment(byte[] addattachment) {
        this.addattachment = addattachment;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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
    
}
