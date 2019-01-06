/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierUserAttachment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierUserAttachmentDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierUserAttachment(SupplierUserAttachment supplierUserAttachment)
    {
       int id = (int) this.template.save(supplierUserAttachment);
       return id;
    }
    public void updateSupplierUserAttachment(SupplierUserAttachment supplierUserAttachment)
    {
        this.template.update(supplierUserAttachment);
    }
    public void deleteSupplierUserAttachment(SupplierUserAttachment supplierUserAttachment)
    {
        this.template.delete(supplierUserAttachment);
    }
    public SupplierUserAttachment getSupplierUserAttachmentById(int id)
    {
        SupplierUserAttachment supplierUserAttachment = this.template.get(SupplierUserAttachment.class, id);
        return supplierUserAttachment;
    }
    public List<?> getSupplierUserAttachment()
    {
        List<SupplierUserAttachment> supplierUserAttachment = new ArrayList<>();
        supplierUserAttachment = this.template.loadAll(SupplierUserAttachment.class);
        return supplierUserAttachment;
    }
    public List<?> findBySupplierId(int id)
    {
        List<?> suppAtt = this.template.findByNamedQueryAndNamedParam("SupplierUserAttachment.findBySupplierId", "id", id);
        return suppAtt;
    }
    
}
