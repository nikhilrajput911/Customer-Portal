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
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class QmQuestionDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveQmQuestion(QmQuestion question)
    {
       int id = (int) this.template.save(question);
       return id;
    }
    public void updateQmQuestion(QmQuestion question)
    {
        this.template.update(question);
    }
    public void deleteQmQuestion(QmQuestion question)
    {
        this.template.delete(question);
    }
    public QmQuestion getQmQuestionId(int id)
    {
        QmQuestion question = this.template.get(QmQuestion.class, id);
        return question;
    }
    public List<?> getAllQmQuestion()
    {
        List<QmAnswer> question = new ArrayList<>();
        question = this.template.loadAll(QmAnswer.class);
        return question;
    }
    public List<?> findByUserWFid(int userWfid)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("QmQuestion.findBySupplierSelection", "id", userWfid);
        return list;
    }
}
