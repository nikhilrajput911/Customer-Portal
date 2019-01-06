/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerSecQueSeeded;
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
public class CustomerSecQueSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveQue(CustomerSecQueSeeded que)
    {
       int id = (int) this.template.save(que);
       return id;
    }
    public void updateQue(CustomerSecQueSeeded que)
    {
        this.template.update(que);
    }
    public void deleteQue(CustomerSecQueSeeded que)
    {
        this.template.delete(que);
    }
    public CustomerSecQueSeeded getQueById(int id)
    {
        CustomerSecQueSeeded que = this.template.get(CustomerSecQueSeeded.class, id);
        return que;
    }
    public List<?> getAllQues()
    {
        List<CustomerSecQueSeeded> que = new ArrayList<>();
        que = this.template.loadAll(CustomerSecQueSeeded.class);
        return que;
    }
    
}
