package xf.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xf.mapper.UserMapper;
import xf.pojo.User;
import xf.service.UserService;


/**
 * 作者:小爱艾
 * 2019/7/15 15:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer checkUsername(String username) {
        //健壮性代码
        if(!StringUtils.isEmpty(username)){

            username = username.trim();//去除两边的空格
        }
        Integer count = userMapper.findCountByUsername(username);
        return count;
    }

    @Override
    public Integer register(User user) {
       //1 对密码加密
        String newPwd = new Md5Hash(user.getPassword(),null,1024).toString();
        user.setPassword(newPwd);
        //2 调用mapper保存数据
        Integer count = userMapper.save(user);
        //3 返回数据
        return count;
    }

    @Override
    public User login(String username, String password) {
        //1 根据用户名查询用户信息
        User user = userMapper.findByUsername(username);
        //2 判断查询的结果是否为null
        if(user != null){
            //2.1 如果不为null，则判断密码
            String newPwd = new Md5Hash(password,null,1024).toString();
            //3 如果密码正确，直接返回查询到user
            if(user.getPassword().equals(newPwd)){
                //登陆成功
                return user;


            }

        }

        //4 其他情况统一返回null
        return null;
    }
}
