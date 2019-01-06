/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierUserRegContactDetails;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierUserRegContactDetailsDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierUserRegContactDetails(SupplierUserRegContactDetails supplierUserRegContactDetails)
    {
       int id = (int) this.template.save(supplierUserRegContactDetails);
       return id;
    }
    public void updateSupplierUserRegContactDetails(SupplierUserRegContactDetails supplierUserRegContactDetails)
    {
        this.template.update(supplierUserRegContactDetails);
    }
    public void deleteSupplierUserRegContactDetails(SupplierUserRegContactDetails supplierUserRegContactDetails)
    {
        this.template.delete(supplierUserRegContactDetails);
    }
    public SupplierUserRegContactDetails getSupplierUserRegContactDetailsById(int id)
    {
        SupplierUserRegContactDetails supplierUserRegContactDetails = this.template.get(SupplierUserRegContactDetails.class, id);
        return supplierUserRegContactDetails;
    }
    public List<?> getSupplierUserRegContactDetails()
    {
        List<SupplierUserRegContactDetails> supplierUserRegContactDetails = new ArrayList<>();
        supplierUserRegContactDetails = this.template.loadAll(SupplierUserRegContactDetails.class);
        return supplierUserRegContactDetails;
    }   
    public List<?> findBySupplierId(int id)
    {
        List<?> suppContact = this.template.findByNamedQueryAndNamedParam("SupplierUserRegContactDetails.findBySupplierId", "id", id);
        return suppContact;
    }
}
