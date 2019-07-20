package xf.service;

import xf.pojo.User;

/**
 * 作者:小爱艾
 * 2019/7/16 21:26
 */
public interface UserService {

    //根据用户名查询是否可用
    Integer checkUsername(String username);

    //注册功能
    Integer register(User user);

    //执行登录
    User login(String username,String password);


}











