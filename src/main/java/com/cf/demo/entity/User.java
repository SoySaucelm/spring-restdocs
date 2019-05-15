/******************************************************************
 *
 *    Java Lib For Web, Powered By Alvis.Yu.
 *
 *    Copyright (c) 2001-2017 Alvis.Yu Co.,Ltd
 *    http://yu.alvis.com/
 *
 *    Package:     domain.users
 *
 *    Filename:    customer.java
 *
 *    Description: customer
 *
 *    Copyright:   Copyright (c) 2001-2017
 *
 *    Company:     Alvis.Yu Co.,Ltd
 *
 *    @author: dell
 *
 *    @version: 1.0.0
 *
 *    Create at:   Jul 18, 2017 3:24:08 PM
 *
 *    Revision:
 *
 *    Jul 18, 2017 3:24:08 PM
 *        - first revision
 *
 *****************************************************************/
package com.cf.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class User  implements Serializable {

    private Integer id;

    private String userUuid;

    private String userName;

    private String password;

    private String name;

    private Integer age;

    private Date lastActiveTime;

}
