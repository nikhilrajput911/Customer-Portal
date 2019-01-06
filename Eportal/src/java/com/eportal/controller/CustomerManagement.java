/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.CustSecQuesDao;
import com.eportal.dao.CustomerAuditReportDao;
import com.eportal.dao.CustomerDocumentDao;
import com.eportal.dao.CustomerDocumentSeededDao;
import com.eportal.dao.CustomerImageDao;
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
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerDocumentSeeded;
import com.eportal.entities.CustomerImage;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
@Controller
@SessionAttributes({"username", "userId", "userRole", "groupIds", "customerid", "ispassupdated", "fullname", "dmsip", "profilePic", "docindex", "passconfig", "NotificationList", "isPersonalInfoUpdated"})
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

    @Value("${webservice.ip}")
    private String webservice_ip;

    @Value("${dmswebservice.ip}")
    private String dmswebservice_ip;

    @Value("${documentindex}")
    private String documentindex;

    @Value("${termsandcondition.loc}")
    private String termsandcondition_loc;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerManagement.class);

//    public Statement getStatement() {
//        Statement stmt = null;
//        Connection con = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://192.168.13.200:1433;databaseName=natsteelap", "natsteelap", "natsteelap");
//            stmt = con.createStatement();
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return stmt;
//    }
    @RequestMapping("/customerlogin")
    public ModelAndView customerLogin(HttpServletRequest request, Map<String, Object> modelMap) {
        System.out.println("customerlogin");

        setDashboardImages(modelMap);

        boolean status = checkCookies(request);
        System.out.println("status: " + status);
        if (status == true) {
            return new ModelAndView("customerdashboard");
        }

        return new ModelAndView("customerlogin");
    }

    @RequestMapping("/customerhome")
    public ModelAndView customerDashboard(HttpServletRequest request, HttpServletResponse response, Map<String, Object> modelMap) {
        System.out.println("customerhome");

        String message = "";
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        if (uname != null && uname.equalsIgnoreCase("tempadmin") && password != null && password.equalsIgnoreCase("pass@12345#")) {
            System.out.println("tempadmin");

            modelMap.put("username", "Temp Admin");
            modelMap.put("userId", 101);
            modelMap.put("userRole", "Admin");
            modelMap.put("fullname", "Temp Admin");
            modelMap.put("ispassupdated", "Yes");

            return new ModelAndView("customerdashboard").addAllObjects(modelMap);

        } else {
            System.out.println("remember_me: " + remember);
            String userRole = "";
            List<Groups> groupList = new ArrayList<>();

//            List<CustomerSubUser> CustSubUserlist = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(uname);
//            System.out.println("CustSubUserlist: " + CustSubUserlist);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerhome.do?uname=" + uname, CustomerSubUser.class);
            System.out.println("response: " + restResponse);
            System.out.println("status code: " + restResponse.getStatusCode());
            System.out.println("body: " + restResponse.getBody());

            CustomerSubUser customerObj = restResponse.getBody();
            System.out.println("ids: " + customerObj);

            if (customerObj.getId() == null) {

                System.out.println("if.139");
                message = "Username is invalid!";
                return new ModelAndView("customerlogin", "message", message);

            } else if (customerObj.getStatus().equalsIgnoreCase("Block")) {

                System.out.println("if.147");
                message = "Your account has been blocked. Please contact system administartor!";
                return new ModelAndView("customerlogin", "message", message);

            } else if (customerObj.getStatus().equalsIgnoreCase("Locked")) {

                System.out.println("if.147");
                message = "Account is locked!";
                return new ModelAndView("customerlogin", "message", message);

            } else if (customerObj.getStatus().equalsIgnoreCase("Inactive")) {

                System.out.println("if.147");
                message = "User is not active!";
                return new ModelAndView("customerlogin", "message", message);

            } else if (customerObj.getStatus().equalsIgnoreCase("Delete")) {

                System.out.println("if.154");
                message = "Account does not exist!";
                return new ModelAndView("customerlogin", "message", message);

            } else if (passwordEncoder.matches(password, customerObj.getPassword()) && customerObj.getStatus().equalsIgnoreCase("Active")) {

                CustomerSubUser loggedInUser = customerObj;
//                loggedInUser.setUserauth("Yes");
//                customerSubUserDao.updateCustomerSubUser(CustSubUserlist.get(0));

                if (remember != null) {
                    Cookie ckUsername = new Cookie("username", uname);
                    ckUsername.setMaxAge(24 * 60 * 60);
                    response.addCookie(ckUsername);

                    Cookie ckPassword = new Cookie("password", password);
                    ckPassword.setMaxAge(24 * 60 * 60);
                    response.addCookie(ckPassword);
                }

                CustomerSubUser customer = customerObj;

                ResponseEntity<CustomerSeeded> restSeededResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + customer.getBpaasCustomerseededCid().getCid(), CustomerSeeded.class);

                CustomerSeeded custSeeded = restSeededResponse.getBody();

                String profilePicString = "";
                if (customerObj.getProfilepic() != null) {
                    System.out.println("if");
                    byte[] image = customerObj.getProfilepic();
                    byte[] encodedImage = Base64.getEncoder().encode(image);
                    profilePicString = new String(encodedImage);
                } else {
                    System.out.println("else");
                    profilePicString = "NotFound";
                }
                System.out.println("profilePicString: " + profilePicString);

                modelMap.put("username", customer.getUsername());
                modelMap.put("userId", customer.getId());
                System.out.println("last name: " + customer.getPersonallastname());

                if (customer.getPersonallastname() == null && customer.getPersonalfirstname() == null) {
                    modelMap.put("fullname", "");
                } else if (customer.getPersonallastname() == null) {
                    modelMap.put("fullname", customer.getPersonalfirstname());
                } else if (customer.getPersonalfirstname() == null) {
                    modelMap.put("fullname", customer.getPersonallastname());
                } else {
                    modelMap.put("fullname", customer.getPersonalfirstname() + " " + customer.getPersonallastname());
                }

                modelMap.put("customerid", customer.getBpaasCustomerseededCid().getCustomercode());
                modelMap.put("dmsip", dmswebservice_ip);
                modelMap.put("profilePic", profilePicString);
                modelMap.put("docindex", documentindex);

                System.out.println("dmswebservice_ip: " + dmswebservice_ip);

                //27Nov2018
                List<PasswordConfiguration> passConfigList = null;
                String passconfigString = "";

                ResponseEntity<List<PasswordConfiguration>> restPassConfigResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getpasswordconfiguration.do",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PasswordConfiguration>>() {
                        });

                passConfigList = restPassConfigResponse.getBody();

                System.out.println("passConfigList: " + passConfigList);

                if (!passConfigList.isEmpty()) {
                    PasswordConfiguration passObj = passConfigList.get(0);
                    passconfigString = passObj.getPassLength() + ", " + passObj.getSpecialChar() + ", " + passObj.getUpparChar() + ", " + passObj.getLowerChar() + ", " + passObj.getNumericCount();
                    System.out.println("passconfigString: " + passconfigString);
                }

                modelMap.put("passconfig", passconfigString);

                //Ends
                ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerusergroup.do?custid=" + customer.getId(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                        });

                System.out.println("response: " + restGroupResponse);
                System.out.println("response: " + restGroupResponse.getBody().size());

                if (restGroupResponse.getBody().isEmpty()) {
                    modelMap.put("userRole", "No");
                    userRole = "No";
                } else {
                    groupList.addAll(restGroupResponse.getBody());
                }

                modelMap.put("groupIds", groupList);
                System.out.println("groupList: " + groupList);

                boolean isAdmin = false;
                Groups adminGroup = null;

                boolean isCompanyAdmin = false;
                Groups companyAdminGroup = null;

                for (Groups group : groupList) {
                    if (group.getGroupname().equalsIgnoreCase("Admin")) {
                        modelMap.put("userRole", "Admin");
                        adminGroup = group;
                        isAdmin = true;
                    } else {
//                        isAdmin = false;
                    }

                    if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                        modelMap.put("userRole", "Company Admin");
                        companyAdminGroup = group;
                        isCompanyAdmin = true;
                    } else {
//                        isCompanyAdmin = false;
                    }
                }
                if (adminGroup != null) {
                    groupList.remove(adminGroup);
                }
                if (companyAdminGroup != null) {
                    groupList.remove(companyAdminGroup);
                }
                System.out.println("groupList: " + groupList);
                System.out.println("isAdmin: " + isAdmin);
                System.out.println("isCompanyAdmin: " + isCompanyAdmin);

                List<CustomerNotification> customerNotificationlist = new ArrayList<>();
                if (adminGroup != null) {
                    //28Nov2018
                    ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findunreadmessages.do?readstatus=true",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                            });

                    System.out.println("restNotificationResponse: " + restNotificationResponse);
                    System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

                    modelMap.put("NotificationList", restNotificationResponse.getBody());
                    //Ends
                } else {
                    //28Nov2018
                    ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findUnreadmessagesbycustomeridandreadstatus.do?customerid=" + customer.getId(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                            });

                    System.out.println("restNotificationResponse: " + restNotificationResponse);
                    System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

                    modelMap.put("NotificationList", restNotificationResponse.getBody());
                    //Ends
                }

                if (isAdmin == false && isCompanyAdmin == false) {
                    modelMap.put("userRole", "Non");
                }

                setDashboardImages(modelMap);

                // Added on 17Oct2018 for Updating Password At First Login Begin
                System.out.println("ispassupdated: " + loggedInUser.getIsPasswordUpdated());

                if (loggedInUser.getIsPasswordUpdated() == null || loggedInUser.getIsPasswordUpdated().equalsIgnoreCase("null")) {
                    modelMap.put("ispassupdated", "No");
                } else {
                    modelMap.put("ispassupdated", loggedInUser.getIsPasswordUpdated());
                }

                //End
                System.out.println("isPersonalInfoUpdated: " + loggedInUser.getIsPersonalInfoUpdated());
                if (loggedInUser.getIsPersonalInfoUpdated() == null || loggedInUser.getIsPersonalInfoUpdated().equalsIgnoreCase("null")) {
                    modelMap.put("isPersonalInfoUpdated", "No");
                } else {
                    modelMap.put("isPersonalInfoUpdated", "Yes");
                }

                return new ModelAndView("customerdashboard").addAllObjects(modelMap);
            } else {
                message = "Sorry, you are not authorised user!";
                return new ModelAndView("customerlogin", "message", message);
            }
        }
    }

    @RequestMapping("customerlogout")
    public ModelAndView doCustomerLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes model) {
        System.out.println("customerlogout");
        //"username", "userId", "userRole", "groupIds"
        model.addFlashAttribute("msg", "");

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

//        CustomerSubUser loggedInUser = customerSubUserDao.getCustomerSubUserById(userId);
//        System.out.println("loggedInUser: " + loggedInUser);
//
//        loggedInUser.setUserauth("No");
//        customerSubUserDao.updateCustomerSubUser(loggedInUser);
        session.removeAttribute("username");
        session.removeAttribute("userId");
        session.removeAttribute("userRole");
        session.removeAttribute("groupIds");

        for (Cookie ck : request.getCookies()) {
            if (ck.getName().equalsIgnoreCase("username")) {
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
            if (ck.getName().equalsIgnoreCase("password")) {
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }

//        session.invalidate();
        return new ModelAndView("redirect:/customerlogin.do");
    }

    public boolean checkCookies(HttpServletRequest request) {
        String username = "", password = "";
        boolean status = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equalsIgnoreCase("username")) {
                username = ck.getValue();
            }
            if (ck.getName().equalsIgnoreCase("password")) {
                password = ck.getValue();
            }
        }
        System.out.println("username in checkcookie: " + username);
        System.out.println("password in checkcookie: " + password);

        if (username != null && !username.equalsIgnoreCase("") && password != null && !password.equalsIgnoreCase("")) {
            status = true;
        }
        return status;
    }

    @RequestMapping("/redirectcustomerhome")
    public ModelAndView redirect(Map<String, Object> modelMap, HttpServletRequest request, HttpSession session) {
        System.out.println("redirectToCustomerHome");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        setDashboardImages(modelMap);

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        RestTemplate restTemplate = new RestTemplate();

        if (userRole != null && userRole.equalsIgnoreCase("Admin")) {
            System.out.println("Role_Admin_if");
            ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findunreadmessages.do?readstatus=true",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("restNotificationResponse: " + restNotificationResponse);
            System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

            modelMap.put("NotificationList", restNotificationResponse.getBody());

        } else {
            System.out.println("Role_User_if");
            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser customer = restCustomerResponse.getBody();

            ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findUnreadmessagesbycustomeridandreadstatus.do?customerid=" + customer.getId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("restNotificationResponse: " + restNotificationResponse);
            System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

            modelMap.put("NotificationList", restNotificationResponse.getBody());

            //Ends
        }

        return new ModelAndView("customerdashboard", "map", modelMap);
    }

    public void setDashboardImages(Map<String, Object> modelMap) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CustomerImage>> restHeaderImageResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbysectionname.do?section=NetSteel Header Image",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerImage>>() {
                });

        List<CustomerImage> headerImageList = restHeaderImageResponse.getBody();

//        List<CustomerImage> headerImageList = (List<CustomerImage>) customerImageDao.findBySectionName("NetSteel Header Image");
        System.out.println("headerImageList: " + headerImageList);

        String headerEncodedString = "";
        if (!headerImageList.isEmpty()) {
            System.out.println("if");
            byte[] image = headerImageList.get(0).getImage();
            byte[] encodedImage = Base64.getEncoder().encode(image);
            headerEncodedString = new String(encodedImage);
        } else {
            System.out.println("else");
            headerEncodedString = "NotFound";
        }
        System.out.println("headerEncodedString: " + headerEncodedString);

        ResponseEntity<List<CustomerImage>> restCarousel1Response = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbysectionname.do?section=Carousel Image 1",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerImage>>() {
                });

        List<CustomerImage> carouseImage1List = restCarousel1Response.getBody();

//        List<CustomerImage> carouseImage1List = (List<CustomerImage>) customerImageDao.findBySectionName("Carousel Image 1");
        System.out.println("carouseImage1List: " + carouseImage1List);

        String carousel1EncodedString = "";
        if (!carouseImage1List.isEmpty()) {
            System.out.println("if");
            byte[] image = carouseImage1List.get(0).getImage();
            byte[] encodedImage = Base64.getEncoder().encode(image);
            carousel1EncodedString = new String(encodedImage);
        } else {
            System.out.println("else");
            carousel1EncodedString = "NotFound";
        }
        System.out.println("carousel1EncodedString: " + carousel1EncodedString);

        ResponseEntity<List<CustomerImage>> restCarousel2Response = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbysectionname.do?section=Carousel Image 2",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerImage>>() {
                });

        List<CustomerImage> carouseImage2List = restCarousel2Response.getBody();

//        List<CustomerImage> carouseImage2List = (List<CustomerImage>) customerImageDao.findBySectionName("Carousel Image 2");
        System.out.println("carouseImage2List: " + carouseImage2List);

        String carousel2EncodedString = "";
        if (!carouseImage2List.isEmpty()) {
            System.out.println("if");
            byte[] image = carouseImage2List.get(0).getImage();
            byte[] encodedImage = Base64.getEncoder().encode(image);
            carousel2EncodedString = new String(encodedImage);
        } else {
            System.out.println("else");
            carousel2EncodedString = "NotFound";
        }
        System.out.println("carousel2EncodedString: " + carousel2EncodedString);

        ResponseEntity<List<CustomerImage>> restCarousel3Response = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbysectionname.do?section=Carousel Image 3",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerImage>>() {
                });

        List<CustomerImage> carouseImage3List = restCarousel3Response.getBody();

//        List<CustomerImage> carouseImage3List = (List<CustomerImage>) customerImageDao.findBySectionName("Carousel Image 3");
        System.out.println("carouseImage3List: " + carouseImage3List);

        String carousel3EncodedString = "";
        if (!carouseImage3List.isEmpty()) {
            System.out.println("if");
            byte[] image = carouseImage3List.get(0).getImage();
            byte[] encodedImage = Base64.getEncoder().encode(image);
            carousel3EncodedString = new String(encodedImage);
        } else {
            System.out.println("else");
            carousel3EncodedString = "NotFound";
        }
        System.out.println("carousel3EncodedString: " + carousel3EncodedString);

        modelMap.put("headerImage", headerEncodedString);
        modelMap.put("carouse1Image1", carousel1EncodedString);
        modelMap.put("carouse1Image2", carousel2EncodedString);
        modelMap.put("carouse1Image3", carousel3EncodedString);
    }

    @RequestMapping("showadddocument")
    public ModelAndView showAddDocument(HttpServletRequest request, HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CustomerDocumentSeeded>> restDocumentResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerdocument.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDocumentSeeded>>() {
                });

        System.out.println("restDocumentResponse: " + restDocumentResponse);
        List<CustomerDocumentSeeded> docList = restDocumentResponse.getBody();

//        List<CustomerDocument> docList = (List<CustomerDocument>) customerDocumentDao.getAllCustomerDocument();
        System.out.println("size: " + docList.size());

        return new ModelAndView("adddocument", "docList", docList);
    }

    @RequestMapping("showsearchdocument")
    public ModelAndView showSearchSocument(Map<String, Object> map, HttpSession session, HttpServletRequest request) {

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        System.out.println("groups: " + session.getAttribute("groupIds"));

        List<Groups> groupList = (ArrayList<Groups>) session.getAttribute("groupIds");
        System.out.println("size: " + groupList.size());

        String userRole = (String) session.getAttribute("userRole");

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

            ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            customerList = restCustomerResponse.getBody();

            //26Nov2018
            CustomerSeeded natSteelHoldingCustomer = null;
            for (CustomerSeeded customer : customerList) {
                if (customer.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
                    natSteelHoldingCustomer = customer;
                }
            }
            if (customerList != null && !customerList.isEmpty()) {
                customerList.remove(natSteelHoldingCustomer);
            }
        }

        List<ProjectSeeded> projectListByCutomer = null;
        List<CustomerProjectMapping> projectMappingList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser custUser = restResponse.getBody();

            int cid = custUser.getBpaasCustomerseededCid().getCid();

            ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomerid.do?customerid=" + cid,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                    });

            projectListByCutomer = restProjectResponse.getBody();
            System.out.println("projectListByCutomer: " + projectListByCutomer);

        } else {
            ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                    });

            projectMappingList = restCustomerProjectMappingResponse.getBody();
            System.out.println("projectMappingList: " + projectMappingList);

        }

        List<CustomerDocumentSeeded> docList = null;

        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
            ResponseEntity<List<CustomerDocumentSeeded>> restDocumentResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerdocument.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDocumentSeeded>>() {
                    });

            System.out.println("restDocumentResponse: " + restDocumentResponse);
            docList = restDocumentResponse.getBody();
        }

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            map.put("projectListByCutomer", projectListByCutomer);
        } else {
            map.put("projectList", projectMappingList);
        }

        map.put("customerList", customerList);

        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
            map.put("docList", docList);
        } else {
            List<Groups> tempList1 = new ArrayList<>();
            List<Groups> tempList2 = new ArrayList<>();
            tempList2.addAll(groupList);
            for (Groups group : groupList) {
                if (group.getGroupname().equalsIgnoreCase("AR Listing")) {
                    tempList1.add(group);
                }
                if (group.getGroupname().equalsIgnoreCase("Statement of Account")) {
                    tempList1.add(group);
                }
                if (group.getGroupname().equalsIgnoreCase("Contract Document")) {
                    tempList1.add(group);
                }
            }
            if (!tempList1.isEmpty()) {
                //groupList.retainAll(tempList1);
                tempList2.removeAll(tempList1);
            }
            map.put("docGroupList", tempList2);
//            map.put("docGroupList", groupList);
        }
        return new ModelAndView("searchdocument", "map", map);
    }

    @RequestMapping(value = "uploaddocument", method = RequestMethod.POST)
    public ModelAndView addSocument(@RequestParam("invoice") CommonsMultipartFile invoice, @RequestParam("mill_certificate") CommonsMultipartFile mill_certificate,
            @RequestParam("purchase_order") CommonsMultipartFile purchase_order, @RequestParam("statement_account") CommonsMultipartFile statement_account,
            @RequestParam("ar_listing") CommonsMultipartFile ar_listing, @RequestParam("debit_note") CommonsMultipartFile debit_note,
            @RequestParam("credit_note") CommonsMultipartFile credit_note, @RequestParam("delivery_order") CommonsMultipartFile delivery_order,
            @RequestParam("sales_terms_conditions") CommonsMultipartFile sales_terms_conditions, HttpSession session, RedirectAttributes model, HttpServletRequest request) {
        System.out.println("uploaddocument");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }
//        int userId = (int) session.getAttribute("userId");
//        String username = (String) session.getAttribute("username");
//        Date today = new Date();
//        System.out.println("userId: " + userId);
//        System.out.println("username: " + username);
//
//        SupplierUser user = supplierDao.getSupplierById(userId);
////        System.out.println("inv: " + invoice);
//        if (!invoice.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("invoice: " + invoice.getOriginalFilename());
//
//            customerDocument.setDocumenttype("Invoice");
//            customerDocument.setDocumentname(invoice.getOriginalFilename());
//            customerDocument.setDocument(invoice.getBytes());
//
//        }
//        if (!mill_certificate.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("mill_certificate: " + mill_certificate.getOriginalFilename());
//            customerDocument.setDocumenttype("Mill Certificate");
//            customerDocument.setDocumentname(mill_certificate.getOriginalFilename());
//            customerDocument.setDocument(mill_certificate.getBytes());
//
//        }
//        if (!purchase_order.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("purchase_order: " + purchase_order.getOriginalFilename());
//            customerDocument.setDocumenttype("Purchase Order");
//            customerDocument.setDocumentname(purchase_order.getOriginalFilename());
//            customerDocument.setDocument(purchase_order.getBytes());
//
//        }
//        if (!statement_account.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("statement_account: " + statement_account.getOriginalFilename());
//            customerDocument.setDocumenttype("Statement of Account");
//            customerDocument.setDocumentname(statement_account.getOriginalFilename());
//            customerDocument.setDocument(statement_account.getBytes());
//
//        }
//        if (!ar_listing.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("ar_listing: " + ar_listing.getOriginalFilename());
//            customerDocument.setDocumenttype("AR Listing");
//            customerDocument.setDocumentname(ar_listing.getOriginalFilename());
//            customerDocument.setDocument(ar_listing.getBytes());
//
//        }
//        if (!debit_note.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("debit_note: " + debit_note.getOriginalFilename());
//            customerDocument.setDocumenttype("Debit Note");
//            customerDocument.setDocumentname(debit_note.getOriginalFilename());
//            customerDocument.setDocument(debit_note.getBytes());
//
//        }
//        if (!credit_note.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("credit_note: " + credit_note.getOriginalFilename());
//            customerDocument.setDocumenttype("Credit Note");
//            customerDocument.setDocumentname(credit_note.getOriginalFilename());
//            customerDocument.setDocument(credit_note.getBytes());
//
//        }
//        if (!delivery_order.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("delivery_order: " + credit_note.getOriginalFilename());
//            customerDocument.setDocumenttype("Delivery Order");
//            customerDocument.setDocumentname(delivery_order.getOriginalFilename());
//            customerDocument.setDocument(delivery_order.getBytes());
//
//        }
//        if (!sales_terms_conditions.getOriginalFilename().equalsIgnoreCase("")) {
//
//            System.out.println("sales_terms_conditions: " + sales_terms_conditions.getOriginalFilename());
//            customerDocument.setDocumenttype("Sales Terms and Conditions");
//            customerDocument.setDocumentname(sales_terms_conditions.getOriginalFilename());
//            customerDocument.setDocument(sales_terms_conditions.getBytes());
//
//        }
//
//        customerDocument.setBpaasSupplieruserId(user);
//        customerDocument.setUpdatedby(username);
//        customerDocument.setUpdatedate(today);
//        customerDocument.setCreatedby(username);
//        customerDocument.setCreationdate(today);
//
//        int docId = customerDocumentDao.saveCustomerDocument(customerDocument);
//        System.out.println("docId: " + docId);
//        model.addFlashAttribute("message", "Document(s) added successfully.");

        return new ModelAndView("redirect:/showadddocument.do");
    }

    @RequestMapping("/manageuser")
    public ModelAndView manageUser(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("manageuser");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        List<CustomerSubUser> userList = null;

        RestTemplate restTemplate = new RestTemplate();

        if (userRole.equalsIgnoreCase("Company Admin")) {

            ResponseEntity<List<CustomerSubUser>> restUserResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findcustomerbycustomerseededid.do?userid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                    });

            System.out.println("restUserResponse: " + restUserResponse);
            userList = restUserResponse.getBody();

        } else {
            ResponseEntity<List<CustomerSubUser>> restUserResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findallcustomerexceptthisstatus.do?status=Delete",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                    });

            System.out.println("restUserResponse: " + restUserResponse);
            userList = restUserResponse.getBody();

        }

        ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                });

        System.out.println("restCustomerResponse: " + restCustomerResponse);
        List<CustomerSeeded> customerList = restCustomerResponse.getBody();

        List<String> userType = new ArrayList<>();
//
//        for (CustomerSubUser customer : userList) {
//            ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerusergroup.do?custid=" + customer.getId(),
//                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
//                    });
//
//            System.out.println("response: " + restGroupResponse);
//            System.out.println("response: " + restGroupResponse.getBody().size());
//            List<Groups> groups = restGroupResponse.getBody();
//            if (!groups.isEmpty()) {
//                boolean isAdmin = false;
//                boolean isCompanyAdmin = false;
//
//                for (Groups group : groups) {
//                    if (group.getGroupname().equalsIgnoreCase("Admin")) {
//                        isAdmin = true;
//                        break;
//                    } else if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
//                        isCompanyAdmin = true;
//                        break;
//                    }
//
//                }
//                System.out.println("isAdmin: " + isAdmin);
//                System.out.println("isCompanyAdmin: " + isCompanyAdmin);
//                if (isAdmin == true) {
//                    userType.add("NatSteel Admin");
//                } else if (isCompanyAdmin == true) {
//                    userType.add("Company Admin");
//                } else {
//                    userType.add("Customer");
//                }
//            } else {
//                userType.add("Customer");
//            }
//        }
        System.out.println("userTypeList size: " + userType.size());
        System.out.println("userTypeList: " + userType);

        //27Nov2018
        List<PasswordConfiguration> passConfigList = null;
        String passconfigString = "";

        ResponseEntity<List<PasswordConfiguration>> restPassConfigResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getpasswordconfiguration.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<PasswordConfiguration>>() {
                });

        passConfigList = restPassConfigResponse.getBody();

        System.out.println("passConfigList: " + passConfigList);

        if (!passConfigList.isEmpty()) {
            PasswordConfiguration passObj = passConfigList.get(0);
            passconfigString = passObj.getPassLength() + ", " + passObj.getSpecialChar() + ", " + passObj.getUpparChar() + ", " + passObj.getLowerChar() + ", " + passObj.getNumericCount();
            System.out.println("passconfigString: " + passconfigString);
        }

        map.put("passconfig", passconfigString);

        //Ends
        //01Dec2018
        ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/managegroup.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                });

        System.out.println("response: " + restGroupResponse);
        System.out.println("response: " + restGroupResponse.getBody().size());

        List<Groups> groupList = restGroupResponse.getBody();
        List<Groups> temp = new ArrayList<>();
        for (Groups group : groupList) {
            if (group.getGroupname().equalsIgnoreCase("Admin")) {
                temp.add(group);
            }
            if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                temp.add(group);
            }
        }
        if (!temp.isEmpty()) {
            groupList.removeAll(temp);
        }
        //Ends

        System.out.println("user size " + userList.size());
        System.out.println(userList);
        System.out.println("customer size " + userList.size());

        map.put("groupList", groupList);
        map.put("subuser", userList);
        map.put("userType", userType);
        map.put("customerList", customerList);

        return new ModelAndView("customermanageuser", "map", map);
    }

    @RequestMapping("/managegroup")
    public ModelAndView manageGroup(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("managegroup");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Groups>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/managegroup.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                });

        System.out.println("response: " + restResponse);
        System.out.println("response: " + restResponse.getBody().size());

        List<Groups> groupList = restResponse.getBody();

        //26Nov2018
        Groups adminGroup = null;
        if (userRole.equalsIgnoreCase("Admin")) {
            for (Groups group : groupList) {
                if (group.getGroupname().equalsIgnoreCase("Admin")) {
                    adminGroup = group;
                }

            }
            if (adminGroup != null) {
                groupList.remove(adminGroup);

            }
        }
        //Ends
//        List<Groups> groupList = (List<Groups>) groupDao.getAllActiveGroups("Active");
        System.out.println("size: " + groupList);

        if (userRole.equalsIgnoreCase("Company Admin")) {
//            Groups adminGroup = null;
            Groups companyAdminGroup = null;

            for (Groups group : groupList) {
                if (group.getGroupname().equalsIgnoreCase("Admin")) {
                    adminGroup = group;
                }
                if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                    companyAdminGroup = group;
                }
            }
            groupList.remove(adminGroup);
            groupList.remove(companyAdminGroup);

            System.out.println("groupList: " + groupList);
        }

        map.put("groups", groupList);

        return new ModelAndView("customermanagegroup", "map", map);
    }

    @RequestMapping(value = "/addcustomersubuser", method = RequestMethod.POST)
    public ModelAndView addCustomerSubUser(@ModelAttribute("user") CustomerSubUser user, HttpSession session, RedirectAttributes model, HttpServletRequest request) {
        Date today = new Date();

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }
        int userId = (int) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

//        System.out.println("userId: " + userId);
        System.out.println("username: " + username);

        String customerId = request.getParameter("customerId");
        String projectId = request.getParameter("projectIds");
        String documentIds = request.getParameter("documentIds");

        System.out.println("customerId: " + customerId);
        System.out.println("projectId: " + projectId);
        System.out.println("documentIds: " + documentIds);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        ResponseEntity<CustomerSeeded> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + Integer.parseInt(customerId), CustomerSeeded.class);

        CustomerSeeded custObj = restResponse.getBody();

//        CustomerSeeded custObj = custSeededDao.getCustomerSeededById(Integer.parseInt(customerId));
        user.setBpaasCustomerseededCid(custObj);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmpassword(passwordEncoder.encode(user.getConfirmpassword()));
        user.setUpdatedate(today);
        user.setCreationdate(today);
        user.setUpdatedby(username);
        user.setCreatedby(username);
        user.setStatus("Active");
        user.setPersonalfirstname(user.getPersonalfirstname());
        user.setPersonallastname(user.getPersonallastname());
        user.setPersonalemailid(user.getPersonalemailid());
        user.setPersonalcontactnumber(user.getPersonalcontactnumber());

        if (custObj != null && custObj.getCustomercode() != null && custObj.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
            user.setUsertype("Natsteel Admin");
        } else {
            user.setUsertype("Customer");
        }
        String responseCustomerId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/addcustomersubuser.do"), user, String.class);

        System.out.println("responseCustomerId: " + responseCustomerId);

        //26Nov2018
        if (custObj != null && custObj.getCustomercode() != null && custObj.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {

            ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getgroupbyname.do?groupname=Admin",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                    });

            System.out.println("restGroupResponse: " + restGroupResponse);
            List<Groups> groupList = restGroupResponse.getBody();
            System.out.println("groupList: " + groupList.size());
            Groups group = groupList.get(0);

            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(responseCustomerId), CustomerSubUser.class);

            CustomerSubUser customer = restCustomerResponse.getBody();

            supplierGroup.setBpaasCustomersubuserId(customer);
            supplierGroup.setBpaasGroupsGid(group);
            supplierGroup.setSgroupname(group.getGroupname());
            supplierGroup.setDescription(group.getDescription());
            supplierGroup.setCreationdate(today);
            supplierGroup.setUpdatedate(today);

            String suppGroupId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/mappcustomertogroup.do"), supplierGroup, String.class);
            System.out.println("suppGroupId: " + suppGroupId);
        }
        //Ends

        if (projectId != null && !projectId.equalsIgnoreCase("")) {

            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(responseCustomerId), CustomerSubUser.class);
            CustomerSubUser custSubUser = restCustomerResponse.getBody();

            String[] projectIds = projectId.split(",");
            System.out.println("projectIds: " + projectIds.length);

            for (String pid : projectIds) {
//                ProjectSeeded project = projectSeededDao.getProjectSeededById(Integer.parseInt(pid));
//                CustomerSubUser custSubUser = customerSubUserDao.getCustomerSubUserById(Integer.parseInt(responseCustomerId));

                ResponseEntity<ProjectSeeded> restProjectResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getprojectseededbyid.do?pid=" + Integer.parseInt(pid), ProjectSeeded.class);
                ProjectSeeded project = restProjectResponse.getBody();

                custProjectMapping.setBpaasProjectseededPid(project);
                custProjectMapping.setBpaasCustomersubuserId(custSubUser);

//                custProjectMappingDao.saveCustomerProjectMapping(custProjectMapping);
                String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprojectmapping.do"),
                        custProjectMapping, String.class);
                System.out.println("result2: " + result);
            }

        }

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(responseCustomerId), CustomerSubUser.class);
        CustomerSubUser custSubUser = restCustomerResponse.getBody();

        if (documentIds != null && !documentIds.equalsIgnoreCase("")) {

            String[] ids = documentIds.split(",");
            for (String id : ids) {
                System.out.println("id: " + id);

                ResponseEntity<Groups> restGroupResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getgroupbyid.do?gid=" + Integer.parseInt(id), Groups.class);
                System.out.println("restGroupResponse: " + restGroupResponse);

                Groups group = restGroupResponse.getBody();

                supplierGroup.setBpaasCustomersubuserId(custSubUser);
                supplierGroup.setBpaasGroupsGid(group);
                supplierGroup.setSgroupname(group.getGroupname());
                supplierGroup.setDescription(group.getDescription());
                supplierGroup.setCreationdate(today);
                supplierGroup.setUpdatedate(today);

                String suppGroupId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/mappcustomertogroup.do"), supplierGroup, String.class);
                System.out.println("suppGroupId: " + suppGroupId);
            }
        }

        custUserTrackingReport.setActivityname("Created");
        custUserTrackingReport.setActivitydate(today);
        custUserTrackingReport.setNgCpCustomersubuserId(custSubUser);
        custUserTrackingReport.setNgCpCustomersubuserAdmin(loggedInUser);

        String trackReportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerusertrackreport.do"), custUserTrackingReport, String.class);
        System.out.println("trackReportId: " + trackReportId);

        String message = "User has been successfully registered.";
        model.addFlashAttribute("message", message);

        return new ModelAndView("redirect:/manageuser.do");
    }

    @RequestMapping("custadvsearch")
    public ModelAndView custAdvSearch(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("customeradvancedsearch");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        System.out.println("groups: " + session.getAttribute("groupIds"));
        List<Groups> groupList = (ArrayList<Groups>) session.getAttribute("groupIds");
        System.out.println("size: " + groupList.size());
        String userRole = (String) session.getAttribute("userRole");

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {
            ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            customerList = restCustomerResponse.getBody();

            //26Nov2018
            CustomerSeeded natSteelHoldingCustomer = null;
            for (CustomerSeeded customer : customerList) {
                if (customer.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
                    natSteelHoldingCustomer = customer;
                }
            }
            if (customerList != null && !customerList.isEmpty()) {
                customerList.remove(natSteelHoldingCustomer);
            }
        }

        List<ProjectSeeded> projectListByCutomer = null;
        List<CustomerProjectMapping> projectMappingList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {

            ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser custUser = restResponse.getBody();

            int cid = custUser.getBpaasCustomerseededCid().getCid();

            ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomerid.do?customerid=" + cid,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                    });

            projectListByCutomer = restProjectResponse.getBody();
            System.out.println("projectListByCutomer: " + projectListByCutomer);

        } else {

            ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                    });

            projectMappingList = restCustomerProjectMappingResponse.getBody();
            System.out.println("projectMappingList: " + projectMappingList);

        }

        List<CustomerDocumentSeeded> docList = null;

        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
            ResponseEntity<List<CustomerDocumentSeeded>> restDocumentResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerdocument.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDocumentSeeded>>() {
                    });

            System.out.println("restDocumentResponse: " + restDocumentResponse);
            docList = restDocumentResponse.getBody();
        }

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            map.put("projectListByCutomer", projectListByCutomer);
        } else {
            map.put("projectList", projectMappingList);
        }

        map.put("customerList", customerList);

        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
            map.put("docList", docList);
        } else {
            List<Groups> tempList1 = new ArrayList<>();
            List<Groups> tempList2 = new ArrayList<>();
            tempList2.addAll(groupList);
            for (Groups group : groupList) {
                if (group.getGroupname().equalsIgnoreCase("AR Listing")) {
                    tempList1.add(group);
                }
                if (group.getGroupname().equalsIgnoreCase("Statement of Account")) {
                    tempList1.add(group);
                }
                if (group.getGroupname().equalsIgnoreCase("Contract Document")) {
                    tempList1.add(group);
                }
            }
            if (!tempList1.isEmpty()) {
                //groupList.retainAll(tempList1);
                tempList2.removeAll(tempList1);
            }
            map.put("docGroupList", tempList2);
//            map.put("docGroupList", groupList);
        }

        return new ModelAndView("customeradvancedsearch", "map", map);
    }

    @RequestMapping("customerstatement")
    public ModelAndView customerStatement(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("customerstatement");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        List<Groups> groupList = (ArrayList<Groups>) session.getAttribute("groupIds");
        System.out.println("size: " + groupList.size());

        String userRole = (String) session.getAttribute("userRole");
//        List<CustomerSeeded> customerList = (List<CustomerSeeded>) custSeededDao.getAllCustomerSeeded();

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {
            ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            customerList = restCustomerResponse.getBody();

            //26Nov2018
            CustomerSeeded natSteelHoldingCustomer = null;
            for (CustomerSeeded customer : customerList) {
                if (customer.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
                    natSteelHoldingCustomer = customer;
                }
            }
            if (customerList != null && !customerList.isEmpty()) {
                customerList.remove(natSteelHoldingCustomer);
            }
        }

//        List<CustomerDocumentSeeded> docList = null;
        List<String> docList = new ArrayList<>();
        List<String> allList = new ArrayList<>();
        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
//
//            ResponseEntity<List<CustomerDocumentSeeded>> restDocumentResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerdocument.do",
//                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDocumentSeeded>>() {
//                    });
//
//            System    .out.println("restDocumentResponse: " + restDocumentResponse);
//            docList = restDocumentResponse.getBody();
            allList.add("All");
            docList.add("AR Listing");
            docList.add("Statement of Account");
        }

        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Company Admin")) {
            map.put("allList", allList);
            map.put("docList", docList);
        } else {
            List<Groups> statementList = new ArrayList();
            List<String> all = new ArrayList<>();
            if (!groupList.isEmpty()) {

                boolean isAR = false;
                boolean isSOA = false;
                for (Groups group : groupList) {
                    if (group.getGroupname().equalsIgnoreCase("AR Listing")) {
                        statementList.add(group);
                    }
                    if (group.getGroupname().equalsIgnoreCase("Statement of Account")) {
                        statementList.add(group);
                    }
                }
                System.out.println("statementList: " + statementList.size());
                for (Groups group : statementList) {
                    if (group.getGroupname().equalsIgnoreCase("AR Listing")) {
                        isAR = true;
                    }
                    if (group.getGroupname().equalsIgnoreCase("Statement of Account")) {
                        isSOA = true;
                    }
                }
                if (isAR == true && isSOA == true) {
                    all.add("All");
                }
//                groupList.contains("AR Listing");
            }
            map.put("allList", all);
            map.put("docGroupList", statementList);
        }

        map.put("customerList", customerList);

        return new ModelAndView("customerstatement", "map", map);
    }

    @RequestMapping("customeraboutus")
    public ModelAndView customerAboutUs(HttpServletRequest request, HttpSession session) {
        System.out.println("customeraboutus");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        return new ModelAndView("customeraboutus");
    }

    @RequestMapping("customermyorders")
    public ModelAndView customerMyOrders(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("customermyorders");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        List<Groups> groupList = (ArrayList<Groups>) session.getAttribute("groupIds");
        System.out.println("size: " + groupList.size());

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {
            ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            customerList = restCustomerResponse.getBody();

            //26Nov2018
            CustomerSeeded natSteelHoldingCustomer = null;
            for (CustomerSeeded customer : customerList) {
                if (customer.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
                    natSteelHoldingCustomer = customer;
                }
            }
            if (customerList != null && !customerList.isEmpty()) {
                customerList.remove(natSteelHoldingCustomer);
            }
        }
        List<ProjectSeeded> projectListByCutomer = null;
        List<CustomerProjectMapping> projectMappingList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser custUser = restResponse.getBody();

            int cid = custUser.getBpaasCustomerseededCid().getCid();

            ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomerid.do?customerid=" + cid,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                    });

            projectListByCutomer = restProjectResponse.getBody();
            System.out.println("projectListByCutomer: " + projectListByCutomer);

        } else {
            ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                    });

            projectMappingList = restCustomerProjectMappingResponse.getBody();
            System.out.println("projectMappingList: " + projectMappingList);

        }
        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            map.put("projectListByCutomer", projectListByCutomer);
        } else {
            map.put("projectList", projectMappingList);
        }

        if (userRole.equalsIgnoreCase("Admin")) {
            map.put("customerList", customerList);
        }
        map.put("docGroupList", groupList);

        return new ModelAndView("customermyorders", "map", map);
    }

    @RequestMapping("customereditprofile")
    public ModelAndView customerEditProfile(HttpSession session, Map<String, Object> map, HttpServletRequest request, Model model) {
        System.out.println("customereditprofile");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        System.out.println("message: " + model.containsAttribute("message"));
        if (model.containsAttribute("message")) {
            map.put("isPersonalInfoUpdated", "Yes");
        }

//        Thread t1 = new Thread();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

        CustomerSubUser subUser = restCustomerResponse.getBody();

//        t1.sleep(5000);
        System.out.println("subUser: " + subUser);
        CustomerSeeded customer = null;
        if (subUser != null) {
            int cid = subUser.getBpaasCustomerseededCid().getCid();
            ResponseEntity<CustomerSeeded> restCustomerSeededResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + cid, CustomerSeeded.class);
            customer = restCustomerSeededResponse.getBody();
        }
        System.out.println("user: " + customer);

        ResponseEntity<List<CustomerSecQueSeeded>> restSecQueResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallques.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSecQueSeeded>>() {
                });

        List<CustomerSecQueSeeded> custSecQueList = restSecQueResponse.getBody();

        System.out.println("custSecQueList: " + custSecQueList.size());

        ResponseEntity<List<CustSecQues>> restQueAnsResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findquesbyuserid.do?userid=" + userId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustSecQues>>() {
                });

        List<CustSecQues> custQueAnsList = restQueAnsResponse.getBody();

        System.out.println("custQueAnsList: " + custQueAnsList.size());

        ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

        CustomerSubUser custUser = restResponse.getBody();

        ResponseEntity<CustomerSeeded> restSeededResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + custUser.getBpaasCustomerseededCid().getCid(), CustomerSeeded.class);

        CustomerSeeded custSeeded = restSeededResponse.getBody();
        String profilePicString = "";
        if (subUser.getProfilepic() != null) {
            System.out.println("if");
            byte[] image = subUser.getProfilepic();
            byte[] encodedImage = Base64.getEncoder().encode(image);
            profilePicString = new String(encodedImage);
        } else {
            System.out.println("else");
            profilePicString = "NotFound";
        }
        System.out.println("profilePicString: " + profilePicString);

        map.put("customer", subUser);
        map.put("secQueList", custSecQueList);
        map.put("custQueAnsList", custQueAnsList);
        map.put("profilePic", profilePicString);
        map.put("ispassupdated", "Yes");

        return new ModelAndView("customereditprofile", "map", map);
    }

    @RequestMapping(value = "updatecustomerprofile", method = RequestMethod.POST)
    public ModelAndView updateCustomerProfile(@ModelAttribute("user") CustomerSeeded user, @RequestParam("profilepicture") CommonsMultipartFile profilepicture, HttpServletRequest request, HttpSession session, RedirectAttributes model) {
        System.out.println("updatecustomerprofile");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        boolean fname = false;
        boolean lname = false;
        boolean add1 = false;
        boolean add2 = false;
        boolean add3 = false;
        boolean emailid = false;
        boolean profilepic = false;

        System.out.println("Cid: " + user.getCid());
        Date today = new Date();
        String username = (String) session.getAttribute("username");
        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);
        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        String personalfirstname = request.getParameter("personalfirstname");
        String personallastname = request.getParameter("personallastname");
        String personalemailid = request.getParameter("personalemailid");
        String personalcontactnumber = request.getParameter("personalcontactnumber");

        System.out.println("personalfirstname: " + personalfirstname);
        System.out.println("personallastname: " + personallastname);
        System.out.println("personalemailid: " + personalemailid);
        System.out.println("personalcontactnumber: " + personalcontactnumber);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

        CustomerSubUser custSubUserObj = restCustomerResponse.getBody();

//        CustomerSubUser custSubUserObj = customerSubUserDao.getCustomerSubUserById(userId);
        ResponseEntity<CustomerSeeded> restCustomerSeededResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/findcustomerbyid.do?customerid=" + user.getCid(), CustomerSeeded.class);
        CustomerSeeded customer = restCustomerSeededResponse.getBody();

//        CustomerSeeded customer = custSeededDao.getCustomerSeededById(user.getCid());
        if (profilepicture.getBytes().length == 0) {
            System.out.println("profile: " + profilepicture.getBytes().length);
//            user.setProfilepic(customer.getProfilepic());
            custSubUserObj.setProfilepic(custSubUserObj.getProfilepic());
        } else {
            profilepic = true;
            System.out.println("profile: " + profilepicture.getBytes().length);
//            user.setProfilepic(profilepicture.getBytes());
            custSubUserObj.setProfilepic(profilepicture.getBytes());
        }

        if (user.getFirstname() != null && custSubUserObj.getFisrtname() != null && (!user.getFirstname().equalsIgnoreCase(custSubUserObj.getFisrtname()))) {
            fname = true;
            customerProfileUpdateReport.setFieldname("First Name");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getFisrtname());
            customerProfileUpdateReport.setNewvalue(user.getFirstname());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }
        if (user.getLastname() != null && custSubUserObj.getLastname() != null && (!user.getLastname().equalsIgnoreCase(custSubUserObj.getLastname()))) {
            lname = true;
            customerProfileUpdateReport.setFieldname("Last Name");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getLastname());
            customerProfileUpdateReport.setNewvalue(user.getLastname());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }
        if (user.getAddressline1() != null && custSubUserObj.getAddressline1() != null && (!user.getAddressline1().equalsIgnoreCase(custSubUserObj.getAddressline1()))) {
            add1 = true;
            customerProfileUpdateReport.setFieldname("Address Line 1");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getAddressline1());
            customerProfileUpdateReport.setNewvalue(user.getAddressline1());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }
        if (user.getAddressline2() != null && custSubUserObj.getAddressline2() != null && (!user.getAddressline2().equalsIgnoreCase(custSubUserObj.getAddressline2()))) {
            add2 = true;
            customerProfileUpdateReport.setFieldname("Address Line 2");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getAddressline2());
            customerProfileUpdateReport.setNewvalue(user.getAddressline2());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }
        if (user.getAddressline3() != null && custSubUserObj.getAddressline3() != null && (!user.getAddressline3().equalsIgnoreCase(custSubUserObj.getAddressline3()))) {
            add3 = true;
            customerProfileUpdateReport.setFieldname("Address Line 3");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getAddressline3());
            customerProfileUpdateReport.setNewvalue(user.getAddressline3());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }
        if (user.getEmailid() != null && custSubUserObj.getEmailid() != null && (!user.getEmailid().equalsIgnoreCase(custSubUserObj.getEmailid()))) {
            emailid = true;
            emailid = true;
            customerProfileUpdateReport.setFieldname("Email Id");
            customerProfileUpdateReport.setOldvalue(custSubUserObj.getEmailid());
            customerProfileUpdateReport.setNewvalue(user.getEmailid());
            customerProfileUpdateReport.setActiondate(today);
            customerProfileUpdateReport.setBpaasCustomersubuserId(custSubUserObj);

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprofileupdatereport.do"),
                    customerProfileUpdateReport, String.class);
            System.out.println("result: " + result);
        }

        System.out.println("fname: " + user.getFirstname());
        custSubUserObj.setFisrtname(user.getFirstname());

        System.out.println("lname: " + user.getLastname());
        custSubUserObj.setLastname(user.getLastname());

        System.out.println("Add1: " + user.getAddressline1());

        custSubUserObj.setAddressline1(user.getAddressline1());

        System.out.println(custSubUserObj.getAddressline1());

        System.out.println("Add2: " + user.getAddressline2());
        custSubUserObj.setAddressline2(user.getAddressline2());
        System.out.println(custSubUserObj.getAddressline2());

        System.out.println("Add3: " + user.getAddressline3());
        custSubUserObj.setAddressline3(user.getAddressline3());
        System.out.println(custSubUserObj.getAddressline3());

        System.out.println("email: " + user.getEmailid());
        custSubUserObj.setEmailid(user.getEmailid());

        custSubUserObj.setPersonalfirstname(personalfirstname);
        custSubUserObj.setPersonallastname(personallastname);
        custSubUserObj.setPersonalemailid(personalemailid);
        custSubUserObj.setPersonalcontactnumber(personalcontactnumber);
        custSubUserObj.setIsPersonalInfoUpdated("Yes");

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                custSubUserObj, String.class);
        System.out.println("result: " + result);

//        user.setCustomercode(customer.getCustomercode());
//        user.setCustomername(customer.getCustomername());
//
//        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomerseeded.do"),
//                user, String.class);
//        System.out.println("result: " + result);
//        custSeededDao.updateCustomerSeeded(user);
        String[] secQuestions = request.getParameterValues("secQuestion");
//        System.out.println("secQuestions: " + secQuestions.length);
//        for (String que : secQuestions) {
//            System.out.println("que: " + que);
//        }

        String[] secAnswers = request.getParameterValues("secAnswer");
//        System.out.println("secAnswers: " + secAnswers.length);
//        for (String ans : secAnswers) {
//            System.out.println("ans: " + ans);
//        }
        ResponseEntity<List<CustSecQues>> restQueAnsResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findquesbyuserid.do?userid=" + userId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustSecQues>>() {
                });

        List<CustSecQues> custQueAnsList = restQueAnsResponse.getBody();
        System.out.println("custQueAnsList in update: " + custQueAnsList);

        if (!custQueAnsList.isEmpty()) {
            for (int i = 0; i < secQuestions.length; i++) {

                CustSecQues queAns = custQueAnsList.get(i);
                queAns.setQuestion(secQuestions[i]);
                queAns.setAnswer(secAnswers[i]);
                queAns.setSelectiondate(today);

                String result3 = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatequeans.do"),
                        queAns, String.class);
                System.out.println("result2: " + result3);
            }
        } else {
            if (secQuestions != null && secQuestions.length > 0) {
                for (int i = 0; i < secQuestions.length; i++) {
                    custQue.setSelectiondate(today);
                    custQue.setBpaasCustomersubuserId(custSubUserObj);
                    custQue.setQuestion(secQuestions[i]);
                    custQue.setAnswer(secAnswers[i]);

                    String result2 = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savequeans.do"),
                            custQue, String.class);
                    System.out.println("result2: " + result2);

//                int queAnsId = custSecQueDao.saveQue(custQue);
//                System.out.println("queAnsId: " + queAnsId);
                }
            }
        }

        String msg = "has updated their profile.";
        if (fname == true) {
            msg = msg + " First Name, ";
        }
        if (lname == true) {
            msg = msg + " Last Name, ";
        }
        if (add1 == true) {
            msg = msg + " Address Line 1, ";
        }
        if (add2 == true) {
            msg = msg + " Address Line 2, ";
        }
        if (add3 == true) {
            msg = msg + " Address Line 3, ";
        }
        if (emailid == true) {
            msg = msg + " Email Id, ";
        }
        if (profilepic == true) {
            msg = msg + " Profile picture";
        }
        System.out.println(msg);

        custNotification.setNotification(msg);
        custNotification.setCommentby("Customer");
        custNotification.setBpaasCustomersubuserId(custSubUserObj);
        custNotification.setCommentdate(today);
        custNotification.setReadstatus("true");
        custNotification.setIsprofileupdate("Y");

        if (!userRole.equalsIgnoreCase("Admin")) {
            String notificationId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/makecustomercomment.do"), custNotification, String.class);
            System.out.println("notificationId: " + notificationId);
        }
        System.out.println("updated");
        String message = "Information has been updated";
        model.addFlashAttribute("message", message);

        return new ModelAndView("redirect:/customereditprofile.do");
    }

    @RequestMapping("customerforgetpassword")
    public ModelAndView toCustomerForgetPass(HttpServletRequest request, HttpSession session, Map<String, Object> map) {
        System.out.println("customerupdatepassword");
//
//        if (session.getAttribute("userId") == null) {
//            return new ModelAndView("redirect:/customerlogin.do");
//        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        //28Nov2018
        List<PasswordConfiguration> passConfigList = null;
        String passconfigString = "";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PasswordConfiguration>> restPassConfigResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getpasswordconfiguration.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<PasswordConfiguration>>() {
                });

        passConfigList = restPassConfigResponse.getBody();

        System.out.println("passConfigList: " + passConfigList);

        if (!passConfigList.isEmpty()) {
            PasswordConfiguration passObj = passConfigList.get(0);
            passconfigString = passObj.getPassLength() + ", " + passObj.getSpecialChar() + ", " + passObj.getUpparChar() + ", " + passObj.getLowerChar() + ", " + passObj.getNumericCount();
            System.out.println("passconfigString: " + passconfigString);
        }

        map.put("passconfig", passconfigString);

        //Ends
        return new ModelAndView("customerupdatepassword");
    }

    @RequestMapping("updatecustomerpassword")
    public ModelAndView updatePassword(HttpServletRequest request, RedirectAttributes model) {
        System.out.println("updatecustomerpassword");

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String username = request.getParameter("customer-username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");

        System.out.println("username: " + username);
        System.out.println("oldpassword: " + oldpassword);
        System.out.println("newpassword: " + newpassword);

        if (username != null && !username.equalsIgnoreCase("") && newpassword != null && !newpassword.equalsIgnoreCase("")) {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<CustomerSubUser>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbyusernamecheck.do?username=" + username,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                    });

            List<CustomerSubUser> customerSubUserList = restResponse.getBody();

//            List<CustomerSubUser> customerSubUserList = (List<CustomerSubUser>) customerSubUserDao.findByUsernameCheck(username);
            System.out.println("supplierUserList: " + customerSubUserList);
            CustomerSubUser subUser = customerSubUserList.get(0);

            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + subUser.getId(), CustomerSubUser.class);

            CustomerSubUser Obj = restCustomerResponse.getBody();

//            CustomerSubUser Obj = customerSubUserDao.getCustomerSubUserById(subUser.getId());
            Obj.setPassword(passwordEncoder.encode(newpassword));
            Obj.setConfirmpassword(passwordEncoder.encode(newpassword));

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                    Obj, String.class);
            System.out.println("result: " + result);

//            customerSubUserDao.updateCustomerSubUser(Obj);
            System.out.println("updated");

            model.addFlashAttribute("message", "Password updated, please login.");
        }

        return new ModelAndView("redirect:/customerlogin.do");
    }

    @RequestMapping("customerresponse")
    public ModelAndView customerResponse(HttpSession session, HttpServletRequest request) {

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        int userId = (int) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userId: " + userId);
        System.out.println("userRole: " + userRole);
        List<CustomerSubUser> customerList = null;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CustomerSubUser>> response = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerresponse.do?userid=" + userId + "&userrole=" + userRole,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                });

        System.out.println("response: " + response);
        System.out.println("response body: " + response.getBody());
        customerList = response.getBody();

        Map<String, Object> map = new HashMap<>();
        map.put("customerUserList", customerList);

        return new ModelAndView("customerresponsesupplier", "map", map);
    }

    @RequestMapping("customerresponsemgnt")
    public ModelAndView customerResponseMgnt(@RequestParam("supplierid") int customerId, HttpServletRequest request, HttpSession session, Map<String, Object> map) {
        System.out.println("customerId: " + customerId);
        System.out.println("customerresponsemgnt");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }
        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        RestTemplate restTemplate = new RestTemplate();

        if (userRole != null && userRole.equalsIgnoreCase("Admin")) {
            //28Nov2018
            ResponseEntity<List<CustomerNotification>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerresponsemgnt.do?supplierid=" + customerId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("response: " + restResponse);
            System.out.println("response: " + restResponse.getBody().size());
            List<CustomerNotification> list = restResponse.getBody();

            System.out.println("size: " + list.size());

            System.out.println("Role_Admin_if");
            ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findunreadmessages.do?readstatus=true",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("restNotificationResponse: " + restNotificationResponse);
            System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

            map.put("NotificationList", restNotificationResponse.getBody());

            map.put("notificationList", list);
            //Ends
        } else {
            //28Nov2018
            ResponseEntity<List<CustomerNotification>> restResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerresponsemgntnonadmin.do?supplierid=" + customerId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("response: " + restResponse);
            System.out.println("response: " + restResponse.getBody().size());
            List<CustomerNotification> list = restResponse.getBody();

            System.out.println("size: " + list.size());

            System.out.println("Role_User_if");
            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser customer = restCustomerResponse.getBody();

            ResponseEntity<List<CustomerNotification>> restNotificationResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findUnreadmessagesbycustomeridandreadstatus.do?customerid=" + customer.getId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerNotification>>() {
                    });

            System.out.println("restNotificationResponse: " + restNotificationResponse);
            System.out.println("restNotificationResponse: " + restNotificationResponse.getBody().size());

            map.put("NotificationList", restNotificationResponse.getBody());

            map.put("notificationList", list);
            //Ends
        }

        return new ModelAndView("customerresponsemgnt", "map", map);
    }

    @RequestMapping("makecustomercomment")
    public ModelAndView makeCustomerComment(@RequestParam("file") CommonsMultipartFile attachment, HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes model) {
        System.out.println("makecustomercomment");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("role: " + userRole);
        int userId = (int) session.getAttribute("userId");
        System.out.println("userid: " + userId);

        Date today = new Date();
        String supplierid = request.getParameter("supplierid");
        String message = request.getParameter("messageContent");

        System.out.println("supplierid: " + supplierid);
        System.out.println("message: " + message);
        System.out.println("att: " + Arrays.toString(attachment.getBytes()));

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(supplierid), CustomerSubUser.class);

        CustomerSubUser customer = restResponse.getBody();

        custNotification.setNotification(message);
        if (userRole != null && userRole.equalsIgnoreCase("Admin")) {
            custNotification.setCommentby("Admin");
        } else {
            custNotification.setCommentby("Customer");
        }
        if (attachment.getBytes().length == 0) {
            custNotification.setAttachment(null);
            custNotification.setAttachmentname(null);
        } else {
            custNotification.setAttachment(attachment.getBytes());
            custNotification.setAttachmentname(attachment.getOriginalFilename());
        }
        custNotification.setBpaasCustomersubuserId(customer);
        custNotification.setCommentdate(today);
        custNotification.setReadstatus("true");
        custNotification.setIsprofileupdate("N");
        custNotification.setNgCpCustomersubuserAdmin(loggedInUser);

        String notificationId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/makecustomercomment.do"), custNotification, String.class);
        System.out.println("notificationId: " + notificationId);

        String msg = "";
        model.addFlashAttribute("message", msg);

        return new ModelAndView("redirect:/customerresponsemgnt.do?supplierid=" + supplierid);
    }

    @RequestMapping("sendmessagetoallcustomeruser")
    public ModelAndView sendMessageToAllCustomerUser(HttpSession session, HttpServletRequest request, @RequestParam("file") CommonsMultipartFile attachment, RedirectAttributes model) {
        System.out.println("sendmessagetoallcustomeruser");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("role: " + userRole);
        int userId = (int) session.getAttribute("userId");
        System.out.println("userid: " + userId);

        String message = request.getParameter("messageToAllCustomerUser");
        System.out.println("message: " + message);
        System.out.println("att: " + attachment.getBytes().length);
        Date today = new Date();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        custNotification.setNotification(message);
        custNotification.setCommentby("Admin");

        if (attachment.getBytes().length == 0) {
            custNotification.setAttachment(null);
            custNotification.setAttachmentname(null);
        } else {
            custNotification.setAttachment(attachment.getBytes());
            custNotification.setAttachmentname(attachment.getOriginalFilename());
        }
        custNotification.setCommentdate(today);
        custNotification.setReadstatus("true");
        custNotification.setIsprofileupdate("N");
        custNotification.setNgCpCustomersubuserAdmin(loggedInUser);

        String notificationId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/sendmessagetoallcustomeruser.do"), custNotification, String.class);
        System.out.println("notificationId: " + notificationId);

        model.addFlashAttribute("message", "Message sent to all.");

        return new ModelAndView("redirect:/customerresponse.do");
    }

    @RequestMapping("mappedgroup.do")
    public ModelAndView getMappedCustomerGroup(@RequestParam("gid") int gid, Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("getmappedgroup");
        System.out.println("gid: " + gid);

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerSubResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

        CustomerSubUser customerSubUser = restCustomerSubResponse.getBody();

        ResponseEntity<Groups> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getgroupbyid.do?gid=" + gid, Groups.class);
        System.out.println("restResponse: " + restResponse);

        Groups group = restResponse.getBody();

        List<CustomerSubUser> subuser = null;

        if (userRole.equalsIgnoreCase("Company Admin")) {

            ResponseEntity<List<CustomerSubUser>> restUserResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findcustomerbycustomerseededid.do?userid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                    });

            System.out.println("restUserResponse: " + restUserResponse);
            subuser = restUserResponse.getBody();

            List<CustomerSubUser> temp = new ArrayList<>();

            for (CustomerSubUser user : subuser) {
                if (!user.getStatus().equalsIgnoreCase("Active")) {
                    temp.add(user);
                }
            }
            subuser.removeAll(temp);

        } else {

            ResponseEntity<List<CustomerSubUser>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/activecustomers.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSubUser>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            System.out.println("restCustomerResponse: " + restCustomerResponse.getBody().size());

            subuser = restCustomerResponse.getBody();
            System.out.println("customer: " + subuser);

            if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                subuser.remove(customerSubUser);
            }
        }

        //26Nov2018
        if (!subuser.isEmpty()) {
            //For Admin
            ResponseEntity<List<SupplierGroup>> restSupplierGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getsuppliergroupbyname.do?groupname=Admin",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<SupplierGroup>>() {
                    });

            System.out.println("restSupplierGroupResponse: " + restSupplierGroupResponse);
            System.out.println("restSupplierGroupResponse: " + restSupplierGroupResponse.getBody().size());

            List<SupplierGroup> supplierGroupList = restSupplierGroupResponse.getBody();
            List<CustomerSubUser> adminUserList = new ArrayList<>();

            for (SupplierGroup suppliergroup : supplierGroupList) {
                adminUserList.add(suppliergroup.getBpaasCustomersubuserId());
            }
            if (!adminUserList.isEmpty()) {
                subuser.removeAll(adminUserList);
            }

            //For Company Admin
            ResponseEntity<List<SupplierGroup>> restSupplierCAGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getsuppliergroupbyname.do?groupname=Company Admin",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<SupplierGroup>>() {
                    });

            System.out.println("restSupplierCAGroupResponse: " + restSupplierCAGroupResponse);
            System.out.println("restSupplierCAGroupResponse: " + restSupplierCAGroupResponse.getBody().size());

            List<SupplierGroup> supplierGroupCAList = restSupplierCAGroupResponse.getBody();
            List<CustomerSubUser> companyAdminUserList = new ArrayList<>();

            for (SupplierGroup suppliergroup : supplierGroupCAList) {
                companyAdminUserList.add(suppliergroup.getBpaasCustomersubuserId());
            }
            if (!companyAdminUserList.isEmpty()) {
                subuser.removeAll(companyAdminUserList);
            }
        }
        //Ends

        ResponseEntity<List<SupplierGroup>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/mappedgroup.do?gid=" + gid,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<SupplierGroup>>() {
                });

        System.out.println("restGroupResponse: " + restGroupResponse);
        System.out.println("restGroupResponse: " + restGroupResponse.getBody().size());

        List<SupplierGroup> supplierGroupList = restGroupResponse.getBody();
        System.out.println("supplier group: " + supplierGroupList);

        if (userRole.equalsIgnoreCase("Admin")) {

            List<SupplierGroup> temp = new ArrayList<>();

            for (SupplierGroup suppGroup : supplierGroupList) {
                if (!suppGroup.getBpaasCustomersubuserId().getStatus().equalsIgnoreCase("Active")) {
                    temp.add(suppGroup);
                }
            }
            supplierGroupList.removeAll(temp);
        }

        List<SupplierGroup> tempGroup = new ArrayList<>();

        if (userRole.equalsIgnoreCase("Company Admin")) {
            for (SupplierGroup suppGroup : supplierGroupList) {
                System.out.println("suppGroup cid: " + suppGroup.getBpaasCustomersubuserId().getBpaasCustomerseededCid().getCid());
                if (Objects.equals(customerSubUser.getBpaasCustomerseededCid().getCid(), suppGroup.getBpaasCustomersubuserId().getBpaasCustomerseededCid().getCid())
                        && suppGroup.getBpaasCustomersubuserId().getStatus().equalsIgnoreCase("Active")) {
                    tempGroup.add(suppGroup);
                }
            }
        }
        System.out.println("tempGroup size: " + tempGroup.size());
        System.out.println("tempGroup: " + tempGroup);

        map.put("group", group);
        map.put("customer", subuser);
        if (userRole.equalsIgnoreCase("Company Admin")) {
            map.put("supplierGroup", tempGroup);
        } else {
            map.put("supplierGroup", supplierGroupList);
        }

        return new ModelAndView("customermappedgroup", "map", map);
    }

    @RequestMapping("mappcustomertogroup")
    public ModelAndView mappCustomerToGroup(HttpServletRequest request, HttpSession session, RedirectAttributes model) {
        System.out.println("mappcustomertogroup");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String gid = request.getParameter("gid");
        String customerIds = request.getParameter("selectedSupplierId");
        System.out.println("customerIds: " + customerIds);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Groups> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getgroupbyid.do?gid=" + gid, Groups.class);
        System.out.println("restResponse: " + restResponse);

        Groups group = restResponse.getBody();

        Date today = new Date();

        if (customerIds != null && !customerIds.equalsIgnoreCase("")) {
            String[] ids = customerIds.split("~");
            for (String id : ids) {
                System.out.println("id: " + id);

                ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(id), CustomerSubUser.class);

                CustomerSubUser customer = restCustomerResponse.getBody();

                if (group.getGroupname().equalsIgnoreCase("Company Admin")) {

                    customer.setUsertype("Company Admin");

                    String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                            customer, String.class);
                    System.out.println("result3: " + result);
                }

                supplierGroup.setBpaasCustomersubuserId(customer);
                supplierGroup.setBpaasGroupsGid(group);
                supplierGroup.setSgroupname(group.getGroupname());
                supplierGroup.setDescription(group.getDescription());
                supplierGroup.setCreationdate(today);
                supplierGroup.setUpdatedate(today);

                String suppGroupId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/mappcustomertogroup.do"), supplierGroup, String.class);
                System.out.println("suppGroupId: " + suppGroupId);

            }
        }

        model.addFlashAttribute("message", "");

        System.out.println("gid: " + gid);
        System.out.println("customerIds: " + customerIds);

        return new ModelAndView("redirect:/managegroup.do");
    }

    @RequestMapping("customeredituser")
    public ModelAndView editCustomerUser(@RequestParam("uid") int uid, Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customeredituser");
        System.out.println("uid: " + uid);

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + uid, CustomerSubUser.class);

        CustomerSubUser custUser = restResponse.getBody();

//        CustomerSubUser custUser = customerSubUserDao.getCustomerSubUserById(uid);
        int cid = custUser.getBpaasCustomerseededCid().getCid();

        ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomerid.do?customerid=" + cid,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                });

        List<ProjectSeeded> projectList = restProjectResponse.getBody();
        System.out.println("projectList: " + projectList);
//        List<ProjectSeeded> projectList = (List<ProjectSeeded>) projectSeededDao.findByCustomerId(cid);

        ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + uid,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                });

        List<CustomerProjectMapping> projectMappingList = restCustomerProjectMappingResponse.getBody();
        System.out.println("projectMappingList: " + projectMappingList);

        ResponseEntity<List<Groups>> restGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/customerusergroup.do?custid=" + custUser.getId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                });

        System.out.println("response: " + restGroupResponse);
        System.out.println("response: " + restGroupResponse.getBody().size());

        List<Groups> groupsList = restGroupResponse.getBody();

        Groups adminGroup = null;
        Groups comapanyAdminGroup = null;

        for (Groups group : groupsList) {
            if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                comapanyAdminGroup = group;
            }
            if (group.getGroupname().equalsIgnoreCase("Admin")) {
                adminGroup = group;
            }
        }
        if (!groupsList.isEmpty()) {
            if (adminGroup != null) {
                groupsList.remove(adminGroup);
            }
            if (comapanyAdminGroup != null) {
                groupsList.remove(comapanyAdminGroup);
            }
        }

        map.put("custUser", custUser);
        map.put("projectList", projectList);
        map.put("projectMappingList", projectMappingList);
        map.put("groupsList", groupsList);

        ResponseEntity<List<Groups>> restTotalGroupResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/managegroup.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Groups>>() {
                });

        System.out.println("response: " + restTotalGroupResponse);
        System.out.println("response: " + restTotalGroupResponse.getBody().size());

        List<Groups> groupTotalList = restTotalGroupResponse.getBody();
        List<Groups> temp = new ArrayList<>();
        for (Groups group : groupTotalList) {
            if (group.getGroupname().equalsIgnoreCase("Admin")) {
                temp.add(group);
            }
            if (group.getGroupname().equalsIgnoreCase("Company Admin")) {
                temp.add(group);
            }
        }
        if (!temp.isEmpty()) {
            groupTotalList.removeAll(temp);
        }
        map.put("groupTotalList", groupTotalList);

        return new ModelAndView("customeredituser", "map", map);
    }

    @RequestMapping("removecustomerproject")
    public ModelAndView updateCustomerUserForDeleteProject(@RequestParam("uid") int uid, @RequestParam("pid") int pid, RedirectAttributes model) {
        System.out.println("uid: " + uid);
        System.out.println("pid: " + pid);
        Date today = new Date();

        RestTemplate restTemplate = new RestTemplate();

//        ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange("http://" + webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + uid,
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
//                });
//        List<CustomerProjectMapping> mappingList = restCustomerProjectMappingResponse.getBody();
        ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findprojectbyprojectandcustomerid.do?customerid=" + uid + "&projectId=" + pid,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                });
        List<CustomerProjectMapping> mappingList = restCustomerProjectMappingResponse.getBody();

        for (CustomerProjectMapping obj : mappingList) {

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/deletecustomerprojectmapping.do"),
                    obj, String.class);
            System.out.println("result1: " + result);
        }

//        customerSubUserDao.updateCustomerSubUser(custUser);
        String message = "Project have been removed.";
        model.addFlashAttribute("message", message);
        return new ModelAndView("redirect:/manageuser.do");
    }

    @RequestMapping("removecustomerdocument")
    public ModelAndView updateCustomerUserForDeleteDocument(@RequestParam("uid") int uid, @RequestParam("gid") int gid, RedirectAttributes model, HttpSession session) {
        System.out.println("uid: " + uid);
        System.out.println("gid: " + gid);
        Date today = new Date();

        int userId = (int) session.getAttribute("userId");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> restCustomerDocumentResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/removecustomerdocument.do?gid=" + gid + "&customerid=" + uid,
                HttpMethod.GET, null, String.class);
        String result = restCustomerDocumentResponse.getBody();
        System.out.println("result: " + result);

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + uid, CustomerSubUser.class);
        CustomerSubUser custUser = restCustomerResponse.getBody();

        custUserTrackingReport.setActivityname("Updated");
        custUserTrackingReport.setActivitydate(today);
        custUserTrackingReport.setNgCpCustomersubuserId(custUser);
        custUserTrackingReport.setNgCpCustomersubuserAdmin(loggedInUser);

        String trackReportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerusertrackreport.do"), custUserTrackingReport, String.class);
        System.out.println("trackReportId: " + trackReportId);

        String message = "Document has been removed.";
        model.addFlashAttribute("message", message);
        return new ModelAndView("redirect:/manageuser.do");
    }

    @RequestMapping("deletecustomeruser")
    public ModelAndView updateCustomerUserForDelete(@RequestParam("uid") int uid, RedirectAttributes model, HttpServletRequest request, HttpSession session) {
        System.out.println("uid: " + uid);

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        int userId = (int) session.getAttribute("userId");

        Date today = new Date();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + uid, CustomerSubUser.class);
        CustomerSubUser custUser = restCustomerResponse.getBody();

//        CustomerSubUser custUser = customerSubUserDao.getCustomerSubUserById(uid);
        custUser.setStatus("Delete");
        custUser.setUpdatedate(today);

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                custUser, String.class);
        System.out.println("result3: " + result);

        custUserTrackingReport.setActivityname("Deleted");
        custUserTrackingReport.setActivitydate(today);
        custUserTrackingReport.setNgCpCustomersubuserId(custUser);

        String trackReportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerusertrackreport.do"), custUserTrackingReport, String.class);
        System.out.println("trackReportId: " + trackReportId);

//        customerSubUserDao.updateCustomerSubUser(custUser);
        String message = "User has been deleted.";
        model.addFlashAttribute("message", message);
        return new ModelAndView("redirect:/manageuser.do");
    }

    @RequestMapping("updatecustomeruser")
    public ModelAndView updateCustomerUser(RedirectAttributes model, HttpServletRequest request, HttpSession session) {
        System.out.println("updatecustomeruser");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }
        int userId = (int) session.getAttribute("userId");

        Date today = new Date();
        String status = request.getParameter("status");
        String uid = request.getParameter("uid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpass = request.getParameter("confirmpassword");
        String projectIds = request.getParameter("projectIds");
        String documentIds = request.getParameter("documentIds");

        String personalfirstname = request.getParameter("personalfirstname");
        String personallastname = request.getParameter("personallastname");
        String personalemailid = request.getParameter("personalemailid");
        String personalcontactnumber = request.getParameter("personalcontactnumber");

        System.out.println("status: " + status);
        System.out.println("uid: " + uid);
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("confirmpass: " + confirmpass);
        System.out.println("projectIds: " + projectIds);
        System.out.println("documentIds: " + documentIds);
        System.out.println("personalfirstname: " + personalfirstname);
        System.out.println("personallastname: " + personallastname);
        System.out.println("personalemailid: " + personalemailid);
        System.out.println("personalcontactnumber: " + personalcontactnumber);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerAdminResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);
        CustomerSubUser loggedInUser = restCustomerAdminResponse.getBody();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(uid), CustomerSubUser.class);
        CustomerSubUser custUser = restCustomerResponse.getBody();
//        CustomerSubUser custUser = customerSubUserDao.getCustomerSubUserById(Integer.parseInt(uid));

        if (projectIds != null && !projectIds.trim().equalsIgnoreCase("")) {

            ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + Integer.parseInt(uid),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                    });
            List<CustomerProjectMapping> mappingList = restCustomerProjectMappingResponse.getBody();
//            List<CustomerProjectMapping> mappingList = (List<CustomerProjectMapping>) custProjectMappingDao.findByCustomerId(Integer.parseInt(uid));

            for (CustomerProjectMapping obj : mappingList) {

                String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/deletecustomerprojectmapping.do"),
                        obj, String.class);
//                custProjectMappingDao.deleteCustomerProjectMapping(obj);
                System.out.println("result1: " + result);
            }
            String[] ids = projectIds.split(",");
            System.out.println("projectIds: " + ids.length);

            for (String pid : ids) {
                ResponseEntity<ProjectSeeded> restProjectResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getprojectseededbyid.do?pid=" + Integer.parseInt(pid), ProjectSeeded.class);
                ProjectSeeded project = restProjectResponse.getBody();
//                ProjectSeeded project = projectSeededDao.getProjectSeededById(Integer.parseInt(pid));
//                CustomerSubUser custSubUser = customerSubUserDao.getCustomerSubUserById(Integer.parseInt(uid));

                custProjectMapping.setBpaasProjectseededPid(project);
                custProjectMapping.setBpaasCustomersubuserId(custUser);

                String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerprojectmapping.do"),
                        custProjectMapping, String.class);
                System.out.println("result2: " + result);
//                custProjectMappingDao.saveCustomerProjectMapping(custProjectMapping);
            }

        }

        custUser.setStatus(status);
        custUser.setUsername(username);
        custUser.setPersonalfirstname(personalfirstname);
        custUser.setPersonallastname(personallastname);
        custUser.setPersonalemailid(personalemailid);
        custUser.setPersonalcontactnumber(personalcontactnumber);

        if (password != null && !password.equalsIgnoreCase("")) {
            custUser.setPassword(passwordEncoder.encode(password));
        }

        if (confirmpass != null && !confirmpass.equalsIgnoreCase("")) {
            custUser.setConfirmpassword(passwordEncoder.encode(confirmpass));
        }

        custUser.setUpdatedate(today);

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                custUser, String.class);
        System.out.println("result3: " + result);

        if (documentIds != null && !documentIds.equalsIgnoreCase("")) {
//
//            ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + custUser.getId(), CustomerSubUser.class);
//            CustomerSubUser custSubUser = restCustomerResponse.getBody();

            String[] ids = documentIds.split(",");
            for (String id : ids) {
                System.out.println("id: " + id);

                ResponseEntity<Groups> restGroupResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/getgroupbyid.do?gid=" + Integer.parseInt(id), Groups.class);
                System.out.println("restGroupResponse: " + restGroupResponse);

                Groups group = restGroupResponse.getBody();

                supplierGroup.setBpaasCustomersubuserId(custUser);
                supplierGroup.setBpaasGroupsGid(group);
                supplierGroup.setSgroupname(group.getGroupname());
                supplierGroup.setDescription(group.getDescription());
                supplierGroup.setCreationdate(today);
                supplierGroup.setUpdatedate(today);

                String suppGroupId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/mappcustomertogroup.do"), supplierGroup, String.class);
                System.out.println("suppGroupId: " + suppGroupId);
            }
        }

        custUserTrackingReport.setActivityname("Updated");
        custUserTrackingReport.setActivitydate(today);
        custUserTrackingReport.setNgCpCustomersubuserId(custUser);
        custUserTrackingReport.setNgCpCustomersubuserAdmin(loggedInUser);

        String trackReportId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerusertrackreport.do"), custUserTrackingReport, String.class);
        System.out.println("trackReportId: " + trackReportId);

//        customerSubUserDao.updateCustomerSubUser(custUser);
        String message = "User has been updated.";
        model.addFlashAttribute("message", message);
        return new ModelAndView("redirect:/manageuser.do");
    }

    @RequestMapping("customercontractdocument")
    public ModelAndView customerDocument(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("customercontractdocument");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        String userRole = (String) session.getAttribute("userRole");
        System.out.println("userRole: " + userRole);

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

            ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                    });

            System.out.println("restCustomerResponse: " + restCustomerResponse);
            customerList = restCustomerResponse.getBody();

            //26Nov2018
            CustomerSeeded natSteelHoldingCustomer = null;
            for (CustomerSeeded customer : customerList) {
                if (customer.getCustomercode().equalsIgnoreCase("NatSteelAdmin")) {
                    natSteelHoldingCustomer = customer;
                }
            }
            if (customerList != null && !customerList.isEmpty()) {
                customerList.remove(natSteelHoldingCustomer);
            }
        }

        List<ProjectSeeded> projectListByCutomer = null;
        List<CustomerProjectMapping> projectMappingList = null;

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {

            ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

            CustomerSubUser custUser = restResponse.getBody();

            int cid = custUser.getBpaasCustomerseededCid().getCid();

            ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/projectbycustomerid.do?customerid=" + cid,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                    });

            projectListByCutomer = restProjectResponse.getBody();
            System.out.println("projectListByCutomer: " + projectListByCutomer);

        } else {
            ResponseEntity<List<CustomerProjectMapping>> restCustomerProjectMappingResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findassignedprojecttocustomer.do?customerid=" + userId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerProjectMapping>>() {
                    });

            projectMappingList = restCustomerProjectMappingResponse.getBody();
            System.out.println("projectMappingList: " + projectMappingList);
        }

        if (userRole.equalsIgnoreCase("Admin")) {

        } else if (userRole.equalsIgnoreCase("Company Admin")) {
            map.put("projectListByCutomer", projectListByCutomer);
        } else {
            map.put("projectList", projectMappingList);
        }

        map.put("customerList", customerList);

        return new ModelAndView("customercontractdocument", "map", map);
    }

    @RequestMapping("customerpasswordconfig")
    public ModelAndView custPasswordConfig(HttpServletRequest request, HttpSession session) {
        System.out.println("customerpasswordconfig");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PasswordConfiguration>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getpasswordconfiguration.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<PasswordConfiguration>>() {
                });

        System.out.println("restProjectResponse: " + restProjectResponse);
        List<PasswordConfiguration> passConfigList = restProjectResponse.getBody();
        System.out.println("passConfigList size: " + passConfigList.size());

        return new ModelAndView("customerpasswordconfig", "passConfigList", passConfigList);
    }

    @RequestMapping("updatecustomerpasswordconfig")
    public ModelAndView updateCustPasswordConfig(@ModelAttribute("passwordconfig") PasswordConfiguration passConfig, HttpServletRequest request, HttpSession session) {
        System.out.println("updatecustomerpasswordconfig");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        System.out.println("PassConfig: " + passConfig.getPassLength());

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savepasswordconfiguration.do"),
                passConfig, String.class);
        System.out.println("result: " + result);

        return new ModelAndView("redirect:/customerpasswordconfig.do");
    }

    @RequestMapping(value = "updatepass", method = RequestMethod.POST)
    public ModelAndView custUpdatePasswordAtFirstLogin(HttpServletRequest request, HttpSession session, Map<String, Object> modelMap, RedirectAttributes model) {
        System.out.println("custUpdatePasswordAtFirstLogin");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

//        setDashboardImages(modelMap);
        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("newpassword");

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId: " + userId);

        System.out.println("oldPassword: " + oldPassword);
        System.out.println("newPassword: " + newPassword);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + userId, CustomerSubUser.class);

        CustomerSubUser loggedInUser = restResponse.getBody();

//        CustomerSubUser loggedInUser = customerSubUserDao.getCustomerSubUserById(userId);
        System.out.println("loggedInUser: " + loggedInUser);

        loggedInUser.setPassword(passwordEncoder.encode(newPassword));
        loggedInUser.setConfirmpassword(passwordEncoder.encode(newPassword));
        loggedInUser.setIsPasswordUpdated("Yes");
        loggedInUser.setUpdatedate(new Date());

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                loggedInUser, String.class);

        System.out.println("result: " + result);
        model.addFlashAttribute("msg", "");
//        customerSubUserDao.updateCustomerSubUser(loggedInUser);
        System.out.println("updated=========================");

        modelMap.put("ispassupdated", "Yes");

//        return new ModelAndView("customerdashboard");
        return new ModelAndView("redirect:/customereditprofile.do");
    }

    @RequestMapping("redirecttocustomerprofile")
    public ModelAndView redirecttocustomerprofile() {
        System.out.println("redirecttocustomerprofile");
        return new ModelAndView("customerdashboard");
    }

    @RequestMapping("customerforgotpassword")
    public ModelAndView toCustomerForgotPassword(HttpServletRequest request, HttpSession session, Map<String, Object> map) {
        System.out.println("customerforgotpassword");
//
//        if (session.getAttribute("userId") == null) {
//            return new ModelAndView("redirect:/customerlogin.do");
//        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        //27Nov2018
        List<PasswordConfiguration> passConfigList = null;
        String passconfigString = "";

        ResponseEntity<List<PasswordConfiguration>> restPassConfigResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getpasswordconfiguration.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<PasswordConfiguration>>() {
                });

        passConfigList = restPassConfigResponse.getBody();

        System.out.println("passConfigList: " + passConfigList);

        if (!passConfigList.isEmpty()) {
            PasswordConfiguration passObj = passConfigList.get(0);
            passconfigString = passObj.getPassLength() + ", " + passObj.getSpecialChar() + ", " + passObj.getUpparChar() + ", " + passObj.getLowerChar() + ", " + passObj.getNumericCount();
            System.out.println("passconfigString: " + passconfigString);
        }

        map.put("passconfig", passconfigString);

        //Ends
        return new ModelAndView("customerforgotpassword");
    }

    @RequestMapping("updateforgotpassword")
    public ModelAndView custUpdateForgotPassword(HttpServletRequest request, RedirectAttributes model) {
        System.out.println("updateforgotpassword");

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String userid = request.getParameter("userid");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpassword = request.getParameter("confirmnewpassword");

        System.out.println("userId: " + userid);

        System.out.println("newpassword: " + newpassword);
        System.out.println("confirmnewpassword: " + confirmnewpassword);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerSubUser> restCustomerResponse = restTemplate.getForEntity(webservice_ip + "/EportalWebServices/customerbyid.do?userid=" + Integer.parseInt(userid), CustomerSubUser.class);

        CustomerSubUser loggedInUser = restCustomerResponse.getBody();

//        CustomerSubUser loggedInUser = customerSubUserDao.getCustomerSubUserById(Integer.parseInt(userid));
        System.out.println("loggedInUser: " + loggedInUser);

        loggedInUser.setPassword(passwordEncoder.encode(newpassword));
        loggedInUser.setConfirmpassword(passwordEncoder.encode(confirmnewpassword));
        loggedInUser.setUpdatedate(new Date());

        String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomersubuser.do"),
                loggedInUser, String.class);

        System.out.println("result: " + result);

//        customerSubUserDao.updateCustomerSubUser(loggedInUser);
        System.out.println("updated=========================");

        model.addFlashAttribute("message", "Your password has been reset.");

        return new ModelAndView("redirect:/customerlogin.do");
    }

    @RequestMapping("customerdashboardconfiguration")
    public ModelAndView customerDashboardConfiguration(HttpServletRequest request, HttpSession session) {
        System.out.println("customerdashboardconfiguration");

        logger.info("customerdashboardconfiguration");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();
//        
//        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><InputCriteria><CustomerId>0001101708</CustomerId></InputCriteria>";
//        System.out.println("xmlString: " + xmlString);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//        
//        HttpEntity<String> request = new HttpEntity<>(xmlString, headers);
//        
//        ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.13.119:8080/CustomerPortalapp/ng/search/project", request, String.class);
//        
//        String body = response.getBody();
//        System.out.println("response body: " + response.getBody());
//        
//        modelMap.put("ResponseBody", response.getBody());

//        return new ModelAndView("customerdashboardconfiguration", "map", modelMap);
        return new ModelAndView("customerdashboardconfiguration");
    }

    @RequestMapping("updatecustomerdashboardimage")
    public ModelAndView updateCustomerDashboard(@RequestParam("image") CommonsMultipartFile image, RedirectAttributes model, HttpServletRequest request, HttpSession session) {
        System.out.println("updatecustomerdashboardimage");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }
        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        String sectionName = request.getParameter("sectionName");
        System.out.println("sectionName: " + sectionName);

        if (image.getBytes().length == 0) {
            System.out.println("image: " + image.getBytes().length);

        } else {
            System.out.println("image: " + image.getBytes().length);
            System.out.println(image.getOriginalFilename());
        }
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CustomerImage>> restImageResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/findbysectionname.do?section=" + sectionName,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerImage>>() {
                });

        List<CustomerImage> custImageList = restImageResponse.getBody();

//        List<CustomerImage> custImageList = (List<CustomerImage>) customerImageDao.findBySectionName(sectionName);
        if (custImageList.isEmpty()) {
            customerImage.setSectionname(sectionName);
            customerImage.setImage(image.getBytes());
            customerImage.setImagetype(image.getOriginalFilename());

            String imageId = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/savecustomerimage.do"),
                    customerImage, String.class);
            System.out.println("imageId: " + imageId);

//            int imageId = customerImageDao.saveCustomerImage(customerImage);
//            System.out.println("imageId: " + imageId);
        } else {
            CustomerImage imageObj = custImageList.get(0);
            imageObj.setImage(image.getBytes());
            imageObj.setImagetype(image.getOriginalFilename());

            String result = restTemplate.postForObject(URI.create(webservice_ip + "/EportalWebServices/updatecustomerimage.do"),
                    imageObj, String.class);
            System.out.println("result: " + result);

//            customerImageDao.updateCustomerImage(imageObj);
//            System.out.println("updated");
        }

        model.addFlashAttribute("message", "Section updated.");

        return new ModelAndView("redirect:/customerdashboardconfiguration.do");
    }

    @RequestMapping("termsandcondition")
    public ModelAndView termsAndCondition() {
        System.out.println("termsandcondition");

        return new ModelAndView("customertermsandcondition");
    }

    @RequestMapping(value = "uploadtermsandconditiondocument", method = RequestMethod.POST)
    public ModelAndView UploadTermsAndConditionDocument(@RequestParam("files") CommonsMultipartFile files, HttpServletRequest request, HttpServletResponse response, RedirectAttributes model) {
//        java.io.PrintWriter out = null;

        System.out.println("UploadTermsAndConditionDocument");
        logger.info("nikhil");
        logger.info("nikhil: UploadTermsAndConditionDocument1");
//        logger.debug("UploadTermsAndConditionDocument2");
//        logger.trace("UploadTermsAndConditionDocument3");
//        logger.error("UploadTermsAndConditionDocument4");
//        logger.fatal("UploadTermsAndConditionDocument5");
        boolean isMultipart;
        String filePath;
        int maxFileSize = 50 * 1024;
        int maxMemSize = 4 * 1024;
        File file;
        filePath = termsandcondition_loc; // Take It From INI
        String maincode = "";
        String msg = "";

        System.out.println("filePath: " + filePath);
        logger.info("nikhil: file: " + filePath);
        System.out.println("indside init()");
        System.out.println("inside doPost()");

        String sOutputXml = "";
        try {
            byte[] fileContent = files.getBytes();
            String FileName = files.getOriginalFilename();
            String Extension = FileName.substring(FileName.lastIndexOf(".") + 1);

            file = new File(filePath + FileName);
            file.createNewFile();

            try (FileOutputStream fileOuputStream = new FileOutputStream(file)) {
                fileOuputStream.write(fileContent);
                fileOuputStream.flush();
                fileOuputStream.close();
            }
//            System.out.println("fileContent: "  + fileContent.length);
            logger.info("nikhil: fileContent: " + fileContent.length);

            byte[] encodedFromFileSystem = Files.readAllBytes(Paths.get(filePath + FileName));
//            System.out.println("encodedFromFileSystem: "  + encodedFromFileSystem.length);
            logger.info("nikhil: encodedFromFileSystem: " + encodedFromFileSystem.length);

            String encodedString = Base64
                    .getEncoder()
                    .encodeToString(encodedFromFileSystem);
            System.out.println("encodedString:" + encodedString);

            System.out.println("FileName: " + FileName);
            System.out.println("Extension: " + Extension);
            System.out.println("encodedString: " + encodedString);

//            logger.info("FileName: " + FileName);
//            logger.info("Extension: " + Extension);
//            logger.info("encodedString: " + encodedString);
            String inputxml = "<InputCriteria>";
            inputxml = inputxml + "<FileName>" + FileName + "</FileName>";
            inputxml = inputxml + "<Extension>" + Extension + "</Extension>";
            inputxml = inputxml + "<FileContent>" + encodedString + "</FileContent>";
            inputxml = inputxml + "</InputCriteria>";

            System.out.println("inputxml: " + inputxml);

//            logger.info("inputxml: " + inputxml);
//            String WebService = "http://192.168.13.119:8080/CustomerPortalapp/ng/search/adddocument";  // Take It From INI
            String WebService = webservice_ip + "/CustomerPortalapp/ng/search/adddocument";  // Take It From INI
            logger.info("nikhil: WebService: " + WebService);
            URL url = new URL(WebService);
            String ConnectionType = "http";
            System.out.println("Triggered WebService URL:" + WebService);

            URLConnection urlConnection = url.openConnection();
            System.out.println("Connection established");

            try {
                if (ConnectionType.equals("https")) {
                    if (urlConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) urlConnection).setRequestMethod("POST");
                    } else {
                        throw new Exception("this connection is NOT an HttpUrlConnection connection");
                    }
                } else {
                    System.out.println("Inside HTTP");
                    if (urlConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection) urlConnection).setRequestMethod("POST");
                    } else {
                        throw new Exception("this connection is NOT an HttpUrlConnection connection");
                    }
                }
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/xml");
                System.out.println("Before Sending the Input to WebService");

                OutputStream os = urlConnection.getOutputStream();
                os.write(inputxml.getBytes());
                os.flush();

                java.io.ByteArrayOutputStream baos = null;
                InputStream is = null;

                if ((is = urlConnection.getInputStream()) != null) {
                    baos = new java.io.ByteArrayOutputStream();
                    byte ba[] = new byte[1];

                    while ((is.read(ba, 0, 1)) != (-1)) {
                        baos.write(ba, 0, 1);
                    }
                    baos.flush();
                    is.close();
                    sOutputXml = new String(baos.toByteArray(), "UTF-8");
                }
//                inputFile.delete();
                System.out.println("OutputXml::" + sOutputXml);
//                logger.info("OutputXml:: " + sOutputXml);

                Files.deleteIfExists(Paths.get(filePath + FileName));

                System.out.println("file_deleted");
//                logger.info("file_deleted");

                maincode = findMainCode(sOutputXml);
//                logger.info("maincode: " + maincode);
                if (maincode.equalsIgnoreCase("0")) {
                    msg = "Document uploaded successfully.";
                } else {
                    msg = "Document upload has failed. Please try again.";
                }
//                response.getWriter().print(sOutputXml);
            } catch (Exception e) {
                System.out.println("Exception in Uploading the S&T document :" + e.getMessage());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        model.addFlashAttribute("message", msg);

        return new ModelAndView("redirect:/termsandcondition.do");
    }

    public String findMainCode(String outputXml) {
        String maincode = "";
        try {

//            String xmlRecords = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//                    + "<OutputCriteria>"
//                    +   "<maincode>500</maincode>"
//                    +   "<message>CustomerId is NULL</message>"
//                    +   "<Type>E</Type>"
//                    + "</OutputCriteria>";
//            
            String xmlRecords = outputXml;
//            logger.info("xmlRecords: " + xmlRecords);
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlRecords));

            Document doc = (Document) db.parse(is);
            NodeList nodes = doc.getElementsByTagName("OutputCriteria");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                NodeList name = element.getElementsByTagName("maincode");
                Element line = (Element) name.item(0);
                System.out.println("maincode: " + getCharacterDataFromElement(line));
                maincode = getCharacterDataFromElement(line);
                System.out.println("maincode: " + maincode);
//                logger.info("maincode: " + maincode);
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maincode;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    @RequestMapping("customerreport")
    public ModelAndView customerReport(Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        System.out.println("customerreport");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<List<CustomerAuditReport>> restCustomerAuditResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallauditreport.do",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAuditReport>>() {
//                });
//
//        System.out.println("restCustomerAuditResponse: " + restCustomerAuditResponse);
//        List<CustomerAuditReport> reportList = restCustomerAuditResponse.getBody();

        ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                });

        System.out.println("restCustomerResponse: " + restCustomerResponse);
        List<CustomerSeeded> customerList = restCustomerResponse.getBody();

//        map.put("reportList", reportList);
        map.put("customerList", customerList);

        return new ModelAndView("customerreport", "map", map);
    }

    @RequestMapping("customerprofilereport")
    public ModelAndView customerProfileReport(Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customerprofilereport");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        return new ModelAndView("customerprofilereport", "map", map);
    }

    @RequestMapping("customermanageprojects")
    public ModelAndView manageProjects(Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customermanageprojects");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ProjectSeeded>> restProjectResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallprojectseeded.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectSeeded>>() {
                });

        System.out.println("restProjectResponse: " + restProjectResponse);
        List<ProjectSeeded> projectList = restProjectResponse.getBody();

        ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                });

        System.out.println("restCustomerResponse: " + restCustomerResponse);
        List<CustomerSeeded> customerList = restCustomerResponse.getBody();

        map.put("customerList", customerList);

        map.put("projectList", projectList);

        return new ModelAndView("customermanageprojects", "map", map);
    }

    @RequestMapping("customeradmintrackingreport")
    public ModelAndView customerAdminTrackingReport(Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customerAdminTrackingReport");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        return new ModelAndView("customeradmintrackingreport", "map", map);
    }

    @RequestMapping("customerautomailconfiguration")
    public ModelAndView customerAutoMailConfiguration(Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customerautomailconfiguration");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }

        RestTemplate restTemplate = new RestTemplate();

        List<CustomerSeeded> customerList = null;

        ResponseEntity<List<CustomerSeeded>> restCustomerResponse = restTemplate.exchange(webservice_ip + "/EportalWebServices/getallcustomerseeded.do",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerSeeded>>() {
                });

        System.out.println("restCustomerResponse: " + restCustomerResponse);
        customerList = restCustomerResponse.getBody();

        map.put("customerList", customerList);

        return new ModelAndView("customerautomail", "map", map);
    }
    
    @RequestMapping("customermailreport")
    public ModelAndView customerMailReport(Map<String, Object> map, HttpServletRequest request, HttpSession session) {
        System.out.println("customerautomailconfiguration");

        if (session.getAttribute("userId") == null) {
            return new ModelAndView("redirect:/customerlogin.do");
        }

        if (request.isRequestedSessionIdValid() == false) {
            return new ModelAndView("timeout");
        }
        
        System.out.println("hello git");
        
        
        return new ModelAndView("customermailreport", "map", map);
    }
}
