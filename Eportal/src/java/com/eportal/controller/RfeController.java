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
import com.eportal.dao.CustomerAuditReportDao;
import com.eportal.dao.CustomerDocumentDao;
import com.eportal.dao.CustomerMailDao;
import com.eportal.dao.CustomerSeededDao;
import com.eportal.dao.CustomerSubUserDao;
import com.eportal.dao.GroupsDao;
import com.eportal.dao.ProjectSeededDao;
import com.eportal.dao.SupplierGroupDao;
import com.eportal.dao.SupplierHeaderDao;
import com.eportal.dao.SupplierLineitemDao;
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.SupplierUserDao;
import com.eportal.dao.UserDetailDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.dao.WorkOrderRfqLineItemDao;
import com.eportal.entities.CategorySubcategory;
import com.eportal.entities.CustSecQues;
import com.eportal.entities.CustomerAuditReport;
import com.eportal.entities.CustomerAutoMail;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerMail;
import com.eportal.entities.CustomerNotification;
import com.eportal.entities.CustomerProfileUpdateReport;
import com.eportal.entities.CustomerSeeded;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.CustomerUserTrackingReport;
import com.eportal.entities.Groups;
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
import java.net.URI;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
    CustomerSubUser customerSubUser;

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

    @Autowired
    CustomerMail customerMail;

    @Autowired
    CustomerAutoMail customerAutoMail;

    @Value("${bid_submitted}")
    private String bid_submitted;

    @Value("${webservice.ip}")
    private String webservice_ip;

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
//                String category = request.getParameter("category");
//                List<CategorySubcategory> subcategories = (List<CategorySubcategory>) categorySubcategory.getSubCategoryByCategory(category);
//                out = response.getWriter();
//
//                for (CategorySubcategory sub : subcategories) {
//                    jArra.put(sub.getSubcategoryname());
//                }
//
//                out.println(jArra);
                //out.close();

            } else if (reqFrom.equalsIgnoreCase("approvals")) {
                System.out.println("approvals");
                //JSONObject jObj = new JSONObject();
//                out = response.getWriter();
//                int id = Integer.parseInt(request.getParameter("approvalId"));
//                System.out.println("id: " + id);
//                List<Userdetail> user = (List<Userdetail>) userDetailDao.findByUserId(id);
//                System.out.println(user.get(0).getUsername());
//                jObj.put("username", user.get(0).getUsername());
//
//                out.println(jObj);
                //out.close();
            } else if (reqFrom.equalsIgnoreCase("releaseRFP")) {
//                out = response.getWriter();
//                System.out.println("releaseRFP");
//                System.out.println(request.getParameter("approvedRfp"));
//                int approved_rfp = Integer.parseInt(request.getParameter("approvedRfp"));
//                System.out.println("approved_rfp: " + approved_rfp);
//
//                WorkOrderRfqHeader header = headerDao.getWorkOrderRfqHeaderById(approved_rfp);
//
//                jObj.put("RfqTitle", header.getRFQTitle());
//                jObj.put("TimeLeft", header.getTimeleft());
//                jObj.put("Desc", header.getDescription());
//
//                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("supplierOnRfpId")) {
//                System.out.println("in supplierOnRfpId");
//                out = response.getWriter();
//                String rfq = request.getParameter("rfpid");
//                System.out.println("rfq: " + rfq);
//                int rfqId = Integer.parseInt(rfq.split("_")[1]);
//                System.out.println("rfq: " + rfq);
//                List<SupplierSelection> supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findByRfqId(rfqId);
//                System.out.println("size: " + supplierSelection.size());
//
//                JSONArray supplierIdJArr = new JSONArray();
//                JSONArray supplierNameJArr = new JSONArray();
//
//                if (!supplierSelection.isEmpty()) {
//                    for (SupplierSelection supplier : supplierSelection) {
//                        supplierIdJArr.put(supplier.getBPaasSupplierUserTableid().getId());
//                        supplierNameJArr.put(supplier.getBPaasSupplierUserTableid().getCompanyname());
//                    }
//                } else {
//
//                }
////                System.out.println("jArr size: " + jArra.length());
//                jObj.put("supplierIdJArr", supplierIdJArr);
//                jObj.put("supplierNameJArr", supplierNameJArr);
//
//                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("supplierRfpStatus")) {
//                System.out.println("supplierRfpStatus==================");
//                out = response.getWriter();
//
//                String username = (String) session.getAttribute("username");
//                System.out.println("username: " + username);
//
//                int rfq = Integer.parseInt(request.getParameter("releasedRfp"));
//                System.out.println("rfq: " + rfq);
//
//                List<SupplierSelection> list = (List<SupplierSelection>) supplierSelectionDao.findByCreatedByAndRfpId(rfq, username);
//                System.out.println("size: " + list.size());
//
//                for (SupplierSelection obj : list) {
//                    JSONObject jsonColumn = new JSONObject();
//                    jsonColumn.put("rfpId", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
//                    jsonColumn.put("supplieName", obj.getBPaasSupplierUserTableid().getCompanyname());
//                    jsonColumn.put("rfpStatus", obj.getSupplierWFstatus());
//                    jsonColumn.put("updateDate", obj.getUpdatedate());
//                    jsonColumn.put("rfpCreateDate", obj.getBPaasWorkOrderRFQHeaderRFQID().getCreationdate());
//                    jsonColumn.put("timeLeft", obj.getBPaasWorkOrderRFQHeaderRFQID().getTimeleft());
//                    jsonColumn.put("supplierId", obj.getBPaasSupplierUserTableid().getId());
//                    jArra.put(jsonColumn);
//                }
//                System.out.println("jArr length: " + jArra.length());
//
//                out.println(jArra);
            } else if (reqFrom.equalsIgnoreCase("budgetComparison")) {
//                System.out.println("budgetComparison");
//                out = response.getWriter();
//
//                String username = (String) session.getAttribute("username");
//                System.out.println("username: " + username);
//
//                int rfq = Integer.parseInt(request.getParameter("rfqId"));
//                System.out.println("rfq: " + rfq);
//
////                WorkOrderRfqHeader rfqHeader = rfqHeaderDao.getWorkOrderRfqHeaderById(rfq);
//                List<WorkOrderRfqLineItem> rfqLineItemList = (List<WorkOrderRfqLineItem>) rfqLineitemDao.findByHeaderId(rfq);
//                System.out.println("rfqLineItemList: " + rfqLineItemList);
//
//                List<SupplierSelection> list = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndRfqId(bid_submitted, rfq);
//                System.out.println("size: " + list);
//
//                List<Integer> Ids;
//                Ids = new ArrayList<>();
//
//                for (SupplierSelection obj : list) {
//                    Ids.add((int) obj.getId());
//                }
//
//                List<SupplierHeader> supplierHeaderList = (List<SupplierHeader>) supplierHeaderDao.findBySupplierSelectionIds(Ids);
//                System.out.println("supplierHeader: " + supplierHeaderList);
//
//                DecimalFormat df = new DecimalFormat("#,###.00");
//
//                JSONArray outerJsonArr = new JSONArray();
//
//                for (SupplierHeader obj : supplierHeaderList) {
//                    System.out.println(obj.getSupplierLineitemCollection());
//                    List<SupplierLineitem> lineItem = (List<SupplierLineitem>) obj.getSupplierLineitemCollection();
//
//                    String supplierName = "";
//                    double expectedPrice = 0;
//                    double totalPrice = 0;
//                    double targetPrice = 0;
//                    JSONObject innerJsonObj = new JSONObject();
//
//                    for (int i = 0; i < lineItem.size(); i++) {
//                        System.out.println(lineItem.get(i));
//                        JSONObject jsonColumn = new JSONObject();
//
//                        jsonColumn.put("rfqid", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBpaasWorkorderrfqheaderRfqid().getRfqid());
//                        jsonColumn.put("timeLeft", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBpaasWorkorderrfqheaderRfqid().getTimeleft());
//                        jsonColumn.put("supplierName", lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid().getCompanyname());
//                        jsonColumn.put("quotPricePerQuantity", df.format(lineItem.get(i).getQuotepriceperquantity()));
//                        jsonColumn.put("expectedPrice", df.format(lineItem.get(i).getExpectedprice()));
//                        jsonColumn.put("totalPrice", df.format(lineItem.get(i).getTotalprice()));
//                        jsonColumn.put("targetPrice", df.format(rfqLineItemList.get(i).getTargetprice()));
//                        jsonColumn.put("quantity", df.format(rfqLineItemList.get(i).getQuantity()));
//
//                        supplierName = lineItem.get(i).getWorkOrderSupplierHeaderTableid().getBPaasSupplierSelectionTableid().getBPaasSupplierUserTableid().getCompanyname();
//                        expectedPrice = expectedPrice + lineItem.get(i).getExpectedprice();
//                        totalPrice = totalPrice + lineItem.get(i).getTotalprice();
//                        targetPrice = targetPrice + rfqLineItemList.get(i).getTargetprice();
//
//                        jArra.put(jsonColumn);
//                    }
////                    System.out.println("expectedPrice: " + expectedPrice);
////                    System.out.println("totalPrice: " + totalPrice);
////                    System.out.println("targetPrice: " + targetPrice);
//
//                    innerJsonObj.put("chart_supplierName", supplierName);
//                    innerJsonObj.put("chart_expectedPrice", expectedPrice);
//                    innerJsonObj.put("chart_totalPrice", totalPrice);
//                    innerJsonObj.put("chart_targetPrice", targetPrice);
//
//                    outerJsonArr.put(innerJsonObj);
//                }
//                jObj.put("chartData", outerJsonArr);
//                jObj.put("tableData", jArra);
//
//                System.out.println("jArr length: " + jArra.length());
//
//                out.println(jObj);

            } else if (reqFrom.equalsIgnoreCase("responseManagement")) {
//                System.out.println("responseManagement");
//                out = response.getWriter();
//
//                String username = (String) session.getAttribute("username");
//                System.out.println("username: " + username);
//
//                String userType = (String) session.getAttribute("userType");
//                System.out.println("userType: " + userType);
//
//                int userId = (int) session.getAttribute("userId");
//                System.out.println("userId: " + userId);
//
//                int rfq = Integer.parseInt(request.getParameter("rfqId"));
//                System.out.println("rfq: " + rfq);
//
//                List<SupplierSelection> supplierSelection = null;
//
//                if (userType != null && userType.equalsIgnoreCase("SCM User")) {
//                    supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findByRfqId(rfq);
//                    System.out.println("supplierSelection: " + supplierSelection);
//                } else if (userType != null && userType.equalsIgnoreCase("Supplier")) {
//                    supplierSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierIdAndRfpId(rfq, userId);
//                    System.out.println("supplierSelection: " + supplierSelection);
//                }
//
//                for (SupplierSelection obj : supplierSelection) {
//                    JSONObject jsonObj = new JSONObject();
//
//                    jsonObj.put("supplierId", obj.getBPaasSupplierUserTableid().getId());
//                    jsonObj.put("supplierName", obj.getBPaasSupplierUserTableid().getCompanyname());
//                    jsonObj.put("rfqId", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
//                    jsonObj.put("rfqTitle", obj.getBPaasWorkOrderRFQHeaderRFQID().getRFQTitle());
//                    jsonObj.put("rfqStatus", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqstatus());
//
//                    jArra.put(jsonObj);
//                }
//                System.out.println("jArra: " + jArra.length());
//
//                out.println(jArra);
            } else if (reqFrom.equalsIgnoreCase("rfqsearchsummary")) {
//                System.out.println("rfqsearchsummary");
//                out = response.getWriter();
//                String rfqid = request.getParameter("rfqid");
//                String rfqtitle = request.getParameter("rfqtitle");
//                String rfqstatus = request.getParameter("rfqstatus");
//                String supplierid = request.getParameter("supplierid");
//                String releasedrfq = request.getParameter("releasedrfq");
//
//                System.out.println("rfqid: " + rfqid);
//                System.out.println("rfqtitle: " + rfqtitle);
//                System.out.println("rfqstatus: " + rfqstatus);
//                System.out.println("supplierid: " + supplierid);
//                System.out.println("releasedrfq: " + releasedrfq);
//
//                List<WorkOrderRfqHeader> headers = null;
//                List<SupplierSelection> supplierSelectionList = null;
//
//                if (rfqid != null && !rfqid.equalsIgnoreCase("")) {
//                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqId(Integer.parseInt(rfqid));
//                } else if (rfqtitle != null && !rfqtitle.equalsIgnoreCase("")) {
//                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqTitle(rfqtitle);
//                } else if (rfqstatus != null && !rfqstatus.equalsIgnoreCase("")) {
//                    headers = (List<WorkOrderRfqHeader>) rfqHeaderDao.findByRfqStatus(rfqstatus);
//                } else if (supplierid != null && !supplierid.equalsIgnoreCase("")) {
//                    supplierSelectionList = (List<SupplierSelection>) supplierSelectionDao.findBySupplierId(Integer.parseInt(supplierid));
//                }
//                String status = "";
//                JSONObject outerObj = new JSONObject();
//                if (headers != null && !headers.isEmpty()) {
//                    status = "SUCCESS";
//                    outerObj.put("status", status);
//
//                    for (WorkOrderRfqHeader obj : headers) {
//                        JSONObject jsonObj = new JSONObject();
//
//                        jsonObj.put("RFQ_ID", obj.getRfqid());
//                        jsonObj.put("RFQ_STATUS", obj.getRfqstatus());
//                        jsonObj.put("RFQ_TITLE", obj.getRFQTitle());
//                        jsonObj.put("CREATION_DATE", obj.getCreationdate());
//                        jsonObj.put("TIME_LEFT", obj.getTimeleft());
//
//                        jArra.put(jsonObj);
//                    }
//                    outerObj.put("data", jArra);
//
//                } else if (supplierSelectionList != null && !supplierSelectionList.isEmpty()) {
//                    status = "SUCCESS";
//                    outerObj.put("status", status);
//
//                    for (SupplierSelection obj : supplierSelectionList) {
//                        JSONObject jsonObj = new JSONObject();
//
//                        jsonObj.put("RFQ_ID", obj.getBPaasWorkOrderRFQHeaderRFQID().getRfqid());
//                        jsonObj.put("RFQ_STATUS", obj.getSupplierWFstatus());
//                        jsonObj.put("RFQ_TITLE", obj.getBPaasWorkOrderRFQHeaderRFQID().getRFQTitle());
//                        jsonObj.put("CREATION_DATE", obj.getBPaasWorkOrderRFQHeaderRFQID().getCreationdate());
//                        jsonObj.put("TIME_LEFT", obj.getBPaasWorkOrderRFQHeaderRFQID().getTimeleft());
//
//                        jArra.put(jsonObj);
//                    }
//                    outerObj.put("data", jArra);
//
//                } else {
//                    status = "FAILED";
//                    outerObj.put("status", status);
//                }
//                out.println(outerObj);
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
//                List<CustomerDocument> docList = (List<CustomerDocument>) customerDocumentDao.findByDocumentByDocType(docType, serachBy);
                List<CustomerDocument> docList = null;
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

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSubUser>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbyusernamecheck.do?username=" + username,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                        });

                List<CustomerSubUser> customerSubUserList = restResponse.getBody();

//                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
                CustomerSubUser customer = null;

                for (CustomerSubUser user : customerSubUserList) {
                    if (user.getStatus().equalsIgnoreCase("Active")) {
                        customer = user;
                        break;
                    } else {
                        customer = user;
                    }
                }

                String status = "";
                if (customerSubUserList.size() > 0 && (customer.getStatus().equalsIgnoreCase("Active")
                        || customer.getStatus().equalsIgnoreCase("Block"))) {
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

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSubUser>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbyusernamecheck.do?username=" + username,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                        });

                List<CustomerSubUser> customerSubUserList = restResponse.getBody();

//                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
                System.out.println("customerSubUserList size: " + customerSubUserList.size());
//                System.out.println("match: " + passwordEncoder.matches(OldPassword, customerSubUserList.get(0).getPassword()));

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

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<CustomerSeeded> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + Integer.parseInt(CustomerId), CustomerSeeded.class);
                System.out.println("restResponse: " + restResponse);

                CustomerSeeded cust = restResponse.getBody();

//                CustomerSeeded cust = custSeededDao.getCustomerSeededById(Integer.parseInt(CustomerId));
                if (cust != null) {
//                    System.out.println(cust.getFirstname());
                    jObj.put("FNAME", cust.getFirstname());
                    jObj.put("LNAME", cust.getLastname());
                    jObj.put("ADDLINE1", cust.getAddressline1());
                    jObj.put("ADDLINE2", cust.getAddressline2());
                    jObj.put("ADDLINE3", cust.getAddressline3());
                    jObj.put("EMAILID", cust.getEmailid());
                }

                ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomercode.do?customercode=" + cust.getCustomercode(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                        });

                List<ProjectSeeded> projectList = restProjectResponse.getBody();
                System.out.println("projectList: " + projectList);

                for (ProjectSeeded obj : projectList) {
                    JSONObject jInnerObj = new JSONObject();
                    jInnerObj.put("PROJECT_ID", obj.getPid());
                    jInnerObj.put("PROJECT_NAME", obj.getProjectname());
                    jInnerObj.put("PROJECT_CODE", obj.getProjectcode());
                    jInnerObj.put("PROJECT_STATUS", obj.getProjectstatus());
                    jArra.put(jInnerObj);
                }
                jObj.put("ProjectList", jArra);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("UnmappCustomerFromGroup")) {
                System.out.println("inside UnmappCustomerFromGroup");
                out = response.getWriter();

                String Gid = request.getParameter("Gid");
                System.out.println("gid: " + Gid);
                String CustId = request.getParameter("CustId");
                System.out.println("CustId: " + CustId);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<SupplierGroup>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getcustomergroup.do?gid=" + Integer.parseInt(Gid) + "&customerid=" + Integer.parseInt(CustId),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<SupplierGroup>>() {
                        });

                System.out.println("restResponse: " + restResponse);
                List<SupplierGroup> supplierGroupList = restResponse.getBody();
                System.out.println("supplierGroupList: " + supplierGroupList.size());

//                List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findByGroupIdAndCustomerId(Integer.parseInt(Gid), Integer.parseInt(CustId));
                System.out.println("size: " + supplierGroupList);

                if (supplierGroupList.size() == 1) {

                    String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/unmappuustomeruromuroup.do"), supplierGroupList.get(0), String.class);
                    System.out.println("result: " + result);

//                    supplierGroupDao.deleteSupplierGroups(supplierGroupList.get(0));
                    jObj.put("status", "Removed");
                    out.println(jObj);
                } else {
                    jObj.put("status", "NotFound");
                    out.println(jObj);
                }
            } else if (reqFrom.equalsIgnoreCase("findProjectByCustomerId")) {
                System.out.println("findProjectByCustomerId");
                out = response.getWriter();
                String CustomerCode = request.getParameter("CustomerId");
                System.out.println("CustomerCode: " + CustomerCode);
//                List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.findByCustomerId(Integer.parseInt(CustomerId));
//                System.out.println("projectList: " + projectList.size());
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomercode.do?customercode=" + CustomerCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                        });

                List<ProjectSeeded> projectList = restProjectResponse.getBody();
                System.out.println("projectList: " + projectList);

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
//                List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
//                System.out.println("customerSubUserList: " + customerSubUserList);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSubUser>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbyusernamecheckasactive.do?username=" + username,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                        });

                List<CustomerSubUser> customerSubUserList = restResponse.getBody();

                String status;

                JSONObject question = new JSONObject();
                JSONObject answer = new JSONObject();
                boolean isAdmin = false;

                if (customerSubUserList.size() > 0) {
//                    List<CustSecQues> queAnslist = (List<CustSecQues>) custSecQueDao.findById(customerSubUserList.get(0).getId());
//                    System.out.println("queAnslist: " + queAnslist.size());

                    //28Nov2018
//                    ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerusergroup.do?custid=" + customerSubUserList.get(0).getId(),
//                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
//                            });
//
//                    System.out.println("response: " + restGroupResponse);
//                    System.out.println("response: " + restGroupResponse.getBody().size());
//                    List<Groups> groups = restGroupResponse.getBody();
//
//                    for (Groups group : groups) {
//                        if (group.getGroupname().equalsIgnoreCase("Admin")) {
//                            isAdmin = true;
//                            break;
//                        }
//
//                    }
                    System.out.println("isAdmin: " + isAdmin);
                    //Ends

                    ResponseEntity<List<CustSecQues>> restSecResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findsecqueandansbyuserid.do?userid=" + customerSubUserList.get(0).getId(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<CustSecQues>>() {
                            });

                    List<CustSecQues> queAnslist = restSecResponse.getBody();

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
                jObj.put("Role", isAdmin);

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
                String customerId = request.getParameter("CustomerId");

                System.out.println("docIndex: " + docIndex);
                System.out.println("docName: " + docName);
                System.out.println("docType: " + docType);
                System.out.println("customerId: " + customerId);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSeeded>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbycustomercode.do?customercode=" + customerId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                        });
                System.out.println("restResponse: " + restResponse);

                List<CustomerSeeded> cust = restResponse.getBody();

                ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

                CustomerSubUser loggedInUser = restCustomerResponse.getBody();

                custAuditReport.setBpaasCustomersubuserId(loggedInUser);
                custAuditReport.setDocumentindex(docIndex);
                custAuditReport.setDocumentname(docName);
                custAuditReport.setDocumenttype(docType);
                custAuditReport.setDownloaddate(today);
                custAuditReport.setNgCpCustomerseededId(cust.get(0));

                String reportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerauditreport.do"), custAuditReport, String.class);
//                
//                int reportId = custAuditReportDao.saveCustomerAuditReport(custAuditReport);
                System.out.println("reportId: " + reportId);

                jObj.put("ReportId", reportId);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerAuditReportForMultiple")) {
                System.out.println("CustomerAuditReportForMultiple");
                Date today = new Date();
                out = response.getWriter();
                int userId = (int) session.getAttribute("userId");
                System.out.println("userId: " + userId);

//                CustomerSubUser loggedInUser = customerSubUserDao.getCustomerSubUserById(userId);
//                System.out.println("loggedInUser: " + loggedInUser);
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

                CustomerSubUser loggedInUser = restCustomerResponse.getBody();

                String customerId = request.getParameter("CustomerId");
                System.out.println("customerId: " + customerId);

                ResponseEntity<List<CustomerSeeded>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbycustomercode.do?customercode=" + customerId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                        });
                System.out.println("restResponse: " + restResponse);

                List<CustomerSeeded> cust = restResponse.getBody();

                String documentsData = request.getParameter("documentsData");
                System.out.println("documentsData: " + documentsData);

                String[] rows = documentsData.split("<>");
                System.out.println("rows: " + rows.length);

                for (int i = 0; i < rows.length; i++) {
                    String[] columns = rows[i].split(",");
                    System.out.println("columns: " + columns.length);

                    custAuditReport.setBpaasCustomersubuserId(loggedInUser);
                    custAuditReport.setDocumentindex(columns[0]);
                    custAuditReport.setDocumentname(columns[1]);
                    custAuditReport.setDocumenttype(columns[2]);
                    custAuditReport.setDownloaddate(today);
                    custAuditReport.setNgCpCustomerseededId(cust.get(0));

                    String reportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerauditreport.do"), custAuditReport, String.class);
                    System.out.println("reportId: " + reportId);
                }
                jObj.put("ReportId", "Success");
                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerNotificationChangeStatus")) {
                System.out.println("CustomerNotificationChangeStatus");
                Date today = new Date();
                out = response.getWriter();

                String notificationId = request.getParameter("notificationId");
                System.out.println("notificationId: " + notificationId);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbynotificationid.do?notificationid=" + Integer.parseInt(notificationId),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                        });

                System.out.println("restNotificationResponse: " + restNotificationResponse);
                System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

                CustomerNotification notiObj = restNotificationResponse.getBody().get(0);

                notiObj.setReadstatus("false");

                String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatenotificationreadstatus.do"),
                        notiObj, String.class);
                System.out.println("result: " + result);

                out.println("success");
            } else if (reqFrom.equalsIgnoreCase("UpdateProjectStatus")) {
                System.out.println("UpdateProjectStatus");
                Date today = new Date();
                out = response.getWriter();

                String Pid = request.getParameter("Pid");
                System.out.println("Pid: " + Pid);
                String Status = request.getParameter("Status");
                System.out.println("Status: " + Status);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/updateprojectseededstatus.do?pid=" + Integer.parseInt(Pid) + "&status=" + Status, String.class);
                String result = restCustomerResponse.getBody();

                System.out.println("result: " + result);

                jObj.put("status", result);
                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerProfileReport")) {
                System.out.println("CustomerProfileReport");
                Date today = new Date();
                out = response.getWriter();

                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");

                System.out.println("fromDate: " + fromDate);
                System.out.println("toDate: " + toDate);
//                
                DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerProfileUpdateReport>> restReportResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/filtercustomerprofileupdatereport.do?fromdate=" + fromDate + "&todate=" + toDate,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProfileUpdateReport>>() {
                        });

                System.out.println("restReportResponse: " + restReportResponse);
                System.out.println("restReportResponse: " + restReportResponse.getBody().size());

                List<CustomerProfileUpdateReport> reportList = restReportResponse.getBody();
                for (CustomerProfileUpdateReport obj : reportList) {
                    JSONObject innerObj = new JSONObject();

                    innerObj.put("FieldName", obj.getFieldname());
                    innerObj.put("OldValue", obj.getOldvalue());
                    innerObj.put("NewValue", obj.getNewvalue());
                    innerObj.put("ActionDate", fromDateFormat.format(obj.getActiondate()));
                    innerObj.put("Username", obj.getBpaasCustomersubuserId().getUsername());

                    if (obj.getBpaasCustomersubuserId().getPersonalfirstname() == null && obj.getBpaasCustomersubuserId().getPersonallastname() == null) {
                        innerObj.put("PersonalName", "");
                    } else if (obj.getBpaasCustomersubuserId().getPersonallastname() == null) {
                        innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname());
                    } else {
                        innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname() + " " + obj.getBpaasCustomersubuserId().getPersonallastname());
                    }

//                    innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname() + " " + obj.getBpaasCustomersubuserId().getPersonallastname());
                    innerObj.put("Customer", obj.getBpaasCustomersubuserId().getBpaasCustomerseededCid().getCustomername());

                    jArra.put(innerObj);
                }
                System.out.println("jArra: " + jArra.length());
                int records = reportList.size();

                jObj.put("Records", records);
                jObj.put("Result", jArra);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerAuditReportFilter")) {
                System.out.println("CustomerAuditReportFilter");
                Date today = new Date();
                out = response.getWriter();

                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");
                String customer = request.getParameter("Customer");

                System.out.println("fromDate: " + fromDate);
                System.out.println("toDate: " + toDate);
                System.out.println("Customer: " + customer);
//                
                DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerAuditReport>> restReportResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/filterauditreport.do?fromdate=" + fromDate + "&todate=" + toDate + "&customer=" + customer,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAuditReport>>() {
                        });

                System.out.println("restReportResponse: " + restReportResponse);
                System.out.println("restReportResponse: " + restReportResponse.getBody().size());

                List<CustomerAuditReport> reportList = restReportResponse.getBody();
                for (CustomerAuditReport obj : reportList) {
                    JSONObject innerObj = new JSONObject();

                    innerObj.put("DocumentName", obj.getDocumentname());
                    innerObj.put("DocumentType", obj.getDocumenttype());

                    if (obj.getBpaasCustomersubuserId().getPersonalfirstname() == null && obj.getBpaasCustomersubuserId().getPersonallastname() == null) {
                        innerObj.put("PersonalName", "");
                    } else if (obj.getBpaasCustomersubuserId().getPersonallastname() == null) {
                        innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname());
                    } else {
                        innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname() + " " + obj.getBpaasCustomersubuserId().getPersonallastname());
                    }

//                    innerObj.put("PersonalName", obj.getBpaasCustomersubuserId().getPersonalfirstname() + " " + obj.getBpaasCustomersubuserId().getPersonallastname());
                    innerObj.put("DownloadDate", fromDateFormat.format(obj.getDownloaddate()));
                    innerObj.put("Username", obj.getBpaasCustomersubuserId().getUsername());
                    innerObj.put("Customer", obj.getNgCpCustomerseededId().getCustomername());

                    jArra.put(innerObj);
                }
                System.out.println("jArra: " + jArra.length());
                int records = reportList.size();

                jObj.put("Records", records);
                jObj.put("Result", jArra);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerAdminTrackingReport")) {
                System.out.println("CustomerAdminTrackingReport");
                Date today = new Date();
                out = response.getWriter();

                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");

                System.out.println("fromDate: " + fromDate);
                System.out.println("toDate: " + toDate);
//                
                DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerUserTrackingReport>> restReportResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/filteradmintrackingreport.do?fromdate=" + fromDate + "&todate=" + toDate,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerUserTrackingReport>>() {
                        });

                System.out.println("restReportResponse: " + restReportResponse);
                System.out.println("restReportResponse: " + restReportResponse.getBody().size());

                List<CustomerUserTrackingReport> reportList = restReportResponse.getBody();
                for (CustomerUserTrackingReport obj : reportList) {
                    JSONObject innerObj = new JSONObject();

                    innerObj.put("Activity", obj.getActivityname());
                    innerObj.put("ActivityDate", fromDateFormat.format(obj.getActivitydate()));
                    innerObj.put("Username", obj.getNgCpCustomersubuserId().getUsername());

                    if (obj.getNgCpCustomersubuserId().getPersonallastname() == null && obj.getNgCpCustomersubuserId().getPersonalfirstname() == null) {
                        innerObj.put("PersonalName", "");
                    } else if (obj.getNgCpCustomersubuserId().getPersonallastname() == null) {
                        innerObj.put("PersonalName", obj.getNgCpCustomersubuserId().getPersonalfirstname());
                    } else {
                        innerObj.put("PersonalName", obj.getNgCpCustomersubuserId().getPersonalfirstname() + " " + obj.getNgCpCustomersubuserId().getPersonallastname());
                    }

                    innerObj.put("Customer", obj.getNgCpCustomersubuserId().getBpaasCustomerseededCid().getCustomername());

//                    System.out.println("lastname: " + obj.getNgCpCustomersubuserAdmin().getPersonallastname());

                    if (obj.getNgCpCustomersubuserAdmin().getPersonalfirstname() == null && obj.getNgCpCustomersubuserAdmin().getPersonallastname() == null) {
                        innerObj.put("ActivityTakenBy", "");
                    } else if (obj.getNgCpCustomersubuserAdmin().getPersonallastname() == null) {
                        innerObj.put("ActivityTakenBy", obj.getNgCpCustomersubuserAdmin().getPersonalfirstname());
                    } else {
                        innerObj.put("ActivityTakenBy", obj.getNgCpCustomersubuserAdmin().getPersonalfirstname() + " " + obj.getNgCpCustomersubuserAdmin().getPersonallastname());
                    }
                    jArra.put(innerObj);
                }
                System.out.println("jArra: " + jArra.length());
                int records = reportList.size();

                jObj.put("Records", records);
                jObj.put("Result", jArra);

                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("CustomerMail")) {
                System.out.println("CustomerMail");
                Date today = new Date();
                out = response.getWriter();
                
                int userId = (int) session.getAttribute("userId");
                System.out.println("userId: " + userId);

                String MailTo = request.getParameter("MailTo");
                String MailCc = request.getParameter("MailCc");
                String MailSubject = request.getParameter("MailSubject");
                String MailBody = request.getParameter("MailBody");
                String DocIndexes = request.getParameter("DocIndexes");
                String DocName = request.getParameter("DocName");
                String CustomerName = request.getParameter("CustomerName");
                String ProjectName = request.getParameter("ProjectName");

                System.out.println("MailTo: " + MailTo);
                System.out.println("MailCc: " + MailCc);
                System.out.println("MailSubject: " + MailSubject);
                System.out.println("MailBody: " + MailBody);
                System.out.println("DocIndexes: " + DocIndexes);
                System.out.println("DocName: " + DocName);
                System.out.println("CustomerName: " + CustomerName);
                System.out.println("ProjectName: " + ProjectName);

                DocName = DocName.substring(0, DocName.length() - 1);
                DocIndexes = DocIndexes.replaceAll(",", ";");

                System.out.println("DocName: " + DocName);
                
                RestTemplate restTemplate = new RestTemplate();
                
                ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

                CustomerSubUser loggedInUser = restCustomerResponse.getBody();

                customerMail.setTOAddress(MailTo);
                customerMail.setCc(MailCc);
                customerMail.setSubject(MailSubject);
                customerMail.setMailBody(MailBody);
                customerMail.setDocumentIndex(DocIndexes + ";");
                customerMail.setDocumentName(DocName + ";");
                customerMail.setMailComposedOn(today);
                customerMail.setMailSentFlag("N");
                customerMail.setCustomerName(CustomerName);
                customerMail.setProjectName(ProjectName);

                if(loggedInUser.getPersonalfirstname() == null && loggedInUser.getPersonallastname() == null)
                {
                    customerMail.setMailSentBy("");
                }
                else if(loggedInUser.getPersonallastname() == null)
                {
                    customerMail.setMailSentBy(loggedInUser.getPersonalfirstname());
                }
                else
                {
                    customerMail.setMailSentBy(loggedInUser.getPersonalfirstname() + " " + loggedInUser.getPersonallastname());
                }

                String mailId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomermail.do"),
                        customerMail, String.class);
                System.out.println("mailId: " + mailId);
//                jObj.put("Result", jArra);
//
//                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("UserDetailsByCustomerSeededCid")) {
                System.out.println("UserDetailsByCustomerSeededCid");
                Date today = new Date();
                out = response.getWriter();

                String cid = request.getParameter("Cid");
                System.out.println("cid: " + cid);

                String customerId = cid.split("#")[0];
                System.out.println("customerId: " + customerId);

                String customerCode = cid.split("#")[1];
                System.out.println("customerCode: " + customerCode);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSubUser>> restReportResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbycustomerseededidforautomail.do?cid=" + Integer.parseInt(customerId),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                        });

//                System.out.println("restReportResponse: " + restReportResponse);
                System.out.println("restReportResponse: " + restReportResponse.getBody().size());
                List<CustomerSubUser> list = restReportResponse.getBody();

                ResponseEntity<List<CustomerAutoMail>> restAutoMailResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findautomailbycustomercode.do?customercode=" + customerCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAutoMail>>() {
                        });
                System.out.println("restAutoMailResponse: " + restAutoMailResponse.getBody().size());
                List<CustomerAutoMail> listCustomerAutoMail = restAutoMailResponse.getBody();
//                System.out.println("listCustomerAutoMail: " + listCustomerAutoMail);

                if (list.isEmpty()) {
                    jObj.put("status", "NotFound");
                } else {
                    jObj.put("status", "Found");
                }
//                for (CustomerSubUser obj : list) {
//                    JSONObject innerJsonObj = new JSONObject();
//
//                    innerJsonObj.put("Username", obj.getUsername());
//                    innerJsonObj.put("Emailid", obj.getPersonalemailid());
//
//                    jArra.put(innerJsonObj);
//
//                }
                for (CustomerSubUser obj : list) {

                    boolean isFound = false;

                    for (CustomerAutoMail objMail : listCustomerAutoMail) {

//                        System.out.println("if.username: " + objMail.getUserName());
                        if (obj.getUsername().equalsIgnoreCase(objMail.getUserName())) {

                            System.out.println("MatchFound");
//                            System.out.println("if.username: " + obj.getUsername());
//                            System.out.println("if.emailid: " + obj.getPersonalemailid());

                            JSONObject innerJsonObj = new JSONObject();

                            innerJsonObj.put("Username", obj.getUsername());
                            innerJsonObj.put("Emailid", obj.getPersonalemailid());

                            innerJsonObj.put("Rights", objMail.getInvoice() + "," + objMail.getSignedDO() + "," + objMail.getUnSignedDO() + "," + objMail.getDebit() + "," + objMail.getCredit() + "," + objMail.getARListing() + "," + objMail.getSoa());

                            jArra.put(innerJsonObj);
                            isFound = true;
                            break;
                        } else {
//                            System.out.println("else.username");

                        }
                    }
                    if (isFound == false) {
                        JSONObject innerJsonObj = new JSONObject();

                        innerJsonObj.put("Username", obj.getUsername());
                        innerJsonObj.put("Emailid", obj.getPersonalemailid());

                        innerJsonObj.put("Rights", "Non");

                        jArra.put(innerJsonObj);
                    }
                }

                jObj.put("Result", jArra);
//
                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("UpdateUserEmailStatus")) {
                System.out.println("UpdateUserEmailStatus");
                Date today = new Date();
                out = response.getWriter();

                String data = request.getParameter("Data");
                System.out.println("data: " + data);

                String[] array = data.split(",");
                System.out.println("length: " + array.length);

                String customerCode = array[0].split("#")[1];
                System.out.println("customerCode: " + customerCode);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerSubUser>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbyusernamecheckasactive.do?username=" + array[2],
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                        });

                List<CustomerSubUser> customerSubUserList = restResponse.getBody();

                if (!customerSubUserList.isEmpty()) {
                    CustomerSubUser obj = customerSubUserList.get(0);

                    if (array[3].equalsIgnoreCase("Non")) {
                        obj.setPersonalemailid("");
                    } else {
                        obj.setPersonalemailid(array[3]);
                    }

                    String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                            obj, String.class);
                    System.out.println("result: " + result);
                }

                ResponseEntity<List<CustomerAutoMail>> restAutoMailResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findautomailbyusername.do?username=" + array[2],
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAutoMail>>() {
                        });
                System.out.println("restAutoMailResponse: " + restAutoMailResponse.getBody().size());
                List<CustomerAutoMail> listCustomerAutoMail = restAutoMailResponse.getBody();

                if (listCustomerAutoMail.isEmpty()) {

                    customerAutoMail.setCustomerId(customerCode);
                    customerAutoMail.setCustomerName(array[1]);
                    customerAutoMail.setUserName(array[2]);

                    if (array[3].equalsIgnoreCase("Non")) {
                        customerAutoMail.setUserMailId("");
                    } else {
                        customerAutoMail.setUserMailId(array[3]);
                    }
                    customerAutoMail.setInvoice(array[4]);
                    customerAutoMail.setSignedDO(array[5]);
                    customerAutoMail.setUnSignedDO(array[6]);
                    customerAutoMail.setDebit(array[7]);
                    customerAutoMail.setCredit(array[8]);
                    customerAutoMail.setARListing(array[9]);
                    customerAutoMail.setSoa(array[10]);

                    String mailId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerautomail.do"),
                            customerAutoMail, String.class);
                    System.out.println("mailId: " + mailId);

                } else {
                    CustomerAutoMail objAutoMail = listCustomerAutoMail.get(0);

                    objAutoMail.setUserMailId(array[3]);
                    objAutoMail.setInvoice(array[4]);
                    objAutoMail.setSignedDO(array[5]);
                    objAutoMail.setUnSignedDO(array[6]);
                    objAutoMail.setDebit(array[7]);
                    objAutoMail.setCredit(array[8]);
                    objAutoMail.setARListing(array[9]);
                    objAutoMail.setSoa(array[10]);

                    String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomerautomail.do"),
                            objAutoMail, String.class);

                    System.out.println("result: " + result);
                }
                jObj.put("Result", jArra);
//
                out.println(jObj);
            } else if (reqFrom.equalsIgnoreCase("AddAutoMailUser")) {
                System.out.println("AddAutoMailUser");
                Date today = new Date();
                out = response.getWriter();

                String loggedInUsername = (String) session.getAttribute("username");

                String data = request.getParameter("Data");
                System.out.println("data: " + data);

                String[] array = data.split("~");
                System.out.println("length: " + array.length);

                String customerId = array[0].split("#")[0];
                System.out.println("customerId: " + customerId);

                String customerCode = array[0].split("#")[1];
                System.out.println("customerCode: " + customerCode);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<CustomerSeeded> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + Integer.parseInt(customerId), CustomerSeeded.class);

                CustomerSeeded custObj = restResponse.getBody();

                customerSubUser.setBpaasCustomerseededCid(custObj);
                customerSubUser.setUsername(array[2]);
                customerSubUser.setPersonalemailid(array[3]);
                customerSubUser.setCreationdate(today);
                customerSubUser.setCreatedby(loggedInUsername);
                customerSubUser.setStatus("Active");
                customerSubUser.setUsertype("AutoEmailUser");

                String responseUser = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/addcustomersubuser.do"), customerSubUser, String.class);

                System.out.println("responseUser: " + responseUser);

                ResponseEntity<List<CustomerAutoMail>> restAutoMailResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findautomailbyusername.do?username=" + array[2],
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAutoMail>>() {
                        });
                System.out.println("restAutoMailResponse: " + restAutoMailResponse.getBody().size());
                List<CustomerAutoMail> listCustomerAutoMail = restAutoMailResponse.getBody();

                if (listCustomerAutoMail.isEmpty()) {
                    customerAutoMail.setCustomerId(customerCode);
                    customerAutoMail.setCustomerName(array[1]);
                    customerAutoMail.setUserName(array[2]);
                    customerAutoMail.setUserMailId(array[3]);
                    customerAutoMail.setInvoice(array[4]);
                    customerAutoMail.setSignedDO(array[5]);
                    customerAutoMail.setUnSignedDO(array[6]);
                    customerAutoMail.setDebit(array[7]);
                    customerAutoMail.setCredit(array[8]);
                    customerAutoMail.setARListing(array[9]);
                    customerAutoMail.setSoa(array[10]);

                    String mailId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerautomail.do"),
                            customerAutoMail, String.class);
                    System.out.println("mailId: " + mailId);

                } else {
                    CustomerAutoMail objAutoMail = listCustomerAutoMail.get(0);

                    objAutoMail.setInvoice(array[4]);
                    objAutoMail.setSignedDO(array[5]);
                    objAutoMail.setUnSignedDO(array[6]);
                    objAutoMail.setDebit(array[7]);
                    objAutoMail.setCredit(array[8]);
                    objAutoMail.setARListing(array[9]);
                    objAutoMail.setSoa(array[10]);

                    String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomerautomail.do"),
                            objAutoMail, String.class);

                    System.out.println("result: " + result);
                }
//                jObj.put("Result", jArra);
//
                out.println(jObj);
                
            } else if (reqFrom.equalsIgnoreCase("CustomerMailReport")) {
                System.out.println("CustomerMailReport");
                Date today = new Date();
                out = response.getWriter();

                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");
                
                String mailType = request.getParameter("mailType");

                System.out.println("fromDate: " + fromDate);
                System.out.println("toDate: " + toDate);
                System.out.println("mailType: " + mailType);
//                
                DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
//
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<List<CustomerMail>> restReportResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/filtermailreport.do?fromdate=" + fromDate + "&todate=" + toDate + "&mailtype=" + mailType,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerMail>>() {
                        });

                System.out.println("restReportResponse: " + restReportResponse);
                System.out.println("restReportResponse: " + restReportResponse.getBody().size());

                List<CustomerMail> reportList = restReportResponse.getBody();
                
                
                for (CustomerMail obj : reportList) {
                    JSONObject innerObj = new JSONObject();

                    innerObj.put("TO_ADD", obj.getTOAddress());
                    
                    if(obj.getCc() == null)
                        innerObj.put("CC_ADD", "");
                    else
                        innerObj.put("CC_ADD", obj.getCc());
                    
                    if(obj.getDocumentName() == null)
                        innerObj.put("DOC_NAME", "");
                    else
                        innerObj.put("DOC_NAME", obj.getDocumentName());
                    
                    if(obj.getMailSentBy() == null)
                        innerObj.put("SENT_BY", "");
                    else
                        innerObj.put("SENT_BY", obj.getMailSentBy());
                    
                    if(obj.getMailSentOn() == null)
                        innerObj.put("SENT_ON", "");
                    else
                        innerObj.put("SENT_ON", fromDateFormat.format(obj.getMailSentOn()));
                    
                    if(obj.getCustomerName() == null)
                        innerObj.put("CUSTOMER_NAME", "");
                    else
                        innerObj.put("CUSTOMER_NAME", obj.getCustomerName());
                    
                    if(obj.getProjectName() == null)
                        innerObj.put("PROJECT_NAME", "");
                    else
                        innerObj.put("PROJECT_NAME", obj.getProjectName());
                    
                    jArra.put(innerObj);
                }
                
                System.out.println("jArra: " + jArra.length());
                int records = reportList.size();

                jObj.put("Records", records);
                jObj.put("Result", jArra);

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
