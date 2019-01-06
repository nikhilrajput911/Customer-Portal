/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "bpaas_user_wf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserWf.findAll", query = "SELECT u FROM UserWf u"),
    @NamedQuery(name = "UserWf.findByRfqId", query = "SELECT u FROM UserWf u where u.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid"),
    @NamedQuery(name = "UserWf.findBySupplierSelectionAndRfqId", query = "SELECT u FROM UserWf u where u.bPaasSupplierSelectionid.id = :id and u.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid"),
    @NamedQuery(name = "UserWf.findById", query = "SELECT u FROM UserWf u WHERE u.id = :id")})
public class UserWf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "BPaas_SupplierSelection_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SupplierSelection bPaasSupplierSelectionid;
    @JoinColumn(name = "BPaas_WorkOrderRFQHeader_RFQID", referencedColumnName = "RFQID")
    @ManyToOne(optional = false)
    private WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID;

    public UserWf() {
    }

    public UserWf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SupplierSelection getBPaasSupplierSelectionid() {
        return bPaasSupplierSelectionid;
    }

    public void setBPaasSupplierSelectionid(SupplierSelection bPaasSupplierSelectionid) {
        this.bPaasSupplierSelectionid = bPaasSupplierSelectionid;
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
        if (!(object instanceof UserWf)) {
            return false;
        }
        UserWf other = (UserWf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.UserWf[ id=" + id + " ]";
    }
    
}
