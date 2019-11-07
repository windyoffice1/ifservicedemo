package com.windyoffice.ifservice.service;

import com.windyoffice.ifservice.common.ServerResponse;
import com.windyoffice.ifservice.pojo.User;

public interface IUserService {


    ServerResponse<User> getUserInfo(String username);


}
