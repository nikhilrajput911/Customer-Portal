/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerUserTrackingReport;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerUserTrackingReportDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerUserTrackingReport(CustomerUserTrackingReport report)
    {
       int id = (int) this.template.save(report);
       return id;
    }
    public void updateCustomerUserTrackingReport(CustomerUserTrackingReport report)
    {
        this.template.update(report);
    }
    public void deleteCustomerUserTrackingReport(CustomerUserTrackingReport report)
    {
        this.template.delete(report);
    }
    public CustomerUserTrackingReport getCustomerUserTrackingReportById(int id)
    {
        CustomerUserTrackingReport report = this.template.get(CustomerUserTrackingReport.class, id);
        return report;
    }
    public List<?> getAllQues()
    {
        List<CustomerUserTrackingReport> report;
        report = this.template.loadAll(CustomerUserTrackingReport.class);
        return report;
    }
    public List<?> findByfromDateAndToDate(Date from, Date to)
    {
        String[] params =  new String[2];
        params[0] = "fromDate";
        params[1] = "toDate";
        
        Object[] values =  new Object[2];
        values[0] = from;
        values[1] = to;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerUserTrackingReport.findByfromDateAndToDate", params, values);
        return list;
    }
}
