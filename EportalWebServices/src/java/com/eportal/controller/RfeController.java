/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import JSON.JSONArray;
import JSON.JSONException;
import JSON.JSONObject;
import com.eportal.dao.CategorySubcategoryDao;
import com.eportal.dao.CustSecQuesDao;
import com.eportal.dao.CustomerDocumentDao;
import com.eportal.dao.CustomerSeededDao;
import com.eportal.dao.CustomerSubUserDao;
import com.eportal.dao.GroupsDao;
import com.eportal.dao.ProjectSeededDao;
import com.eportal.dao.SupplierGroupDao;
import com.eportal.dao.CustomerAuditReportDao;
import com.eportal.dao.SupplierHeaderDao;
import com.eportal.dao.SupplierLineitemDao;
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.SupplierUserDao;
import com.eportal.dao.UserDetailDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderRfqLineItemDao;
import com.eportal.entities.CategorySubcategory;
import com.eportal.entities.CustSecQues;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerAuditReport;
import com.eportal.entities.CustomerSeeded;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.ProjectSeeded;
import com.eportal.entities.SupplierGroup;
import com.eportal.entities.SupplierHeader;
import com.eportal.entities.SupplierLineitem;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.SupplierUser;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderRfqHeader;
import com.eportal.entities.WorkOrderRfqLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RfeController {

    @Autowired
    CategorySubcategoryDao categorySubcategory;
    @Autowired
    UserDetailDao userDetailDao;
    @Autowired
    WorkOrderRfqHeaderDao headerDao;
    @Autowired
    SupplierSelectionDao supplierSelectionDao;

    @Autowired
    SupplierHeaderDao supplierHeaderDao;

    @Autowired
    SupplierLineitemDao supplierLineItemDao;

    @Autowired
    WorkOrderRfqHeaderDao rfqHeaderDao;

    @Autowired
    WorkOrderRfqLineItemDao rfqLineitemDao;

    @Autowired
    CustomerDocumentDao customerDocumentDao;

    @Autowired
    SupplierUserDao supplierUserDao;

    @Autowired
    CustomerSeededDao custSeededDao;

    @Autowired
    CustomerSubUserDao customerSubUserDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GroupsDao groupDao;

    @Autowired
    SupplierGroupDao supplierGroupDao;
    @Autowired
    SupplierGroup supplierGroup;

    @Autowired
    ProjectSeededDao projectSeededDao;

    @Autowired
    CustSecQuesDao custSecQueDao;

    @Autowired
    CustomerAuditReportDao custAuditReportDao;
    @Autowired
    CustomerAuditReport custAuditReport;

    @Value("${bid_submitted}")
    private String bid_submitted;

    @RequestMapping(value = "/rfeCont")
    public void service(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        PrintWriter out = null;
        JSONArray jArra = new JSONArray();
        JSONObject jObj = new JSONObject();
        try {

            System.out.println("========Rfe Controller==========");
            String reqFrom = request.getParameter("reqFrom");
            System.out.println("reqFrom: " + reqFrom);

            if (reqFrom.equalsIgnoreCase("category")) {
                //System.out.println("category");
                String category = request.getParameter("category");
                List<CategorySubcategory> subcategories = (List<CategorySubcategory>) categorySubcategory.getSubCategoryByCategory(category);
                out = response.getWriter();

                for (CategorySubcategory sub : subcategories) {
                    jArra.put(sub.getSubcategoryname());
                }

                out.println(jArra);
                //out.close();

            } else if (reqFrom.equalsIgnoreCase("approvals")) {
                System.out.println("approvals");
                //JSONObject jObj = new JSONObject();
                out = response.getWriter();
                int id = Integer.parseInt(request.getParameter("approvalId"));
                System.out.println("id: " + id);
                List<Userdetail> user = (List<Userdetail>) userDetailDao.findByUserId(id);
                System.out.println(user.get(0).getUsername());
                jObj.put("username", user.get(0).getUsername());

                out.println(jObj);
                //out.close();
            } else if (reqFrom.equalsIgnoreCase("releaseRFP")) {
                out = response.getWriter();
                System.out.println("releaseRFP");
                System.out.println(request.getParameter("approvedRfp"));
                int approved_rfp = Integer.parseInt(request.getParameter("approvedRfp"));
                System.out.println("approved_rfp: " + approved_rfp);

                WorkOrderRfqHeader header = headerDao.getWorkOrderRfqHeaderById(approved_rfp);

                jObj.put("RfqTitle", header.getRFQTitle());
                jObj.put("TimeLeft", header.getTimeleft());
                jObj.put("Desc", header.getDescription());

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("supplierOnRfpId")) {
                System.out.println("in supplierOnRfpId");
                out = response.getWriter();
                String rfq = request.getParameter("rfpid");
                System.out.println("rfq: " + rfq);
                int rfqId = Integer.parseInt(rfq.split("_")[1]);
                System.out.println("rfq: " + rfq);
                List<SupplierSelection> supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findByRfqId(rfqId);
                System.out.println("size: " + supplierSelection.size());

                JSONArray supplierIdJArr = new JSONArray();
                JSONArray supplierNameJArr = new JSONArray();

                if (!supplierSelection.isEmpty()) {
                    for (SupplierSelection supplier : supplierSelection) {
                        supplierIdJArr.put(supplier.getBPaasSupplierUserTableid().getId());
                        supplierNameJArr.put(supplier.getBPaasSupplierUserTableid().getCompanyname());
                    }
                } else {

                }
//                System.out.println("jArr size: " + jArra.length());
                jObj.put("supplierIdJArr", supplierIdJArr);
                jObj.put("supplierNameJArr", supplierNameJArr);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("supplierRfpStatus")) {
                System.out.println("supplierRfpStatus==================");
                out = response.getWriter();

                String username = (String) session.getAttribute("username");
                System.out.println("username: " + username);

                int rfq = Integer.parseInt(request.getParameter("releasedRfp"));
                System.out.println("rfq: " + rfq);

                List<SupplierSelection> list = (List<SupplierSelection>) supplierSelectionDao.findByCreatedByAndRfpId(rfq, username);
                System.out.println("size: " + list.size());

                for (SupplierSelection obj : list) {
                    JSONObject jsonColumn = new JSONObject();
                    jsonColumn.put("rfpId", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
                    jsonColumn.put("supplieName", obj.getBPaasSupplierUserTableid().getCompanyname());
                    jsonColumn.put("rfpStatus", obj.getSupplierWFstatus());
                    jsonColumn.put("updateDate", obj.getUpdatedate());
                    jsonColumn.put("rfpCreateDate", obj.getBPaasWorkOrderRFQHeaderRFQID().getCreationdate());
                    jsonColumn.put("timeLeft", obj.getBPaasWorkOrderRFQHeaderRFQID().getTimeleft());
                    jsonColumn.put("supplierId", obj.getBPaasSupplierUserTableid().getId());
                    jArra.put(jsonColumn);
                }
                System.out.println("jArr length: " + jArra.length());

                out.println(jArra);
            } else if (reqFrom.equalsIgnoreCase("budgetComparison")) {
                System.out.println("budgetComparison");
                out = response.getWriter();

                String username = (String) session.getAttribute("username");
                System.out.println("username: " + username);

                int rfq = Integer.parseInt(request.getParameter("rfqId"));
                System.out.println("rfq: " + rfq);

//                WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfq);
                List<WorkOrderRfqLineItem> rfqLineItemList = (List<WorkOrderRfqLineItem>) rfqLineitemDao.findByHeaderId(rfq);
                System.out.println("rfqLineItemList: " + rfqLineItemList);

                List<SupplierSelection> list = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndRfqId(bid_submitted, rfq);
                System.out.println("size: " + list);

                List<Integer> Ids;
                Ids = new ArrayList<>();

                for (SupplierSelection obj : list) {
                    Ids.add((int) obj.getId());
                }

                List<SupplierHeader> supplierHeaderList = (List<SupplierHeader>) supplierHeaderDao.findBySupplierSelectionIds(Ids);
                System.out.println("supplierHeader: " + supplierHeaderList);

                DecimalFormat df = new DecimalFormat("#,###.00");

                JSONArray outerJsonArr = new JSONArray();

                for (SupplierHeader obj : supplierHeaderList) {
                    System.out.println(obj.getSupplierLineitemCollection());
                    List<SupplierLineitem> lineItem = (List<SupplierLineitem>) obj.getSupplierLineitemCollection();

                    String supplierName = "";
                    double expectedPrice = 0;
                    double totalPrice = 0;
                    double targetPrice = 0;
                    JSONObject innerJsonObj = new JSONObject();

                    for (int i = 0; i < lineItem.size(); i++) {
                        System.out.println(lineItem.get(i));
                        JSONObject jsonColumn = new JSONObject();

                        jsonColumn.put("rfqid", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBpaasWorkorderrfqheaderRfqid().getRfqid());
                        jsonColumn.put("timeLeft", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBpaasWorkorderrfqheaderRfqid().getTimeleft());
                        jsonColumn.put("supplierName", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid().getCompanyname());
                        jsonColumn.put("quotPricePerQuantity", df.format(lineItem.get(i).getQuotepriceperquantity()));
                        jsonColumn.put("expectedPrice", df.format(lineItem.get(i).getExpectedprice()));
                        jsonColumn.put("totalPrice", df.format(lineItem.get(i).getTotalprice()));
                        jsonColumn.put("targetPrice", df.format(rfqLineItemList.get(i).getTargetprice()));
                        jsonColumn.put("quantity", df.format(rfqLineItemList.get(i).getQuantity()));

                        supplierName = lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid().getCompanyname();
                        expectedPrice = expectedPrice + lineItem.get(i).getExpectedprice();
                        totalPrice = totalPrice + lineItem.get(i).getTotalprice();
                        targetPrice = targetPrice + rfqLineItemList.get(i).getTargetprice();

                        jArra.put(jsonColumn);
                    }
//                    System.out.println("expectedPrice: " + expectedPrice);
//                    System.out.println("totalPrice: " + totalPrice);
//                    System.out.println("targetPrice: " + targetPrice);

                    innerJsonObj.put("chart_supplierName", supplierName);
                    innerJsonObj.put("chart_expectedPrice", expectedPrice);
                    innerJsonObj.put("chart_totalPrice", totalPrice);
                    innerJsonObj.put("chart_targetPrice", targetPrice);

                    outerJsonArr.put(innerJsonObj);
                }
                jObj.put("chartData", outerJsonArr);
                jObj.put("tableData", jArra);

                System.out.println("jArr length: " + jArra.length());

                out.println(jObj);

            } else if (reqFrom.equalsIgnoreCase("responseManagement")) {
                System.out.println("responseManagement");
                out = response.getWriter();

                String username = (String) session.getAttribute("username");
                System.out.println("username: " + username);

                String userType = (String) session.getAttribute("userType");
                System.out.println("userType: " + userType);

                int userId = (int) session.getAttribute("userId");
                System.out.println("userId: " + userId);

                int rfq = Integer.parseInt(request.getParameter("rfqId"));
                System.out.println("rfq: " + rfq);

                List<SupplierSelection> supplierSelection = null;

                if (userType != null && userType.equalsIgnoreCase("SCM User")) {
                    supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findByRfqId(rfq);
                    System.out.println("supplierSelection: " + supplierSelection);
                } else if (userType != null && userType.equalsIgnoreCase("Supplier")) {
                    supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfq, userId);
                    System.out.println("supplierSelection: " + supplierSelection);
                }

                for (SupplierSelection obj : supplierSelection) {
                    JSONObject jsonObj = new JSONObject();

                    jsonObj.put("supplierId", obj.getBPaasSupplierUserTableid().getId());
                    jsonObj.put("supplierName", obj.getBPaasSupplierUserTableid().getCompanyname());
                    jsonObj.put("rfqId", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
                    jsonObj.put("rfqTitle", obj.getBPaasWorkOrderRFQHeaderRFQID().getRFQTitle());
                    jsonObj.put("rfqStatus", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqstatus());

                    jArra.put(jsonObj);
                }
                System.out.println("jArra: " + jArra.length());

                out.println(jArra);
            } else if (reqFrom.equalsIgnoreCase("rfqsearchsummary")) {
                System.out.println("rfqsearchsummary");
                out = response.getWriter();
                String rfqid = request.getParameter("rfqid");
                String rfqtitle = request.getParameter("rfqtitle");
                String rfqstatus = request.getParameter("rfqstatus");
                String supplierid = request.getParameter("supplierid");
                String releasedrfq = request.getParameter("releasedrfq");

                System.out.println("rfqid: " + rfqid);
                System.out.println("rfqtitle: " + rfqtitle);
                System.out.println("rfqstatus: " + rfqstatus);
                System.out.println("supplierid: " + supplierid);
                System.out.println("releasedrfq: " + releasedrfq);

                List<WorkOrderRfqHeader> headers = null;
                List<SupplierSelection> supplierSelectionList = null;

                if (rfqid != null && !rfqid.equalsIgnoreCase("")) {
                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqId(Integer.parseInt(rfqid));
                } else if (rfqtitle != null && !rfqtitle.equalsIgnoreCase("")) {
                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqTitle(rfqtitle);
                } else if (rfqstatus != null && !rfqstatus.equalsIgnoreCase("")) {
                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus(rfqstatus);
                } else if (supplierid != null && !supplierid.equalsIgnoreCase("")) {
                    supplierSelectionList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierId(Integer.parseInt(supplierid));
                }
                String status = "";
                JSONObject outerObj = new JSONObject();
                if (headers != null && !headers.isEmpty()) {
                    status = "SUCCESS";
                    outerObj.put("status", status);

                    for (WorkOrderRfqHeader obj : headers) {
                        JSONObject jsonObj = new JSONObject();

                        jsonObj.put("RFQ_ID", obj.getRfqid());
                        jsonObj.put("RFQ_STATUS", obj.getRfqstatus());
                        jsonObj.put("RFQ_TITLE", obj.getRFQTitle());
                        jsonObj.put("CREATION_DATE", obj.getCreationdate());
                        jsonObj.put("TIME_LEFT", obj.getTimeleft());

                        jArra.put(jsonObj);
                    }
                    outerObj.put("data", jArra);

                } else if (supplierSelectionList != null && !supplierSelectionList.isEmpty()) {
                    status = "SUCCESS";
                    outerObj.put("status", status);

                    for (SupplierSelection obj : supplierSelectionList) {
                        JSONObject jsonObj = new JSONObject();

                        jsonObj.put("RFQ_ID", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
                        jsonObj.put("RFQ_STATUS", obj.getSupplierWFstatus());
                        jsonObj.put("RFQ_TITLE", obj.getBPaasWorkOrderRFQHeaderRFQID().getRFQTitle());
                        jsonObj.put("CREATION_DATE", obj.getBPaasWorkOrderRFQHeaderRFQID().getCreationdate());
                        jsonObj.put("TIME_LEFT", obj.getBPaasWorkOrderRFQHeaderRFQID().getTimeleft());

                        jArra.put(jsonObj);
                    }
                    outerObj.put("data", jArra);

                } else {
                    status = "FAILED";
                    outerObj.put("status", status);
                }
                out.println(outerObj);
            } else if (reqFrom.equalsIgnoreCase("SearchDoc")) {
                System.out.println("inside SearchDoc");
                out = response.getWriter();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                String docType = request.getParameter("DocType");
                String serachBy = request.getParameter("SearchBy");
                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");

                System.out.println("docType: " + docType);
                System.out.println("serachBy: " + serachBy);
                System.out.println("fromDate: " + fromDate);
                System.out.println("toDate: " + toDate);

                if (fromDate != null && !fromDate.equalsIgnoreCase("") && toDate != null && toDate.equalsIgnoreCase("")) {
                    Date from_date = df.parse(fromDate);
                    Date to_date = df.parse(toDate);

                    System.out.println("from_date: " + from_date);
                    System.out.println("to_date: " + to_date);
                }
                String status = "";
                List<CustomerDocument> docList = (List<CustomerDocument>) customerDocumentDao.findByDocumentByDocType(docType, serachBy);
                System.out.println("size: " + docList.size());
                if (docList.isEmpty()) {
                    status = "fail";
                } else {
                    status = "success";
                    for (CustomerDocument doc : docList) {
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("docId", doc.getDocid());
                        jsonObj.put("docType", doc.getDocumenttype());
                        jsonObj.put("docName", doc.getDocumentname());
                        jsonObj.put("uploadedDate", doc.getUpdatedate());

                        jArra.put(jsonObj);
                    }
                }
                jObj.put("Data", jArra);
                jObj.put("Status", status);

                out.println(jObj);

            } else if (reqFrom.equalsIgnoreCase("CustomerUsernameChecking")) {
                System.out.println("inside CustomerUsernameChecking");
                out = response.getWriter();
                String username = request.getParameter("UserName");
                System.out.println("UserName: " + username);
                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
                String status = "";
                if (customerSubUserList.size() > 0) {
                    status = "Exists";
                } else {
                    status = "NotExits";
                }
                jObj.put("Status", status);
                out.println(jObj);

            } else if (reqFrom.equalsIgnoreCase("CustomerUsernameAndPasswordChecking")) {
                System.out.println("inside CustomerUsernameAndPasswordChecking");
                out = response.getWriter();
                String username = request.getParameter("UserName");
                String OldPassword = request.getParameter("OldPassword");
                System.out.println("UserName: " + username);
                System.out.println("OldPassword: " + OldPassword);

                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
                System.out.println("customerSubUserList size: " + customerSubUserList.size());
                System.out.println("match: " + passwordEncoder.matches(OldPassword, customerSubUserList.get(0).getPassword()));

                String status = "";
                if (customerSubUserList != null && customerSubUserList.size() > 0 && passwordEncoder.matches(OldPassword, customerSubUserList.get(0).getPassword())) {
                    status = "Exists";
                } else {
                    status = "NotExits";
                }
                jObj.put("Status", status);
                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerDetails")) {
                System.out.println("inside CustomerDetails");
                out = response.getWriter();

                String CustomerId = request.getParameter("CustomerId");
                System.out.println("CustomerId: " + CustomerId);
                CustomerSeeded cust = custSeededDao.getCustomerSeededById(Integer.parseInt(CustomerId));

                if (cust != null) {
//                    System.out.println(cust.getFirstname());
                    jObj.put("FNAME", cust.getFirstname());
                    jObj.put("LNAME", cust.getLastname());
                    jObj.put("ADDLINE1", cust.getAddressline1());
                    jObj.put("ADDLINE2", cust.getAddressline2());
                    jObj.put("ADDLINE3", cust.getAddressline3());
                    jObj.put("EMAILID", cust.getEmailid());
                }

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("UnmappCustomerFromGroup")) {
                System.out.println("inside UnmappCustomerFromGroup");
                out = response.getWriter();

                String Gid = request.getParameter("Gid");
                System.out.println("gid: " + Gid);
                String CustId = request.getParameter("CustId");
                System.out.println("CustId: " + CustId);

                List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findByGroupIdAndCustomerId(Integer.parseInt(Gid), Integer.parseInt(CustId));
                System.out.println("size: " + supplierGroupList);
                if (supplierGroupList.size() == 1) {
                    supplierGroupDao.deleteSupplierGroups(supplierGroupList.get(0));
                    jObj.put("status", "Removed");
                    out.println(jObj);
                }
            } else if (reqFrom.equalsIgnoreCase("findProjectByCustomerId")) {
                System.out.println("findProjectByCustomerId");
                out = response.getWriter();
                String CustomerId = request.getParameter("CustomerId");
                System.out.println("CustomerId: " + CustomerId);
                List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.findByCustomerId(Integer.parseInt(CustomerId));
                System.out.println("projectList: " + projectList.size());

                for (ProjectSeeded obj : projectList) {
                    JSONObject jobj = new JSONObject();
                    jobj.put("PROJECT_ID", obj.getPid());
                    jobj.put("PROJECT_NAME", obj.getProjectname());
                    jobj.put("PROJECT_CODE", obj.getProjectcode());
                    jobj.put("PROJECT_STATUS", obj.getProjectstatus());
                    jArra.put(jobj);
                }
                String status = "";
                if (projectList.isEmpty()) {
                    status = "NOT_FOUND";
                } else {
                    status = "FOUND";
                }
                jObj.put("status", status);
                jObj.put("data", jArra);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerUsernameCheckingAnsSecQue")) {
                System.out.println("inside CustomerUsernameCheckingAnsSecQue");
                out = response.getWriter();
                String username = request.getParameter("UserName");
                System.out.println("UserName: " + username);
                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
                System.out.println("customerSubUserList: " + customerSubUserList);
                String status;

                JSONObject question = new JSONObject();
                JSONObject answer = new JSONObject();

                if (customerSubUserList.size() > 0) {
                    List<CustSecQues> queAnslist = (List<CustSecQues>) custSecQueDao.findById(customerSubUserList.get(0).getId());
                    System.out.println("queAnslist: " + queAnslist.size());

                    JSONArray innerQuestionArr = new JSONArray();
                    JSONArray innerAnswerArr = new JSONArray();

                    for (CustSecQues queAnsObj : queAnslist) {

                        innerQuestionArr.put(queAnsObj.getQuestion());
                        innerAnswerArr.put(queAnsObj.getAnswer());
                    }

                    question.put("QuestionArr", innerQuestionArr);
                    answer.put("AnswerArr", innerAnswerArr);

                    jObj.put("UserId", customerSubUserList.get(0).getId());

                    status = "Exists";
                } else {
                    status = "NotExits";
                }
                jObj.put("Status", status);

                jObj.put("Question", question);
                jObj.put("Answer", answer);

                out.println(jObj);

            } else if (reqFrom.equalsIgnoreCase("CustomerAuditReport")) {
                System.out.println("CustomerAuditReport");
                Date today = new Date();
                out = response.getWriter();
                int userId = (int) session.getAttribute("userId");
                System.out.println("userId: " + userId);

                String docIndex = request.getParameter("DocIndex");
                String docName = request.getParameter("DocName");
                String docType = request.getParameter("DocType");

                System.out.println("docIndex: " + docIndex);
                System.out.println("docName: " + docName);
                System.out.println("docType: " + docType);

                CustomerSubUser loggedInUser = customerSubUserDao.getCustomerSubUserById(userId);
                System.out.println("loggedInUser: " + loggedInUser);

                custAuditReport.setBpaasCustomersubuserId(loggedInUser);
                custAuditReport.setDocumentindex(docIndex);
                custAuditReport.setDocumentname(docName);
                custAuditReport.setDocumenttype(docType);
                custAuditReport.setDownloaddate(today);

                int reportId = custAuditReportDao.saveCustomerAuditReport(custAuditReport);
                System.out.println("reportId: " + reportId);

                jObj.put("ReportId", reportId);

                out.println(jObj);
            }

        } catch (IOException | JSONException | ParseException ex) {
            Logger.getLogger(RfeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}
