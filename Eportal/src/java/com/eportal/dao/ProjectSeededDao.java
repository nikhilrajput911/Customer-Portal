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
import com.eportal.entities.ProjectSeeded;
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
public class ProjectSeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveProjectSeeded(ProjectSeeded project)
    {
       int id = (int) this.template.save(project);
       return id;
    }
    public void updateProjectSeeded(ProjectSeeded project)
    {
        this.template.update(project);
    }
    public void deleteProjectSeeded(ProjectSeeded project)
    {
        this.template.delete(project);
    }
    public ProjectSeeded getProjectSeededById(int id)
    {
        ProjectSeeded project = this.template.get(ProjectSeeded.class, id);
        return project;
    }
    public List<?> getAllProjectSeeded()
    {
        List<ProjectSeeded> project = new ArrayList<>();
        project = this.template.loadAll(ProjectSeeded.class);
        return project;
    }
    public List<?> findByCustomerId(int customerId)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("ProjectSeeded.findByCustomerId", "cid", customerId);
        return list;
    }
}
