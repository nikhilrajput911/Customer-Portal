/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.CustSecQuesDao;
import com.eportal.dao.CustomerAuditReportDao;
import com.eportal.dao.CustomerAutoMailDao;
import com.eportal.dao.CustomerDocumentDao;
import com.eportal.dao.CustomerDocumentSeededDao;
import com.eportal.dao.CustomerImageDao;
import com.eportal.dao.CustomerMailDao;
import com.eportal.dao.CustomerNotificationDao;
import com.eportal.dao.CustomerProfileUpdateReportDao;
import com.eportal.dao.CustomerProjectMappingDao;
import com.eportal.dao.CustomerSecQueSeededDao;
import com.eportal.dao.CustomerSeededDao;
import com.eportal.dao.CustomerSubUserDao;
import com.eportal.dao.CustomerUserTrackingReportDao;
import com.eportal.dao.GroupsDao;
import com.eportal.dao.PasswordConfigurationDao;
import com.eportal.dao.ProjectSeededDao;
import com.eportal.dao.SupplierGroupDao;
import com.eportal.dao.SupplierUserDao;
import com.eportal.entities.CustSecQues;
import com.eportal.entities.CustomerAuditReport;
import com.eportal.entities.CustomerAutoMail;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerImage;
import com.eportal.entities.CustomerMail;
import com.eportal.entities.CustomerNotification;
import com.eportal.entities.CustomerProfileUpdateReport;
import com.eportal.entities.CustomerProjectMapping;
import com.eportal.entities.CustomerSecQueSeeded;
import com.eportal.entities.CustomerSeeded;
import com.eportal.entities.CustomerSubUser;
import com.eportal.entities.CustomerUserTrackingReport;
import com.eportal.entities.Groups;
import com.eportal.entities.PasswordConfiguration;
import com.eportal.entities.ProjectSeeded;
import com.eportal.entities.SupplierGroup;
import com.eportal.entities.SupplierUser;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author admin
 */
@RestController
public class CustomerManagement {

    @Autowired
    SupplierUserDao supplierDao;

    @Autowired
    CustomerDocumentDao customerDocumentDao;

    @Autowired
    CustomerDocument customerDocument;

    @Autowired
    CustomerSubUserDao customerSubUserDao;
    @Autowired
    CustomerSubUser customerSubUser;

    @Autowired
    CustomerDocumentSeededDao custDocSeededDao;

    @Autowired
    GroupsDao groupDao;

    @Autowired
    SupplierGroupDao supplierGroupDao;
    @Autowired
    SupplierGroup supplierGroup;

    @Autowired
    CustomerSeededDao custSeededDao;

    @Autowired
    ProjectSeededDao projectSeededDao;

    @Autowired
    CustomerNotificationDao custNotiDao;
    @Autowired
    CustomerNotification custNotification;

    @Autowired
    CustomerProjectMappingDao custProjectMappingDao;
    @Autowired
    CustomerProjectMapping custProjectMapping;

    @Autowired
    CustSecQuesDao custSecQueDao;
    @Autowired
    CustSecQues custQue;

    @Autowired
    CustomerSecQueSeededDao custSecQueSeededDao;

    @Autowired
    CustomerImageDao customerImageDao;
    @Autowired
    CustomerImage customerImage;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasswordConfigurationDao passConfigDao;

    @Autowired
    CustomerAuditReportDao custAuditReportDao;

    @Autowired
    CustomerProfileUpdateReportDao customerProfileUpdateReportDao;

    @Autowired
    CustomerProfileUpdateReport customerProfileUpdateReport;
    
    
    @Autowired
    CustomerUserTrackingReport custUserTrackingReport;
    @Autowired
    CustomerUserTrackingReportDao custUserTrackingReportDao;
    
    @Autowired
    CustomerMailDao customerMailDao;
    @Autowired
    CustomerMail customerMail;
    
    @Autowired
    CustomerAutoMailDao customerAutoMailDao;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerManagement.class);

    @RequestMapping(value = "/customerhome.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerSubUser> customerDashboard(@RequestParam("uname") String uname) {
        System.out.println("uname: " + uname);

        List<CustomerSubUser> custSubUserlist = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(uname);
        System.out.println("CustSubUserlist: " + custSubUserlist);

        CustomerSubUser customer = null;

        if (!custSubUserlist.isEmpty()) {
            for (CustomerSubUser user : custSubUserlist) {
                if (user.getStatus().equalsIgnoreCase("Active")) {
                    customer = user;
                    break;
                } else {
                    customer = user;
                }
            }
//            customer = custSubUserlist.get(0);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(customerSubUser, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/customerusergroup", method = RequestMethod.GET)
    public ResponseEntity<List<Groups>> findByLeftJoinOnSupplierGroup(@RequestParam("custid") int custId) {

        System.out.println("custId: " + custId);
        List<Object> supplierGroupList = (List<Object>) supplierGroupDao.findByLeftJoinOnSupplierGroup(custId);
        System.out.println("supplierGroupList size: " + supplierGroupList.size());
        System.out.println("supplierGroupList: " + supplierGroupList);
        List<Groups> groupList = new ArrayList<>();

        if (!supplierGroupList.isEmpty()) {
            for (Object obj : supplierGroupList) {
                System.out.println("obj: " + obj);
                Object[] group = (Object[]) obj;
                System.out.println(group.length);
                if (group.length > 0) {
                    System.out.println(group[0] + " : " + group[1]);
                    SupplierGroup supplierGroup = (SupplierGroup) group[0];
                    System.out.println(supplierGroup);
                    groupList.add(supplierGroup.getBpaasGroupsGid());
                }
            }
        }
        return new ResponseEntity<>(groupList, HttpStatus.OK);

    }

    @RequestMapping(value = "/customerresponse", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> customerResponse(@RequestParam("userid") int userId, @RequestParam("userrole") String userRole) {

        System.out.println("userId: " + userId);
        System.out.println("userRole: " + userRole);
        List<CustomerSubUser> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {
            System.out.println("Admin");
            customerList = (List<CustomerSubUser>) customerSubUserDao.getAllCustomerSubUser();
        } else {
            System.out.println("User");
            customerList = (List<CustomerSubUser>) customerSubUserDao.findByCustomerId(userId);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @RequestMapping(value = "customerresponsemgnt", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerNotification>> customerResponseMgnt(@RequestParam("supplierid") int customerId) {

        System.out.println("customerId: " + customerId);
        List<CustomerNotification> list = (List<CustomerNotification>) custNotiDao.findByCustomerUserId(customerId);
        System.out.println("size: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "customerresponsemgntnonadmin", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerNotification>> customerResponseMgntNonAdmin(@RequestParam("supplierid") int customerId) {

        System.out.println("customerId: " + customerId);
        List<CustomerNotification> list = (List<CustomerNotification>) custNotiDao.findByCustomerUserIdAndNoUpdateProfile(customerId);
        System.out.println("size: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "findunreadmessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerNotification>> findUnreadMessages(@RequestParam("readstatus") String readstatus) {

        System.out.println("readstatus: " + readstatus);
        List<CustomerNotification> list = (List<CustomerNotification>) custNotiDao.findUnreadMessages(readstatus);
        System.out.println("size: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "findUnreadmessagesbycustomeridandreadstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerNotification>> findUnreadMessagesByCustomerIdAndReadStatus(@RequestParam("customerid") int customerId) {

        System.out.println("customerId: " + customerId);
        List<CustomerNotification> list = (List<CustomerNotification>) custNotiDao.findUnreadMessagesByCustomerIdAndReadStatus(customerId);
        System.out.println("size: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "findbynotificationid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerNotification>> findByNotificationId(@RequestParam("notificationid") int notificationId) {

        System.out.println("notificationId: " + notificationId);
        List<CustomerNotification> list = (List<CustomerNotification>) custNotiDao.findByNotificationId(notificationId);
        System.out.println("size: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatenotificationreadstatus", method = RequestMethod.POST)
    public String updateNotificationReadStatus(@RequestBody CustomerNotification obj) {

        custNotiDao.updateCustomerNotifications(obj);
        System.out.println("updated in WS");

        return "updated";
    }

    @RequestMapping(value = "/customerbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerSubUser> findCustomerById(@RequestParam("userid") int customerId) {

        System.out.println("userid in ws: " + customerId);
        CustomerSubUser customer = customerSubUserDao.getCustomerSubUserById(customerId);
        System.out.println("customer in ws: " + customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/projectbycustomerid", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectSeeded>> findProjectByCustomerId(@RequestParam("customerid") int customerId) {
        System.out.println("findProjectByCustomerId");

        System.out.println("customerId: " + customerId);
        List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.findByCustomerId(customerId);
        System.out.println("projectList in WS: " + projectList.size());

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/projectbycustomercode", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectSeeded>> findProjectByCustomerCode(@RequestParam("customercode") String customercode) {
        System.out.println("projectbycustomercode");

        System.out.println("customercode: " + customercode);
        List<CustomerSeeded> customerSeededList = (List<CustomerSeeded>) custSeededDao.findByCustomerCode(customercode);

        List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.findByCustomerId(customerSeededList.get(0).getCid());
        System.out.println("projectList in WS: " + projectList.size());

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findassignedprojecttocustomer", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerProjectMapping>> findAssignedProjectToCustomer(@RequestParam("customerid") int customerId) {
        System.out.println("findAssignedProjectToCustomer");

        System.out.println("userId: " + customerId);

        List<CustomerProjectMapping> customerProjectMappingList = (List<CustomerProjectMapping>) custProjectMappingDao.findByCustomerId(customerId);
        System.out.println("customerProjectMappingList: " + customerProjectMappingList.size());

        return new ResponseEntity<>(customerProjectMappingList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findprojectbyprojectandcustomerid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerProjectMapping>> findProjectByProjectAndCustomerId(@RequestParam("customerid") int customerId, @RequestParam("projectId") int projectId) {
        System.out.println("findprojectbyprojectandcustomerid");

        System.out.println("customerId: " + customerId);
        System.out.println("projectId: " + projectId);

        List<CustomerProjectMapping> customerProjectMappingList = (List<CustomerProjectMapping>) custProjectMappingDao.findByCustomerIdAndProjectId(customerId, projectId);
        System.out.println("customerProjectMappingList: " + customerProjectMappingList.size());

        return new ResponseEntity<>(customerProjectMappingList, HttpStatus.OK);
    }

    @RequestMapping(value = "/deletecustomerprojectmapping", method = RequestMethod.POST)
    public String deleteCustomerProjectMapping(@RequestBody CustomerProjectMapping obj) {

        custProjectMappingDao.deleteCustomerProjectMapping(obj);
        System.out.println("deleted in WS");

        return "deleted";
    }

    @RequestMapping(value = "/getprojectseededbyid", method = RequestMethod.GET)
    public ResponseEntity<ProjectSeeded> getProjectSeededById(@RequestParam("pid") int pid) {

        System.out.println("pid in ws: " + pid);
        ProjectSeeded project = projectSeededDao.getProjectSeededById(pid);
        System.out.println("project in ws: " + project);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/savecustomerprojectmapping", method = RequestMethod.POST)
    public String saveCustomerProjectMapping(@RequestBody CustomerProjectMapping obj) {

        custProjectMappingDao.saveCustomerProjectMapping(obj);
        System.out.println("saved in WS");

        return "saved";
    }

    @RequestMapping(value = "/updatecustomersubuser", method = RequestMethod.POST)
    public String updateCustomerSubUser(@RequestBody CustomerSubUser custUser) {

        customerSubUserDao.updateCustomerSubUser(custUser);
        System.out.println("updated in WS");

        return "updated";
    }

    @RequestMapping(value = "/savequeans", method = RequestMethod.POST)
    public String saveQueAns(@RequestBody CustSecQues custQues) {

        int queAnsId = custSecQueDao.saveQue(custQues);
        System.out.println("queAnsId: " + queAnsId);

        return queAnsId + "";
    }

    @RequestMapping(value = "/updatequeans", method = RequestMethod.POST)
    public String updateQueAns(@RequestBody CustSecQues custQues) {

//        System.out.println("custQues id: " + custQues.getId());
        custSecQueDao.updateQue(custQues);

        return "updatedQue";
    }

    @RequestMapping(value = "/updatecustomerseeded", method = RequestMethod.POST)
    public String updateCustomerSeeded(@RequestBody CustomerSeeded custSeeded) {

        System.out.println("updatecustomerseeded in ws");
        custSeededDao.updateCustomerSeeded(custSeeded);
        System.out.println("updated in WS");

        return "updated";
    }

    @RequestMapping(value = "/makecustomercomment.do", method = RequestMethod.POST)
    public String makeCustomerComment(@RequestBody CustomerNotification notification) {

        System.out.println("notification: " + notification.getNotification());
        int notificationId = custNotiDao.saveCustomerNotification(notification);
        System.out.println("notificationId in WS : " + notificationId);

        return notificationId + "";
    }

    @RequestMapping(value = "sendmessagetoallcustomeruser", method = RequestMethod.POST)
    public String sendMessageToAllCustomerUser(@RequestBody CustomerNotification notification) {

        List<CustomerSubUser> customerList = (List<CustomerSubUser>) customerSubUserDao.getAllCustomerSubUser();
        System.out.println("customerList: " + customerList.size());

        int commentId = 0;

        for (CustomerSubUser customer : customerList) {

            notification.setBpaasCustomersubuserId(customer);

            commentId = custNotiDao.saveCustomerNotification(notification);
            System.out.println("commentId: " + commentId);

        }

        return commentId + "";
    }

    @RequestMapping(value = "/managegroup", method = RequestMethod.GET)
    public ResponseEntity<List<Groups>> manageGroup() {

        System.out.println("managegroup");

        List<Groups> groupList = (List<Groups>) groupDao.getAllActiveGroups("Active");
        System.out.println("size: " + groupList);

        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getgroupbyid", method = RequestMethod.GET)
    public ResponseEntity<Groups> getGroupById(@RequestParam("gid") int gid) {

        System.out.println("managegroup");

        Groups group = groupDao.getGroupById(gid);
        System.out.println("group: " + group);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "/getgroupbyname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Groups>> getGroupByName(@RequestParam("groupname") String groupname) {

        System.out.println("managegroup");

        List<Groups> group = (List<Groups>) groupDao.findByGroupName(groupname);
        System.out.println("group: " + group);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "/getsuppliergroupbyname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SupplierGroup>> findBySupplierGroupname(@RequestParam("groupname") String groupname) {

        System.out.println("getsuppliergroupbyname");

        List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findBySupplierGroupname(groupname);
        System.out.println("supplierGroupList: " + supplierGroupList.size());

        return new ResponseEntity<>(supplierGroupList, HttpStatus.OK);
    }

    @RequestMapping(value = "/activecustomers", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> getActiveCustomer() {

        System.out.println("managegroup");

        List<CustomerSubUser> subuser = (List<CustomerSubUser>) customerSubUserDao.findByStatus("Active");
        System.out.println("customer: " + subuser);

        return new ResponseEntity<>(subuser, HttpStatus.OK);
    }

    @RequestMapping(value = "/mappedgroup", method = RequestMethod.GET)
    public ResponseEntity<List<SupplierGroup>> getMappedCustomerGroup(@RequestParam("gid") int gid) {
        System.out.println("getmappedgroup");
        System.out.println("gid: " + gid);

        Groups group = groupDao.getGroupById(gid);
        System.out.println("group: " + group);

        List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findByGroupId(gid);
        System.out.println("supplier group: " + supplierGroupList);

        return new ResponseEntity<>(supplierGroupList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mappcustomertogroup", method = RequestMethod.POST)
    public String mappCustomerToGroup(@RequestBody SupplierGroup supplierGroup) {
        System.out.println("mappcustomertogroup");

        int suppGroupId = supplierGroupDao.saveSupplierGroup(supplierGroup);
        System.out.println("suppGroupId: " + suppGroupId);

        return suppGroupId + "";
    }

    @RequestMapping(value = "/getcustomergroup", method = RequestMethod.GET)
    public ResponseEntity<List<SupplierGroup>> getCustomerGroup(@RequestParam("gid") int gid, @RequestParam("customerid") int customerId) {

        System.out.println("gid: " + gid);
        System.out.println("customerId: " + customerId);

        List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findByGroupIdAndCustomerId(gid, customerId);

        return new ResponseEntity<>(supplierGroupList, HttpStatus.OK);
    }

    @RequestMapping(value = "/removecustomerdocument", method = RequestMethod.GET)
    public ResponseEntity<String> removeCustomerDocument(@RequestParam("gid") int gid, @RequestParam("customerid") int customerId) {

        System.out.println("gid: " + gid);
        System.out.println("customerId: " + customerId);

        List<SupplierGroup> supplierGroupList = (List<SupplierGroup>) supplierGroupDao.findByGroupIdAndCustomerId(gid, customerId);
        supplierGroupDao.deleteSupplierGroups(supplierGroupList.get(0));

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/unmappuustomeruromuroup", method = RequestMethod.POST)
    public String unmappCustomerFromGroup(@RequestBody SupplierGroup supplierGroup) {
        System.out.println("UnmappCustomerFromGroup in ws");

        supplierGroupDao.deleteSupplierGroups(supplierGroup);

        return "success";
    }

    @RequestMapping(value = "/findallcustomerexceptthisstatus", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findAllCustomerExceptThisStatus(@RequestParam("status") String status) {
        System.out.println("getmappedgroup");
        System.out.println("status: " + status);

        List<CustomerSubUser> userList = (List<CustomerSubUser>) customerSubUserDao.findAllCustomerExceptDeleteStatus(status);
        System.out.println("userList: " + userList.size());

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallcustomerseeded", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSeeded>> getAllCustomerSeeded() {
        System.out.println("getAllCustomerSeeded");

        List<CustomerSeeded> customerList = (List<CustomerSeeded>) custSeededDao.findByAccountGroupType("Customer");
        System.out.println("customerList: " + customerList.size());

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallprojectseeded", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectSeeded>> getAllProjectSeeded() {
        System.out.println("getAllProjectSeeded");

        List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.getAllProjectSeeded();
        System.out.println("customerList: " + projectList.size());

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallcustomerdocument", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDocumentSeeded>> getAllCustomerDocument() {
        System.out.println("getAllProjectSeeded");

        List<CustomerDocumentSeeded> docList = (List<CustomerDocumentSeeded>) custDocSeededDao.getAllCustomerDocument();
        System.out.println("customerList: " + docList.size());

        return new ResponseEntity<>(docList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findcustomerbyid", method = RequestMethod.GET)
    public ResponseEntity<CustomerSeeded> getCustomerSeededById(@RequestParam("customerid") int customerId) {
        System.out.println("getCustomerSeededById in ws");
        System.out.println("customerId: " + customerId);

        CustomerSeeded customer = custSeededDao.getCustomerSeededById(customerId);
        System.out.println("customer: " + customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findbycustomercode", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSeeded>> findByCustomerCode(@RequestParam("customercode") String customercode) {
        System.out.println("findByCustomerCode in ws");
        System.out.println("customercode: " + customercode);

        List<CustomerSeeded> customer = (List<CustomerSeeded>) custSeededDao.findByCustomerCode(customercode);
        System.out.println("customer: " + customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/findbyusernamecheck", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findByUsernameCheck(@RequestParam("username") String username) {
        System.out.println("getCustomerSeededById in ws");
        System.out.println("username: " + username);

        List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);

        System.out.println("customer: " + customerSubUserList);

        return new ResponseEntity<>(customerSubUserList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findbyusernamecheckasactive", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findByUsernameActiveCheck(@RequestParam("username") String username) {
        System.out.println("getCustomerSeededById in ws");
        System.out.println("username: " + username);

        List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheckAsActive(username);

        System.out.println("customer: " + customerSubUserList);

        return new ResponseEntity<>(customerSubUserList, HttpStatus.OK);
    }

    @RequestMapping(value = "/addcustomersubuser", method = RequestMethod.POST)
    public String addCustomerSubUser(@RequestBody CustomerSubUser user) {

        int id = customerSubUserDao.saveCustomerSubUser(user);
        System.out.println("id: " + id);

        return id + "";
    }

    @RequestMapping(value = "/getallques", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSecQueSeeded>> getAllQues() {
        System.out.println("getAllQues in ws");

        List<CustomerSecQueSeeded> custSecQueList = (List<CustomerSecQueSeeded>) custSecQueSeededDao.getAllQues();
        System.out.println("custSecQueList: " + custSecQueList.size());

        System.out.println("custSecQueList: " + custSecQueList);

        return new ResponseEntity<>(custSecQueList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findquesbyuserid", method = RequestMethod.GET)
    public ResponseEntity<List<CustSecQues>> findQuesByUserId(@RequestParam("userid") int userid) {
        System.out.println("findQuesByUserId in ws");
        System.out.println("userid: " + userid);

        List<CustSecQues> custQueAnsList = (List<CustSecQues>) custSecQueDao.findById(userid);
        System.out.println("custQueAnsList: " + custQueAnsList.size());

        System.out.println("custQueAnsList: " + custQueAnsList);

        return new ResponseEntity<>(custQueAnsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findbysectionname", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerImage>> findBySectionName(@RequestParam("section") String sectionName) {
        System.out.println("findBySectionName in ws");
        System.out.println("sectionName: " + sectionName);

        List<CustomerImage> custImageList = (List<CustomerImage>) customerImageDao.findBySectionName(sectionName);
        System.out.println("custImageList: " + custImageList);

        return new ResponseEntity<>(custImageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/savecustomerimage", method = RequestMethod.POST)
    public String saveCustomerImage(@RequestBody CustomerImage image) {

        int imageId = customerImageDao.saveCustomerImage(image);
        System.out.println("imageId: " + imageId);

        return imageId + "";
    }

    @RequestMapping(value = "/updatecustomerimage", method = RequestMethod.POST)
    public String updateCustomerImage(@RequestBody CustomerImage image) {

        System.out.println("updatecustomerseeded in ws");
        customerImageDao.updateCustomerImage(image);
        System.out.println("updated in WS");

        return "updated";
    }

    @RequestMapping(value = "/getpasswordconfiguration", method = RequestMethod.GET)
    public ResponseEntity<List<PasswordConfiguration>> getAllPasswordConfiguration() {
        System.out.println("getpasswordconfiguration in ws");

        List<PasswordConfiguration> passConfigList = (List<PasswordConfiguration>) passConfigDao.getAllPasswordConfiguration();
        System.out.println("passConfigList in ws size: " + passConfigList.size());

        return new ResponseEntity<>(passConfigList, HttpStatus.OK);
    }

    @RequestMapping(value = "/savepasswordconfiguration", method = RequestMethod.POST)
    public String savePasswordConfiguration(@RequestBody PasswordConfiguration passConfig) {

        List<PasswordConfiguration> passConfigList = (List<PasswordConfiguration>) passConfigDao.getAllPasswordConfiguration();

        if (passConfigList.isEmpty()) {
            passConfigDao.savePasswordConfiguration(passConfig);
        } else {
//            passConfig.setId(1);
            passConfigDao.updatePasswordConfiguration(passConfig);
        }
        System.out.println("save/update in WS");

        return "save_update";
    }

    @RequestMapping(value = "/findcustomerbycustomerseededid", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findCustomerByCustomerSeededId(@RequestParam("userid") int userId) {
        System.out.println("findcustomerbycustomerseededid in ws");
        System.out.println("userid in ws: " + userId);

        List<CustomerSubUser> loggedInUserList = (List<CustomerSubUser>) customerSubUserDao.findByCustomerId(userId);
        CustomerSubUser loggedInUser = loggedInUserList.get(0);
        List<CustomerSubUser> userList = (List<CustomerSubUser>) customerSubUserDao.findByCustomerSeededId(loggedInUser.getBpaasCustomerseededCid().getCid());

        userList.remove(loggedInUser);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findbycustomerseededid", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findByCustomerSeededId(@RequestParam("cid") int cid) {
        System.out.println("findbycustomerseededid in ws");
        System.out.println("cid in ws: " + cid);

        List<CustomerSubUser> userList = (List<CustomerSubUser>) customerSubUserDao.findByCustomerSeededId(cid);


        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findbycustomerseededidforautomail", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerSubUser>> findByCustomerSeededIdForAutoMail(@RequestParam("cid") int cid) {
        System.out.println("findbycustomerseededid in ws");
        System.out.println("cid in ws: " + cid);

        List<CustomerSubUser> userList = (List<CustomerSubUser>) customerSubUserDao.findByCustomerSeededIdForAutoMail(cid);


        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findsecqueandansbyuserid", method = RequestMethod.GET)
    public ResponseEntity<List<CustSecQues>> findSecQueAndAnsByUserId(@RequestParam("userid") int userId) {
        System.out.println("findsecqueandansbyuserid in ws");
        System.out.println("userid in ws: " + userId);

        List<CustSecQues> queAnslist = (List<CustSecQues>) custSecQueDao.findById(userId);
        System.out.println("queAnslist: " + queAnslist.size());

        return new ResponseEntity<>(queAnslist, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallauditreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAuditReport>> getAllAuditReport() {
        System.out.println("getpasswordconfiguration in ws");

        List<CustomerAuditReport> reportList = (List<CustomerAuditReport>) custAuditReportDao.getAllAuditReport();
        System.out.println("reportList: " + reportList);

        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @RequestMapping(value = "/savecustomerauditreport", method = RequestMethod.POST)
    public String saveCustomerAuditReport(@RequestBody CustomerAuditReport custAuditReport) {

        int reportId = custAuditReportDao.saveCustomerAuditReport(custAuditReport);

        System.out.println("saved in WS");

        return "" + reportId;
    }

    @RequestMapping(value = "/getallcustomerprofileupdatereport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerProfileUpdateReport>> getAllCustomerProfileUpdateReport() {
        System.out.println("getpasswordconfiguration in ws");

        List<CustomerProfileUpdateReport> reportList = (List<CustomerProfileUpdateReport>) customerProfileUpdateReportDao.getAllCustomerProfileUpdateReport();
        System.out.println("reportList: " + reportList);

        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @RequestMapping(value = "/savecustomerprofileupdatereport", method = RequestMethod.POST)
    public String saveCustomerProfileUpdate(@RequestBody CustomerProfileUpdateReport customerProfileUpdateReport) {

        int updateReportId = customerProfileUpdateReportDao.saveCustomerProfileUpdateReport(customerProfileUpdateReport);

        System.out.println("saved in WS");

        return "" + updateReportId;
    }

    @RequestMapping(value = "/updateprojectseededstatus", method = RequestMethod.GET)
    public ResponseEntity<String> updateProjectSeededStatus(@RequestParam("pid") int pid, @RequestParam("status") String status) {
        System.out.println("updateprojectseededstatus in ws");
        System.out.println("pid in ws: " + pid);
        System.out.println("status in ws: " + status);

        ProjectSeeded projectObj = projectSeededDao.getProjectSeededById(pid);
        projectObj.setProjectstatus(status);
        projectSeededDao.updateProjectSeeded(projectObj);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/filtercustomerprofileupdatereport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerProfileUpdateReport>> filterProfileUpdateReport(@RequestParam("fromdate") String fromDate, @RequestParam("todate") String toDate) {
        List<CustomerProfileUpdateReport> reportList = null;
        try {
            System.out.println("filtercustomerprofileupdatereport in ws");
            
            System.out.println("fromDate: " + fromDate);
            System.out.println("toDate: " + toDate);
            
            DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            
            Date from_date = fromDateFormat.parse(fromDate);
            Date to_date = fromDateFormat.parse(toDate);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(to_date);
            cal.add(Calendar.DATE, 1);
            to_date = cal.getTime();
            
            System.out.println("from_date: " + from_date);
            System.out.println("to_date: " + to_date);
            
            reportList = (List<CustomerProfileUpdateReport>) customerProfileUpdateReportDao.findByfromDateAndToDate(from_date, to_date);
            System.out.println("reportList: " + reportList);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }
    @RequestMapping(value = "/filterauditreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAuditReport>> filterAuditReport(@RequestParam("fromdate") String fromDate, @RequestParam("todate") String toDate, @RequestParam("customer") String customer) {
        List<CustomerAuditReport> reportList = null;
        try {
            System.out.println("filterauditreport in ws");
            
            System.out.println("fromDate: " + fromDate);
            System.out.println("toDate: " + toDate);
            System.out.println("Customer: " + customer);
            
            DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            
            Date from_date = fromDateFormat.parse(fromDate);
            Date to_date = fromDateFormat.parse(toDate);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(to_date);
            cal.add(Calendar.DATE, 1);
            to_date = cal.getTime();
            
            System.out.println("from_date: " + from_date);
            System.out.println("to_date: " + to_date);
            
            
            reportList = (List<CustomerAuditReport>) custAuditReportDao.findByfromDateAndToDateAndCustomerName(from_date, to_date, customer);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/savecustomerusertrackreport", method = RequestMethod.POST)
    public String saveCustomerUserTrackReport(@RequestBody CustomerUserTrackingReport obj) {

        int trackReportId = custUserTrackingReportDao.saveCustomerUserTrackingReport(obj);

        System.out.println("saved in WS");

        return "" + trackReportId;
    }
    
    @RequestMapping(value = "/filteradmintrackingreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerUserTrackingReport>> filterAdminTrackingReport(@RequestParam("fromdate") String fromDate, @RequestParam("todate") String toDate) {
        List<CustomerUserTrackingReport> reportList = null;
        try {
            System.out.println("filteradmintrackingreport in ws");
            
            System.out.println("fromDate: " + fromDate);
            System.out.println("toDate: " + toDate);
            
            DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            
            Date from_date = fromDateFormat.parse(fromDate);
            Date to_date = fromDateFormat.parse(toDate);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(to_date);
            cal.add(Calendar.DATE, 1);
            to_date = cal.getTime();
            
            System.out.println("from_date: " + from_date);
            System.out.println("to_date: " + to_date);
            
            reportList = (List<CustomerUserTrackingReport>) custUserTrackingReportDao.findByfromDateAndToDate(from_date, to_date);
            System.out.println("reportList: " + reportList);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }
    @RequestMapping(value = "/savecustomermail", method = RequestMethod.POST)
    public String saveCustomerMail(@RequestBody CustomerMail obj) {

        int mailId = customerMailDao.saveMail(obj);

        System.out.println("saved in WS");

        return "" + mailId;
    }
    
    @RequestMapping(value = "/savecustomerautomail", method = RequestMethod.POST)
    public String saveCustomerAutoMail(@RequestBody CustomerAutoMail obj) {

        int mailId = customerAutoMailDao.saveCustomerAutoMail(obj);

        System.out.println("saved in WS");

        return "" + mailId;
    }
    @RequestMapping(value = "/findallcustomerautomail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAutoMail>> findAllCustomerAutoMail() {

        List<CustomerAutoMail> list = (List<CustomerAutoMail>) customerAutoMailDao.getCustomerAutoMails();
        

        System.out.println("size in WS: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @RequestMapping(value = "/findautomailbycustomercode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAutoMail>> findAutoMailByCustomerCode(@RequestParam("customercode") String customerCode) {

        List<CustomerAutoMail> list = (List<CustomerAutoMail>) customerAutoMailDao.findByCustomerCode(customerCode);
        

        System.out.println("size in WS: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @RequestMapping(value = "/findautomailbyusername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAutoMail>> findAutoMailByUserName(@RequestParam("username") String username) {

        List<CustomerAutoMail> list = (List<CustomerAutoMail>) customerAutoMailDao.findByUsername(username);
        

        System.out.println("size in WS: " + list.size());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatecustomerautomail", method = RequestMethod.POST)
    public String updateCustomerAutoMail(@RequestBody CustomerAutoMail obj) {

        customerAutoMailDao.updateCustomerAutoMail(obj);

        System.out.println("updated in WS");

        return "Updated";
    }
    
    
    @RequestMapping(value = "/filtermailreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerMail>> filterMailReport(@RequestParam("fromdate") String fromDate, @RequestParam("todate") String toDate, @RequestParam("mailtype") String mailType) {
        
        List<CustomerMail> reportList = null;
        try {
            System.out.println("filtermailreport in ws");
            
            System.out.println("fromDate: " + fromDate);
            System.out.println("toDate: " + toDate);
            
            DateFormat fromDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat toDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            
            Date from_date = fromDateFormat.parse(fromDate);
            Date to_date = fromDateFormat.parse(toDate);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(to_date);
            cal.add(Calendar.DATE, 1);
            to_date = cal.getTime();
            
            System.out.println("from_date: " + from_date);
            System.out.println("to_date: " + to_date);
            
            reportList = (List<CustomerMail>) customerMailDao.findByFromAndToDateAndMailType(from_date, to_date, mailType);
            
            System.out.println("reportList: " + reportList);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }
    //Web Service Ends
}
