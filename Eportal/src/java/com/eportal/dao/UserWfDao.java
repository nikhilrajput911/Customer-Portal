/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierUserAttachment;
import com.eportal.entities.UserWf;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class UserWfDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveUserWf(UserWf userwf)
    {
       int id = (int) this.template.save(userwf);
       return id;
    }
    public void updateUserWf(UserWf userwf)
    {
        this.template.update(userwf);
    }
    public void deleteUserWf(UserWf userwf)
    {
        this.template.delete(userwf);
    }
    public UserWf getUserWfId(int id)
    {
        UserWf userwf = this.template.get(UserWf.class, id);
        return userwf;
    }
    public List<?> getAllUserWf()
    {
        List<UserWf> userwf = new ArrayList<>();
        userwf = this.template.loadAll(UserWf.class);
        return userwf;
    }
    public List<?> findByRfqId(int rfqid)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("UserWf.findByRfqId", "rfqid", rfqid);
        return list;
    }
    public List<?> findBySupplierSelectionAndRfqId(int supplierSelection, int rfqid)
    {
        String[] params = new String[2];
        params[0] = "id";
        params[1] = "rfqid";

        Object[] values = new Object[2];
        values[0] = supplierSelection;
        values[1] = rfqid;
        
        List<?> list = this.template.findByNamedQueryAndNamedParam("UserWf.findBySupplierSelectionAndRfqId", params, values);
        return list;
    }
}
