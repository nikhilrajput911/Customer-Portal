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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "bpaas_qm_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QmQuestion.findAll", query = "SELECT q FROM QmQuestion q"),
    @NamedQuery(name = "QmQuestion.findBySupplierSelection", query = "SELECT q FROM QmQuestion q where q.bPaasSupplierSelectionid.id = :id order by q.questiondate desc"),
    @NamedQuery(name = "QmQuestion.findById", query = "SELECT q FROM QmQuestion q WHERE q.id = :id")})
public class QmQuestion implements Serializable {
    @Lob
    @Column(name = "addattachment")
    private byte[] addattachment;
    @JoinColumn(name = "BPaas_SupplierSelection_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierSelection bPaasSupplierSelectionid;
    @JoinColumn(name = "BPaas_WorkOrderRFQHeader_RFQID", referencedColumnName = "RFQID")
    @ManyToOne
    private WorkOrderRfqHeader bPaasWorkOrderRFQHeaderRFQID;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 50)
    @Column(name = "initiatedby")
    private String initiatedby;
    @Column(name = "questiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date questiondate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "askquestion")
    private String askquestion;
    @Lob
    @Size(max = 65535)
    @Column(name = "attachmentdescription")
    private String attachmentdescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bPaasQMQuestionid")
    private Collection<QmAnswer> qmAnswerCollection;

    public QmQuestion() {
    }

    public QmQuestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAskquestion() {
        return askquestion;
    }

    public void setAskquestion(String askquestion) {
        this.askquestion = askquestion;
    }


    public String getAttachmentdescription() {
        return attachmentdescription;
    }

    public void setAttachmentdescription(String attachmentdescription) {
        this.attachmentdescription = attachmentdescription;
    }

    @XmlTransient
    public Collection<QmAnswer> getQmAnswerCollection() {
        return qmAnswerCollection;
    }

    public void setQmAnswerCollection(Collection<QmAnswer> qmAnswerCollection) {
        this.qmAnswerCollection = qmAnswerCollection;
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
        if (!(object instanceof QmQuestion)) {
            return false;
        }
        QmQuestion other = (QmQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.QmQuestion[ id=" + id + " ]";
    }


    public String getInitiatedby() {
        return initiatedby;
    }

    public void setInitiatedby(String initiatedby) {
        this.initiatedby = initiatedby;
    }

    public Date getQuestiondate() {
        return questiondate;
    }

    public void setQuestiondate(Date questiondate) {
        this.questiondate = questiondate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public byte[] getAddattachment() {
        return addattachment;
    }

    public void setAddattachment(byte[] addattachment) {
        this.addattachment = addattachment;
    }
    
}
