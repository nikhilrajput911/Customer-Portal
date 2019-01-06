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
import com.eportal.dao.SupplierAddattachmentDao;
import com.eportal.dao.SupplierHeaderDao;
import com.eportal.dao.SupplierLineitemDao;
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.SupplierUserDao;
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
import com.eportal.entities.SupplierAddattachment;
import com.eportal.entities.SupplierHeader;
import com.eportal.entities.SupplierLineitem;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.SupplierUser;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderAddclauses;
import com.eportal.entities.WorkOrderAttachment;
import com.eportal.entities.WorkOrderComment;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderRfqLineItem;
import com.eportal.entities.WorkOrderSelectedApprover;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

@Controller
public class RfeManagementController {

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;
    @Autowired
    WorkOrderRfqHeader rfqHeader;

    @Autowired
    SupplierUserDao supplierUserDao;

    @Autowired
    SupplierSelectionDao supplierSelectionDao;
    @Autowired
    SupplierSelection supplierSelection;

    @Autowired
    SupplierHeaderDao supplierHeaderDao;
    @Autowired
    SupplierHeader supplierHeader;
    @Autowired
    SupplierLineitemDao supplierLineItemDao;
    @Autowired
    SupplierLineitem supplierLineItem;
    @Autowired
    SupplierAddattachmentDao supplierAttachmentDao;
    @Autowired
    SupplierAddattachment supplierAttachment;

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
    WorkOrderSelectedApproverDao selAppDao;
    @Autowired
    WorkOrderSelectedApprover selApp;

    @Autowired
    WorkOrderCommentDao workOrderCommentDao;

    @Value("${acknowledge_awaiting}")
    private String acknowledge_awaiting;

    @Value("${rejected_by_supplier}")
    private String rejected_by_supplier;

    @Value("${bid_awaiting}")
    private String bid_awaiting;

    @Autowired
    RfeCostCodeDao costCode;
    @Autowired
    RfeNegotiationStyleDao negotiationStyle;
    @Autowired
    UserDetailDao userDetailDao;
    @Autowired
    CategorySubcategoryDao categorySubcategory;
    @Autowired
    ClausesDao clausesDao;

    @Value("${awarded}")
    private String awarded;

    @Value("${bid_submitted}")
    private String bid_submitted;

    @Value("${approval_awaiting}")
    private String approval_awaiting;

    @Value("${release_awaiting}")
    private String release_awaiting;

    @Value("${sent_for_revision}")
    private String sent_for_revision;

    @RequestMapping(value = "releaseRfp")
    public ModelAndView releaseRfp(Model model, HttpSession session) {

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        if (model.containsAttribute("message")) {
            System.out.println("message");
        }

        List<WorkOrderRfqHeader> headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByUserdetail_IdAndRfqStatus(userId, "Awaiting Release");
        System.out.println("headers.size: " + headers.size());

//        List<WorkOrderRfqHeader> releasedHeaders = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus("Awaiting Acknowledge");
//        System.out.println("releasedHeaders.size: " + releasedHeaders.size());
//
        List<SupplierUser> suppliers = (List<SupplierUser>) supplierUserDao.findBySupplierStatusOrderByCreationDateDesc("Active");
        System.out.println("supplier: " + suppliers.size());
//        
        Map<String, Object> map = new HashMap<>();
        map.put("headers", headers);
        map.put("suppliers", suppliers);
//        map.put("releasedHeaders", releasedHeaders);

        return new ModelAndView("releaseRfp", "map", map);
    }

    @RequestMapping(value = "/doReleaseRfp", method = RequestMethod.POST)
    public ModelAndView doReleaseRfp(HttpServletRequest request, RedirectAttributes model, HttpSession session) {
        System.out.println("doReleaseRfp");

        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);

        Date today = new Date();

        String rfpId = request.getParameter("approved_rfp_select");
        System.out.println("rfpid: " + rfpId);

        WorkOrderRfqHeader header = rfqHeaderDao.getWorkOrderRfqHeaderById(Integer.parseInt(rfpId));

        String selectedSupplier = request.getParameter("selectedSupplierId");
        System.out.println("selectedSupplier: " + selectedSupplier);

        if (selectedSupplier != null) {
            String[] selectedSupplierIds = selectedSupplier.split("~");
            System.out.println("length: " + selectedSupplierIds.length);
            for (String id : selectedSupplierIds) {
                System.out.println("id: " + id);
                SupplierUser supplier = supplierUserDao.getSupplierById(Integer.parseInt(id));

                supplierSelection.setBPaasSupplierUserTableid(supplier);
                supplierSelection.setBPaasWorkOrderRFQHeaderRFQID(header);
                supplierSelection.setCreationdate(today);
                supplierSelection.setUpdatedate(today);
                supplierSelection.setCreatedby(username);
                supplierSelection.setUpdatedby(username);
                supplierSelection.setSupplierWFstatus(acknowledge_awaiting);

                supplierSelectionDao.saveSupplierSelection(supplierSelection);
            }
            header.setRfqstatus(acknowledge_awaiting);
            header.setAssignedTo("Supplier(s)");
            header.setUpdatedate(today);
            rfqHeaderDao.updateWorkOrderRfqHeader(header);
        }

        model.addFlashAttribute("message", "RFP_" + rfpId + " has been released.");

        return new ModelAndView("redirect:/releaseRfp.do");
    }

    @RequestMapping(value = "/doActionOnRejectReleasedRfp")
    public ModelAndView doRejectReleasedRfp(@RequestParam("rfpId") int rfpId, @RequestParam("action") String action, HttpSession session) {
        System.out.println("rfpId: " + rfpId);
        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);
        System.out.println("action: " + action);
        Date today = new Date();
        String username = (String) session.getAttribute("username");

        List<SupplierSelection> supplierList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, userId);
        System.out.println("supplier: " + supplierList.size());

        if (!supplierList.isEmpty() && supplierList.size() == 1) {
            SupplierSelection supplier = supplierList.get(0);
            if (action != null && action.equalsIgnoreCase("reject")) {
                supplier.setSupplierWFstatus(rejected_by_supplier);
            } else if (action != null && action.equalsIgnoreCase("acknowledge")) {
                supplier.setSupplierWFstatus(bid_awaiting);

                rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
                rfqHeader.setRfqstatus(bid_awaiting);
                rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
            }
            supplier.setUpdatedate(today);
            supplier.setUpdatedby(username);

            supplierSelectionDao.updateSupplierSelection(supplier);
            System.out.println("updated");
        }

        return new ModelAndView("redirect:/mytask.do");
    }

    @RequestMapping(value = "/doSubmitReleasedRfp", method = RequestMethod.POST)
    public ModelAndView doSaveReleasedRfp(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {

        System.out.println("doSubmitReleasedRfp");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int rfpId = Integer.parseInt(request.getParameter("rfpId"));
        int userId = (int) session.getAttribute("userId");

        Date today = new Date();
        String username = (String) session.getAttribute("username");

        WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);

        List<SupplierSelection> supplierList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, userId);
        System.out.println("supplier: " + supplierList.size());
        System.out.println("rfpId: " + rfpId);
        String notetobuyer = request.getParameter("notetobuyer");

        if (!supplierList.isEmpty() && supplierList.size() == 1) {
            SupplierSelection supplierSelectionObj = supplierList.get(0);

            supplierHeader.setBPaasSupplierSelectionTableid(supplierSelectionObj);
            supplierHeader.setNotetobuyer(notetobuyer);
            supplierHeader.setCreatedby(username);
            supplierHeader.setCreationdate(today);
            supplierHeader.setUpdatedate(today);
            supplierHeader.setUpdatedby(username);
            supplierHeader.setBpaasWorkorderrfqheaderRfqid(rfqHeader);

            int supplierHeaderId = supplierHeaderDao.saveSupplierHeader(supplierHeader);
            System.out.println("supplierHeaderId: " + supplierHeaderId);

            int noOfCategory = Integer.parseInt(request.getParameter("noOfCategory"));
            System.out.println("noOfCategory: " + noOfCategory);

            String[] price_per_quantity = request.getParameterValues("price_per_quantity");
            System.out.println("price_per_quantity size: " + price_per_quantity.length);

            String[] total_price = request.getParameterValues("total_price");
            System.out.println("total_price size: " + price_per_quantity.length);

            String[] expected_price = request.getParameterValues("expected_price");
            System.out.println("expected_price size: " + price_per_quantity.length);

            String[] expected_delv_date = request.getParameterValues("expected_delv_date");
            System.out.println("expected_delv_date size: " + price_per_quantity.length);

            for (int i = 0; i < noOfCategory; i++) {
                System.out.println("expected_delv_date: " + df.parse(expected_delv_date[i]));

                supplierLineItem.setWorkOrderSupplierHeaderTableid(supplierHeader);
                supplierLineItem.setQuotepriceperquantity(Double.parseDouble(price_per_quantity[i]));
                supplierLineItem.setTotalprice(Double.parseDouble(total_price[i]));
                supplierLineItem.setExpectedprice(Double.parseDouble(expected_price[i]));
                supplierLineItem.setDeliverydate(df.parse(expected_delv_date[i]));
                supplierLineItem.setCreatedby(username);
                supplierLineItem.setCreationdate(today);
                supplierLineItem.setUpdatedby(username);
                supplierLineItem.setUpdatedate(today);

                int supplierLineitemId = supplierLineItemDao.saveSupplierLineitem(supplierLineItem);
                System.out.println("supplierLineitemId: " + supplierLineitemId);
            }

            System.out.println("no of files: " + files.length);
            String[] attDesc = request.getParameterValues("supplierAttDescription");

            if (files[0].getOriginalFilename() != null && !files[0].getOriginalFilename().equalsIgnoreCase("")) {
                for (int i = 0; i < files.length; i++) {
                    System.out.println("file name: " + files[i].getOriginalFilename());

                    supplierAttachment.setBPaasWOSupplierHeaderid(supplierHeader);
                    supplierAttachment.setAddattachment(files[i].getBytes());
                    supplierAttachment.setName(files[i].getOriginalFilename());
                    supplierAttachment.setDescription(attDesc[i]);
                    supplierAttachment.setCreatedate(today);
                    supplierAttachment.setCreatedby(username);
                    supplierAttachment.setUpdatedate(today);
                    supplierAttachment.setUpdatedby(username);

                    int supplierAttchId = supplierAttachmentDao.saveSupplierAddattachment(supplierAttachment);
                    System.out.println("supplierAttchId: " + supplierAttchId);
                }
            }

            rfqHeader.setRfqstatus(bid_submitted);
            rfqHeader.setAssignedTo(rfqHeader.getCreatedby());

            rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);

//            System.out.println("supplierSelection: " + supplierSelectionObj.getId() + " ; " + supplierSelectionObj.getSupplierWFstatus());
//            System.out.println("bid_submitted:323 " + bid_submitted);
            supplierSelectionObj.setSupplierWFstatus(bid_submitted);
//            System.out.println(supplierSelectionObj.getSupplierWFstatus());

            supplierSelectionObj.setUpdatedate(today);
            supplierSelectionObj.setUpdatedby(username);

            supplierSelectionDao.updateSupplierSelection(supplierSelectionObj);

            System.out.println("updated=================================");
        }

        return new ModelAndView("redirect:/mytask.do");
    }

    @RequestMapping(value = "/rfpStatus")
    public ModelAndView rfpStatus(HttpSession session) {
        System.out.println("rfp status====================");

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        List<WorkOrderRfqHeader> releasedHeaders = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByManyStatus();
        System.out.println("releasedHeaders.size: " + releasedHeaders.size());

        List<WorkOrderRfqHeader> awardedHeaders = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus("Awarded");
        System.out.println("awardedHeaders.size: " + awardedHeaders.size());

        List<SupplierSelection> supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatus("Awarded");
        System.out.println("supplierSelection.size: " + supplierSelection.size());

        Map<String, Object> map = new HashMap<>();
        map.put("releasedHeaders", releasedHeaders);
        map.put("awardedHeaders", awardedHeaders);
        map.put("supplierSelection", supplierSelection);

        return new ModelAndView("rfpstatus", "map", map);

    }

    @RequestMapping(value = "/showAwardRfp")
    public ModelAndView showRfpAward(@RequestParam("rfpid") int rfpId, @RequestParam("supplierid") int supplierId, HttpSession session) {
        System.out.println("showRfpAward");
        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);

        System.out.println("rfpId: " + rfpId);
        System.out.println("supplierId: " + supplierId);

        rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
        System.out.println("title: " + rfqHeader.getRFQTitle());

        List<WorkOrderRfqHeader> rfqHeaders = new ArrayList<>();
        rfqHeaders.add(rfqHeader);

        List<WorkOrderRfqLineItem> lineItems = (List<WorkOrderRfqLineItem>) rfqLineItemDao.findByHeaderId(rfpId);
        System.out.println("line item size: " + lineItems.size());

        List<WorkOrderAddclauses> clauses = (List<WorkOrderAddclauses>) rfqClausesDao.findByHeaderId(rfpId);
        System.out.println("clauses size: " + clauses.size());

        List<WorkOrderAttachment> attmnt = (List<WorkOrderAttachment>) rfqAttDao.findByHeaderId(rfpId);
        System.out.println("attmnt size: " + attmnt.size());

        List<WorkOrderSelectedApprover> approval = (List<WorkOrderSelectedApprover>) selAppDao.findByHeaderId(rfpId);
        System.out.println("approval size: " + approval.size());

        int approvalId = approval.get(0).getId();

//        List<WorkOrderComment> commentList = (List<WorkOrderComment>) workOrderCommentDao.findLatestCommentByApprovalId(approvalId);
//        System.out.println("list : " + commentList);
//        
        List<SupplierHeader> supplierHeader = (List<SupplierHeader>) supplierHeaderDao.findBySupplierAndRfqId(rfpId, supplierId);
        System.out.println("supplierHeader: " + supplierHeader);

        List<SupplierLineitem> supplierLineitem = null;
        List<SupplierAddattachment> supplierAttachment = null;
        List<SupplierUser> supplierUser = null;

        if (!supplierHeader.isEmpty() && supplierHeader.size() == 1) {
            SupplierHeader supplier = supplierHeader.get(0);
            supplierLineitem = (List<SupplierLineitem>) supplierLineItemDao.findBySupplierHeaderId(supplier.getId());
            supplierAttachment = (List<SupplierAddattachment>) supplierAttachmentDao.findBySupplierHeaderId(supplier.getId());
            SupplierUser user = supplier.getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid();
            supplierUser = new ArrayList<>();
            supplierUser.add(user);
        }

        Map<String, List<?>> rfqDetails = new HashMap<>();

        rfqDetails.put("rfqHeaders", rfqHeaders);
        rfqDetails.put("rfqLineItems", lineItems);
        rfqDetails.put("rfqClauses", clauses);
        rfqDetails.put("rfqAtmnt", attmnt);
        rfqDetails.put("rfqApproval", approval);
        rfqDetails.put("rfqSupplierHeader", supplierHeader);
        rfqDetails.put("rfqSupplierLineitem", supplierLineitem);
        rfqDetails.put("rfqSupplierAttachment", supplierAttachment);
        rfqDetails.put("rfqsupplierUser", supplierUser);
//        if (commentList != null) {
//            rfqDetails.put("approvalComment", commentList);
//        }
        if (userType != null && userType.equalsIgnoreCase("Supplier")) {
            return new ModelAndView("supplierAwardRfp", "rfqDetails", rfqDetails);
        } else {
            return new ModelAndView("awardrfp", "rfqDetails", rfqDetails);
        }

    }

    @RequestMapping("doAwardRfp")
    public ModelAndView doAwardRfp(@RequestParam("rfpid") int rfpId, @RequestParam("supplierid") int supplierId, HttpSession session) {
        System.out.println("doAwardRfp");
        int userId = (int) session.getAttribute("userId");

        System.out.println("rfpId: " + rfpId);
        System.out.println("supplierId: " + supplierId);
        System.out.println("userId: " + userId);

        Date today = new Date();
        String username = (String) session.getAttribute("username");

        WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);

        rfqHeader.setRfqstatus(awarded);
        rfqHeader.setUpdatedate(today);
        rfqHeader.setUpdatedby(username);

        rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
        System.out.println("rfq header updated");

        List<SupplierSelection> selection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, supplierId);
        System.out.println("selection size: " + selection.size());
        if (selection != null) {
            supplierSelection = selection.get(0);
            supplierSelection.setSupplierWFstatus(awarded);
            supplierSelection.setUpdatedate(today);
            supplierSelection.setUpdatedby(username);

            supplierSelectionDao.updateSupplierSelection(supplierSelection);
        }

        return new ModelAndView("redirect:/rfpStatus.do");
    }

    @RequestMapping("doAwardAllSupplierRfp")
    public ModelAndView doAwardAllSupplierRfp(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("doAwardAllSupplierRfp");
        int userId = (int) session.getAttribute("userId");

        System.out.println(request.getParameter("rfqid"));
        System.out.println(request.getParameter("suppliersId"));

        int rfpId = Integer.parseInt(request.getParameter("rfqid"));
        String suppliersId = request.getParameter("suppliersId");

        System.out.println("rfpId: " + rfpId);
//        System.out.println("supplierId: " + suppliersId);
        System.out.println("userId: " + userId);

        Date today = new Date();
        String username = (String) session.getAttribute("username");

        WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);

        rfqHeader.setRfqstatus(awarded);
        rfqHeader.setUpdatedate(today);
        rfqHeader.setUpdatedby(username);

        rfqHeaderDao.updateWorkOrderRfqHeader(rfqHeader);
        System.out.println("rfq header updated");

        String[] supplierIds = suppliersId.split("~");
        System.out.println("supplier size: " + supplierIds);

        for (String supplier : supplierIds) {

            int supplierId = Integer.parseInt(supplier);
            System.out.println("supplierId: " + supplierId);
            List<SupplierSelection> selection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, supplierId);
            System.out.println("selection size: " + selection.size());
            if (selection != null) {
                supplierSelection = selection.get(0);
                supplierSelection.setSupplierWFstatus(awarded);
                supplierSelection.setUpdatedate(today);
                supplierSelection.setUpdatedby(username);

                supplierSelectionDao.updateSupplierSelection(supplierSelection);
            }
        }

        return new ModelAndView("redirect:/rfpStatus.do");
    }

    @RequestMapping(value = "redirectByRfpStatus")
    public ModelAndView redirectByRfpStatus(@RequestParam("rfpid") int rfpid, @RequestParam("status") String status, HttpSession session) {
        System.out.println("rfpid: " + rfpid);
        System.out.println("status: " + status);

        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        if (status != null && (status.equalsIgnoreCase("Bid Submitted") || status.equalsIgnoreCase("Awarded"))
                && (userType != null && userType.equalsIgnoreCase("Supplier"))) {
            return new ModelAndView("redirect:/showAwardRfp.do?rfpid=" + rfpid + "&supplierid=" + userId);
        } else {
            return new ModelAndView("redirect:/showRfp.do?rfpId=" + rfpid);
        }

    }

    @RequestMapping(value = "rfpstatussummary")
    public ModelAndView rfpStatusSummary(Map<String, Object> map) {
        System.out.println("rfpstatussummary================");

//        rfqHeaderDao.findByLeftJoinOnSupplierSelection();
//        List<SupplierUser> supplierList = (List<SupplierUser>) supplierUserDao.getSupplier();
//        System.out.println("supplierList: " + supplierList.size());
//        
//        List<WorkOrderRfqHeader> supplierSelectionList = (List<WorkOrderRfqHeader>) supplierSelectionDao.findDistinctRfqId();
//        System.out.println("supplierSelectionList: " + supplierSelectionList.size());
//        
//        List<String> statusList = new ArrayList<>();
//        
//        statusList.add(awarded);
//        statusList.add(release_awaiting);
//        statusList.add(sent_for_revision);
//        statusList.add(acknowledge_awaiting);
//        statusList.add(rejected_by_supplier);
//        statusList.add(bid_awaiting);
//        statusList.add(bid_submitted);
//        statusList.add(approval_awaiting);
//        
//        Collections.sort(statusList);
//        
//        map.put("rfqHeader", rfqHeaderList);
//        map.put("statusList", statusList);
//        map.put("supplierList", supplierList);
//        map.put("releasedRfqList", supplierSelectionList);
//        List<WorkOrderRfqHeader> rfqHeaderList = (List<WorkOrderRfqHeader>) rfqHeaderDao.getWorkOrderRfqHeaders();
//        System.out.println("rfqHeaderList size: " + rfqHeaderList.size());
        List<Object> list = (List<Object>) supplierSelectionDao.findByLeftJoinOnSupplierSelection();
        System.out.println("findByLeftJoinOnSupplierSelection: " + list.size());

        List<WorkOrderRfqHeader> releasedHeaders = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByManyStatus();
        System.out.println("releasedHeaders.size: " + releasedHeaders.size());

        List<SupplierSelection> supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatus("Awarded");
        System.out.println("supplierSelection.size: " + supplierSelection.size());
//        for(Object obj : list)
//        {
////            System.out.println(obj);
//            Object[] rows = (Object[]) obj;
//            System.out.println(rows.length + "; " + rows[0] + " : " + rows[1]);
////            if(rows[0] != null)
////            {
////                SupplierSelection supp = (SupplierSelection) rows[0];
////                System.out.println(supp.getBPaasSupplierUserTableid().getCompanyname());
////            }
//        }

        map.put("rfqHeaderSupplierList", list);
        map.put("releasedHeaders", releasedHeaders);
        map.put("supplierSelection", supplierSelection);

        return new ModelAndView("rfpstatussummary", "map", map);
    }

    @RequestMapping(value = "rfpsummarydetails")
    public ModelAndView rfpSummaryDetails(@RequestParam("rfqid") int rfpId, @RequestParam("rfpstatus") String rfpstatus, @RequestParam("supplierrfpstatus") String supplierrfpstatus, @RequestParam("supplierid") String supplierid, Map<String, Object> rfqDetails, HttpSession session) {
        System.out.println("rfpsummarydetails================");
        System.out.println(rfpId);
        System.out.println(rfpstatus);
        System.out.println(supplierrfpstatus);
        System.out.println(supplierid);

        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        if (supplierrfpstatus != null && !supplierrfpstatus.equalsIgnoreCase("")
                && (supplierrfpstatus.equalsIgnoreCase("Bid Submitted") || supplierrfpstatus.equalsIgnoreCase("Awarded"))) {

            return new ModelAndView("redirect:/awardrfpsummarydetails.do?rfpid=" + rfpId + "&supplierid=" + supplierid);

        } else if (supplierrfpstatus != null && !supplierrfpstatus.equalsIgnoreCase("") 
                && (supplierrfpstatus.equalsIgnoreCase("Rejected by Supplier") || supplierrfpstatus.equalsIgnoreCase("Awaiting Acknowledge"))) {

            return new ModelAndView("redirect:/supplierstatussummarydetails.do?rfpid=" + rfpId + "&supplierid=" + supplierid);

        } else {
            System.out.println("rfpId: " + rfpId);
            rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
            System.out.println("title: " + rfqHeader.getRFQTitle());

//            List<SupplierSelection> supplierSelectionList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, Integer.parseInt(supplierid));
            List<WorkOrderRfqHeader> rfqHeaders = new ArrayList<>();
            rfqHeaders.add(rfqHeader);

            List<WorkOrderRfqLineItem> lineItems = (List<WorkOrderRfqLineItem>) rfqLineItemDao.findByHeaderId(rfpId);
            System.out.println("line item size: " + lineItems.size());

            List<WorkOrderAddclauses> clauses = (List<WorkOrderAddclauses>) rfqClausesDao.findByHeaderId(rfpId);
            System.out.println("clauses size: " + clauses.size());

            List<WorkOrderAttachment> attmnt = (List<WorkOrderAttachment>) rfqAttDao.findByHeaderId(rfpId);
            System.out.println("attmnt size: " + attmnt.size());

            List<WorkOrderSelectedApprover> approval = (List<WorkOrderSelectedApprover>) selAppDao.findByHeaderId(rfpId);
            System.out.println("approval size: " + approval.size());

            int approvalId = approval.get(0).getId();

            List<WorkOrderComment> commentList = (List<WorkOrderComment>) workOrderCommentDao.findLatestCommentByApprovalId(approvalId);
            System.out.println("list : " + commentList);

            rfqDetails.put("rfqHeaders", rfqHeaders);
            rfqDetails.put("rfqLineItems", lineItems);
            rfqDetails.put("rfqClauses", clauses);
            rfqDetails.put("rfqAtmnt", attmnt);
            rfqDetails.put("rfqApproval", approval);
//            rfqDetails.put("supplierSelection", supplierSelectionList);
            if (commentList != null) {
                rfqDetails.put("approvalComment", commentList);
            }

            System.out.println(userType);

            return new ModelAndView("rfpsummarydetails", "rfqDetails", rfqDetails);
        }
    }

    @RequestMapping(value = "awardrfpsummarydetails")
    public ModelAndView awardRfpSummaryDetails(@RequestParam("rfpid") int rfpId, @RequestParam("supplierid") String supplierId, Map<String, Object> rfqDetails, HttpSession session) {
        System.out.println("awardrfpsummarydetails");
        System.out.println(rfpId);
        System.out.println(supplierId);

        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);

        System.out.println("rfpId: " + rfpId);
        System.out.println("supplierId: " + supplierId);

        rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
        System.out.println("title: " + rfqHeader.getRFQTitle());

        List<WorkOrderRfqHeader> rfqHeaders = new ArrayList<>();
        rfqHeaders.add(rfqHeader);

        List<WorkOrderRfqLineItem> lineItems = (List<WorkOrderRfqLineItem>) rfqLineItemDao.findByHeaderId(rfpId);
        System.out.println("line item size: " + lineItems.size());

        List<WorkOrderAddclauses> clauses = (List<WorkOrderAddclauses>) rfqClausesDao.findByHeaderId(rfpId);
        System.out.println("clauses size: " + clauses.size());

        List<WorkOrderAttachment> attmnt = (List<WorkOrderAttachment>) rfqAttDao.findByHeaderId(rfpId);
        System.out.println("attmnt size: " + attmnt.size());

        List<WorkOrderSelectedApprover> approval = (List<WorkOrderSelectedApprover>) selAppDao.findByHeaderId(rfpId);
        System.out.println("approval size: " + approval.size());

        int approvalId = approval.get(0).getId();

//        List<WorkOrderComment> commentList = (List<WorkOrderComment>) workOrderCommentDao.findLatestCommentByApprovalId(approvalId);
//        System.out.println("list : " + commentList);
//        
        List<SupplierHeader> supplierHeader = (List<SupplierHeader>) supplierHeaderDao.findBySupplierAndRfqId(rfpId, Integer.parseInt(supplierId));
        System.out.println("supplierHeader: " + supplierHeader);

        List<SupplierLineitem> supplierLineitem = null;
        List<SupplierAddattachment> supplierAttachment = null;
        List<SupplierUser> supplierUser = null;

        if (!supplierHeader.isEmpty() && supplierHeader.size() == 1) {
            SupplierHeader supplier = supplierHeader.get(0);
            supplierLineitem = (List<SupplierLineitem>) supplierLineItemDao.findBySupplierHeaderId(supplier.getId());
            supplierAttachment = (List<SupplierAddattachment>) supplierAttachmentDao.findBySupplierHeaderId(supplier.getId());
            SupplierUser user = supplier.getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid();
            supplierUser = new ArrayList<>();
            supplierUser.add(user);
        }

        rfqDetails.put("rfqHeaders", rfqHeaders);
        rfqDetails.put("rfqLineItems", lineItems);
        rfqDetails.put("rfqClauses", clauses);
        rfqDetails.put("rfqAtmnt", attmnt);
        rfqDetails.put("rfqApproval", approval);
        rfqDetails.put("rfqSupplierHeader", supplierHeader);
        rfqDetails.put("rfqSupplierLineitem", supplierLineitem);
        rfqDetails.put("rfqSupplierAttachment", supplierAttachment);
        rfqDetails.put("rfqsupplierUser", supplierUser);

        return new ModelAndView("awardrfpsummarydetails", "rfqDetails", rfqDetails);
    }

    @RequestMapping(value = "supplierstatussummarydetails")
    public ModelAndView supplierStatusSummaryDetails(@RequestParam("rfpid") int rfpId, @RequestParam("supplierid") String supplierId, Map<String, Object> rfqDetails, HttpSession session) {
        System.out.println("awardrfpsummarydetails");
        System.out.println(rfpId);
        System.out.println(supplierId);

        String userType = (String) session.getAttribute("userType");
        System.out.println("userType: " + userType);

        System.out.println("rfpId: " + rfpId);
        System.out.println("supplierId: " + supplierId);

        System.out.println("rfpId: " + rfpId);
        rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfpId);
        System.out.println("title: " + rfqHeader.getRFQTitle());

        List<SupplierSelection> supplierSelectionList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfpId, Integer.parseInt(supplierId));
        
        List<WorkOrderRfqHeader> rfqHeaders = new ArrayList<>();
        rfqHeaders.add(rfqHeader);

        List<WorkOrderRfqLineItem> lineItems = (List<WorkOrderRfqLineItem>) rfqLineItemDao.findByHeaderId(rfpId);
        System.out.println("line item size: " + lineItems.size());

        List<WorkOrderAddclauses> clauses = (List<WorkOrderAddclauses>) rfqClausesDao.findByHeaderId(rfpId);
        System.out.println("clauses size: " + clauses.size());

        List<WorkOrderAttachment> attmnt = (List<WorkOrderAttachment>) rfqAttDao.findByHeaderId(rfpId);
        System.out.println("attmnt size: " + attmnt.size());

        List<WorkOrderSelectedApprover> approval = (List<WorkOrderSelectedApprover>) selAppDao.findByHeaderId(rfpId);
        System.out.println("approval size: " + approval.size());

        int approvalId = approval.get(0).getId();

        List<WorkOrderComment> commentList = (List<WorkOrderComment>) workOrderCommentDao.findLatestCommentByApprovalId(approvalId);
        System.out.println("list : " + commentList);

        rfqDetails.put("rfqHeaders", rfqHeaders);
        rfqDetails.put("rfqLineItems", lineItems);
        rfqDetails.put("rfqClauses", clauses);
        rfqDetails.put("rfqAtmnt", attmnt);
        rfqDetails.put("rfqApproval", approval);
        rfqDetails.put("supplierSelection", supplierSelectionList);
        if (commentList != null) {
            rfqDetails.put("approvalComment", commentList);
        }

        System.out.println(userType);

        return new ModelAndView("supplierstatussummarydetails", "rfqDetails", rfqDetails);
    }
}
