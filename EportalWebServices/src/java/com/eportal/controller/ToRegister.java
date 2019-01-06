/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.controller;

import com.eportal.dao.UserCountrySeededDao;
import com.eportal.dao.UserSecSeededDao;
import com.eportal.entities.UserCountrySeeded;
import com.eportal.entities.UserSecSeeded;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ToRegister {

    @Autowired
    UserSecSeededDao secQueDao;
    @Autowired
    UserCountrySeededDao userCntryDao;
    
    @RequestMapping(value = "/toregister")
    public ModelAndView toRegister() {
        
        List<UserSecSeeded> secQues = secQueDao.getSecQues();
        //System.out.println("SecQues: " + secQues.size());
        List<UserCountrySeeded> userCntry = userCntryDao.getUserCntries();
        System.out.println("userCntry: " + userCntry.size());
        
        Map<String, List<?>> regLOV = new HashMap<>();
        regLOV.put("secQues", secQues);
        regLOV.put("userCntry", userCntry);
        
        return new ModelAndView("registration", "regLOV", regLOV);
    }
}
