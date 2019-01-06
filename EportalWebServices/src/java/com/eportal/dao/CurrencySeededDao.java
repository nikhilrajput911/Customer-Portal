/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.SupplierUserAttachment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CurrencySeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCurrency(CurrencySeeded currency)
    {
       int id = (int) this.template.save(currency);
       return id;
    }
    public void updateCurrency(CurrencySeeded currency)
    {
        this.template.update(currency);
    }
    public void deleteCurrency(CurrencySeeded currency)
    {
        this.template.delete(currency);
    }
    public CurrencySeeded getCurrencyId(int id)
    {
        CurrencySeeded currency = this.template.get(CurrencySeeded.class, id);
        return currency;
    }
    public List<?> getAllCurrency()
    {
        List<CurrencySeeded> currency = new ArrayList<>();
        currency = this.template.loadAll(CurrencySeeded.class);
        return currency;
    }
    
}
