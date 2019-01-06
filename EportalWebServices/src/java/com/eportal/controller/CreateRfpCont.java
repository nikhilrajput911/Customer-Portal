/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.UserDetailDao;
import com.eportal.dao.WorkOrderAddclausesDao;
import com.eportal.dao.WorkOrderAttachmentDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderRfqLineItemDao;
import com.eportal.dao.WorkOrderSelectedApproverDao;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderAddclauses;
import com.eportal.entities.WorkOrderAttachment;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderRfqLineItem;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateRfpCont {

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;
    @Autowired
    WorkOrderRfqHeader rfqHeader;

    @Autowired
    WorkOrderRfqLineItemDao rfqLineItemDao;
    @Autowired
    WorkOrderRfqLineItem rfqLineItem;

    @Autowired
    WorkOrderAddclausesDao rfqClausesDao;
    @Autowired
    WorkOrderAddclauses rfqClauses;

    @Autowired
    WorkOrderAttachmentDao rfqAttDao;
    @Autowired
    WorkOrderAttachment rfqAtt;

    @Autowired
    UserDetailDao userDao;

    @Autowired
    WorkOrderSelectedApproverDao selAppDao;
    @Autowired
    WorkOrderSelectedApprover selApp;

    @Value("${approval_awaiting}")
    private String awaitingapproval;
//    @Autowired
//    SecurityContextHolder contextHolder;
    @RequestMapping(value = "/registerRfpRequest", method = RequestMethod.POST)
    public ModelAndView createRfp(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            System.out.println("register rfp===============");

            DateFormat from = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();

            int userId = (int) session.getAttribute("userId");
            System.out.println("userId: " + userId);
            Userdetail userById = userDao.getUserById(userId);

            rfqHeader.setUserDetailuserid(userById);
            rfqHeader.setUpdatedby(request.getParameter("updatedby"));
            rfqHeader.setCreatedby(request.getParameter("updatedby"));
            rfqHeader.setUpdatedate(today);
            rfqHeader.setCreationdate(today);
            rfqHeader.setRFQTitle(request.getParameter("RFQTitle"));
            rfqHeader.setRfqstatus(awaitingapproval);
            rfqHeader.setDescription(request.getParameter("description"));
            rfqHeader.setPaymentterms(request.getParameter("paymentterms"));
            rfqHeader.setAssignedTo(request.getParameter("app1Email"));

            String openDate = request.getParameter("opendate");
            System.out.println("openDate: " + openDate);
            Date date = from.parse(openDate);
            System.out.println("open: " + date);
            rfqHeader.setOpendate(date);

            String closeDate = request.getParameter("closedate");
            //System.out.println("closedate: " + closeDate);
            date = from.parse(closeDate);
            //System.out.println("close: " + date);
            rfqHeader.setClosedate(date);

            String timeLeft = request.getParameter("timeleft");
            //System.out.println("timeLeft: " + timeLeft);

            rfqHeader.setTimeleft(request.getParameter("timeleft"));
            rfqHeader.setBilltoaddress(request.getParameter("billtoaddress"));
            rfqHeader.setShiptoaddress(request.getParameter("shiptoaddress"));
            rfqHeader.setNegotationstyle(request.getParameter("Negotationstyle"));
            rfqHeader.setCostcode(request.getParameter("costcode"));
            rfqHeader.setProjectnamecode(request.getParameter("Projectnamecode"));

            int headerId = rfqHeaderDao.saveWorkOrderRfqHeader(rfqHeader);

            String cat_sub_details = request.getParameter("cat_sub_details");
            //System.out.println("cat_sub_details: " + cat_sub_details);
            String[] categories = cat_sub_details.split("<>");
            for (String category : categories) {
                //System.out.println(category);
                String[] cat_sub = category.split("~");
                //System.out.println(cat_sub.length);

                rfqLineItem.setBPaasWorkOrderRFQHeaderRFQID(rfqHeader);
                rfqLineItem.setCategory(cat_sub[0]);
                rfqLineItem.setSubcategory(cat_sub[1]);
                rfqLineItem.setItemname(cat_sub[2]);
                rfqLineItem.setTargetprice(Double.parseDouble(cat_sub[3]));
                rfqLineItem.setQuantity(Integer.parseInt(cat_sub[4]));
                rfqLineItem.setUpdatedby(request.getParameter("updatedby"));
                rfqLineItem.setUpdatedate(today);
                rfqLineItem.setCreatedby(request.getParameter("updatedby"));
                rfqLineItem.setCreationdate(today);

                int lineItemId = rfqLineItemDao.saveWorkOrderRfqLineItem(rfqLineItem);
                //System.out.println("lineItemId: " + lineItemId);

            }
            String clause = request.getParameter("rfeClauses");
            System.out.println("clause: " + clause);
            String[] clauses = clause.split("~");
            for (String cls : clauses) {
                System.out.println("Cls: " + cls);
                rfqClauses.setClausesname(cls);
                rfqClauses.setClausesdescription(cls);
                rfqClauses.setUpdatedby(request.getParameter("updatedby"));
                rfqClauses.setUpdatedate(today);
                rfqClauses.setCreatedby(request.getParameter("updatedby"));
                rfqClauses.setCreationdate(today);
                rfqClauses.setBPaasWorkOrderRFQHeaderRFQID(rfqHeader);

                int clauseId = rfqClausesDao.saveWorkOrderRfqClauses(rfqClauses);
                System.out.println("clauseId: " + clauseId);
            }
            String[] attName = request.getParameterValues("name");
            String[] attDesc = request.getParameterValues("attDescription");
            
            for(String name : attName)
                System.out.println(name);
            for(String desc : attDesc)
                System.out.println(desc);
            
            System.out.println("no of files: " + files.length);
            
            //for (CommonsMultipartFile file : files) {
            for(int i = 0; i <  files.length; i++)
            {
                System.out.println("file name: " + files[i].getOriginalFilename());
                //System.out.println("file name: " + files[i].getContentType());
                rfqAtt.setName(files[i].getOriginalFilename());
                rfqAtt.setAttachment(files[i].getBytes());
                rfqAtt.setDescription(attDesc[i]);
                rfqAtt.setUpdatedby(request.getParameter("updatedby"));
                rfqAtt.setUpdatedate(today);
                rfqAtt.setCreatedby(request.getParameter("updatedby"));
                rfqAtt.setCreationdate(today);
                rfqAtt.setBPaasWorkOrderRFQHeaderRFQID(rfqHeader);

                int attId = rfqAttDao.saveWorkOrderAttachment(rfqAtt);
                System.out.println("attId: " + attId);
            }

            String app_seq = "";
            String revision_seq = "";
            
            if(request.getParameter("app1Email") != null && !request.getParameter("app1Email").equalsIgnoreCase(""))
            {
                revision_seq = revision_seq + request.getParameter("app1Email") + ",";
            }
            if(request.getParameter("app2Email") != null && !request.getParameter("app2Email").equalsIgnoreCase(""))
            {
                app_seq = app_seq + request.getParameter("app2Email") + ",";
                revision_seq = revision_seq + request.getParameter("app2Email") + ",";
            }
            if(request.getParameter("app3Email") != null && !request.getParameter("app3Email").equalsIgnoreCase(""))
            {
                app_seq = app_seq + request.getParameter("app3Email") + ",";
                revision_seq = revision_seq + request.getParameter("app3Email") + ",";
            }
            if(request.getParameter("app4Email") != null && !request.getParameter("app4Email").equalsIgnoreCase(""))
            {
                app_seq = app_seq + request.getParameter("app4Email") + ",";
                revision_seq = revision_seq + request.getParameter("app4Email") + ",";
            }
            if(request.getParameter("app5Email") != null && !request.getParameter("app5Email").equalsIgnoreCase(""))
            {
                app_seq = app_seq + request.getParameter("app5Email") + ",";
                revision_seq = revision_seq + request.getParameter("app5Email") + ",";
            }
            
            System.out.println("app_seq: " + app_seq);
            System.out.println("revision_seq: " + revision_seq);
            
            selApp.setBPaasUserDetailuserid(userById);
            selApp.setBPaasWorkOrderRFQHeaderRFQID(rfqHeader);
            selApp.setUpdatedby(request.getParameter("updatedby"));
            selApp.setUpdatedate(today);
            selApp.setCreatedby(request.getParameter("updatedby"));
            selApp.setCreationdate(today);
            selApp.setApprovername1(request.getParameter("app1Name"));
            selApp.setApprovername2(request.getParameter("app2Name"));
            selApp.setApprovername3(request.getParameter("app3Name"));
            selApp.setApprovername4(request.getParameter("app4Name"));
            selApp.setApprovername5(request.getParameter("app5Name"));
            selApp.setUsername1(request.getParameter("app1Email"));
            selApp.setUsername2(request.getParameter("app2Email"));
            selApp.setUsername3(request.getParameter("app3Email"));
            selApp.setUsername4(request.getParameter("app4Email"));
            selApp.setUsername5(request.getParameter("app5Email"));
            selApp.setEmailid1(request.getParameter("app1Email"));
            selApp.setEmail2(request.getParameter("app2Email"));
            selApp.setEmail3(request.getParameter("app3Email"));
            selApp.setEmail4(request.getParameter("app4Email"));
            selApp.setEmail5(request.getParameter("app5Email"));
            selApp.setApprovalsequence(app_seq);
            selApp.setAssignedTo(request.getParameter("app1Email"));
            selApp.setRevisionsequence(revision_seq);

            int selAppId = selAppDao.saveWorkOrderSelectedApprover(selApp);
            System.out.println("selAppId: " + selAppId);

        } catch (NumberFormatException | ParseException ex) {
            Logger.getLogger(CreateRfpCont.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return new ModelAndView("redirect:/home.do");
    }
}
