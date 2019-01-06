/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierStatusSeeded;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierStatusSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierStatus(SupplierStatusSeeded suppStatus)
    {
       int id = (int) this.template.save(suppStatus);
       return id;
    }
    public void updateSupplierStatus(SupplierStatusSeeded suppStatus)
    {
        this.template.update(suppStatus);
    }
    public void deleteSupplierStatus(SupplierStatusSeeded suppStatus)
    {
        this.template.delete(suppStatus);
    }
    public SupplierStatusSeeded getSupplierStatusById(int id)
    {
        SupplierStatusSeeded suppStatus = this.template.get(SupplierStatusSeeded.class, id);
        return suppStatus;
    }
    public List<?> getSupplierStatus()
    {
        List<SupplierStatusSeeded> suppStatus = new ArrayList<>();
        suppStatus = this.template.loadAll(SupplierStatusSeeded.class);
        return suppStatus;
    }
    
}
