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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 *
 * @author admin
 */
@Entity
@Table(name = "ng_cp_admintrackingreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerUserTrackingReport.findAll", query = "SELECT c FROM CustomerUserTrackingReport c"),
    @NamedQuery(name = "CustomerUserTrackingReport.findById", query = "SELECT c FROM CustomerUserTrackingReport c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerUserTrackingReport.findByfromDateAndToDate", query = "SELECT c FROM CustomerUserTrackingReport c WHERE c.activitydate >= :fromDate AND c.activitydate <= :toDate ORDER BY c.activitydate DESC"),
    @NamedQuery(name = "CustomerUserTrackingReport.findByActivityname", query = "SELECT c FROM CustomerUserTrackingReport c WHERE c.activityname = :activityname"),
    @NamedQuery(name = "CustomerUserTrackingReport.findByActivitydate", query = "SELECT c FROM CustomerUserTrackingReport c WHERE c.activitydate = :activitydate")})
public class CustomerUserTrackingReport implements Serializable {
    @JoinColumn(name = "ng_cp_customersubuser_admin", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser ngCpCustomersubuserAdmin;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "activityname")
    private String activityname;
    @Column(name = "activitydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activitydate;
    @JoinColumn(name = "ng_cp_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser ngCpCustomersubuserId;

    public CustomerUserTrackingReport() {
    }

    public CustomerUserTrackingReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public Date getActivitydate() {
        return activitydate;
    }

    public void setActivitydate(Date activitydate) {
        this.activitydate = activitydate;
    }

    public CustomerSubUser getNgCpCustomersubuserId() {
        return ngCpCustomersubuserId;
    }

    public void setNgCpCustomersubuserId(CustomerSubUser ngCpCustomersubuserId) {
        this.ngCpCustomersubuserId = ngCpCustomersubuserId;
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
        if (!(object instanceof CustomerUserTrackingReport)) {
            return false;
        }
        CustomerUserTrackingReport other = (CustomerUserTrackingReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustomerUserTrackingReport[ id=" + id + " ]";
    }
    
    public CustomerSubUser getNgCpCustomersubuserAdmin() {
        return ngCpCustomersubuserAdmin;
    }

    public void setNgCpCustomersubuserAdmin(CustomerSubUser ngCpCustomersubuserAdmin) {
        this.ngCpCustomersubuserAdmin = ngCpCustomersubuserAdmin;
    }
    
    
}
