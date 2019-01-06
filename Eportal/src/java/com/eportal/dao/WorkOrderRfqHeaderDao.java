/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.WorkOrderRfqHeader;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional 
public class WorkOrderRfqHeaderDao {
    
    HibernateTemplate template;
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveWorkOrderRfqHeader(WorkOrderRfqHeader rfqHeader)
    {
       int id = (int) this.template.save(rfqHeader);
       return id;
    }
    
    @Transactional
    public void updateWorkOrderRfqHeader(WorkOrderRfqHeader rfqHeader)
    {
        this.template.update(rfqHeader);
        
    }
    
    //@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteWorkOrderRfqHeader(WorkOrderRfqHeader rfqHeader)
    {
        this.template.delete(rfqHeader);
    }
    public WorkOrderRfqHeader getWorkOrderRfqHeaderById(int id)
    {
        WorkOrderRfqHeader rfqHeader = this.template.get(WorkOrderRfqHeader.class, id);
        return rfqHeader;
    }
    public List<?> getWorkOrderRfqHeaders()
    {
        List<WorkOrderRfqHeader> rfqHeader = new ArrayList<>();
        rfqHeader = this.template.loadAll(WorkOrderRfqHeader.class);
        return rfqHeader;
    }
    public List<?> findByUserdetail_Id(int userId)
    {
//        Query query = this.template.getSessionFactory().getCurrentSession().createSQLQuery("select nextval('seq_name')");
//        long seq = ((BigInteger) query.uniqueResult()).longValue();
        
//        DetachedCriteria criteria = DetachedCriteria.forClass(WorkOrderRfqHeader.class)
//                .add(Restrictions.eq("UserDetail_userid", userId));
//        List<?> list = this.template.findByCriteria(criteria);
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderRfqHeader.findByUserDetailUserIdOrderByCreationdateDesc", "userDetailuserid", userId);  
        return list;
    }
    public List<?> findByUserdetail_IdAndRfqStatus(int userId, String status)
    {
        String[] params =  new String[2];
        params[0] = "userDetailuserid";
        params[1] = "rfqstatus";
        
        Object[] values =  new Object[2];
        values[0] = userId;
        values[1] = status;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("WorkOrderRfqHeader.findByUserDetailUserIdAndStatusOrderByCreationdateDesc", "userDetailuserid", userId);  
        return list;
    }
    public List<?> findByRfqStatus(String status)
    {
        List<?> list =  this.template.findByNamedQueryAndNamedParam("WorkOrderRfqHeader.findByRfqstatus", "rfqstatus", status);
        return list;
    }
    public List<?> findByManyStatus()
    {
        List<?> list =  this.template.findByNamedQuery("WorkOrderRfqHeader.findByManyStatus");
        return list;
    }
    public List<?> findByRfqTitle(String title)
    {
        List<?> list = this.template.findByCriteria(
                            DetachedCriteria.forClass(WorkOrderRfqHeader.class)
                            .add(Restrictions.eq("rFQTitle", title)));
        return list;
    }
    public List<?> findByRfqId(int rfqid)
    {
        List<?> list = this.template.findByCriteria(
                            DetachedCriteria.forClass(WorkOrderRfqHeader.class)
                            .add(Restrictions.eq("rfqid", rfqid)));
        return list;
    }
    public List<?> findByLeftJoinOnSupplierSelection()
    {
        List<?> list = this.template.findByCriteria(
                            DetachedCriteria.forClass(WorkOrderRfqHeader.class)
                                .setFetchMode("rfqid.bPaasWorkOrderRFQHeaderRFQID", FetchMode.JOIN));
        
        System.out.println("findByLeftJoinOnSupplierSelection: " + list.size());
        System.out.println(list);
        
        return list;
    }
}