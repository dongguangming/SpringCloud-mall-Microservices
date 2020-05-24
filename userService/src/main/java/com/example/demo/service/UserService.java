package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

/**
 * 
 * @author dgm
 * @describe "用户服务"
 * @date 2020年5月20日
 */
public interface UserService extends BaseService<User> {

	User findByUsername(String username);
    Boolean comparePassword(String pwd, String inputpwd);
    String getToken(User user);
    int createUser(User user);
    Boolean isUsernameValid(String username);
    int getClusterUsageAll();
    int getClusterUsageByUserLevel(Integer level,Integer system);
    int resetUserExpStatus(User user);
    int resetUserFreeStatus(User user);
    int resetUserInitStatus(Long uid);
    int setUserToken(Long uid, String token);
	List<User> listUser(Long uid);
}
