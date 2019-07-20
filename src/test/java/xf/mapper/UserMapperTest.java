package xf.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xf.mytest.AcTest;
import xf.pojo.User;

/**
 * 作者:小爱艾
 * 2019/7/15 15:03
 */
public class UserMapperTest extends AcTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findCountByUsername(){
        Integer admin = userMapper.findCountByUsername("admin1");
        System.out.println(admin);
    }

    @Test
    public void save(){
        User user = new User();
        user.setUsername("xxx");
        user.setPassword("23243523");

        userMapper.save(user);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("admin");
        System.out.println(user.toString());
    }

}