/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eportal.config;

import com.newgen.encryption.DataEncryption;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import sun.misc.BASE64Decoder;

/**
 *
 * @author admin
 */
public class CustomDriverManagerDataSource extends DriverManagerDataSource{
    public CustomDriverManagerDataSource()
    {
        super();
    }
    @Override
    public String getPassword()
    {
        String password = super.getPassword();
        return decode(password);
    }
    private String decode(String password)
    {
        String decodedPassword;
        decodedPassword = DataEncryption.decrypt(password);
        System.out.println("decodedPassword: " + decodedPassword);
        return decodedPassword;
    }
}
