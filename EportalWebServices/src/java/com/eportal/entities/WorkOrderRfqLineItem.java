/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author RaphelTudu
 */
@Entity
@Table(name = "bpaas_workorderrfqlineitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkOrderRfqLineItem.findAll", query = "SELECT w FROM WorkOrderRfqLineItem w"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByRFQLineID", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.rFQLineID = :rFQLineID"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByCategory", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.category = :category"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findBySubcategory", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.subcategory = :subcategory"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByItemname", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.itemname = :itemname"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByTargetprice", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.targetprice = :targetprice"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByUpdatedate", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.updatedate = :updatedate"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByUpdatedby", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.updatedby = :updatedby"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByQuantity", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.quantity = :quantity"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByCreationdate", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.creationdate = :creationdate"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByCreatedby", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.createdby = :createdby"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByHeaderId", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.bPaasWorkOrderRFQHeaderRFQID.rfqid = :rfqid"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByAf1", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.af1 = :af1"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByAf2", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.af2 = :af2"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByAf3", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.af3 = :af3"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByAf4", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.af4 = :af4"),
    @NamedQuery(name = "WorkOrderRfqLineItem.findByAf5", query = "SELECT w FROM WorkOrderRfqLineItem w WHERE w.af5 = :af5")})
public class WorkOrderRfqLineItem implements Serializable {
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 20)
    @Column(name = "itemcode")
    private String itemcode;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RFQLineID")
    private Integer rFQLineID;
    @Size(max = 30)
    @Column(name = "category")
    private String category;
    @Size(max = 30)
    @Column(name = "subcategory")
    private String subcategory;
    @Size(max = 40)
    @Column(name = "itemname")
    private String itemname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "targetprice")
    private Double targetprice;
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
    @JoinColumn(name = "BPaas_WorkOrderRFQHeader_RFQID", referencedColumnName = "RFQID")
    @ManyToOne(optional = false)
    private WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID;

    public WorkOrderRfqLineItem() {
    }

    public WorkOrderRfqLineItem(Integer rFQLineID) {
        this.rFQLineID = rFQLineID;
    }

    public Integer getRFQLineID() {
        return rFQLineID;
    }

    public void setRFQLineID(Integer rFQLineID) {
        this.rFQLineID = rFQLineID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Double getTargetprice() {
        return targetprice;
    }

    public void setTargetprice(Double targetprice) {
        this.targetprice = targetprice;
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

    public WorkOrderRfqHeader getBPaasWorkOrderRFQHeaderRFQID() {
        return bPaasWorkOrderRFQHeaderRFQID;
    }

    public void setBPaasWorkOrderRFQHeaderRFQID(WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID) {
        this.bPaasWorkOrderRFQHeaderRFQID = bPaasWorkOrderRFQHeaderRFQID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rFQLineID != null ? rFQLineID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkOrderRfqLineItem)) {
            return false;
        }
        WorkOrderRfqLineItem other = (WorkOrderRfqLineItem) object;
        if ((this.rFQLineID == null && other.rFQLineID != null) || (this.rFQLineID != null && !this.rFQLineID.equals(other.rFQLineID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.WorkOrderRfqLineItem[ rFQLineID=" + rFQLineID + " ]";
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
    
}
