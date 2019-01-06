/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateRfpForRevision {
//
//    @Autowired
//    WorkOrderRfqHeaderDao rfqHeaderDao;
//
//    @Autowired
//    WorkOrderSelectedApproverDao selAppDao;
//
//    @RequestMapping("/updateForRevision")
//    public ModelAndView updateRfp(HttpServletRequest request) {
//        System.out.println("=======================================");
//        int rfpId = Integer.parseInt(request.getParameter("rfpId"));
//        System.out.println("rfpId: " + rfpId);
//        Date today = new Date();
//        System.out.println(request.getParameter("RFQTitle"));
//
//        WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
//
//        List<WorkOrderSelectedApprover> selApp = (List<WorkOrderSelectedApprover>) selAppDao.findByRfqId(rfpId);
//        System.out.println("selApp size: " + selApp.size());
//        WorkOrderSelectedApprover selAppObj = selApp.get(0);
//
//        String revision_seq = selAppObj.getRevisionsequence();
//        System.out.println("revision_seq: " + revision_seq);
//        String[] revs = revision_seq.split(",");
//        System.out.println(revs.length);
//
//        selAppObj.setAssignedTo(revs[0]);
//        String reset_app_seq = revision_seq.replace(revs[0] + ",", "");
//        selAppObj.setApprovalsequence(reset_app_seq);
//        selAppObj.setUpdatedate(today);
//        
//        rfqHeader.setAssignedTo(revs[0]);
//        rfqHeader.setRfqstatus("Awaiting Approval");
//        rfqHeader.setUpdatedate(today);
//
//        selAppDao.updateWorkOrderSelectedApprover(selAppObj);
//        rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
//
//        return new ModelAndView("redirect:/mytask.do");
//    }
}
