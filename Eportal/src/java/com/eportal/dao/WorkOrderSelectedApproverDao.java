/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.WorkOrderSelectedApprover;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class WorkOrderSelectedApproverDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveWorkOrderSelectedApprover(WorkOrderSelectedApprover approver)
    {
       int id = (int) this.template.save(approver);
       return id;
    }
    public void updateWorkOrderSelectedApprover(WorkOrderSelectedApprover approver)
    {
        this.template.update(approver);
    }
    public void deleteWorkOrderSelectedApprover(WorkOrderSelectedApprover approver)
    {
        this.template.delete(approver);
    }
    public WorkOrderSelectedApprover getWorkOrderSelectedApproverById(int id)
    {
        WorkOrderSelectedApprover approver = this.template.get(WorkOrderSelectedApprover.class, id);
        return approver;
    }
    public List<?> getWorkOrderSelectedApprover()
    {
        List<WorkOrderSelectedApprover> approver = new ArrayList<>();
        approver = this.template.loadAll(WorkOrderSelectedApprover.class);
        return approver;
    }
    public List<?> findByHeaderId(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderSelectedApprover.findByHeaderId", "rfqid", id);
        return list;
    }
    public List<?> findByAssignedTo(String username)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderSelectedApprover.findByAssignedTo", "assignedTo", username);
        return list;
    }
    public List<?> findByRfqId(int rfqId)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderSelectedApprover.findByHeaderId", "rfqid", rfqId);
        return list;
    }
    public List<?> findByApprovername(String username)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderSelectedApprover.findByUsername", "username", username);
        return list;
    }
    public List<?> findByAssignedToUser(String username)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderSelectedApprover.findByAssignedToUser", "assignedToUsername", username);
        return list;
    }
}