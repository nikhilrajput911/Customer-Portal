/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierAddattachment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;


public class SupplierAddattachmentDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierAddattachment(SupplierAddattachment attachment)
    {
       int id = (int) this.template.save(attachment);
       return id;
    }
    public void updateSupplierAddattachment(SupplierAddattachment attachment)
    {
        this.template.update(attachment);
    }
    public void deleteSupplierAddattachment(SupplierAddattachment attachment)
    {
        this.template.delete(attachment);
    }
    public SupplierAddattachment getSupplierAddattachmentById(int id)
    {
        SupplierAddattachment attachment = this.template.get(SupplierAddattachment.class, id);
        return attachment;
    }
    public List<?> getSupplierAddattachment()
    {
        List<SupplierAddattachment> attachment;
        attachment = this.template.loadAll(SupplierAddattachment.class);
        return attachment;
    }
    public List<?> findBySupplierHeaderId(int supplierHeaderId)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierAddattachment.findBySupplierHeaderId", "id", supplierHeaderId);
        return list;
    }
}
