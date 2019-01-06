/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.UserSecSeeded;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class UserSecSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSecQue(UserSecSeeded secSeeded)
    {
       int id = (int) this.template.save(secSeeded);
       return id;
    }
    public void updateSecQue(UserSecSeeded secSeeded)
    {
        this.template.update(secSeeded);
    }
    public void deleteSecQue(UserSecSeeded secSeeded)
    {
        this.template.delete(secSeeded);
    }
    public UserSecSeeded getSecQueById(int id)
    {
        UserSecSeeded secSeeded = this.template.get(UserSecSeeded.class, id);
        return secSeeded;
    }
    public List<UserSecSeeded> getSecQues()
    {
        List<UserSecSeeded> secQues = new ArrayList<>();
        secQues = this.template.loadAll(UserSecSeeded.class);
        return secQues;
    }
    
}
