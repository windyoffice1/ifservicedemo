package com.windyoffice.ifservice.service;

import com.windyoffice.ifservice.common.ServerResponse;
import com.windyoffice.ifservice.pojo.User;
import com.windyoffice.ifservice.pojo.UserRequest;

public interface IUserService {


    ServerResponse<User> getUserInfo(String username);

    ServerResponse<User> getUserInfoBySing(UserRequest request);


}
