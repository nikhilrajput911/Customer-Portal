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
import javax.persistence.Lob;
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
@Table(name = "bpaas_clausesseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clauses.findAll", query = "SELECT c FROM Clauses c"),
    @NamedQuery(name = "Clauses.findById", query = "SELECT c FROM Clauses c WHERE c.id = :id"),
    @NamedQuery(name = "Clauses.findByUpdatedate", query = "SELECT c FROM Clauses c WHERE c.updatedate = :updatedate"),
    @NamedQuery(name = "Clauses.findByUpdatedby", query = "SELECT c FROM Clauses c WHERE c.updatedby = :updatedby"),
    @NamedQuery(name = "Clauses.findByCreationdate", query = "SELECT c FROM Clauses c WHERE c.creationdate = :creationdate"),
    @NamedQuery(name = "Clauses.findByCreatedby", query = "SELECT c FROM Clauses c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Clauses.findByAf1", query = "SELECT c FROM Clauses c WHERE c.af1 = :af1"),
    @NamedQuery(name = "Clauses.findByAf2", query = "SELECT c FROM Clauses c WHERE c.af2 = :af2"),
    @NamedQuery(name = "Clauses.findByAf3", query = "SELECT c FROM Clauses c WHERE c.af3 = :af3"),
    @NamedQuery(name = "Clauses.findByAf4", query = "SELECT c FROM Clauses c WHERE c.af4 = :af4"),
    @NamedQuery(name = "Clauses.findByAf5", query = "SELECT c FROM Clauses c WHERE c.af5 = :af5"),
    @NamedQuery(name = "Clauses.findByStatus", query = "SELECT c FROM Clauses c WHERE c.status = :status")})
public class Clauses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "clausesname")
    private String clausesname;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
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
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    public Clauses() {
    }

    public Clauses(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClausesname() {
        return clausesname;
    }

    public void setClausesname(String clausesname) {
        this.clausesname = clausesname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Clauses)) {
            return false;
        }
        Clauses other = (Clauses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.Clauses[ id=" + id + " ]";
    }
    
}
