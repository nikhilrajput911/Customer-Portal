/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierLineitem;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


public class SupplierLineitemDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public int saveSupplierLineitem(SupplierLineitem lineItem)
    {
       int id = (int) this.template.save(lineItem);
       return id;
    }
    public void updateSupplierLineitem(SupplierLineitem lineItem)
    {
        this.template.update(lineItem);
    }
    public void deleteSupplierLineitem(SupplierLineitem lineItem)
    {
        this.template.delete(lineItem);
    }
    public SupplierLineitem getSupplierLineitemById(int id)
    {
        SupplierLineitem lineItem = this.template.get(SupplierLineitem.class, id);
        return lineItem;
    }
    public List<?> getSupplierLineitem()
    {
        List<SupplierLineitem> lineItem;
        lineItem = this.template.loadAll(SupplierLineitem.class);
        return lineItem;
    }
    public List<?> findBySupplierHeaderId(int supplierHeaderId)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierLineitem.findBySupplierHeaderId", "id", supplierHeaderId);
        return list;
    }
    public List<?> findBySupplierHeaderIds(List<Integer> ids)
    {

        List<?> supplierHeader =  this.template.findByCriteria(
                DetachedCriteria.forClass(SupplierLineitem.class)
                .add(Restrictions.in("workOrderSupplierHeaderTableid.id", ids)));
//        System.out.println("size: " + supplierHeader.size());
        return supplierHeader;
    }
}
