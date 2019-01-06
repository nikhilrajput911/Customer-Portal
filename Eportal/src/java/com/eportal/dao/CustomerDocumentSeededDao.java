/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
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
public class CustomerDocumentSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerDocument(CustomerDocumentSeeded doc)
    {
       int id = (int) this.template.save(doc);
       return id;
    }
    public void updateCustomerDocument(CustomerDocumentSeeded doc)
    {
        this.template.update(doc);
    }
    public void deleteCustomerDocument(CustomerDocumentSeeded doc)
    {
        this.template.delete(doc);
    }
    public CustomerDocumentSeeded getCustomerDocumentId(int id)
    {
        CustomerDocumentSeeded doc = this.template.get(CustomerDocumentSeeded.class, id);
        return doc;
    }
    public List<?> getAllCustomerDocument()
    {
        List<CustomerDocumentSeeded> doc = new ArrayList<>();
        doc = this.template.loadAll(CustomerDocumentSeeded.class);
        return doc;
    }
    
}
