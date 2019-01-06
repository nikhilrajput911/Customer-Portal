/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerNotification;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.Groups;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierUserAttachment;
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
public class CustomerNotificationDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerNotification(CustomerNotification notify)
    {
       int id = (int) this.template.save(notify);
       return id;
    }
    public void updateCustomerNotifications(CustomerNotification notify)
    {
        this.template.update(notify);
    }
    public void deleteCustomerNotifications(CustomerNotification notify)
    {
        this.template.delete(notify);
    }
    public CustomerNotification getCustomerNotificationById(int id)
    {
        CustomerNotification notify = this.template.get(CustomerNotification.class, id);
        return notify;
    }
    public List<?> getAllCustomerNotifications()
    {
        List<CustomerNotification> notify = new ArrayList<>();
        notify = this.template.loadAll(CustomerNotification.class);
        return notify;
    }
    public List<?> findByCustomerUserId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerNotification.findByCustomerUserId", "id", id);
        return list;
    }
    
}
