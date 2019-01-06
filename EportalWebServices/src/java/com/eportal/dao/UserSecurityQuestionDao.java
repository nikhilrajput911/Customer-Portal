/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.UserSecurityQuestion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserSecurityQuestionDao {
    
    HibernateTemplate template;
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveSecQue(UserSecurityQuestion secQue)
    {
       int id = (int) this.template.save(secQue);
       return id;
    }
    public void updateSecQue(UserSecurityQuestion secQue)
    {
        this.template.update(secQue);
    }
    public void deleteSecQue(UserSecurityQuestion secQue)
    {
        this.template.delete(secQue);
    }
    public UserSecurityQuestion getSecQueById(int id)
    {
        UserSecurityQuestion secQue = this.template.get(UserSecurityQuestion.class, id);
        return secQue;
    }
    public List<UserSecurityQuestion> getSecQues()
    {
        List<UserSecurityQuestion> secQues = new ArrayList<UserSecurityQuestion>();
        secQues = this.template.loadAll(UserSecurityQuestion.class);
        return secQues;
    }
    
}
