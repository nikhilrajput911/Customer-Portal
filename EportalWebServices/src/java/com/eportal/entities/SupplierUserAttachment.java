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
import javax.persistence.Lob;
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
@Table(name = "bpaas_supplieruserattachment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierUserAttachment.findAll", query = "SELECT s FROM SupplierUserAttachment s"),
    @NamedQuery(name = "SupplierUserAttachment.findById", query = "SELECT s FROM SupplierUserAttachment s WHERE s.id = :id"),
    @NamedQuery(name = "SupplierUserAttachment.findByCreatedby", query = "SELECT s FROM SupplierUserAttachment s WHERE s.createdby = :createdby"),
    @NamedQuery(name = "SupplierUserAttachment.findByCreationdate", query = "SELECT s FROM SupplierUserAttachment s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "SupplierUserAttachment.findBySupplierId", query = "SELECT s FROM SupplierUserAttachment s WHERE s.bpaasSupplieruserId.id = :id"),
    @NamedQuery(name = "SupplierUserAttachment.findByUpdatedby", query = "SELECT s FROM SupplierUserAttachment s WHERE s.updatedby = :updatedby"),
    @NamedQuery(name = "SupplierUserAttachment.findByUpdatedate", query = "SELECT s FROM SupplierUserAttachment s WHERE s.updatedate = :updatedate")})
public class SupplierUserAttachment implements Serializable {
    @Lob
    @Column(name = "pan_card")
    private byte[] panCard;
    @Lob
    @Column(name = "cancelledcheque")
    private byte[] cancelledcheque;
    @Lob
    @Column(name = "cmpnyincorpletter")
    private byte[] cmpnyincorpletter;
    @Lob
    @Column(name = "gstcertificate")
    private byte[] gstcertificate;
    @Lob
    @Column(name = "other")
    private byte[] other;
    @Size(max = 40)
    @Column(name = "pancard_name")
    private String pancardName;
    @Size(max = 40)
    @Column(name = "cancelledcheque_name")
    private String cancelledchequeName;
    @Size(max = 40)
    @Column(name = "cmpnyincorpletter_name")
    private String cmpnyincorpletterName;
    @Size(max = 40)
    @Column(name = "gstcertificate_name")
    private String gstcertificateName;
    @Size(max = 40)
    @Column(name = "other_name")
    private String otherName;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(name = "updatedby")
    private String updatedby;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @JoinColumn(name = "bpaas_supplieruser_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierUser bpaasSupplieruserId;

    public SupplierUserAttachment() {
    }

    public SupplierUserAttachment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPanCard() {
        return panCard;
    }

    public void setPanCard(byte[] panCard) {
        this.panCard = panCard;
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

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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
        if (!(object instanceof SupplierUserAttachment)) {
            return false;
        }
        SupplierUserAttachment other = (SupplierUserAttachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.SupplierUserAttachment[ id=" + id + " ]";
    }


    public String getPancardName() {
        return pancardName;
    }

    public void setPancardName(String pancardName) {
        this.pancardName = pancardName;
    }


    public String getCancelledchequeName() {
        return cancelledchequeName;
    }

    public void setCancelledchequeName(String cancelledchequeName) {
        this.cancelledchequeName = cancelledchequeName;
    }


    public String getCmpnyincorpletterName() {
        return cmpnyincorpletterName;
    }

    public void setCmpnyincorpletterName(String cmpnyincorpletterName) {
        this.cmpnyincorpletterName = cmpnyincorpletterName;
    }


    public String getGstcertificateName() {
        return gstcertificateName;
    }

    public void setGstcertificateName(String gstcertificateName) {
        this.gstcertificateName = gstcertificateName;
    }


    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }


    public byte[] getCancelledcheque() {
        return cancelledcheque;
    }

    public void setCancelledcheque(byte[] cancelledcheque) {
        this.cancelledcheque = cancelledcheque;
    }

    public byte[] getCmpnyincorpletter() {
        return cmpnyincorpletter;
    }

    public void setCmpnyincorpletter(byte[] cmpnyincorpletter) {
        this.cmpnyincorpletter = cmpnyincorpletter;
    }

    public byte[] getGstcertificate() {
        return gstcertificate;
    }

    public void setGstcertificate(byte[] gstcertificate) {
        this.gstcertificate = gstcertificate;
    }

    public byte[] getOther() {
        return other;
    }

    public void setOther(byte[] other) {
        this.other = other;
    }
    
}
