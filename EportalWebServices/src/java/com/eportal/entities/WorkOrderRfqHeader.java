/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "bpaas_workorderrfqheader")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkOrderRfqHeader.findAll", query = "SELECT w FROM WorkOrderRfqHeader w"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByRfqid", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.rfqid = :rfqid"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByPurchaserequisitionnumber", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.purchaserequisitionnumber = :purchaserequisitionnumber"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByRFQTitle", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.rFQTitle = :rFQTitle"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByRfqstatus", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.rfqstatus = :rfqstatus order by w.updatedate desc"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByOpendate", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.opendate = :opendate"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByClosedate", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.closedate = :closedate"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByTimeleft", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.timeleft = :timeleft"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByShiptoaddress", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.shiptoaddress = :shiptoaddress"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByCreatedby", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.createdby = :createdby"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByUpdatedate", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.updatedate = :updatedate"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByUpdatedby", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.updatedby = :updatedby"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByCreationdate", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.creationdate = :creationdate"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByNegotationstyle", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.negotationstyle = :negotationstyle"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf1", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af1 = :af1"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf2", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af2 = :af2"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf3", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af3 = :af3"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf4", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af4 = :af4"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf5", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af5 = :af5"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf6", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af6 = :af6"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByAf7", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.af7 = :af7"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByManyStatus", query = "SELECT w FROM WorkOrderRfqHeader w WHERE (w.rfqstatus = 'Awaiting Acknowledge' or w.rfqstatus = 'Bid Submitted' or w.rfqstatus = 'Awaiting Bid') Order By w.updatedate desc"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByCostcode", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.costcode = :costcode"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByPaymentterms", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.paymentterms = :paymentterms"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByProjectnamecode", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.projectnamecode = :projectnamecode"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByUserDetailUserIdOrderByCreationdateDesc", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.userDetailuserid.userid = :userDetailuserid Order By w.rfqid desc"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByUserDetailUserIdAndStatusOrderByCreationdateDesc", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.userDetailuserid.userid = :userDetailuserid  and (w.rfqstatus = 'Awaiting Release') Order By w.creationdate desc"),
    @NamedQuery(name = "WorkOrderRfqHeader.findByBuyerWFstatus", query = "SELECT w FROM WorkOrderRfqHeader w WHERE w.buyerWFstatus = :buyerWFstatus")})
public class WorkOrderRfqHeader implements Serializable {
    @OneToMany(mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<QmQuestion> qmQuestionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<UserWf> userWfCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpaasWorkorderrfqheaderRfqid")
    private Collection<SupplierHeader> supplierHeaderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWorkOrderRFQHeaderRFQID", fetch = FetchType.EAGER)
    private Collection<SupplierSelection> supplierSelectionCollection;
    @Size(max = 50)
    @Column(name = "assigned_to")
    private String assignedTo;
    @Size(max = 100)
    @Column(name = "timeleft")
    private String timeleft;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<WorkOrderSelectedApprover> workOrderSelectedApproverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<WorkOrderAddclauses> workOrderAddclausesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<WorkOrderAttachment> workOrderAttachmentCollection;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bPaasWorkOrderRFQHeaderRFQID")
    private Collection<WorkOrderRfqLineItem> workOrderRfqLineItemCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RFQID")
    private Integer rfqid;
    @Size(max = 50)
    @Column(name = "purchaserequisitionnumber")
    private String purchaserequisitionnumber;
    @Size(max = 100)
    @Column(name = "RFQTitle")
    private String rFQTitle;
    @Size(max = 20)
    @Column(name = "rfqstatus")
    private String rfqstatus;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "opendate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opendate;
    @Column(name = "closedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedate;
    @Lob
    @Size(max = 65535)
    @Column(name = "billtoaddress")
    private String billtoaddress;
    @Size(max = 100)
    @Column(name = "shiptoaddress")
    private String shiptoaddress;
    @Size(max = 30)
    @Column(name = "createdby")
    private String createdby;
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
    @Column(name = "Negotationstyle")
    private String negotationstyle;
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
    @Column(name = "AF6")
    private Integer af6;
    @Column(name = "AF7")
    private Integer af7;
    @Column(name = "AF8")
    private Integer af8;
    @Size(max = 50)
    @Column(name = "costcode")
    private String costcode;
    @Size(max = 50)
    @Column(name = "paymentterms")
    private String paymentterms;
    @Size(max = 100)
    @Column(name = "Projectnamecode")
    private String projectnamecode;
    @Size(max = 30)
    @Column(name = "buyerWFstatus")
    private String buyerWFstatus;
    @JoinColumn(name = "UserDetail_userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Userdetail userDetailuserid;
    
    public WorkOrderRfqHeader() {
    }

    public WorkOrderRfqHeader(Integer rfqid) {
        this.rfqid = rfqid;
    }

    public Integer getRfqid() {
        return rfqid;
    }

    public void setRfqid(Integer rfqid) {
        this.rfqid = rfqid;
    }

    public String getPurchaserequisitionnumber() {
        return purchaserequisitionnumber;
    }

    public void setPurchaserequisitionnumber(String purchaserequisitionnumber) {
        this.purchaserequisitionnumber = purchaserequisitionnumber;
    }

    public String getRFQTitle() {
        return rFQTitle;
    }

    public void setRFQTitle(String rFQTitle) {
        this.rFQTitle = rFQTitle;
    }

    public String getRfqstatus() {
        return rfqstatus;
    }

    public void setRfqstatus(String rfqstatus) {
        this.rfqstatus = rfqstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }


    public String getBilltoaddress() {
        return billtoaddress;
    }

    public void setBilltoaddress(String billtoaddress) {
        this.billtoaddress = billtoaddress;
    }

    public String getShiptoaddress() {
        return shiptoaddress;
    }

    public void setShiptoaddress(String shiptoaddress) {
        this.shiptoaddress = shiptoaddress;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
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

    public String getNegotationstyle() {
        return negotationstyle;
    }

    public void setNegotationstyle(String negotationstyle) {
        this.negotationstyle = negotationstyle;
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

    public Integer getAf6() {
        return af6;
    }

    public void setAf6(Integer af6) {
        this.af6 = af6;
    }

    public Integer getAf7() {
        return af7;
    }

    public void setAf7(Integer af7) {
        this.af7 = af7;
    }

    public Integer getAf8() {
        return af8;
    }

    public void setAf8(Integer af8) {
        this.af8 = af8;
    }

    public String getCostcode() {
        return costcode;
    }

    public void setCostcode(String costcode) {
        this.costcode = costcode;
    }

    public String getPaymentterms() {
        return paymentterms;
    }

    public void setPaymentterms(String paymentterms) {
        this.paymentterms = paymentterms;
    }

    public String getProjectnamecode() {
        return projectnamecode;
    }

    public void setProjectnamecode(String projectnamecode) {
        this.projectnamecode = projectnamecode;
    }

    public String getBuyerWFstatus() {
        return buyerWFstatus;
    }

    public void setBuyerWFstatus(String buyerWFstatus) {
        this.buyerWFstatus = buyerWFstatus;
    }

    public Userdetail getUserDetailuserid() {
        return userDetailuserid;
    }

    public void setUserDetailuserid(Userdetail userDetailuserid) {
        this.userDetailuserid = userDetailuserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfqid != null ? rfqid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkOrderRfqHeader)) {
            return false;
        }
        WorkOrderRfqHeader other = (WorkOrderRfqHeader) object;
        if ((this.rfqid == null && other.rfqid != null) || (this.rfqid != null && !this.rfqid.equals(other.rfqid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.WorkOrderRfqHeader[ rfqid=" + rfqid + " ]";
    }

    @XmlTransient
    public Collection<WorkOrderRfqLineItem> getWorkOrderRfqLineItemCollection() {
        return workOrderRfqLineItemCollection;
    }

    public void setWorkOrderRfqLineItemCollection(Collection<WorkOrderRfqLineItem> workOrderRfqLineItemCollection) {
        this.workOrderRfqLineItemCollection = workOrderRfqLineItemCollection;
    }

    @XmlTransient
    public Collection<WorkOrderAttachment> getWorkOrderAttachmentCollection() {
        return workOrderAttachmentCollection;
    }

    public void setWorkOrderAttachmentCollection(Collection<WorkOrderAttachment> workOrderAttachmentCollection) {
        this.workOrderAttachmentCollection = workOrderAttachmentCollection;
    }

    @XmlTransient
    public Collection<WorkOrderAddclauses> getWorkOrderAddclausesCollection() {
        return workOrderAddclausesCollection;
    }

    public void setWorkOrderAddclausesCollection(Collection<WorkOrderAddclauses> workOrderAddclausesCollection) {
        this.workOrderAddclausesCollection = workOrderAddclausesCollection;
    }

    @XmlTransient
    public Collection<WorkOrderSelectedApprover> getWorkOrderSelectedApproverCollection() {
        return workOrderSelectedApproverCollection;
    }

    public void setWorkOrderSelectedApproverCollection(Collection<WorkOrderSelectedApprover> workOrderSelectedApproverCollection) {
        this.workOrderSelectedApproverCollection = workOrderSelectedApproverCollection;
    }

    public String getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(String timeleft) {
        this.timeleft = timeleft;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @XmlTransient
    public Collection<SupplierSelection> getSupplierSelectionCollection() {
        return supplierSelectionCollection;
    }

    public void setSupplierSelectionCollection(Collection<SupplierSelection> supplierSelectionCollection) {
        this.supplierSelectionCollection = supplierSelectionCollection;
    }

    @XmlTransient
    public Collection<SupplierHeader> getSupplierHeaderCollection() {
        return supplierHeaderCollection;
    }

    public void setSupplierHeaderCollection(Collection<SupplierHeader> supplierHeaderCollection) {
        this.supplierHeaderCollection = supplierHeaderCollection;
    }

    @XmlTransient
    public Collection<UserWf> getUserWfCollection() {
        return userWfCollection;
    }

    public void setUserWfCollection(Collection<UserWf> userWfCollection) {
        this.userWfCollection = userWfCollection;
    }

    @XmlTransient
    public Collection<QmQuestion> getQmQuestionCollection() {
        return qmQuestionCollection;
    }

    public void setQmQuestionCollection(Collection<QmQuestion> qmQuestionCollection) {
        this.qmQuestionCollection = qmQuestionCollection;
    }
    
}
