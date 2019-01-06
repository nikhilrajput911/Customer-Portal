/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.CategorySubcategory;
import com.eportal.entities.Userdetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RaphelTudu
 */
@Transactional 
public class CategorySubcategoryDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) 
    {
        this.template = template;
    }
    public int saveCategorySubcategory(CategorySubcategory catSubsat)
    {
       int id = (int) this.template.save(catSubsat);
       return id;
    }
    public void updateCategorySubcategory(CategorySubcategory catSubsat)
    {
        this.template.update(catSubsat);
    }
    public void deleteCategorySubcategory(CategorySubcategory catSubsat)
    {
        this.template.delete(catSubsat);
    }
    public CategorySubcategory getCategorySubcategoryById(int id)
    {
        CategorySubcategory catSubsat = this.template.get(CategorySubcategory.class, id);
        return catSubsat;
    }
    public List<?> getCategorySubcategory()
    {
        List<CategorySubcategory> catSubsat = new ArrayList<CategorySubcategory>();
        catSubsat = this.template.loadAll(CategorySubcategory.class);
        return catSubsat;
    }
    public List<?> getDistinctCategory()
    {
        List<?> categories = this.template.findByNamedQuery("CategorySubcategory.findDistinctCategory");
        return categories;
    }        
    public List<?> getSubCategoryByCategory(String category)
    {
        List<?> subcategories = this.template.findByNamedQueryAndNamedParam("CategorySubcategory.findByCategoryname", "categoryname", category);
        return subcategories;
    }
}