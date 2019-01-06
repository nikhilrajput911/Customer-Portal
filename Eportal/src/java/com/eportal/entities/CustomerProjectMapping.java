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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "NG_CP_customerprojectmapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerProjectMapping.findAll", query = "SELECT c FROM CustomerProjectMapping c"),
    @NamedQuery(name = "CustomerProjectMapping.findById", query = "SELECT c FROM CustomerProjectMapping c WHERE c.id = :id")})
public class CustomerProjectMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "NG_CP_projectseeded_pid", referencedColumnName = "pid")
    @ManyToOne
    private ProjectSeeded bpaasProjectseededPid;
    @JoinColumn(name = "NG_CP_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;

    public CustomerProjectMapping() {
    }

    public CustomerProjectMapping(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectSeeded getBpaasProjectseededPid() {
        return bpaasProjectseededPid;
    }

    public void setBpaasProjectseededPid(ProjectSeeded bpaasProjectseededPid) {
        this.bpaasProjectseededPid = bpaasProjectseededPid;
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
        if (!(object instanceof CustomerProjectMapping)) {
            return false;
        }
        CustomerProjectMapping other = (CustomerProjectMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerProjectMapping[ id=" + id + " ]";
    }
    
}
