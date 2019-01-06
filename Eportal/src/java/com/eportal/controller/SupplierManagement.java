/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.CurrencySeededDao;
import com.eportal.dao.GstStateSeededDao;
import com.eportal.dao.SupplierBankDetailsNewDao;
import com.eportal.dao.SupplierGroupDao;
import com.eportal.dao.SupplierSelectionDao;
import com.eportal.dao.SupplierStatusSeededDao;
import com.eportal.dao.SupplierUserAttachmentDao;
import com.eportal.dao.SupplierUserBankDetailDao;
import com.eportal.dao.SupplierUserDao;
import com.eportal.dao.SupplierUserRegContactDetailsDao;
import com.eportal.dao.TypeOfBusinessSeededDao;
import com.eportal.dao.UserCountrySeededDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.entities.CurrencySeeded;
import com.eportal.entities.GstStateSeeded;
import com.eportal.entities.SupplierBankDetailsNew;
import com.eportal.entities.SupplierSelection;
import com.eportal.entities.SupplierStatusSeeded;
import com.eportal.entities.SupplierUser;
import com.eportal.entities.SupplierUserAttachment;
import com.eportal.entities.SupplierUserRegContactDetails;
import com.eportal.entities.TypeOfBusinessSeeded;
import com.eportal.entities.UserCountrySeeded;
import com.eportal.entities.WorkOrderRfqHeader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes({"username", "userType", "name", "userId", "emailId", "mobile"})
public class SupplierManagement {
//
//    @Autowired
//    TypeOfBusinessSeededDao businessTypeDao;
//
//    @Autowired
//    GstStateSeededDao gstStateDao;
//
//    @Autowired
//    SupplierStatusSeededDao supplierStatusDao;
//
//    @Autowired
//    SupplierUserDao supplierDao;
//
//    @Autowired
//    SupplierBankDetailsNewDao supplierBankDetailsNewDao;
//    @Autowired
//    SupplierBankDetailsNew supplierBankDetails;
//
//    @Autowired
//    WorkOrderRfqHeaderDao rfqHeaderDao;
//
//    @Autowired
//    WorkOrderRfqHeaderDao workOrderRfqHeaderDao;
//
//    @Autowired
//    SupplierSelectionDao supplierSelectionDao;
//
//    @Autowired
//    UserCountrySeededDao userCntryDao;
//
//    @Autowired
//    SupplierUserRegContactDetailsDao supplierContactDao;
//    @Autowired
//    SupplierUserRegContactDetails supplierUserContact;
//
//    @Autowired
//    SupplierUserAttachmentDao supplierUserAttDao;
//    @Autowired
//    SupplierUserAttachment supplierUserAtt;
//    
//    @Autowired
//    CurrencySeededDao currencyDao;
//    
//    @Autowired
//    SupplierGroupDao supplierGroupDao;
//
//    @RequestMapping(value = "/newsupplier")
//    public ModelAndView toSupplierMgmt() {
//
//        System.out.println("toSupplierMgmt");
//
//        List<TypeOfBusinessSeeded> bussType = (List<TypeOfBusinessSeeded>) businessTypeDao.getGstStates();
//        System.out.println("bussType size: " + bussType.size());
//
//        List<GstStateSeeded> gstState = (List<GstStateSeeded>) gstStateDao.getGstStates();
//        System.out.println("gstState size: " + gstState.size());
//
//        List<SupplierStatusSeeded> suppStatus = (List<SupplierStatusSeeded>) supplierStatusDao.getSupplierStatus();
//        System.out.println("suppStatus size: " + suppStatus.size());
//
//        List<UserCountrySeeded> userCntry = userCntryDao.getUserCntries();
//        System.out.println("userCntry: " + userCntry.size());
//        
//        List<CurrencySeeded> currencies = (List<CurrencySeeded>) currencyDao.getAllCurrency();
//        System.out.println("currencies size: " + currencies.size());
//
//        Map<String, List<?>> supplierLOV = new HashMap<>();
//        supplierLOV.put("bussType", bussType);
//        supplierLOV.put("gstState", gstState);
//        supplierLOV.put("suppStatus", suppStatus);
//        supplierLOV.put("userCntry", userCntry);
//        supplierLOV.put("currency", currencies);
//
//        return new ModelAndView("newsupplier", "supplierLOV", supplierLOV);
//    }
//
//    @RequestMapping(value = "/registerSupplier", method = RequestMethod.POST)
//    public ModelAndView registerSupplier(@ModelAttribute("supplier") SupplierUser supplier, HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("pancard") CommonsMultipartFile pancard, @RequestParam("cancelledcheque") CommonsMultipartFile cancelledcheque, @RequestParam("compnanyincopletter") CommonsMultipartFile compnanyincopletter, @RequestParam("gstcertificate") CommonsMultipartFile gstcertificate, @RequestParam("otherfile") CommonsMultipartFile otherfile) {
//
//        System.out.println("registerSupplier");
//
//        Date today = new Date();
//        String username = (String) session.getAttribute("username");
//        //System.out.println("username: " + username);
//        
//        supplier.setCreatedby(username);
//        supplier.setUpdatedby(username);
//        supplier.setCreationdate(today);
//        supplier.setUpdatedate(today);
//        supplier.setSupplierstatus("Active");
//
//        int id = supplierDao.saveSupplier(supplier);
//        System.out.println("Supplier Id: " + id);
//
//        String[] businessAddress = request.getParameterValues("businessAddress");
//        String[] businessCountry = request.getParameterValues("businessCountry");
//        String[] contactPerson2 = request.getParameterValues("contactPerson2");
//        String[] contact2EmailAddress = request.getParameterValues("contact2EmailAddress");
//        String[] contact2MobileNumber = request.getParameterValues("contact2MobileNumber");
//
//        SupplierUser supplierUser = supplierDao.getSupplierById(id);
//
//        for (int i = 0; i < businessAddress.length; i++) {
//            supplierUserContact.setBpaasSupplieruserId(supplierUser);
//            supplierUserContact.setBusinessaddress(businessAddress[i]);
//            supplierUserContact.setContactperson2(contactPerson2[i]);
//            supplierUserContact.setContact2emailaddress(contact2EmailAddress[i]);
//            supplierUserContact.setContact2mobilenumber(contact2MobileNumber[i]);
//            supplierUserContact.setCountry(businessCountry[i]);
//            supplierUserContact.setState(businessCountry[i]);
//            supplierUserContact.setCreatedby(username);
//            supplierUserContact.setCreationdate(today);
//            supplierUserContact.setUpdatedby(username);
//            supplierUserContact.setUpdatedate(today);
//
//            int contactId = supplierContactDao.saveSupplierUserRegContactDetails(supplierUserContact);
//            System.out.println("contactId: " + contactId);
//        }
//
//        String[] bankName = request.getParameterValues("bankName");
//        String[] bankAccountType = request.getParameterValues("bankAccountType");
//        String[] bankAccountNumber = request.getParameterValues("bankAccountNumber");
//        String[] bankBranchName = request.getParameterValues("bankBranchName");
//        String[] bankAddress = request.getParameterValues("bankAddress");
//        String[] pinCode = request.getParameterValues("pinCode");
//        String[] ifscCode = request.getParameterValues("ifscCode");
//        String[] micrNumber = request.getParameterValues("micrNumber");
//        String[] vendorNameAsperBankRecord = request.getParameterValues("vendorNameAsperBankRecord");
//        String[] bankCurrency = request.getParameterValues("bankCurrency");
//
//        for (int i = 0; i < bankName.length; i++) {
//            supplierBankDetails.setBpaasSupplieruserId(supplierUser);
//            supplierBankDetails.setBankname(bankName[i]);
//            supplierBankDetails.setBankaccountnumber(bankAccountNumber[i]);
//            supplierBankDetails.setBankaccounttype(bankAccountType[i]);
//            supplierBankDetails.setBankbranchname(bankBranchName[i]);
//            supplierBankDetails.setBankaddress(bankAddress[i]);
//            supplierBankDetails.setPincode(pinCode[i]);
//            supplierBankDetails.setIfsccode(ifscCode[i]);
//            supplierBankDetails.setMicrnumber(micrNumber[i]);
//            supplierBankDetails.setVendornameAsperbankrecord(vendorNameAsperBankRecord[i]);
//            supplierBankDetails.setCurrency(bankCurrency[i]);
//            supplierBankDetails.setCreatedby(username);
//            supplierBankDetails.setCreationdate(today);
//            supplierBankDetails.setUpdatedby(username);
//            supplierBankDetails.setUpdatdate(today);
//
//            int bankId = supplierBankDetailsNewDao.saveSupplierBankDetails(supplierBankDetails);
//            System.out.println("bankId: " + bankId);
//        }
//        
//        //System.out.println(otherfile.getBytes().length);
//        
//        supplierUserAtt.setBpaasSupplieruserId(supplierUser);
//        supplierUserAtt.setPanCard(pancard.getBytes());
//        supplierUserAtt.setPancardName(pancard.getOriginalFilename());
//        supplierUserAtt.setCancelledcheque(cancelledcheque.getBytes());
//        supplierUserAtt.setCancelledchequeName(cancelledcheque.getOriginalFilename());
//        supplierUserAtt.setCmpnyincorpletter(compnanyincopletter.getBytes());
//        supplierUserAtt.setCmpnyincorpletterName(compnanyincopletter.getOriginalFilename());
//        supplierUserAtt.setGstcertificate(gstcertificate.getBytes());
//        supplierUserAtt.setGstcertificateName(gstcertificate.getOriginalFilename());
//        if (0 == otherfile.getBytes().length) {
//        } else {
//            supplierUserAtt.setOther(otherfile.getBytes());
//            supplierUserAtt.setOtherName(otherfile.getOriginalFilename());
//        }
//        supplierUserAtt.setCreatedby(username);
//        supplierUserAtt.setCreationdate(today);
//        supplierUserAtt.setUpdatedby(username);
//        supplierUserAtt.setUpdatedate(today);
//
//        int supplierUserAttId = supplierUserAttDao.saveSupplierUserAttachment(supplierUserAtt);
//        System.out.println("supplierUserAttId: " + supplierUserAttId);
//
//        return new ModelAndView("redirect:/registeredSupplier.do");
//    }
//
//    @RequestMapping("/registeredSupplier")
//    public ModelAndView registeredSupplier() {
//        List<SupplierUser> supplier = (List<SupplierUser>) supplierDao.findAllSupplierOrderByCreationDate();
//        System.out.println(supplier.size());
//        return new ModelAndView("registeredsupplier", "supplierList", supplier);
//    }
//
//    @RequestMapping("/showSupplier")
//    public ModelAndView showSupplier(@RequestParam("supplierId") int supplierId) {
//        System.out.println("supplierId: " + supplierId);
//
//        List<SupplierUser> supplierObj = (List<SupplierUser>) supplierDao.findSupplierById(supplierId);
//        //System.out.println(supplierObj.size());
//        SupplierUser supplier = supplierObj.get(0);
//
//        List<SupplierBankDetailsNew> bankList = (List<SupplierBankDetailsNew>) supplierBankDetailsNewDao.findBySupplierId(supplierId);
//        //System.out.println(bankObj.size());;
//        List<SupplierUserRegContactDetails> suppConatct =  (List<SupplierUserRegContactDetails>) supplierContactDao.findBySupplierId(supplierId);
//        System.out.println("suppConatct size: " + suppConatct.size());
//        
//        List<TypeOfBusinessSeeded> bussType = (List<TypeOfBusinessSeeded>) businessTypeDao.getGstStates();
//        System.out.println("bussType size: " + bussType.size());
//            
//        List<SupplierUserAttachment> supplierAtt = (List<SupplierUserAttachment>) supplierUserAttDao.findBySupplierId(supplierId);
//        System.out.println("supplierAtt size: " + supplierAtt.size());
//        
//        List<GstStateSeeded> gstState = (List<GstStateSeeded>) gstStateDao.getGstStates();
//        System.out.println("gstState size: " + gstState.size());
//
//        List<SupplierStatusSeeded> suppStatus = (List<SupplierStatusSeeded>) supplierStatusDao.getSupplierStatus();
//        System.out.println("suppStatus size: " + suppStatus.size());
//        
//        List<UserCountrySeeded> userCntry = userCntryDao.getUserCntries();
//
//        Map<String, Object> supplierDetails = new HashMap<>();
//        supplierDetails.put("supplier", supplier);
//        supplierDetails.put("supplierBankDetails", bankList);
//        supplierDetails.put("bussType", bussType);
//        supplierDetails.put("gstState", gstState);
//        supplierDetails.put("suppStatus", suppStatus);
//        supplierDetails.put("suppConatct", suppConatct);
//        supplierDetails.put("suppCntry", userCntry);
//        supplierDetails.put("supplierAtt", supplierAtt.get(0));
//
//        return new ModelAndView("supplierDetails", "supplierDetails", supplierDetails);
//    }
//
//    @RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
//    public ModelAndView updateSupplierDetails(@ModelAttribute("supplier") SupplierUser supplier,
//            @ModelAttribute("supplierBankDetails") SupplierBankDetailsNew supplierBankDetails, HttpSession session) {
//        System.out.println("updating........");
//
//        SupplierUser supplierObj = supplierDao.getSupplierById(supplier.getId());
//
//        Date today = new Date();
//        String username = (String) session.getAttribute("username");
//        System.out.println("username: " + username);
//
//        supplier.setUpdatedby(username);
//        supplier.setUpdatedate(today);
//        supplier.setCreatedby(supplierObj.getUsername());
//        supplier.setCreationdate(supplierObj.getCreationdate());
//
//        supplierDao.updateSupplier(supplier);
////
////        SupplierBankDetailsNew bankObj = supplierBankDetailsNewDao.getSupplierBankDetailsById(supplierBankDetails.getBankid());
////        System.out.println(bankObj.getBankid());
////
////        supplierBankDetails.setUpdatedby(username);
////        supplierBankDetails.setUpdatdate(today);
////        supplierBankDetails.setCreatedby(username);
////        supplierBankDetails.setCreationdate(bankObj.getCreationdate());
////        supplierBankDetails.setBpaasSupplieruserId(supplier);
////
////        supplierBankDetailsNewDao.updateSupplierBankDetails(supplierBankDetails);
//        System.out.println("updated.");
//
//        return new ModelAndView("redirect:/registeredSupplier.do");
//    }
//
//    @RequestMapping(value = "/inviteSupplier")
//    public ModelAndView inviteSupplier() {
//        System.out.println("inviteSupplier");
//        return new ModelAndView("invitesupplier");
//    }
//
//    @RequestMapping(value = "/supplierLogin")
//    public ModelAndView toSupplierLogin() {
//        System.out.println("supplierLogin");
//        return new ModelAndView("supplierLogin");
//    }
//
//    @RequestMapping(value = "/supplierHome")
//    public ModelAndView doSupplierLogin(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("supplierLogin");
//
//        String message = "";
//        String uname = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        List<SupplierUser> supplierUser = (List<SupplierUser>) supplierDao.findByUsername(uname, password);
////        List<Object> supplierGroupList = (List<Object>) supplierGroupDao.findByLeftJoinOnSupplierGroup();
////        System.out.println("Group size: " + supplierGroupList.size());
//        if (supplierUser.isEmpty()) {
//            message = "Sorry, you are not authorised user!";
//            return new ModelAndView("supplierLogin", "message", message);
//        } else {
//            message = "hello " + uname;
//            return new ModelAndView("redirect:/supplierdashboard.do")
//                    .addObject("username", supplierUser.get(0).getUsername())
//                    .addObject("userType", "Supplier")
//                    .addObject("name", supplierUser.get(0).getOwnername())
//                    .addObject("userId", supplierUser.get(0).getId())
//                    .addObject("emailId", supplierUser.get(0).getOwneremailid())
//                    .addObject("mobile", supplierUser.get(0).getOwnermobilenumber());
//        }
//
//    }
//
//    @RequestMapping(value = "/supplierdashboard.do")
//    public ModelAndView supplierDashboard(HttpSession session) {
//        System.out.println("supplierDashboard");
//        int userId = (int) session.getAttribute("userId");
//        System.out.println("userId: " + userId);
//
//        String userType = (String) session.getAttribute("userType");
//        System.out.println("userType: " + userType);
//
//        String userName = (String) session.getAttribute("username");
//        System.out.println("userName: " + userName);
//
//        List<SupplierSelection> totalSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierId(userId); //total
//        List<SupplierSelection> awardedSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndSupplierId("Awarded", userId);
//        List<SupplierSelection> awaitingAwardSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndSupplierId("Bid Submitted", userId);
//        List<SupplierSelection> rejectedSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndSupplierId("Rejected by Supplier", userId);
//        List<SupplierSelection> pendingSelection = (List<SupplierSelection>) supplierSelectionDao.findBySupplierStatusAndSupplierId("Released", userId);
//
//        Map<String, Object> detailsMap = new HashMap<>();
//        detailsMap.put("totalSelection", totalSelection.size());
//        detailsMap.put("awardedSelection", awardedSelection.size());
//        detailsMap.put("awaitingAwardSelection", awaitingAwardSelection.size());
//        detailsMap.put("rejectedSelection", rejectedSelection.size());
//        detailsMap.put("pendingSelection", pendingSelection.size());
//        detailsMap.put("headers", totalSelection);
//
//        return new ModelAndView("supplierDashboard", "detailsMap", detailsMap);
//    }
}
