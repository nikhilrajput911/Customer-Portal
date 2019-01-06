/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerSeeded;
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
public class CustomerSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerSeeded(CustomerSeeded customer)
    {
       int id = (int) this.template.save(customer);
       return id;
    }
    public void updateCustomerSeeded(CustomerSeeded customer)
    {
        this.template.update(customer);
    }
    public void deleteCustomerSeeded(CustomerSeeded customer)
    {
        this.template.delete(customer);
    }
    public CustomerSeeded getCustomerSeededById(int id)
    {
        CustomerSeeded customer = this.template.get(CustomerSeeded.class, id);
        return customer;
    }
    public List<?> getAllCustomerSeeded()
    {
        List<CustomerSeeded> customer = new ArrayList<>();
        customer = this.template.loadAll(CustomerSeeded.class);
        return customer;
    }
    public List<?> findByCustomerCode(String customerCode)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerSeeded.findByCustomercode", "customercode", customerCode);
        return list;
    }
    public List<?> findByAccountGroupType(String accountGroupType)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerSeeded.findByAccountGroupType", "accountGroupType", accountGroupType);
        return list;
    }
}
