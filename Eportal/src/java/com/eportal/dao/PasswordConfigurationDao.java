/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.Groups;
import com.eportal.entities.PasswordConfiguration;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierUserAttachment;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class PasswordConfigurationDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int savePasswordConfiguration(PasswordConfiguration passConfig)
    {
       int id = (int) this.template.save(passConfig);
       return id;
    }
    public void updatePasswordConfiguration(PasswordConfiguration passConfig)
    {
        this.template.update(passConfig);
    }
    public void deletePasswordConfiguration(PasswordConfiguration passConfig)
    {
        this.template.delete(passConfig);
    }
    public PasswordConfiguration getPasswordConfigurationById(int id)
    {
        PasswordConfiguration passConfig = this.template.get(PasswordConfiguration.class, id);
        return passConfig;
    }
    public List<?> getAllPasswordConfiguration()
    {
        List<PasswordConfiguration> passConfig = new ArrayList<>();
        passConfig = this.template.loadAll(PasswordConfiguration.class);
        return passConfig;
    }
}
