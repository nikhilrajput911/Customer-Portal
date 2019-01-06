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
@Table(name = "ng_cp_custprofileupdaterpt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerProfileUpdateReport.findAll", query = "SELECT c FROM CustomerProfileUpdateReport c"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findById", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByFieldname", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.fieldname = :fieldname"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByOldvalue", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.oldvalue = :oldvalue"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByNewvalue", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.newvalue = :newvalue"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByActiondate", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.actiondate = :actiondate"),
//    @NamedQuery(name = "CustomerProfileUpdateReport.findByfromDateAndToDate", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.actiondate BETWEEN :fromDate AND :toDate"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByfromDateAndToDate", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.actiondate >= :fromDate AND c.actiondate <= :toDate order by c.actiondate desc"),
    @NamedQuery(name = "CustomerProfileUpdateReport.findByLastmodifieddate", query = "SELECT c FROM CustomerProfileUpdateReport c WHERE c.lastmodifieddate = :lastmodifieddate")})
public class CustomerProfileUpdateReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "fieldname")
    private String fieldname;
    @Size(max = 100)
    @Column(name = "oldvalue")
    private String oldvalue;
    @Size(max = 100)
    @Column(name = "newvalue")
    private String newvalue;
    @Column(name = "actiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actiondate;
    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodifieddate;
    @JoinColumn(name = "bpaas_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;

    public CustomerProfileUpdateReport() {
    }

    public CustomerProfileUpdateReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getOldvalue() {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }

    public String getNewvalue() {
        return newvalue;
    }

    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }

    public Date getActiondate() {
        return actiondate;
    }

    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    public Date getLastmodifieddate() {
        return lastmodifieddate;
    }

    public void setLastmodifieddate(Date lastmodifieddate) {
        this.lastmodifieddate = lastmodifieddate;
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
        if (!(object instanceof CustomerProfileUpdateReport)) {
            return false;
        }
        CustomerProfileUpdateReport other = (CustomerProfileUpdateReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerProfileUpdateReport[ id=" + id + " ]";
    }
    
}
