/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.dao;

import com.eportal.entities.RfeCostCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional 
public class RfeCostCodeDao {
    
    HibernateTemplate template;
    
    
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
    
    public int saveCostCode(RfeCostCode costcode)
    {
       int id = (int) this.template.save(costcode);
       return id;
    }
    public void updateCostCode(RfeCostCode costcode)
    {
        this.template.update(costcode);
    }
    public void deleteCostCode(RfeCostCode costcode)
    {
        this.template.delete(costcode);
    }
    public RfeCostCode getCostCodeById(int id)
    {
        RfeCostCode costcode = this.template.get(RfeCostCode.class, id);
        return costcode;
    }
    public List<?> getCostCodes()
    {
        List<RfeCostCode> costcode = new ArrayList<>();
        costcode = this.template.loadAll(RfeCostCode.class);
        return costcode;
    }
    
}
