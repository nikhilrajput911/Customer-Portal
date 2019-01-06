/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.SupplierUser;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class SupplierUserDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSupplier(SupplierUser supplier)
    {
       int id = (int) this.template.save(supplier);
       return id;
    }
    public void updateSupplier(SupplierUser supplier)
    {
        this.template.update(supplier);
    }
    public void deleteSupplier(SupplierUser supplier)
    {
        this.template.delete(supplier);
    }
    public SupplierUser getSupplierById(int id)
    {
        SupplierUser supplier = this.template.get(SupplierUser.class, id);
        return supplier;
    }
    public List<?> getSupplier()
    {
        List<SupplierUser> supplier = new ArrayList<>();
        supplier = this.template.loadAll(SupplierUser.class);
        return supplier;
    }
    public List<?> findAllSupplierOrderByCreationDate()
    {
        List<?> supplier = this.template.findByNamedQuery("SupplierUser.findAll");
        return supplier;
    }
    public List<?> findSupplierById(int id)
    {
        List<?> supplier = this.template.findByNamedQueryAndNamedParam("SupplierUser.findById", "id", id);
        return supplier;
    }
    public List<?> findBySupplierStatusOrderByCreationDateDesc(String status)
    {
        List<?> suppliers = this.template.findByNamedQueryAndNamedParam("SupplierUser.findBySupplierstatus", "supplierstatus", status);
        return suppliers;
    }
    
    public List<?> findByUsername(String username, String password)
    {
        String[] params =  new String[2];
        params[0] = "username";
        params[1] = "password";
        
        String[] values =  new String[2];
        values[0] = username;
        values[1] = password;

        List<?> user = this.template.findByNamedQueryAndNamedParam("SupplierUser.findByUsername", params, values);
        return user;
    }
    public List<?> findBySupplierstatus(String status)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierUser.findBySupplierstatus", "supplierstatus", status);
        return list;
    }
    public List<?> findByUsernameCheck(String username)
    {
        List<?> list = this.template.findByCriteria(DetachedCriteria.forClass(SupplierUser.class).add(Restrictions.eq("username", username)));
        return list;
    }
}
