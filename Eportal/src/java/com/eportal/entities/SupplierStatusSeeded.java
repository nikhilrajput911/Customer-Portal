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
@Table(name = "bpaas_supplierstatusseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierStatusSeeded.findAll", query = "SELECT s FROM SupplierStatusSeeded s"),
    @NamedQuery(name = "SupplierStatusSeeded.findById", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierStatusSeeded.findBySupplierstatus", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.supplierstatus = :supplierstatus"),
    @NamedQuery(name = "SupplierStatusSeeded.findByUpdatedate", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierStatusSeeded.findByUpdatedby", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierStatusSeeded.findByCreationdate", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierStatusSeeded.findByCreatedby", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierStatusSeeded.findByAf1", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierStatusSeeded.findByAf2", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierStatusSeeded.findByAf3", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierStatusSeeded.findByAf4", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierStatusSeeded.findByAf5", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.af5 = :af5"),
    @NamedQuery(name = "SupplierStatusSeeded.findByStatus", query = "SELECT s FROM SupplierStatusSeeded s WHERE s.status = :status")})
public class SupplierStatusSeeded implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "supplierstatus")
    private String supplierstatus;
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
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    public SupplierStatusSeeded() {
    }

    public SupplierStatusSeeded(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(String supplierstatus) {
        this.supplierstatus = supplierstatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof SupplierStatusSeeded)) {
            return false;
        }
        SupplierStatusSeeded other = (SupplierStatusSeeded) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierStatusSeeded[ id=" + id + " ]";
    }
    
}
