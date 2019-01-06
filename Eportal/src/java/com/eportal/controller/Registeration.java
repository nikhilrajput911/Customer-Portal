/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;


import com.eportal.dao.UserDetailDao;
import com.eportal.dao.UserSecurityQuestionDao;
import com.eportal.entities.UserSecurityQuestion;
import com.eportal.entities.Userdetail;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Registeration {
//
//    @Autowired
//    UserDetailDao userDao;
//    @Autowired
//    UserSecurityQuestionDao userSecQueDao;
//    
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    
//    @RequestMapping(value="/register",method = RequestMethod.POST)
//    public ModelAndView doRegisteration(@ModelAttribute("user") Userdetail user, 
//            @ModelAttribute("userSecQue") UserSecurityQuestion userSecQue)
//    {
//        Date today = new Date();
//        user.setCreationdate(today);
//        user.setUpdatedate(today);
//        System.out.println("password: " + user.getPassword());
//        System.out.println("encoded: " + passwordEncoder.encode(user.getPassword()));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        
//        String message = "";
//        int id = userDao.saveUser(user);
//        System.out.println("user id is: " + id);
//        
//        userSecQue.setUserId(id);
//        System.out.println(userSecQue.getUserId());
//        
//        int secQueId = userSecQueDao.saveSecQue(userSecQue);
//        System.out.println("SecQueId: " + secQueId);
//        
//        message = "You have successfully registered with this system";
//        return new ModelAndView("login", "message", message);
//    }

}
