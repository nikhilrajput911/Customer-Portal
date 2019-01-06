/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.WorkOrderCommentDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.WorkOrderComment;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateRfpRequest {

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;

    @Autowired
    WorkOrderSelectedApproverDao selAppDao;

    @Autowired
    WorkOrderCommentDao workOrderCommentDao;
    @Autowired
    WorkOrderComment workOrderComment;

    @Value("${release_awaiting}")
    private String release_awaiting;
    
    @Value("${sent_for_revision}")
    private String sent_for_revision;

    @RequestMapping(value = "/updateRfp", method = RequestMethod.POST)
    public ModelAndView updateRfpReq(HttpServletRequest request, HttpSession session) {
        Date today = new Date();
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);

        String action = request.getParameter("action");

        System.out.println("action: " + action);

        if (action != null && action.equalsIgnoreCase("Approve")) {
            int rfpId = Integer.parseInt(request.getParameter("approve_rfpId"));
            System.out.println("rfpId: " + rfpId);

            String remarks = request.getParameter("approvaeRemarks");
            System.out.println("remarks: " + remarks);

            WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
            //System.out.println("status: " + rfqHeader.getRfqstatus());

            List<WorkOrderSelectedApprover> selApp = (List<WorkOrderSelectedApprover>) selAppDao.findByRfqId(rfpId);
            System.out.println("selApp size: " + selApp.size());

            WorkOrderSelectedApprover selAppObj = selApp.get(0);

            String app_seq = selAppObj.getApprovalsequence();
            System.out.println("app_seq: " + app_seq);
            String[] apps = app_seq.split(",");
            System.out.println(apps.length);

            if (!app_seq.trim().equalsIgnoreCase("") && apps.length > 0) {
                //System.out.println("if============");
                selAppObj.setAssignedTo(apps[0]);
                String new_seq = app_seq.replace(apps[0] + ",", "");
                selAppObj.setApprovalsequence(new_seq);
                selAppObj.setUpdatedate(today);
                rfqHeader.setAssignedTo(apps[0]);
                rfqHeader.setUpdatedate(today);

                workOrderComment.setApprovername(username);
                workOrderComment.setAction(action);
                workOrderComment.setComment(remarks);
                workOrderComment.setCommentdate(today);
                workOrderComment.setBpaasWorkorderselectedapproverId(selAppObj);
            } else {
                selAppObj.setAssignedTo("Closed");
                selAppObj.setUpdatedate(today);
                //System.out.println("else===================");
                rfqHeader.setRfqstatus(release_awaiting);
                rfqHeader.setAssignedTo(rfqHeader.getCreatedby());
                rfqHeader.setUpdatedate(today);

                workOrderComment.setApprovername(username);
                workOrderComment.setAction(release_awaiting);
                workOrderComment.setComment(remarks);
                workOrderComment.setCommentdate(today);
                workOrderComment.setBpaasWorkorderselectedapproverId(selAppObj);
            }

            selAppDao.updateWorkOrderSelectedApprover(selAppObj);
            rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
            workOrderCommentDao.saveComment(workOrderComment);

        } else if (action != null && action.equalsIgnoreCase("Revision")) {
            int rfpId = Integer.parseInt(request.getParameter("revision_rfpId"));
            System.out.println("rfpId: " + rfpId);

            String remarks = request.getParameter("revisionRemarks");
            System.out.println("remarks: " + remarks);

            WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
            System.out.println("status: " + rfqHeader.getRfqstatus());

            List<WorkOrderSelectedApprover> selApp = (List<WorkOrderSelectedApprover>) selAppDao.findByRfqId(rfpId);
            System.out.println("selApp size: " + selApp.size());
            WorkOrderSelectedApprover selAppObj = selApp.get(0);

            selAppObj.setAssignedTo(rfqHeader.getUserDetailuserid().getUsername());
            selAppObj.setUpdatedate(today);

            rfqHeader.setRfqstatus(sent_for_revision);
            rfqHeader.setAssignedTo(rfqHeader.getUserDetailuserid().getUsername());
            rfqHeader.setUpdatedate(today);

            workOrderComment.setApprovername(username);
            workOrderComment.setAction(sent_for_revision);
            workOrderComment.setComment(remarks);
            workOrderComment.setCommentdate(today);
            workOrderComment.setBpaasWorkorderselectedapproverId(selAppObj);
            //System.out.println("status: " + rfqHeader.getRfqstatus());

            selAppDao.updateWorkOrderSelectedApprover(selAppObj);
            rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
            workOrderCommentDao.saveComment(workOrderComment);
        }
        return new ModelAndView("redirect:/mytask.do");
    }
}
