/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierUserAttachment;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerSubUserDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerSubUser(CustomerSubUser user)
    {
       int id = (int) this.template.save(user);
       return id;
    }
    public void updateCustomerSubUser(CustomerSubUser user)
    {
        this.template.update(user);
    }
    public void deleteCustomerSubUser(CustomerSubUser user)
    {
        this.template.delete(user);
    }
    public CustomerSubUser getCustomerSubUserById(int id)
    {
        CustomerSubUser user = this.template.get(CustomerSubUser.class, id);
        return user;
    }
    public List<?> getAllCustomerSubUser()
    {
        List<CustomerSubUser> user = new ArrayList<>();
        user = this.template.loadAll(CustomerSubUser.class);
        return user;
    }
    public List<?> findByUsernameCheck(String username)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerSubUser.findByUsername", "username", username);
        return list;
    }
    public List<?> findAllCustomerExceptDeleteStatus(String status)
    {
//        List<?> list = this.template.findByCriteria(DetachedCriteria.forClass(CustomerSubUser.class)
//                                                    .add(Restrictions.ne("status", "Delete")));
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerSubUser.findByStatus", "status", status);
        return list;
    }
    public List<?> findByStatus(String status)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerSubUser.findByStatusAsActive", "status", status);
        return list;
    }
    public List<?> findByCustomerId(int id)
    {
        List<?> customer = this.template.findByNamedQueryAndNamedParam("CustomerSubUser.findById", "id", id);
        return customer;
    }
}
