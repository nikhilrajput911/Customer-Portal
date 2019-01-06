/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerMail;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;


public class CustomerMailDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveClauses(CustomerMail mail)
    {
       int id = (int) this.template.save(mail);
       return id;
    }
    public void updateClauses(CustomerMail mail)
    {
        this.template.update(mail);
    }
    public void deleteClauses(CustomerMail mail)
    {
        this.template.delete(mail);
    }
    public CustomerMail getClausesById(int id)
    {
        CustomerMail mail = this.template.get(CustomerMail.class, id);
        return mail;
    }
    public List<?> getClauses()
    {
        List<CustomerMail> mail;
        mail = this.template.loadAll(CustomerMail.class);
        return mail;
    }
    
}
