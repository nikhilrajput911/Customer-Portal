/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.dao;

import com.eportal.entities.CustomerMail;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CustomerMailDao {

    HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public int saveMail(CustomerMail mail) {
        int id = (int) this.template.save(mail);
        return id;
    }

    public void updateMail(CustomerMail mail) {
        this.template.update(mail);
    }

    public void deleteMail(CustomerMail mail) {
        this.template.delete(mail);
    }

    public CustomerMail getMailById(int id) {
        CustomerMail mail = this.template.get(CustomerMail.class, id);
        return mail;
    }

    public List<?> getMail() {
        List<CustomerMail> mail;
        mail = this.template.loadAll(CustomerMail.class);
        return mail;
    }

    public List<?> findByFromAndToDateAndMailType(Date from, Date to, String mailType) {

        List<?> list;

        if (mailType.equalsIgnoreCase("All")) {
            
            System.out.println("if Query");
            
            String[] params = new String[2];
            params[0] = "fromDate";
            params[1] = "toDate";
            
            Object[] values = new Object[2];
            values[0] = from;
            values[1] = to;
            
            list = this.template.findByNamedQueryAndNamedParam("CustomerMail.findByFromAndToDate", params, values);
        } else {

            System.out.println("else Query");
            
            String[] params = new String[3];
            params[0] = "fromDate";
            params[1] = "toDate";
            params[2] = "mailType";

            Object[] values = new Object[3];
            values[0] = from;
            values[1] = to;
            values[2] = mailType;

            list = this.template.findByNamedQueryAndNamedParam("CustomerMail.findByFromAndToDateAndMailType", params, values);
        }

        return list;

    }
}
