package com.cf.demo.controller;

import com.cf.demo.entity.User;
import com.cf.demo.entity.UserResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author SoySauce
 * @date 2019/5/13
 */
@RestController
@RequestMapping("api")
public class HomeController {

    static class SystemCode {
        int getCode() {
            return 200;
        }

        String getMessage() {
            return "success";
        }
    }

    public List getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(getCurrentUser());
        }
        return users;
    }

    SystemCode systemCode = new SystemCode();

    @GetMapping("/")
    @ApiOperation(
            value = "Finds Pets by status",
            notes = "Multiple status values can be provided with comma seperated strings"
    )
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid status value")})
    public Map<String, String> greeting() {
        return Collections.singletonMap("message", "Hello World");
    }

    public User getCurrentUser() {
        User user = new User();
        user.setAge(11);
        user.setId(11);
        user.setLastActiveTime(new Date());
        user.setName("張三");
        user.setPassword("123");
        user.setUserName("李四");
        user.setUserUuid("1234");
        return user;
    }

    @RequestMapping("/v1.0.1/list")
    public UserResult GetAllUser(Integer age) {
        UserResult customerResult = new UserResult(systemCode.getCode(), systemCode.getMessage());
        List<User> customers = getUsers();
        customerResult.setUserList(customers);
        return customerResult;
    }

    @PostMapping("/v1.0.1/test")
    public UserResult Test() {
        UserResult result = new UserResult(systemCode.getCode(), systemCode.getMessage());
        return result;
    }

    @PostMapping("/v1.0.1/test2")
    public User Test2() {
        User user = getCurrentUser();
        return user;
    }

    @PostMapping("/v1.0.1/test3")
    public ResponseEntity<User> Test3() {
        User user = getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/v1.0.1/test")
    public UserResult GetTest() {
        UserResult result = new UserResult(systemCode.getCode(), systemCode.getMessage());
        return result;
    }


    @PostMapping("/v1.0.1/news")
    public UserResult News() {
        UserResult result = new UserResult(systemCode.getCode(), systemCode.getMessage());
        return result;
    }
}
