/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.Userdetail;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class UserDetailDao {
    
    HibernateTemplate template;
    
    @Autowired
    protected SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.openSession();
    }
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveUser(Userdetail user)
    {
       int id = (int) this.template.save(user);
       return id;
    }
    public void updateUser(Userdetail user)
    {
        this.template.update(user);
    }
    public void deleteUser(Userdetail user)
    {
        this.template.delete(user);
    }
    public Userdetail getUserById(int id)
    {
        Userdetail user = this.template.get(Userdetail.class, id);
        return user;
    }
    public List<Userdetail> getUsers()
    {
        List<Userdetail> users = new ArrayList<Userdetail>();
        users = this.template.loadAll(Userdetail.class);
        return users;
    }
    public List<?> findByUsernameAndPassword(String username, String password)
    {
        String[] params =  new String[2];
        params[0] = "username";
        params[1] = "password";
        
        String[] values =  new String[2];
        values[0] = username;
        values[1] = password;
//        List<?> user = this.template.findByNamedQueryAndNamedParam("Userdetail.findByUsername", "username", username);
        List<?> user = this.template.findByNamedQueryAndNamedParam("Userdetail.findByUsername", params, values);
        return user;
    }
    public List<?> findByUserType(String userType)
    {
        List<?> users = this.template.findByNamedQueryAndNamedParam("Userdetail.findByUsertype", "usertype", userType);
        return users;
    }
    public List<?> findByUserId(int userId)
    {
        List<?> user = this.template.findByNamedQueryAndNamedParam("Userdetail.findByUserid", "userid", userId);
        return user;
    }
    public List<?> findByUsername(String username)
    {
//        Session session = sessionFactory.getCurrentSession();
//        Session session = getSession();
//        List<?> list = session.createCriteria(Userdetail.class).add(Restrictions.eq("username", username)).list();
        List<?> list = this.template.findByCriteria(DetachedCriteria.forClass(Userdetail.class).add(Restrictions.eq("username", username)));
        return list;
    }
}
