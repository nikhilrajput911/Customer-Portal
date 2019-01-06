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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ng_cp_passwordconfiguration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordConfiguration.findAll", query = "SELECT p FROM PasswordConfiguration p"),
    @NamedQuery(name = "PasswordConfiguration.findById", query = "SELECT p FROM PasswordConfiguration p WHERE p.id = :id"),
    @NamedQuery(name = "PasswordConfiguration.findByPassLength", query = "SELECT p FROM PasswordConfiguration p WHERE p.passLength = :passLength"),
    @NamedQuery(name = "PasswordConfiguration.findBySpecialChar", query = "SELECT p FROM PasswordConfiguration p WHERE p.specialChar = :specialChar"),
    @NamedQuery(name = "PasswordConfiguration.findByUpparChar", query = "SELECT p FROM PasswordConfiguration p WHERE p.upparChar = :upparChar"),
    @NamedQuery(name = "PasswordConfiguration.findByLowerChar", query = "SELECT p FROM PasswordConfiguration p WHERE p.lowerChar = :lowerChar"),
    @NamedQuery(name = "PasswordConfiguration.findByNumericCount", query = "SELECT p FROM PasswordConfiguration p WHERE p.numericCount = :numericCount")})
public class PasswordConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "pass_length")
    private String passLength;
    @Size(max = 100)
    @Column(name = "special_char")
    private String specialChar;
    @Size(max = 100)
    @Column(name = "uppar_char")
    private String upparChar;
    @Size(max = 100)
    @Column(name = "lower_char")
    private String lowerChar;
    @Size(max = 100)
    @Column(name = "numeric_count")
    private String numericCount;

    public PasswordConfiguration() {
    }

    public PasswordConfiguration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassLength() {
        return passLength;
    }

    public void setPassLength(String passLength) {
        this.passLength = passLength;
    }

    public String getSpecialChar() {
        return specialChar;
    }

    public void setSpecialChar(String specialChar) {
        this.specialChar = specialChar;
    }

    public String getUpparChar() {
        return upparChar;
    }

    public void setUpparChar(String upparChar) {
        this.upparChar = upparChar;
    }

    public String getLowerChar() {
        return lowerChar;
    }

    public void setLowerChar(String lowerChar) {
        this.lowerChar = lowerChar;
    }

    public String getNumericCount() {
        return numericCount;
    }

    public void setNumericCount(String numericCount) {
        this.numericCount = numericCount;
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
        if (!(object instanceof PasswordConfiguration)) {
            return false;
        }
        PasswordConfiguration other = (PasswordConfiguration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.PasswordConfiguration[ id=" + id + " ]";
    }
    
}
