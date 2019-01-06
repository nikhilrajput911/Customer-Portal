/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerAutoMail;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;


public class CustomerAutoMailDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerAutoMail(CustomerAutoMail mail)
    {
       int id = (int) this.template.save(mail);
       return id;
    }
    public void updateCustomerAutoMail(CustomerAutoMail mail)
    {
        this.template.update(mail);
    }
    public void deleteCustomerAutoMail(CustomerAutoMail mail)
    {
        this.template.delete(mail);
    }
    public CustomerAutoMail getCustomerAutoMailById(int id)
    {
        CustomerAutoMail mail = this.template.get(CustomerAutoMail.class, id);
        return mail;
    }
    public List<?> getClauses()
    {
        List<CustomerAutoMail> mail;
        mail = this.template.loadAll(CustomerAutoMail.class);
        return mail;
    }
    
}
