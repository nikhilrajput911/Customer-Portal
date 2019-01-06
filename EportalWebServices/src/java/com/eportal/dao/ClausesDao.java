/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.Clauses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;


public class ClausesDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveClauses(Clauses clause)
    {
       int id = (int) this.template.save(clause);
       return id;
    }
    public void updateClauses(Clauses clause)
    {
        this.template.update(clause);
    }
    public void deleteClauses(Clauses clause)
    {
        this.template.delete(clause);
    }
    public Clauses getClausesById(int id)
    {
        Clauses clause = this.template.get(Clauses.class, id);
        return clause;
    }
    public List<?> getClauses()
    {
        List<Clauses> clauses;
        clauses = this.template.loadAll(Clauses.class);
        return clauses;
    }
    
}
