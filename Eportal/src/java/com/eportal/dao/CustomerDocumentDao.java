/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
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
public class CustomerDocumentDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerDocument(CustomerDocument doc)
    {
       int id = (int) this.template.save(doc);
       return id;
    }
    public void updateCustomerDocument(CustomerDocument doc)
    {
        this.template.update(doc);
    }
    public void deleteCustomerDocument(CustomerDocument doc)
    {
        this.template.delete(doc);
    }
    public CustomerDocument getCustomerDocumentId(int id)
    {
        CustomerDocument doc = this.template.get(CustomerDocument.class, id);
        return doc;
    }
    public List<?> getAllCustomerDocument()
    {
        List<CustomerDocument> doc = new ArrayList<>();
        doc = this.template.loadAll(CustomerDocument.class);
        return doc;
    }
    public List<?> findByDocumentByDocType(String docType, String name)
    {
//        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerDocument.findByByDocType", "documenttype", docType);
//        return list;
        List<?> list = null;
        

        Criterion docTypeCn = Restrictions.eq("documenttype", docType);
        Criterion docNameCn = Restrictions.like("documentname", name, MatchMode.ANYWHERE);
        
        if(name.equalsIgnoreCase("*"))
        {
            list = this.template.findByCriteria(
                DetachedCriteria.forClass(CustomerDocument.class).add(docTypeCn));
        }
        else
        {
            list = this.template.findByCriteria(
                DetachedCriteria.forClass(CustomerDocument.class).add(docTypeCn).add(docNameCn));
        }
        return list;
        
    }
}
