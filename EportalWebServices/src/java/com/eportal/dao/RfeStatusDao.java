/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.RfeStatus;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class RfeStatusDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveStatus(RfeStatus status)
    {
       int id = (int) this.template.save(status);
       return id;
    }
    public void updateStatus(RfeStatus status)
    {
        this.template.update(status);
    }
    public void deleteStatus(RfeStatus status)
    {
        this.template.delete(status);
    }
    public RfeStatus getStatusById(int id)
    {
        RfeStatus status = this.template.get(RfeStatus.class, id);
        return status;
    }
    public List<?> getStatus()
    {
        List<RfeStatus> status = new ArrayList<>();
        status = this.template.loadAll(RfeStatus.class);
        return status;
    }
    
}
