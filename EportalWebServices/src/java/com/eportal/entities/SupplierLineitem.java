/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "bpaas_supplierlineitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierLineitem.findAll", query = "SELECT s FROM SupplierLineitem s"),
    @NamedQuery(name = "SupplierLineitem.findById", query = "SELECT s FROM SupplierLineitem s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierLineitem.findByQuotepriceperquantity", query = "SELECT s FROM SupplierLineitem s WHERE s.quotepriceperquantity = :quotepriceperquantity"),
    @NamedQuery(name = "SupplierLineitem.findByExpectedprice", query = "SELECT s FROM SupplierLineitem s WHERE s.expectedprice = :expectedprice"),
    @NamedQuery(name = "SupplierLineitem.findByDeliverydate", query = "SELECT s FROM SupplierLineitem s WHERE s.deliverydate = :deliverydate"),
    @NamedQuery(name = "SupplierLineitem.findByCreationdate", query = "SELECT s FROM SupplierLineitem s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierLineitem.findByUpdatedate", query = "SELECT s FROM SupplierLineitem s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "SupplierLineitem.findByUpdatedby", query = "SELECT s FROM SupplierLineitem s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierLineitem.findByCreatedby", query = "SELECT s FROM SupplierLineitem s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierLineitem.findByAf1", query = "SELECT s FROM SupplierLineitem s WHERE s.af1 = :af1"),
    @NamedQuery(name = "SupplierLineitem.findByAf2", query = "SELECT s FROM SupplierLineitem s WHERE s.af2 = :af2"),
    @NamedQuery(name = "SupplierLineitem.findByAf3", query = "SELECT s FROM SupplierLineitem s WHERE s.af3 = :af3"),
    @NamedQuery(name = "SupplierLineitem.findByAf4", query = "SELECT s FROM SupplierLineitem s WHERE s.af4 = :af4"),
    @NamedQuery(name = "SupplierLineitem.findByAf5", query = "SELECT s FROM SupplierLineitem s WHERE s.af5 = :af5"),
    @NamedQuery(name = "SupplierLineitem.findBySupplierHeaderId", query = "SELECT s FROM SupplierLineitem s WHERE s.workOrderSupplierHeaderTableid.id = :id"),
    @NamedQuery(name = "SupplierLineitem.findByTotalprice", query = "SELECT s FROM SupplierLineitem s WHERE s.totalprice = :totalprice")})
public class SupplierLineitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quotepriceperquantity")
    private Double quotepriceperquantity;
    @Column(name = "expectedprice")
    private Double expectedprice;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;
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
    @Column(name = "totalprice")
    private Double totalprice;
    @JoinColumn(name = "WorkOrderSupplierHeaderTable_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private SupplierHeader workOrderSupplierHeaderTableid;

    public SupplierLineitem() {
    }

    public SupplierLineitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuotepriceperquantity() {
        return quotepriceperquantity;
    }

    public void setQuotepriceperquantity(Double quotepriceperquantity) {
        this.quotepriceperquantity = quotepriceperquantity;
    }

    public Double getExpectedprice() {
        return expectedprice;
    }

    public void setExpectedprice(Double expectedprice) {
        this.expectedprice = expectedprice;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
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

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public SupplierHeader getWorkOrderSupplierHeaderTableid() {
        return workOrderSupplierHeaderTableid;
    }

    public void setWorkOrderSupplierHeaderTableid(SupplierHeader workOrderSupplierHeaderTableid) {
        this.workOrderSupplierHeaderTableid = workOrderSupplierHeaderTableid;
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
        if (!(object instanceof SupplierLineitem)) {
            return false;
        }
        SupplierLineitem other = (SupplierLineitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierLineitem[ id=" + id + " ]";
    }
    
}
