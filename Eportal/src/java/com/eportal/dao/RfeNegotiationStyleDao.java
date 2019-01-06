/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.RfeNegotiationStyle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class RfeNegotiationStyleDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveNegotiationStyle(RfeNegotiationStyle style)
    {
       int id = (int) this.template.save(style);
       return id;
    }
    public void updateNegotiationStyle(RfeNegotiationStyle style)
    {
        this.template.update(style);
    }
    public void deleteNegotiationStyle(RfeNegotiationStyle style)
    {
        this.template.delete(style);
    }
    public RfeNegotiationStyle getNegotiationStyleById(int id)
    {
        RfeNegotiationStyle style = this.template.get(RfeNegotiationStyle.class, id);
        return style;
    }
    public List<?> getNegotiationStyles()
    {
        List<RfeNegotiationStyle> style = new ArrayList<>();
        style = this.template.loadAll(RfeNegotiationStyle.class);
        return style;
    }
    
}
