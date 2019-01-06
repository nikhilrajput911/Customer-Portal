/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RaphelTudu
 */
@Entity
@Table(name = "bpaas_negotiationstyleseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfeNegotiationStyle.findAll", query = "SELECT r FROM RfeNegotiationStyle r"),
    @NamedQuery(name = "RfeNegotiationStyle.findById", query = "SELECT r FROM RfeNegotiationStyle r WHERE r.id = :id"),
    @NamedQuery(name = "RfeNegotiationStyle.findByNegotiationstyle", query = "SELECT r FROM RfeNegotiationStyle r WHERE r.negotiationstyle = :negotiationstyle"),
    @NamedQuery(name = "RfeNegotiationStyle.findByStatus", query = "SELECT r FROM RfeNegotiationStyle r WHERE r.status = :status")})
public class RfeNegotiationStyle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "negotiationstyle")
    private String negotiationstyle;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    public RfeNegotiationStyle() {
    }

    public RfeNegotiationStyle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNegotiationstyle() {
        return negotiationstyle;
    }

    public void setNegotiationstyle(String negotiationstyle) {
        this.negotiationstyle = negotiationstyle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof RfeNegotiationStyle)) {
            return false;
        }
        RfeNegotiationStyle other = (RfeNegotiationStyle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.RfeNegotiationStyle[ id=" + id + " ]";
    }
    
}
