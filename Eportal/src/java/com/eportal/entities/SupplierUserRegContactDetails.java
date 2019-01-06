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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "bpaas_supplieruserregcontactdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierUserRegContactDetails.findAll", query = "SELECT s FROM SupplierUserRegContactDetails s"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findById", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByBusinessaddress", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.businessaddress = :businessaddress"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByContactperson2", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.contactperson2 = :contactperson2"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByContact2mobilenumber", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.contact2mobilenumber = :contact2mobilenumber"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByContact2emailaddress", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.contact2emailaddress = :contact2emailaddress"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByCountry", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.country = :country"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByState", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.state = :state"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByCreationdate", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByUpdatedate", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByUpdatedby", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findBySupplierId", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.bpaasSupplieruserId.id = :id"),
    @NamedQuery(name = "SupplierUserRegContactDetails.findByCreatedby", query = "SELECT s FROM SupplierUserRegContactDetails s WHERE s.createdby = :createdby")})
public class SupplierUserRegContactDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "businessaddress")
    private String businessaddress;
    @Size(max = 30)
    @Column(name = "contactperson2")
    private String contactperson2;
    @Size(max = 15)
    @Column(name = "contact2mobilenumber")
    private String contact2mobilenumber;
    @Size(max = 50)
    @Column(name = "contact2emailaddress")
    private String contact2emailaddress;
    @Size(max = 50)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "state")
    private String state;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Size(max = 30)
    @Column(name = "updatedby")
    private String updatedby;
    @Size(max = 30)
    @Column(name = "createdby")
    private String createdby;
    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierUser bpaasSupplieruserId;

    public SupplierUserRegContactDetails() {
    }

    public SupplierUserRegContactDetails(Integer id) {
        this.id = id;
    }

    public SupplierUserRegContactDetails(Integer id, String state) {
        this.id = id;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public String getContactperson2() {
        return contactperson2;
    }

    public void setContactperson2(String contactperson2) {
        this.contactperson2 = contactperson2;
    }

    public String getContact2mobilenumber() {
        return contact2mobilenumber;
    }

    public void setContact2mobilenumber(String contact2mobilenumber) {
        this.contact2mobilenumber = contact2mobilenumber;
    }

    public String getContact2emailaddress() {
        return contact2emailaddress;
    }

    public void setContact2emailaddress(String contact2emailaddress) {
        this.contact2emailaddress = contact2emailaddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public SupplierUser getBpaasSupplieruserId() {
        return bpaasSupplieruserId;
    }

    public void setBpaasSupplieruserId(SupplierUser bpaasSupplieruserId) {
        this.bpaasSupplieruserId = bpaasSupplieruserId;
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
        if (!(object instanceof SupplierUserRegContactDetails)) {
            return false;
        }
        SupplierUserRegContactDetails other = (SupplierUserRegContactDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierUserRegContactDetails[ id=" + id + " ]";
    }
    
}
