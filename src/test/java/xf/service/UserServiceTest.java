package xf.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xf.mytest.AcTest;
import xf.pojo.User;

/**
 * 作者:小爱艾
 * 2019/7/15 22:49
 */
public class UserServiceTest extends AcTest {
        @Autowired
        private UserService userService;

        @Test
        public void checkUsername(){

            Integer admin = userService.checkUsername("admin11");
            System.out.println(admin);

        }

        @Test
        public void login(){
            User login = userService.login("admin", "admin");
            System.out.println(new Md5Hash("admin",null,1024).toString());
            System.out.println(login);
        }





}











