/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "NG_CP_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGid", query = "SELECT g FROM Groups g WHERE g.gid = :gid"),
    @NamedQuery(name = "Groups.findByGroupname", query = "SELECT g FROM Groups g WHERE g.groupname = :groupname"),
    @NamedQuery(name = "Groups.findByStatus", query = "SELECT g FROM Groups g WHERE g.status = :status")})
public class Groups implements Serializable {
	@Size(max = 50)
    @Column(name = "groupalias")
    private String groupalias;
    
    @JsonIgnore    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpaasGroupsGid")
    private Collection<SupplierGroup> supplierGroupCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Size(max = 50)
    @Column(name = "groupname")
    private String groupname;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 30)
    @Column(name = "status")
    private String status;

    public Groups() {
    }

    public Groups(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.Groups[ gid=" + gid + " ]";
    }

    @XmlTransient
    public Collection<SupplierGroup> getSupplierGroupCollection() {
        return supplierGroupCollection;
    }

    public void setSupplierGroupCollection(Collection<SupplierGroup> supplierGroupCollection) {
        this.supplierGroupCollection = supplierGroupCollection;
    }
    public String getGroupalias() {
        return groupalias;
    }

    public void setGroupalias(String groupalias) {
        this.groupalias = groupalias;
    }
}
