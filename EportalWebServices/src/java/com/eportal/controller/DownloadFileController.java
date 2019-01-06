/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.CustomerDocumentDao;
import com.eportal.dao.CustomerNotificationDao;
import com.eportal.dao.QmQuestionDao;
import com.eportal.dao.SupplierAddattachmentDao;
import com.eportal.dao.SupplierUserAttachmentDao;
import com.eportal.dao.WorkOrderAttachmentDao;
import com.eportal.entities.CustomerDocument;
import com.eportal.entities.CustomerNotification;
import com.eportal.entities.QmQuestion;
import com.eportal.entities.SupplierAddattachment;
import com.eportal.entities.SupplierUserAttachment;
import com.eportal.entities.WorkOrderAttachment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadFileController {

    @Autowired
    WorkOrderAttachmentDao rfqAttDao;
    @Autowired
    WorkOrderAttachment rfqAtt;

    @Autowired
    SupplierAddattachmentDao supplierAttDao;

    @Autowired
    SupplierUserAttachmentDao supplierUserAttDao;

    @Autowired
    QmQuestionDao qmQuestionDao;

    @Autowired
    CustomerDocumentDao customerDocDao;

    @Autowired
    CustomerNotificationDao custNotiDao;

    @RequestMapping("downloadFile")
    public ModelAndView downloadFile(@RequestParam("attmtId") int attmtId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("downloading.....");
        System.out.println("attmtId: " + attmtId);

        WorkOrderAttachment attmnt = rfqAttDao.getWorkOrderAttachmentById(attmtId);

        System.out.println("attmnt size: " + attmnt);
        if (attmnt != null) {
            byte[] fileBytes = attmnt.getAttachment();
            String fileName = attmnt.getName();
            System.out.println("file name: " + fileName);

            String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
            System.out.println("File Type is: " + fileType);

            if (fileType.trim().equalsIgnoreCase("txt")) {
                response.setContentType("text/plain");
            } else if (fileType.trim().equalsIgnoreCase("doc")) {
                response.setContentType("application/msword");
            } else if (fileType.trim().equalsIgnoreCase("xls")) {
                response.setContentType("application/vnd.ms-excel");
            } else if (fileType.trim().equalsIgnoreCase("pdf")) {
                response.setContentType("application/pdf");
            } else if (fileType.trim().equalsIgnoreCase("ppt")) {
                response.setContentType("application/ppt");
            } else {
                response.setContentType("application/octet-stream");
            }
            /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");

            try (ServletOutputStream out = response.getOutputStream()) {
                out.write(fileBytes);
                out.flush();
            }

        }
        return null;

    }

    @RequestMapping("downloadSupplierFile")
    public ModelAndView downloadSupplierFile(@RequestParam("attmtId") int attmtId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("downloading.....");
        System.out.println("attmtId: " + attmtId);

        SupplierAddattachment attmnt = supplierAttDao.getSupplierAddattachmentById(attmtId);

        System.out.println("attmnt size: " + attmnt);
        if (attmnt != null) {
            byte[] fileBytes = attmnt.getAddattachment();
            String fileName = attmnt.getName();
            System.out.println("file name: " + fileName);

            String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
            System.out.println("File Type is: " + fileType);

            if (fileType.trim().equalsIgnoreCase("txt")) {
                response.setContentType("text/plain");
            } else if (fileType.trim().equalsIgnoreCase("doc")) {
                response.setContentType("application/msword");
            } else if (fileType.trim().equalsIgnoreCase("xls")) {
                response.setContentType("application/vnd.ms-excel");
            } else if (fileType.trim().equalsIgnoreCase("pdf")) {
                response.setContentType("application/pdf");
            } else if (fileType.trim().equalsIgnoreCase("ppt")) {
                response.setContentType("application/ppt");
            } else {
                response.setContentType("application/octet-stream");
            }
            /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");

            try (ServletOutputStream out = response.getOutputStream()) {
                out.write(fileBytes);
                out.flush();
            }

        }
        return null;

    }

    @RequestMapping("downloadSupplierAttachment")
    public ModelAndView downloadSupplierAttachment(@RequestParam("attmtId") int attmtId, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("attmtId: " + attmtId);
        System.out.println("type: " + type);
        SupplierUserAttachment attmnt = supplierUserAttDao.getSupplierUserAttachmentById(attmtId);

        System.out.println("attmnt size: " + attmnt);
        if (attmnt != null) {
            byte[] fileBytes = null;
            String fileName = "";
            if (type.equalsIgnoreCase("pancard")) {

                fileBytes = attmnt.getPanCard();
                fileName = attmnt.getPancardName();

            } else if (type.equalsIgnoreCase("cancelledcheque")) {

                fileBytes = attmnt.getCancelledcheque();
                fileName = attmnt.getCancelledchequeName();

            } else if (type.equalsIgnoreCase("companyincorpletter")) {

                fileBytes = attmnt.getCmpnyincorpletter();
                fileName = attmnt.getCmpnyincorpletterName();

            } else if (type.equalsIgnoreCase("gstcertificate")) {

                fileBytes = attmnt.getGstcertificate();
                fileName = attmnt.getGstcertificateName();

            } else if (type.equalsIgnoreCase("other")) {

                fileBytes = attmnt.getOther();
                fileName = attmnt.getOtherName();

            }
            System.out.println("file name: " + fileName);

            String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
            System.out.println("File Type is: " + fileType);

            if (fileType.trim().equalsIgnoreCase("txt")) {
                response.setContentType("text/plain");
            } else if (fileType.trim().equalsIgnoreCase("doc")) {
                response.setContentType("application/msword");
            } else if (fileType.trim().equalsIgnoreCase("xls")) {
                response.setContentType("application/vnd.ms-excel");
            } else if (fileType.trim().equalsIgnoreCase("pdf")) {
                response.setContentType("application/pdf");
            } else if (fileType.trim().equalsIgnoreCase("ppt")) {
                response.setContentType("application/ppt");
            } else {
                response.setContentType("application/octet-stream");
            }
            /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");

            try (ServletOutputStream out = response.getOutputStream()) {
                out.write(fileBytes);
                out.flush();
            }

        }
        return null;
    }

    @RequestMapping("downloadConversationAttachment")
    public ModelAndView downloadSupplierAttachment(@RequestParam("attmtId") int attmtId, HttpServletResponse response) throws IOException {
        System.out.println("downloadConversationAttachment");
        QmQuestion questionAttch = qmQuestionDao.getQmQuestionId(attmtId);

        String fileName = questionAttch.getAttachmentdescription();
        byte[] fileBytes = questionAttch.getAddattachment();
        System.out.println("file name: " + fileName);

        String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        System.out.println("File Type is: " + fileType);

        if (fileType.trim().equalsIgnoreCase("txt")) {
            response.setContentType("text/plain");
        } else if (fileType.trim().equalsIgnoreCase("doc")) {
            response.setContentType("application/msword");
        } else if (fileType.trim().equalsIgnoreCase("xls")) {
            response.setContentType("application/vnd.ms-excel");
        } else if (fileType.trim().equalsIgnoreCase("pdf")) {
            response.setContentType("application/pdf");
        } else if (fileType.trim().equalsIgnoreCase("ppt")) {
            response.setContentType("application/ppt");
        } else {
            response.setContentType("application/octet-stream");
        }
        /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("cache-control", "must-revalidate");

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(fileBytes);
            out.flush();
        }
        return null;
    }

    @RequestMapping("downloadCustomerDocument")
    public ModelAndView downloadCustomerDocument(@RequestParam("docId") int attmtId, HttpServletResponse response) throws IOException {
        System.out.println("downloadCustomerDocument");
        CustomerDocument doc = customerDocDao.getCustomerDocumentId(attmtId);

        String fileName = doc.getDocumentname();
        byte[] fileBytes = doc.getDocument();
        System.out.println("file name: " + fileName);

        String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        System.out.println("File Type is: " + fileType);

        if (fileType.trim().equalsIgnoreCase("txt")) {
            response.setContentType("text/plain");
        } else if (fileType.trim().equalsIgnoreCase("doc")) {
            response.setContentType("application/msword");
        } else if (fileType.trim().equalsIgnoreCase("xls")) {
            response.setContentType("application/vnd.ms-excel");
        } else if (fileType.trim().equalsIgnoreCase("pdf")) {
            response.setContentType("application/pdf");
        } else if (fileType.trim().equalsIgnoreCase("ppt")) {
            response.setContentType("application/ppt");
        } else {
            response.setContentType("application/octet-stream");
        }
        /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("cache-control", "must-revalidate");

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(fileBytes);
            out.flush();
        }

        return null;
    }

    @RequestMapping("customertermsandconditions")
    public ModelAndView customerTermsAndConditions(HttpServletResponse response) {
        FileInputStream fis = null;
        try (ServletOutputStream out = response.getOutputStream()) {
            System.out.println("customertermsandconditions");
            String path = "C:\\FILE_SYSTEM\\Term and Condition Document.pdf";
            String fileName = "Term and Condition Document.pdf";
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
            response.setContentType("application/pdf");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");
//            System.out.println(Arrays.toString(encoded));
            out.write(fileBytes);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(DownloadFileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @RequestMapping(value = "/downloadcustomerconversationattachment", method = RequestMethod.GET)
    public ResponseEntity<CustomerNotification> downloadCustomerConversationAttachment(@RequestParam("attmtId") int attmtId) throws IOException {
        System.out.println("attmtId in WS: "  +attmtId);
        System.out.println("downloadConversationAttachment");
        CustomerNotification notification = custNotiDao.getCustomerNotificationById(attmtId);
        System.out.println("notification: " + notification);
        
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
    
//    @RequestMapping("downloadcustomerconversationattachment")
//    public ModelAndView downloadCustomerConversationAttachment(@RequestParam("attmtId") int attmtId, HttpServletResponse response) throws IOException {
//        System.out.println("downloadConversationAttachment");
//        CustomerNotification att = custNotiDao.getCustomerNotificationById(attmtId);
//
//        String fileName = att.getAttachmentname();
//        byte[] fileBytes = att.getAttachment();
//        System.out.println("file name: " + fileName);
//
//        String fileType = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
//        System.out.println("File Type is: " + fileType);
//
//        if (fileType.trim().equalsIgnoreCase("txt")) {
//            response.setContentType("text/plain");
//        } else if (fileType.trim().equalsIgnoreCase("doc")) {
//            response.setContentType("application/msword");
//        } else if (fileType.trim().equalsIgnoreCase("xls")) {
//            response.setContentType("application/vnd.ms-excel");
//        } else if (fileType.trim().equalsIgnoreCase("pdf")) {
//            response.setContentType("application/pdf");
//        } else if (fileType.trim().equalsIgnoreCase("ppt")) {
//            response.setContentType("application/ppt");
//        } else {
//            response.setContentType("application/octet-stream");
//        }
//        /*specify content length in the http header if file is lager than 10MB otherwise browser will behave strange*/
//
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//        response.setHeader("cache-control", "no-cache");
//        response.setHeader("cache-control", "must-revalidate");
//
//        try (ServletOutputStream out = response.getOutputStream()) {
//            out.write(fileBytes);
//            out.flush();
//        }
//        return null;
//    }

    @RequestMapping("downloadcustomerusermanual")
    public ModelAndView customerUserManual(HttpServletResponse response) {
        FileInputStream fis = null;
        try (ServletOutputStream out = response.getOutputStream()) {

            System.out.println("downloadcustomerusermanual");
            String path = "C:\\FILE_SYSTEM\\UserManual.pdf";
            String fileName = "UserManual.pdf";

            byte[] fileBytes = Files.readAllBytes(Paths.get(path));

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");
//            System.out.println(Arrays.toString(encoded));
            out.write(fileBytes);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(DownloadFileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }
}
