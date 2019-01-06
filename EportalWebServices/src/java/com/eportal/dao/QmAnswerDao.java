/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.SupplierUserAttachment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class QmAnswerDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveQmAnswer(QmAnswer answer)
    {
       int id = (int) this.template.save(answer);
       return id;
    }
    public void updateQmAnswer(QmAnswer answer)
    {
        this.template.update(answer);
    }
    public void deleteQmAnswer(QmAnswer answer)
    {
        this.template.delete(answer);
    }
    public QmAnswer getQmAnswerId(int id)
    {
        QmAnswer answer = this.template.get(QmAnswer.class, id);
        return answer;
    }
    public List<?> getAllQmAnswer()
    {
        List<QmAnswer> answer = new ArrayList<>();
        answer = this.template.loadAll(QmAnswer.class);
        return answer;
    }
    
}
