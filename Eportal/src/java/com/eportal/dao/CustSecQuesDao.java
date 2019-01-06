/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustSecQues;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustSecQuesDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveQue(CustSecQues que)
    {
       int id = (int) this.template.save(que);
       return id;
    }
    public void updateQue(CustSecQues que)
    {
        this.template.update(que);
    }
    public void deleteQue(CustSecQues que)
    {
        this.template.delete(que);
    }
    public CustSecQues getQueById(int id)
    {
        CustSecQues que = this.template.get(CustSecQues.class, id);
        return que;
    }
    public List<?> getAllQues()
    {
        List<CustSecQues> que;
        que = this.template.loadAll(CustSecQues.class);
        return que;
    }
    public List<?> findById(int id)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustSecQues.findByCustomerSubUserId", "id", id);
        return list;
    }
}
