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
