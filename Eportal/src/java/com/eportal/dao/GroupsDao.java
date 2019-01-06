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
public class GroupsDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveGroup(Groups group)
    {
       int id = (int) this.template.save(group);
       return id;
    }
    public void updateGroups(Groups group)
    {
        this.template.update(group);
    }
    public void deleteGroups(Groups group)
    {
        this.template.delete(group);
    }
    public Groups getGroupById(int id)
    {
        Groups user = this.template.get(Groups.class, id);
        return user;
    }
    public List<?> getAllGroups()
    {
        List<Groups> group = new ArrayList<>();
        group = this.template.loadAll(Groups.class);
        return group;
    }
    public List<?> getAllActiveGroups(String status)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("Groups.findByStatus", "status", status);
        return list;
    }
}
