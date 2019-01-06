/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierGroup;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierGroupDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplierGroup(SupplierGroup group)
    {
       int id = (int) this.template.save(group);
       return id;
    }
    public void updateSupplierGroups(SupplierGroup group)
    {
        this.template.update(group);
    }
    public void deleteSupplierGroups(SupplierGroup group)
    {
        this.template.delete(group);
    }
    public SupplierGroup getSupplierGroupById(int id)
    {
        SupplierGroup group = this.template.get(SupplierGroup.class, id);
        return group;
    }
    public List<?> getAllSupplierGroups()
    {
        List<SupplierGroup> group = new ArrayList<>();
        group = this.template.loadAll(SupplierGroup.class);
        return group;
    }
    public List<?> findByLeftJoinOnSupplierGroup(int userId)
    {
        List<?> list = this.template.find("from SupplierGroup supplier RIGHT JOIN supplier.bpaasCustomersubuserId where supplier.bpaasCustomersubuserId.id = " + userId);
        
        return list;
    }
    public List<?> findByGroupId(int gid)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierGroup.findByGroupId", "gid", gid);
        return list;
    }
    public List<?> findByGroupIdAndCustomerId(int gid, int custId)
    {
        String[] params =  new String[2];
        params[0] = "gid";
        params[1] = "custId";
        
        Object[] values =  new Object[2];
        values[0] = gid;
        values[1] = custId;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierGroup.findByGroupIdAndCustomerId", params, values);
        return list;
    }
}
