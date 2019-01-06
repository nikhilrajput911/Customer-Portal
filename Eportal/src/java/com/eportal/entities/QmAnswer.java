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
@Table(name = "bpaas_qm_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QmAnswer.findAll", query = "SELECT q FROM QmAnswer q"),
    @NamedQuery(name = "QmAnswer.findById", query = "SELECT q FROM QmAnswer q WHERE q.id = :id")})
public class QmAnswer implements Serializable {
    @Lob
    @Column(name = "addattachment")
    private byte[] addattachment;
    @Size(max = 100)
    @Column(name = "attachmentdescription")
    private String attachmentdescription;
    @Size(max = 50)
    @Column(name = "answeredby")
    private String answeredby;
    @Column(name = "answerdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Lob
    @Size(max = 65535)
    @Column(name = "attachemntdescription")
    private String attachemntdescription;
    @JoinColumn(name = "BPaas_QM_Question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private QmQuestion bPaasQMQuestionid;
    @JoinColumn(name = "BPaas_UserDetail_userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Userdetail bPaasUserDetailuserid;

    public QmAnswer() {
    }

    public QmAnswer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String getAttachemntdescription() {
        return attachemntdescription;
    }

    public void setAttachemntdescription(String attachemntdescription) {
        this.attachemntdescription = attachemntdescription;
    }

    public QmQuestion getBPaasQMQuestionid() {
        return bPaasQMQuestionid;
    }

    public void setBPaasQMQuestionid(QmQuestion bPaasQMQuestionid) {
        this.bPaasQMQuestionid = bPaasQMQuestionid;
    }

    public Userdetail getBPaasUserDetailuserid() {
        return bPaasUserDetailuserid;
    }

    public void setBPaasUserDetailuserid(Userdetail bPaasUserDetailuserid) {
        this.bPaasUserDetailuserid = bPaasUserDetailuserid;
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
        if (!(object instanceof QmAnswer)) {
            return false;
        }
        QmAnswer other = (QmAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.QmAnswer[ id=" + id + " ]";
    }

    public byte[] getAddattachment() {
        return addattachment;
    }

    public void setAddattachment(byte[] addattachment) {
        this.addattachment = addattachment;
    }

    public String getAttachmentdescription() {
        return attachmentdescription;
    }

    public void setAttachmentdescription(String attachmentdescription) {
        this.attachmentdescription = attachmentdescription;
    }

    public String getAnsweredby() {
        return answeredby;
    }

    public void setAnsweredby(String answeredby) {
        this.answeredby = answeredby;
    }

    public Date getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(Date answerdate) {
        this.answerdate = answerdate;
    }
    
}
