/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.CategorySubcategoryDao;
import com.eportal.dao.ClausesDao;
import com.eportal.dao.RfeCostCodeDao;
import com.eportal.dao.RfeNegotiationStyleDao;
import com.eportal.dao.RfeStatusDao;
import com.eportal.dao.UserDetailDao;
import com.eportal.entities.Clauses;
import com.eportal.entities.RfeCostCode;
import com.eportal.entities.RfeNegotiationStyle;
import com.eportal.entities.RfeStatus;
import com.eportal.entities.Userdetail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateRfp {

    @Autowired
    RfeStatusDao statusDao;
    @Autowired
    RfeCostCodeDao costCode;
    @Autowired
    RfeNegotiationStyleDao negotiationStyle;
    @Autowired
    CategorySubcategoryDao categorySubcategory; 
    @Autowired
    ClausesDao clausesDao;
    @Autowired
    UserDetailDao userDetailDao;
    
    
    @RequestMapping(value = "/createrfp")
    public ModelAndView createRfp() {
        
        List<RfeStatus> statusList = (List<RfeStatus>) statusDao.getStatus();
        List<RfeCostCode> costCodeList = (List<RfeCostCode>) costCode.getCostCodes();
        List<RfeNegotiationStyle> styleList = (List<RfeNegotiationStyle>) negotiationStyle.getNegotiationStyles();
        List<String> distinctCat = (List<String>) categorySubcategory.getDistinctCategory();
        List<Clauses> clauses = (List<Clauses>) clausesDao.getClauses();
        List<Userdetail> users = (List<Userdetail>) userDetailDao.findByUserType("Approver");
        
        Map<String, List<?>> rfeLOV = new HashMap<>();
        rfeLOV.put("rfeStatus", statusList);
        rfeLOV.put("rfeCostCode", costCodeList);
        rfeLOV.put("rfeNegoStyle", styleList);
        rfeLOV.put("rfeCategory", distinctCat);
        rfeLOV.put("rfeClauses", clauses);
        rfeLOV.put("rfeApprovals", users);
        
        System.out.println("map size: " + rfeLOV.size());
        
        //System.out.println("=========CreateRFP");
        return new ModelAndView("createrfp", "rfeLOV", rfeLOV);
    }
}
