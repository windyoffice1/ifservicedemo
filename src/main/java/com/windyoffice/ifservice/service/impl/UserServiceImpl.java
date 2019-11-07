package com.windyoffice.ifservice.service.impl;

import com.windyoffice.ifservice.common.ServerResponse;
import com.windyoffice.ifservice.pojo.User;
import com.windyoffice.ifservice.service.IUserService;
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
        logger.info("no cache getUserInfo");
        User user=new User();
        user.setUserName("张三");
        user.setAge(18);
        user.setAddress("测试路测试号");
        return ServerResponse.createBySuccess("登录成功",user);
    }
}
