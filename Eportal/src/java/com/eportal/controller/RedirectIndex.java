/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import JSON.JSONArray;
import JSON.JSONException;
import JSON.JSONObject;
import com.eportal.dao.SupplierUserDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.SupplierUser;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.security.Principal;
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
public class RedirectIndex {
//
//    @Autowired
//    WorkOrderRfqHeaderDao rfqHeaderDao;
//
//    @Autowired
//    WorkOrderRfqHeaderDao workOrderRfqHeaderDao;
//
//    @Autowired
//    WorkOrderSelectedApproverDao approverDao;
//
//    @Autowired
//    SupplierUserDao supplierUserDao;
//
//    @RequestMapping("/home")
//    public ModelAndView redirectToIndex(HttpSession session, Principal principal, Authentication authentication) throws JSONException {
//        System.out.println("redirecting to index=============");
//
//        JSONArray jArra = new JSONArray();
//        JSONObject jObj = new JSONObject();
//
//        System.out.println("Principal : " + principal);
//        System.out.println("Authentication : " + authentication);
//
//        if (principal != null) {
//            System.out.println("Name: " + principal.getName());
//        }
//        if (authentication != null) {
//            System.out.println("Auth Name: " + authentication.getName());
//            System.out.println("Auth : " + authentication.getAuthorities());
//        }
//
//        int userId = (int) session.getAttribute("userId");
//        System.out.println("userId: " + userId);
//
//        String userType = (String) session.getAttribute("userType");
//        System.out.println("userType: " + userType);
//
//        String userName = (String) session.getAttribute("username");
//        System.out.println("userName: " + userName);
//
//        List<WorkOrderRfqHeader> headers = null;
//        List<WorkOrderRfqHeader> approvedList = null;
//        List<WorkOrderRfqHeader> pendingList = null;
//        List<WorkOrderRfqHeader> releasedList = null;
//        List<WorkOrderRfqHeader> awardedList = null;
//        List<WorkOrderSelectedApprover> approval = null;
//        List<WorkOrderSelectedApprover> approvalPending = null;
//        List<SupplierUser> supplierUser = null;
//
//        if (userType != null && userType.equalsIgnoreCase("SCM User")) {
//
//            headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByUserdetail_Id(userId);
//            System.out.println("size: " + headers.size());
//            approvedList = (List<WorkOrderRfqHeader>) workOrderRfqHeaderDao.findByRfqStatus("Awaiting Release");
//            pendingList = (List<WorkOrderRfqHeader>) workOrderRfqHeaderDao.findByRfqStatus("Awaiting Approval");
//            releasedList = (List<WorkOrderRfqHeader>) workOrderRfqHeaderDao.findByRfqStatus("Awaiting Acknowledge");
//            awardedList = (List<WorkOrderRfqHeader>) workOrderRfqHeaderDao.findByRfqStatus("Awarded");
//            supplierUser = (List<SupplierUser>) supplierUserDao.findBySupplierstatus("Active");
//
//        } else if (userType != null && userType.equalsIgnoreCase("Approver")) {
//
//            approval = (List<WorkOrderSelectedApprover>) approverDao.findByApprovername(userName);
//            System.out.println("approval size: " + approval.size());
//
//            approvalPending = (List<WorkOrderSelectedApprover>) approverDao.findByAssignedToUser(userName);
//            System.out.println("approvalPending size: " + approvalPending.size());
//        }
//
//        Map<String, Object> detailsMap = new HashMap<>();
//        if (userType != null && userType.equalsIgnoreCase("SCM User")) {
//            detailsMap.put("ApprovedRfq", approvedList.size());
//            detailsMap.put("PendingRfq", pendingList.size());
//            detailsMap.put("ReleasedRfq", releasedList.size());
//            detailsMap.put("AwardedRfq", awardedList.size());
//            detailsMap.put("headers", headers);
//            detailsMap.put("SupplierUser", supplierUser);
//        } else if (userType != null && userType.equalsIgnoreCase("Approver")) {
//            detailsMap.put("selectedApproval", approval);
//            detailsMap.put("approvalPending", approvalPending);
//        }
//
//        return new ModelAndView("index", "detailsMap", detailsMap);
//    }
}
