/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.WorkOrderAddclauses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RaphelTudu
 */
@Transactional 
public class WorkOrderAddclausesDao {
    
    HibernateTemplate template;
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveWorkOrderRfqClauses(WorkOrderAddclauses rfqClauses)
    {
       int id = (int) this.template.save(rfqClauses);
       return id;
    }
    public void updateWorkOrderRfqClauses(WorkOrderAddclauses rfqClauses)
    {
        this.template.update(rfqClauses);
    }
    public void deleteWorkOrderRfqClauses(WorkOrderAddclauses rfqClauses)
    {
        this.template.delete(rfqClauses);
    }
    public WorkOrderAddclauses getWorkOrderRfqClausesById(int id)
    {
        WorkOrderAddclauses rfqClauses = this.template.get(WorkOrderAddclauses.class, id);
        return rfqClauses;
    }
    public List<?> getWorkOrderRfqClauses()
    {
        List<WorkOrderAddclauses> rfqClauses = new ArrayList<>();
        rfqClauses = this.template.loadAll(WorkOrderAddclauses.class);
        return rfqClauses;
    }
    public List<?> findByHeaderId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderAddclauses.findByHeaderId", "rfqid", id);
        return list;
    }
}