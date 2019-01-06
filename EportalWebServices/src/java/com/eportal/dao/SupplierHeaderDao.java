/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierHeader;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


public class SupplierHeaderDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public int saveSupplierHeader(SupplierHeader header)
    {
       int id = (int) this.template.save(header);
       return id;
    }
    
    public void updateSupplierHeader(SupplierHeader header)
    {
        this.template.update(header);
    }
    public void deleteSupplierHeader(SupplierHeader header)
    {
        this.template.delete(header);
    }
    public SupplierHeader getSupplierHeaderById(int id)
    {
        SupplierHeader header = this.template.get(SupplierHeader.class, id);
        return header;
    }
    public List<?> getSupplierHeaders()
    {
        List<SupplierHeader> header;
        header = this.template.loadAll(SupplierHeader.class);
        return header;
    }
    public List<?> findByRfqId(int rfqId)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierHeader.findByWorkOrderRfqHeaderId", "rfqid", rfqId);
        return list;
    }
    public List<?> findBySupplierAndRfqId(int rfqId, int supplierId)
    {
        String[] params =  new String[2];
        params[0] = "rfqid";
        params[1] = "supplierId";
        
        Object[] values =  new Object[2];
        values[0] = rfqId;
        values[1] = supplierId;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierHeader.findBySupplierAndRfqId", params, values);
        return list;
    }
    public List<?> findBySupplierSelectionIds(List<Integer> ids)
    {

        List<?> supplierHeader =  this.template.findByCriteria(
                DetachedCriteria.forClass(SupplierHeader.class)
                .add(Restrictions.in("bPaasSupplierSelectionTableid.id", ids))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
//        System.out.println("size in dao: " + supplierHeader);
        return supplierHeader;
    }
}
