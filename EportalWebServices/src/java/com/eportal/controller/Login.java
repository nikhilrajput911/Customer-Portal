/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import JSON.JSONArray;
import JSON.JSONException;
import JSON.JSONObject;
import com.eportal.dao.UserDetailDao;
import com.eportal.dao.WorkOrderRfqHeaderDao;
import com.eportal.entities.Userdetail;
import com.eportal.entities.WorkOrderRfqHeader;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"username", "userId", "userType", "name", "emailId", "mobile"})
public class Login {

    @Autowired
    UserDetailDao userDao;

    @Autowired
    WorkOrderRfqHeaderDao workOrderRfqHeaderDao;

    @Value("${approval_awaiting}")
    private String awaitingapproval;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    private static final Logger logger = Logger.getLogger(Login.class);

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView welcomePage(Principal principal, Authentication authentication) {
        System.out.println("login");
        System.out.println("Principal : " + principal);
        System.out.println("Authentication : " + authentication);
        logger.info("login in logger");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/index")
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response, Principal principal, Authentication authentication) throws JSONException {
        String message = "";
        System.out.println("index");
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("Principal : " + principal);
        System.out.println("Authentication : " + authentication);

        List<Userdetail> user = null;
        user = (List<Userdetail>) userDao.findByUsername(uname);
        System.out.println("Size: " + user.size());
        System.out.println("pass: " + passwordEncoder.matches(password, user.get(0).getPassword()));
        if (user != null && !user.isEmpty() && passwordEncoder.matches(password, user.get(0).getPassword())) {
            
                return new ModelAndView("redirect:/home.do")
                        .addObject("username", user.get(0).getUsername())
                        .addObject("userId", user.get(0).getUserid())
                        .addObject("userType", user.get(0).getUsertype())
                        .addObject("name", user.get(0).getFirstname() + " " + user.get(0).getLastname())
                        .addObject("emailId", user.get(0).getWorkemailid())
                        .addObject("mobile", user.get(0).getContactnumber());
            
        } else {
            message = "Sorry, you are not authorised user!";
            return new ModelAndView("login", "message", message);
        }
//        return new ModelAndView("redirect:/home.do");
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal principal) {
        ModelAndView model = new ModelAndView();
        if (principal == null) {
            model.addObject("message", "you do not have permission to access this page!");
        } else {
            model.addObject("msg", "You have permission to access this page!");
        }
        model.setViewName("error");
        return model;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView doLogout()
    {
        String message = "You have been logged out.";
        return new ModelAndView("welcome", "message", message);
    }
}
