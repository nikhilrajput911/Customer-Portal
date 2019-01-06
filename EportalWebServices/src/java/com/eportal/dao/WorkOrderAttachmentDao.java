/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.WorkOrderAttachment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class WorkOrderAttachmentDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveWorkOrderAttachment(WorkOrderAttachment attch)
    {
       int id = (int) this.template.save(attch);
       return id;
    }
    public void updateWorkOrderAttachment(WorkOrderAttachment attch)
    {
        this.template.update(attch);
    }
    public void deleteWorkOrderAttachment(WorkOrderAttachment attch)
    {
        this.template.delete(attch);
    }
    public WorkOrderAttachment getWorkOrderAttachmentById(int id)
    {
        WorkOrderAttachment attch = this.template.get(WorkOrderAttachment.class, id);
        return attch;
    }
    public List<?> getWorkOrderAttachment()
    {
        List<WorkOrderAttachment> attch = new ArrayList<>();
        attch = this.template.loadAll(WorkOrderAttachment.class);
        return attch;
    }
    public List<?> findByHeaderId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderAttachment.findByHeaderId", "rfqid", id);
        return list;
    }
    public List<?> findByAttmtId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderAttachment.findById", "id", id);
        return list;
    }
}