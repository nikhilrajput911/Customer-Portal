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
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.UserDetailDao;
import com.eportal.dao.WorkOrderAddclausesDao;
import com.eportal.dao.WorkOrderAttachmentDao;
import com.eportal.dao.WorkOrderCommentDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderRfqLineItemDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.Clauses;
import com.eportal.entities.RfeCostCode;
import com.eportal.entities.RfeNegotiationStyle;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderAddclauses;
import com.eportal.entities.WorkOrderAttachment;
import com.eportal.entities.WorkOrderComment;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderRfqLineItem;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowRfpController {
//
//    @Autowired
//    WorkOrderRfqHeaderDao rfqHeaderDao;
//    @Autowired
//    WorkOrderRfqHeader rfqHeader;
//
//    @Autowired
//    WorkOrderRfqLineItemDao rfqLineItemDao;
//    @Autowired
//    WorkOrderRfqLineItem rfqLineItem;
//
//    @Autowired
//    WorkOrderAddclausesDao rfqClausesDao;
//    @Autowired
//    WorkOrderAddclauses rfqClauses;
//
//    @Autowired
//    WorkOrderAttachmentDao rfqAttDao;
//    @Autowired
//    WorkOrderAttachment rfqAtt;
//
//    @Autowired
//    WorkOrderSelectedApproverDao selAppDao;
//    @Autowired
//    WorkOrderSelectedApprover selApp;
//
//    @Autowired
//    RfeCostCodeDao costCode;
//    @Autowired
//    RfeNegotiationStyleDao negotiationStyle;
//    @Autowired
//    UserDetailDao userDetailDao;
//    @Autowired
//    CategorySubcategoryDao categorySubcategory;
//    @Autowired
//    ClausesDao clausesDao;
//    @Autowired
//    WorkOrderCommentDao workOrderCommentDao;
//    
//    @Autowired
//    SupplierSelectionDao supplierSelectionDao;
//
//    @RequestMapping(value = "/showRfp")
//    public ModelAndView showRfp(@RequestParam("rfpId") int rfpId, HttpSession session) {
//        String userType = (String) session.getAttribute("userType");
//        System.out.println("userType: " + userType);
//        
//        int userId = (int) session.getAttribute("userId");
//        System.out.println("userId: " + userId);
//        
//        
//        System.out.println("rfpId: " + rfpId);
//        rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
//        System.out.println("title: " + rfqHeader.getRFQTitle());
//
//        List<WorkOrderRfqHeader> rfqHeaders = new ArrayList<>();
//        rfqHeaders.add(rfqHeader);
//
//        List<WorkOrderRfqLineItem> lineItems = (List<WorkOrderRfqLineItem>) rfqLineItemDao.findByHeaderId(rfpId);
//        System.out.println("line item size: " + lineItems.size());
//
//        List<WorkOrderAddclauses> clauses = (List<WorkOrderAddclauses>) rfqClausesDao.findByHeaderId(rfpId);
//        System.out.println("clauses size: " + clauses.size());
//
//        List<WorkOrderAttachment> attmnt = (List<WorkOrderAttachment>) rfqAttDao.findByHeaderId(rfpId);
//        System.out.println("attmnt size: " + attmnt.size());
//
//        List<WorkOrderSelectedApprover> approval = (List<WorkOrderSelectedApprover>) selAppDao.findByHeaderId(rfpId);
//        System.out.println("approval size: " + approval.size());
//
//        int approvalId = approval.get(0).getId();
//
//        List<WorkOrderComment> commentList = (List<WorkOrderComment>) workOrderCommentDao.findLatestCommentByApprovalId(approvalId);
//        System.out.println("list : " + commentList);
//        
//        List<SupplierSelection> supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, userId);
//        System.out.println("supplierSelection size: " + supplierSelection.size());
//        
////        if (commentList != null) {
////            for (WorkOrderComment comment : commentList) {
////                System.out.println(comment.getComment());
////                System.out.println(comment.getApprovername());
////                System.out.println(comment.getCommentdate());
////            }
////        }
//        List<RfeCostCode> costCodeList = null;
//        List<RfeNegotiationStyle> styleList = null;
//        List<Userdetail> users = null;
//        List<String> distinctCat = null;
//        List<Clauses> availClauses = null;
//
//        if (userType.equalsIgnoreCase("SCM User") && rfqHeader.getRfqstatus().equalsIgnoreCase("Sent for Revision")) {
//            costCodeList = (List<RfeCostCode>) costCode.getCostCodes();
//            styleList = (List<RfeNegotiationStyle>) negotiationStyle.getNegotiationStyles();
//            users = (List<Userdetail>) userDetailDao.findByUserType("Approver");
//            distinctCat = (List<String>) categorySubcategory.getDistinctCategory();
//            availClauses = (List<Clauses>) clausesDao.getClauses();
//        }
//        Map<String, List<?>> rfqDetails = new HashMap<>();
//
//        rfqDetails.put("rfqHeaders", rfqHeaders);
//        rfqDetails.put("rfqLineItems", lineItems);
//        rfqDetails.put("rfqClauses", clauses);
//        rfqDetails.put("rfqAtmnt", attmnt);
//        rfqDetails.put("rfqApproval", approval);
//        if (commentList != null) {
//            rfqDetails.put("approvalComment", commentList);
//        }
//
//        if (userType.equalsIgnoreCase("SCM User") && rfqHeader.getRfqstatus().equalsIgnoreCase("Sent for Revision")) {
//            rfqDetails.put("rfeCostCode", costCodeList);
//            rfqDetails.put("rfeNegoStyle", styleList);
//            rfqDetails.put("rfeApprovals", users);
//            rfqDetails.put("rfeCategory", distinctCat);
//            rfqDetails.put("availRfeClauses", availClauses);
//        }
//        if(userType.equalsIgnoreCase("Supplier"))
//        {
//            rfqDetails.put("supplierSelection", supplierSelection);
//        }
//        System.out.println(userType);
//        if (userType.equalsIgnoreCase("Supplier")) {
//            System.out.println("supplier");
//            return new ModelAndView("supplierRfp", "rfqDetails", rfqDetails);
//        } else {
//            System.out.println("non-supplier");
//            return new ModelAndView("showRfp", "rfqDetails", rfqDetails);
//        }
//    }
}
