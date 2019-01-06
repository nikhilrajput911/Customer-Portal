/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierBankDetailsNew;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierBankDetailsNewDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierBankDetails(SupplierBankDetailsNew supplierBankDetails)
    {
       int id = (int) this.template.save(supplierBankDetails);
       return id;
    }
    public void updateSupplierBankDetails(SupplierBankDetailsNew supplierBankDetails)
    {
        this.template.update(supplierBankDetails);
    }
    public void deleteSupplierBankDetails(SupplierBankDetailsNew supplierBankDetails)
    {
        this.template.delete(supplierBankDetails);
    }
    public SupplierBankDetailsNew getSupplierBankDetailsById(int id)
    {
        SupplierBankDetailsNew supplierBankDetails = this.template.get(SupplierBankDetailsNew.class, id);
        return supplierBankDetails;
    }
    public List<?> getSupplierBankDetails()
    {
        List<SupplierBankDetailsNew> supplierBankDetails = new ArrayList<>();
        supplierBankDetails = this.template.loadAll(SupplierBankDetailsNew.class);
        return supplierBankDetails;
    }
    public List<?> findBySupplierId(int id)
    {
        List<?> suppBankObj = this.template.findByNamedQueryAndNamedParam("SupplierBankDetailsNew.findBySupplierId", "id", id);
        return suppBankObj;
    }
}
