/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerProfileUpdateReport;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerProfileUpdateReportDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerProfileUpdateReport(CustomerProfileUpdateReport report)
    {
       int id = (int) this.template.save(report);
       return id;
    }
    public void updateCustomerProfileUpdateReport(CustomerProfileUpdateReport report)
    {
        this.template.update(report);
    }
    public void deleteCustomerProfileUpdateReport(CustomerProfileUpdateReport report)
    {
        this.template.delete(report);
    }
    public CustomerProfileUpdateReport getCustomerProfileUpdateReportById(int id)
    {
        CustomerProfileUpdateReport report = this.template.get(CustomerProfileUpdateReport.class, id);
        return report;
    }
    public List<?> getAllCustomerProfileUpdateReport()
    {
        List<CustomerProfileUpdateReport> report;
        report = this.template.loadAll(CustomerProfileUpdateReport.class);
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
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerProfileUpdateReport.findByfromDateAndToDate", params, values);
        return list;
    }
}
