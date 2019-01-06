/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerProjectMapping;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerProjectMappingDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerProjectMapping(CustomerProjectMapping mapping)
    {
       int id = (int) this.template.save(mapping);
       return id;
    }
    public void updateCustomerProjectMapping(CustomerProjectMapping mapping)
    {
        this.template.update(mapping);
    }
    public void deleteCustomerProjectMapping(CustomerProjectMapping mapping)
    {
        this.template.delete(mapping);
    }
    public CustomerProjectMapping getCustomerProjectMappingById(int id)
    {
        CustomerProjectMapping mapping = this.template.get(CustomerProjectMapping.class, id);
        return mapping;
    }
    public List<?> getAllCustomerProjectMapping()
    {
        List<CustomerProjectMapping> mapping = new ArrayList<>();
        mapping = this.template.loadAll(CustomerProjectMapping.class);
        return mapping;
    }
    public List<?> findByCustomerId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerProjectMapping.findByCustomerUserId", "customerUserId", id);
        return list;
    }
    public List<?> findByCustomerIdAndProjectId(int id, int pid)
    {
        String[] params =  new String[2];
        params[0] = "id";
        params[1] = "pid";
        
        Object[] values =  new Object[2];
        values[0] = id;
        values[1] = pid;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerProjectMapping.findByCustomerUserIdAndProjectId", params, values);
        return list;
    }
}
