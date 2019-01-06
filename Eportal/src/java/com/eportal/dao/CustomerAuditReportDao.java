/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerAuditReport;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerAuditReportDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerAuditReport(CustomerAuditReport report)
    {
       int id = (int) this.template.save(report);
       return id;
    }
    public void updateCustomerAuditReport(CustomerAuditReport report)
    {
        this.template.update(report);
    }
    public void deleteCustomerAuditReport(CustomerAuditReport report)
    {
        this.template.delete(report);
    }
    public CustomerAuditReport getCustomerAuditReportById(int id)
    {
        CustomerAuditReport report = this.template.get(CustomerAuditReport.class, id);
        return report;
    }
    public List<?> getAllQues()
    {
        List<CustomerAuditReport> report;
        report = this.template.loadAll(CustomerAuditReport.class);
        return report;
    }
}
