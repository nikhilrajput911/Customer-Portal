/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.QmAnswerDao;
import com.eportal.dao.QmQuestionDao;
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.UserDetailDao;
import com.eportal.dao.UserWfDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.entities.QmAnswer;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.UserWf;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderRfqHeader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/")
public class ReponseManagement {

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;

    @Autowired
    SupplierSelectionDao supplierSelectionDao;

    @Autowired
    UserWfDao userWFDao;
    @Autowired
    UserWf userWF;

    @Autowired
    QmQuestionDao qmQuestionDao;
    @Autowired
    QmQuestion qmQuestion;

    @Autowired
    QmAnswerDao qmAnswerDao;
    @Autowired
    QmAnswer qmAnswer;

    @Autowired
    UserDetailDao userDao;

    @Value("${bid_awaiting}")
    private String bid_awaiting;

    @RequestMapping(value = "/responseManagement")
    public ModelAndView responseManagement(HttpSession session, Model model) {
        System.out.println("responsemanagement");

        
        
        int userId = (int) session.getAttribute("userId");
        String usetType = (String) session.getAttribute("userType");

        System.out.println("userId: " + userId);
        System.out.println("usetType: " + usetType);

        List<WorkOrderRfqHeader> rfqHeader = null;

        if (usetType != null && usetType.equalsIgnoreCase("SCM User")) {
            rfqHeader = (List<WorkOrderRfqHeader>) supplierSelectionDao.findBySupplierStatusDistinctRfqId(bid_awaiting);
            System.out.println("WorkOrderRfqHeader: " + rfqHeader);

        } else if (usetType != null && usetType.equalsIgnoreCase("Supplier")) {
            rfqHeader = (List<WorkOrderRfqHeader>) supplierSelectionDao.findBySupplierStatusAndSupplierIdDistinctRfqid(bid_awaiting, userId);
            System.out.println("WorkOrderRfqHeader: " + rfqHeader);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rfqHeader", rfqHeader);

        return new ModelAndView("responsemanagement", "map", map);
    }

    @RequestMapping(value = "/responseComment")
    public ModelAndView responseComment(@RequestParam("rfqid") int rfqid, @RequestParam("supplierid") int supplierid, HttpSession session, Map<String, Object> map) {
        System.out.println("responseComment");

        int userId = (int) session.getAttribute("userId");
        String userType = (String) session.getAttribute("userType");

        System.out.println("userId: " + userId);
        System.out.println("usetType: " + userType);

        System.out.println("rfqid: " + rfqid);
        System.out.println("supplierid: " + supplierid);

        WorkOrderRfqHeader header = rfqHeaderDao.getWorkOrderRfqHeaderById(rfqid);
        List<SupplierSelection> supplierSelectionlist = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfqid, supplierid);
        System.out.println("supplierSelectionlist: " + supplierSelectionlist);
        List<QmQuestion> qmQuestionList = (List<QmQuestion>) qmQuestionDao.findByUserWFid(supplierSelectionlist.get(0).getId());
        System.out.println("qmQuestionList: " + qmQuestionList);

        map.put("question", qmQuestionList);
        map.put("rfpHeader", header);
        map.put("supplier", supplierSelectionlist.get(0).getBPaasSupplierUserTableid());

        return new ModelAndView("responsecomment", "map", map);
    }

    @RequestMapping(value = "/makeComment", method = RequestMethod.POST)
    public ModelAndView makeComment(@RequestParam("file") CommonsMultipartFile attachment, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("makeComment");

        int userId = (int) session.getAttribute("userId");
        String userType = (String) session.getAttribute("userType");
        String username = (String) session.getAttribute("username");
        Date today = new Date();

        System.out.println("userId: " + userId);
        System.out.println("usetType: " + userType);

        String rfqid = request.getParameter("rfqid");
        String supplierid = request.getParameter("supplierid");
        String message = request.getParameter("messageContent");

        System.out.println("rfqid: " + rfqid);
        System.out.println("supplierid: " + supplierid);
        System.out.println("message: " + message);

        List<SupplierSelection> supplierSelectionlist = (List<SupplierSelection>) supplierSelectionDao.
                findBySupplierIdAndRfpId(Integer.parseInt(rfqid), Integer.parseInt(supplierid));

        System.out.println("supplierSelectionlist size: " + supplierSelectionlist.size());
        WorkOrderRfqHeader header = rfqHeaderDao.getWorkOrderRfqHeaderById(Integer.parseInt(rfqid));

        System.out.println("att: " + attachment.getBytes().length);

        qmQuestion.setBPaasWorkOrderRFQHeaderRFQID(header);
        qmQuestion.setBPaasSupplierSelectionid(supplierSelectionlist.get(0));
        qmQuestion.setAskquestion(message);

        if (attachment.getBytes().length == 0) {
            qmQuestion.setAddattachment(null);
            qmQuestion.setAttachmentdescription(null);
        } else {
            qmQuestion.setAddattachment(attachment.getBytes());
            qmQuestion.setAttachmentdescription(attachment.getOriginalFilename());
        }

        qmQuestion.setInitiatedby(username);
        qmQuestion.setQuestiondate(today);

        if (userType != null && userType.equalsIgnoreCase("Supplier")) {
            qmQuestion.setType("Question");

        } else if (userType != null && userType.equalsIgnoreCase("SCM User")) {
            qmQuestion.setType("Answer");
        }

        int questionId = qmQuestionDao.saveQmQuestion(qmQuestion);
        System.out.println("questionId: " + questionId);

        return new ModelAndView("redirect:/responseComment.do?rfqid=" + rfqid + "&supplierid=" + supplierid + "");
    }

    @RequestMapping(value = "/makeCommentToAllSelectedSupplier", method = RequestMethod.POST)
    public ModelAndView makeCommentToAllSelectedSupplier(@RequestParam("file") CommonsMultipartFile attachment, RedirectAttributes model
            , HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("makeCommentToAllSelectedSupplier");

        int userId = (int) session.getAttribute("userId");
        String userType = (String) session.getAttribute("userType");
        String username = (String) session.getAttribute("username");
        Date today = new Date();

        System.out.println("userId: " + userId);
        System.out.println("usetType: " + userType);

        String rfqid = request.getParameter("allRfqid");
        String allSupplierid = request.getParameter("allSupplierid");
        String message = request.getParameter("messageContentToAllSupplier");

        String[] supplierIds = allSupplierid.split("~");

        System.out.println("rfqid: " + rfqid);
        System.out.println("supplierid: " + allSupplierid);
        System.out.println("message: " + message);

        for (String supplierid : supplierIds) {
            System.out.println("supplierid: " + supplierid);
            List<SupplierSelection> supplierSelectionlist = (List<SupplierSelection>) supplierSelectionDao.
                    findBySupplierIdAndRfpId(Integer.parseInt(rfqid), Integer.parseInt(supplierid));

            System.out.println("supplierSelectionlist size: " + supplierSelectionlist.size());
            WorkOrderRfqHeader header = rfqHeaderDao.getWorkOrderRfqHeaderById(Integer.parseInt(rfqid));

            System.out.println("att: " + attachment.getBytes().length);

            qmQuestion.setBPaasWorkOrderRFQHeaderRFQID(header);
            qmQuestion.setBPaasSupplierSelectionid(supplierSelectionlist.get(0));
            qmQuestion.setAskquestion(message);

            if (attachment.getBytes().length == 0) {
                qmQuestion.setAddattachment(null);
                qmQuestion.setAttachmentdescription(null);
            } else {
                qmQuestion.setAddattachment(attachment.getBytes());
                qmQuestion.setAttachmentdescription(attachment.getOriginalFilename());
            }

            qmQuestion.setInitiatedby(username);
            qmQuestion.setQuestiondate(today);

            if (userType != null && userType.equalsIgnoreCase("Supplier")) {
                qmQuestion.setType("Question");

            } else if (userType != null && userType.equalsIgnoreCase("SCM User")) {
                qmQuestion.setType("Answer");
            }

            int questionId = qmQuestionDao.saveQmQuestion(qmQuestion);
            System.out.println("questionId: " + questionId);
        }
        model.addFlashAttribute("message", "Message has been sent to all selected suppliers.");
        return new ModelAndView("redirect:/responseManagement.do");
    }
}
