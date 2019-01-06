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
@Table(name = "ng_cp_custsecques")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustSecQues.findAll", query = "SELECT c FROM CustSecQues c"),
    @NamedQuery(name = "CustSecQues.findById", query = "SELECT c FROM CustSecQues c WHERE c.id = :id"),
    @NamedQuery(name = "CustSecQues.findByCustomerSubUserId", query = "SELECT c FROM CustSecQues c WHERE c.bpaasCustomersubuserId.id = :id"),
    @NamedQuery(name = "CustSecQues.findBySelectiondate", query = "SELECT c FROM CustSecQues c WHERE c.selectiondate = :selectiondate")})
public class CustSecQues implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "question")
    private String question;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Column(name = "selectiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date selectiondate;
    @JoinColumn(name = "ng_cp_customersubuser_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSubUser bpaasCustomersubuserId;

    public CustSecQues() {
    }

    public CustSecQues(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getSelectiondate() {
        return selectiondate;
    }

    public void setSelectiondate(Date selectiondate) {
        this.selectiondate = selectiondate;
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
        if (!(object instanceof CustSecQues)) {
            return false;
        }
        CustSecQues other = (CustSecQues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CustSecQues[ id=" + id + " ]";
    }
    
}
