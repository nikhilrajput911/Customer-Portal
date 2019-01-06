/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.TypeOfBusinessSeeded;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class TypeOfBusinessSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveGstState(TypeOfBusinessSeeded bussType)
    {
       int id = (int) this.template.save(bussType);
       return id;
    }
    public void updateGstState(TypeOfBusinessSeeded bussType)
    {
        this.template.update(bussType);
    }
    public void deleteGstState(TypeOfBusinessSeeded bussType)
    {
        this.template.delete(bussType);
    }
    public TypeOfBusinessSeeded getGstStateById(int id)
    {
        TypeOfBusinessSeeded bussType = this.template.get(TypeOfBusinessSeeded.class, id);
        return bussType;
    }
    public List<?> getGstStates()
    {
        List<TypeOfBusinessSeeded> bussType = new ArrayList<>();
        bussType = this.template.loadAll(TypeOfBusinessSeeded.class);
        return bussType;
    }
    
}
