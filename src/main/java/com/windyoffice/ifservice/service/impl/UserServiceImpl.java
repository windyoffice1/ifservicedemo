package com.windyoffice.ifservice.service.impl;

import com.windyoffice.ifservice.common.ServerResponse;
import com.windyoffice.ifservice.pojo.User;
import com.windyoffice.ifservice.pojo.UserRequest;
import com.windyoffice.ifservice.service.IUserService;
import com.windyoffice.ifservice.util.PropertiesUtil;
import com.windyoffice.ifservice.util.SignatureUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Cacheable(value = "myCache",key = "# username")
    @Override
    public ServerResponse<User> getUserInfo(String username) {
        logger.info("UserServiceImpl  getUserInfo");
        User user=new User();
        user.setUserName("张三");
        user.setAge(18);
        user.setAddress("测试路测试号");
        return ServerResponse.createBySuccess("获取用户信息成功",user);
    }

    @Override
    public ServerResponse<User> getUserInfoBySing(UserRequest request) {
       if(validateRequest(request)) {
           User user=new User();
           user.setUserName("张三");
           user.setAge(18);
           user.setAddress("测试路测试号");
           return ServerResponse.createBySuccess("获取用户信息成功",user);
       }else{
          return ServerResponse.createByErrorMsg("验签不通过");
       }
    }

    private Boolean  validateRequest(UserRequest request) {
        return SignatureUtil.verify(request.getUserName(),request.getSignData(), PropertiesUtil.getProperty("sign.publickey"));

    }
}
