/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderRfqLineItem;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyTask {

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;
    @Autowired
    WorkOrderRfqHeader rfqHeader;
    
    @Autowired
    WorkOrderRfqLineItem rfqLineItem;
    
    @Autowired
    WorkOrderSelectedApproverDao selAppDao;
    @Autowired
    WorkOrderSelectedApprover selApp;
    
    @Autowired
    SupplierSelectionDao supplierSelectionDao;

    @RequestMapping("/mytask")
    public ModelAndView redirectToIndex(HttpSession session, Principal principal, Authentication authentication) {
        //System.out.println("redirecting to mytask=============");
        System.out.println("Principal : " + principal);
        System.out.println("Authentication : " + authentication);
        
        if(principal != null)
        {
            System.out.println("Name: " + principal.getName());
        }
        if(authentication != null)
        {
            System.out.println("Auth Name: " + authentication.getName());
            System.out.println("Auth : "  +authentication.getAuthorities());
        }
        
        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);
        
        String userName = (String) session.getAttribute("username");
        System.out.println("userName: " + userName);
        
        List<WorkOrderRfqHeader> headers = null;
        List<SupplierSelection> supplierSelection = null; 
                
        if (userType != null && userType.equalsIgnoreCase("SCM User")) {
            
            headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByUserdetail_Id(userId);
            System.out.println("size: " + headers.size());

        } else if (userType != null && userType.equalsIgnoreCase("Approver")) {
            
            List<WorkOrderSelectedApprover> appr = (List<WorkOrderSelectedApprover>) selAppDao.findByAssignedTo(userName);
            System.out.println("appr size: " + appr.size());
            
            headers = new ArrayList<>();
            for(WorkOrderSelectedApprover app : appr)
            {
                headers.add(app.getBPaasWorkOrderRFQHeaderRFQID());
            }
        } else if (userType != null && userType.equalsIgnoreCase("Supplier")) {
            
//            headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus("Released");
            supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierId(userId);
            System.out.println("supplier size: " + supplierSelection.size());
            
            headers = new ArrayList<>();
            for(SupplierSelection supplier : supplierSelection)
            {
                headers.add(supplier.getBPaasWorkOrderRFQHeaderRFQID());
            }
        }
        
        Map<String, List<?>> map = new HashMap<>();
        map.put("rqfheaders", headers);
        if(userType.equalsIgnoreCase("Supplier"))
            map.put("supplierSelection", supplierSelection);
        
        return new ModelAndView("mytask", "rfqDetails", map);
    }
}
