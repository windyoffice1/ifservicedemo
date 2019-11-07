package com.windyoffice.ifservice.controller;

import com.windyoffice.ifservice.common.ServerResponse;
import com.windyoffice.ifservice.pojo.User;
import com.windyoffice.ifservice.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    @RequestMapping(value ="getuserinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(String userName){

        logger.info("getUserInfo");
        return userService.getUserInfo(userName);
    };

}
