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
@Table(name = "bpaas_usercountryseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCountrySeeded.findAll", query = "SELECT u FROM UserCountrySeeded u"),
    @NamedQuery(name = "UserCountrySeeded.findById", query = "SELECT u FROM UserCountrySeeded u WHERE u.id = :id"),
    @NamedQuery(name = "UserCountrySeeded.findByCountry", query = "SELECT u FROM UserCountrySeeded u WHERE u.country = :country"),
    @NamedQuery(name = "UserCountrySeeded.findByAf1", query = "SELECT u FROM UserCountrySeeded u WHERE u.af1 = :af1"),
    @NamedQuery(name = "UserCountrySeeded.findByAf2", query = "SELECT u FROM UserCountrySeeded u WHERE u.af2 = :af2"),
    @NamedQuery(name = "UserCountrySeeded.findByAf3", query = "SELECT u FROM UserCountrySeeded u WHERE u.af3 = :af3"),
    @NamedQuery(name = "UserCountrySeeded.findByAf4", query = "SELECT u FROM UserCountrySeeded u WHERE u.af4 = :af4"),
    @NamedQuery(name = "UserCountrySeeded.findByAf5", query = "SELECT u FROM UserCountrySeeded u WHERE u.af5 = :af5"),
    @NamedQuery(name = "UserCountrySeeded.findByStatus", query = "SELECT u FROM UserCountrySeeded u WHERE u.status = :status")})
public class UserCountrySeeded implements Serializable {
    @Size(max = 10)
    @Column(name = "country_code")
    private String countryCode;
    @Size(max = 100)
    @Column(name = "country_name")
    private String countryName;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "country")
    private String country;
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

    public UserCountrySeeded() {
    }

    public UserCountrySeeded(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(object instanceof UserCountrySeeded)) {
            return false;
        }
        UserCountrySeeded other = (UserCountrySeeded) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.UserCountrySeeded[ id=" + id + " ]";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
}
