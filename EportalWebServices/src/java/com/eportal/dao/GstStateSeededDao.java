/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.GstStateSeeded;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class GstStateSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveGstState(GstStateSeeded gstState)
    {
       int id = (int) this.template.save(gstState);
       return id;
    }
    public void updateGstState(GstStateSeeded gstState)
    {
        this.template.update(gstState);
    }
    public void deleteGstState(GstStateSeeded gstState)
    {
        this.template.delete(gstState);
    }
    public GstStateSeeded getGstStateById(int id)
    {
        GstStateSeeded gstState = this.template.get(GstStateSeeded.class, id);
        return gstState;
    }
    public List<?> getGstStates()
    {
        List<GstStateSeeded> gstState = new ArrayList<>();
        gstState = this.template.loadAll(GstStateSeeded.class);
        return gstState;
    }
    
}
