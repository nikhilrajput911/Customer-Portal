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
@Table(name = "bpaas_gststateseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GstStateSeeded.findAll", query = "SELECT g FROM GstStateSeeded g"),
    @NamedQuery(name = "GstStateSeeded.findById", query = "SELECT g FROM GstStateSeeded g WHERE g.id = :id"),
    @NamedQuery(name = "GstStateSeeded.findByGstState", query = "SELECT g FROM GstStateSeeded g WHERE g.gstState = :gstState"),
    @NamedQuery(name = "GstStateSeeded.findByCreatedby", query = "SELECT g FROM GstStateSeeded g WHERE g.createdby = :createdby"),
    @NamedQuery(name = "GstStateSeeded.findByCreationdate", query = "SELECT g FROM GstStateSeeded g WHERE g.creationdate = :creationdate"),
    @NamedQuery(name = "GstStateSeeded.findByUpdatedate", query = "SELECT g FROM GstStateSeeded g WHERE g.updatedate = :updatedate"),
    @NamedQuery(name = "GstStateSeeded.findByStatus", query = "SELECT g FROM GstStateSeeded g WHERE g.status = :status")})
public class GstStateSeeded implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "gst_state")
    private String gstState;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    public GstStateSeeded() {
    }

    public GstStateSeeded(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGstState() {
        return gstState;
    }

    public void setGstState(String gstState) {
        this.gstState = gstState;
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

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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
        if (!(object instanceof GstStateSeeded)) {
            return false;
        }
        GstStateSeeded other = (GstStateSeeded) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.GstStateSeeded[ id=" + id + " ]";
    }
    
}
