/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierUserBankDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierUserBankDetailDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierBankDetails(SupplierUserBankDetail supplierBankDetails)
    {
       int id = (int) this.template.save(supplierBankDetails);
       return id;
    }
    public void updateSupplierBankDetails(SupplierUserBankDetail supplierBankDetails)
    {
        this.template.update(supplierBankDetails);
    }
    public void deleteSupplierBankDetails(SupplierUserBankDetail supplierBankDetails)
    {
        this.template.delete(supplierBankDetails);
    }
    public SupplierUserBankDetail getSupplierBankDetailsById(int id)
    {
        SupplierUserBankDetail supplierBankDetails = this.template.get(SupplierUserBankDetail.class, id);
        return supplierBankDetails;
    }
    public List<?> getSupplierBankDetails()
    {
        List<SupplierUserBankDetail> supplierBankDetails = new ArrayList<>();
        supplierBankDetails = this.template.loadAll(SupplierUserBankDetail.class);
        return supplierBankDetails;
    }
    public List<?> findBySupplierId(int id)
    {
        List<?> suppBankObj = this.template.findByNamedQueryAndNamedParam("SupplierUserBankDetail.findBySupplierId", "id", id);
        return suppBankObj;
    }
}
