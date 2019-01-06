/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.WorkOrderRfqLineItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class WorkOrderRfqLineItemDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveWorkOrderRfqLineItem(WorkOrderRfqLineItem lineItem)
    {
       int id = (int) this.template.save(lineItem);
       return id;
    }
    public void updateWorkOrderRfqLineItem(WorkOrderRfqLineItem lineItem)
    {
        this.template.update(lineItem);
    }
    public void deleteWorkOrderRfqLineItem(WorkOrderRfqLineItem lineItem)
    {
        this.template.delete(lineItem);
    }
    public WorkOrderRfqLineItem getWorkOrderRfqLineItemById(int id)
    {
        WorkOrderRfqLineItem lineItem = this.template.get(WorkOrderRfqLineItem.class, id);
        return lineItem;
    }
    public List<?> getWorkOrderRfqLineItem()
    {
        List<WorkOrderRfqLineItem> lineItem = new ArrayList<WorkOrderRfqLineItem>();
        lineItem = this.template.loadAll(WorkOrderRfqLineItem.class);
        return lineItem;
    }
    public List<?> findByHeaderId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderRfqLineItem.findByHeaderId", "rfqid", id);
        return list;
    }
}