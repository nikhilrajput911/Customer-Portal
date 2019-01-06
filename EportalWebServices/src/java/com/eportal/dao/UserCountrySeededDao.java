/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.UserCountrySeeded;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class UserCountrySeededDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveUserCntry(UserCountrySeeded userCntry)
    {
       int id = (int) this.template.save(userCntry);
       return id;
    }
    public void updateUserCntry(UserCountrySeeded userCntry)
    {
        this.template.update(userCntry);
    }
    public void deleteUserCntry(UserCountrySeeded userCntry)
    {
        this.template.delete(userCntry);
    }
    public UserCountrySeeded getUserCntryById(int id)
    {
        UserCountrySeeded userCntry = this.template.get(UserCountrySeeded.class, id);
        return userCntry;
    }
    public List<UserCountrySeeded> getUserCntries()
    {
        List<UserCountrySeeded> userCntrys = new ArrayList<UserCountrySeeded>();
        userCntrys = this.template.loadAll(UserCountrySeeded.class);
        return userCntrys;
    }
    
    public List<?> findByUserId(int userId)
    {
        List<?> userCntrys = this.template.findByNamedQueryAndNamedParam("Userdetail.findByUserid", "userid", userId);
        return userCntrys;
    }
}
