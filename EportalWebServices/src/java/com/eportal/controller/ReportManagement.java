/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.controller;

import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.WorkOrderRfqHeader;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/")
public class ReportManagement {
    
    @Value("${bid_submitted}")
    private String bid_submitted;
    
    @Autowired
    SupplierSelectionDao supplierSelectionDao;
    
    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;
    
    @RequestMapping(value = "/budgetComparison")
    public ModelAndView responseManagement(HttpSession session, Map<String, Object> map) {
        System.out.println("budgetComparison");
        
        List<WorkOrderRfqHeader> rfqHeaderList = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus(bid_submitted);
        System.out.println("rfqHeaderList: " + rfqHeaderList.size());
        
        List<SupplierSelection> supplierSelectionlist = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatus(bid_submitted);
        System.out.println("supplierSelectionlist: " + supplierSelectionlist.size());
        
        map.put("supplierSelectionlist", supplierSelectionlist);
        map.put("rfqHeaderList", rfqHeaderList);
        
        return new ModelAndView("budgetcomparison", "map", map);
    }
}


