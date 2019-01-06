/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CustomerImage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class CustomerImageDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCustomerImage(CustomerImage image)
    {
       int id = (int) this.template.save(image);
       return id;
    }
    public void updateCustomerImage(CustomerImage image)
    {
        this.template.update(image);
    }
    public void deleteCustomerImage(CustomerImage image)
    {
        this.template.delete(image);
    }
    public CustomerImage getCustomerImageId(int id)
    {
        CustomerImage image = this.template.get(CustomerImage.class, id);
        return image;
    }
    public List<?> getAllCustomerImage()
    {
        List<CustomerImage> image = new ArrayList<>();
        image = this.template.loadAll(CustomerImage.class);
        return image;
    }
    public List<?> findBySectionName(String name)
    {
        List<?> list = this.template.findByNamedQueryAndNamedParam("CustomerImage.findBySectionname", "sectionname", name);
        return list;
    }
}
