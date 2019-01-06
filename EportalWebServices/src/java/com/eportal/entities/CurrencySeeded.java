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
@Table(name = "bpaas_currenciesseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrencySeeded.findAll", query = "SELECT c FROM CurrencySeeded c"),
    @NamedQuery(name = "CurrencySeeded.findById", query = "SELECT c FROM CurrencySeeded c WHERE c.id = :id"),
    @NamedQuery(name = "CurrencySeeded.findByCountry", query = "SELECT c FROM CurrencySeeded c WHERE c.country = :country"),
    @NamedQuery(name = "CurrencySeeded.findByCurrency", query = "SELECT c FROM CurrencySeeded c WHERE c.currency = :currency"),
    @NamedQuery(name = "CurrencySeeded.findByCode", query = "SELECT c FROM CurrencySeeded c WHERE c.code = :code"),
    @NamedQuery(name = "CurrencySeeded.findBySymbol", query = "SELECT c FROM CurrencySeeded c WHERE c.symbol = :symbol"),
    @NamedQuery(name = "CurrencySeeded.findByThousandSeparator", query = "SELECT c FROM CurrencySeeded c WHERE c.thousandSeparator = :thousandSeparator"),
    @NamedQuery(name = "CurrencySeeded.findByDecimalSeparator", query = "SELECT c FROM CurrencySeeded c WHERE c.decimalSeparator = :decimalSeparator")})
public class CurrencySeeded implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "country")
    private String country;
    @Size(max = 100)
    @Column(name = "currency")
    private String currency;
    @Size(max = 25)
    @Column(name = "code")
    private String code;
    @Size(max = 25)
    @Column(name = "symbol")
    private String symbol;
    @Size(max = 10)
    @Column(name = "thousand_separator")
    private String thousandSeparator;
    @Size(max = 10)
    @Column(name = "decimal_separator")
    private String decimalSeparator;

    public CurrencySeeded() {
    }

    public CurrencySeeded(Integer id) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
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
        if (!(object instanceof CurrencySeeded)) {
            return false;
        }
        CurrencySeeded other = (CurrencySeeded) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.CurrencySeeded[ id=" + id + " ]";
    }
    
}
