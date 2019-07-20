package xf.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xf.mytest.AcTest;
import xf.pojo.Item;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作者:小爱艾
 * 2019/7/17 21:27
 */
//测试类继承AcTest测试父类
public class ItemMapperTestTest extends AcTest {

    @Autowired
    private  ItemMapper itemMapper;

    @Test
    @Transactional
    public void del(){
        Integer integer = itemMapper.delById(2L);

        System.out.println(integer);
    }

    @Test
    @Transactional
    public void add(){
        Item item = new Item();
        item.setName("XXXX");
        item.setPrice(new BigDecimal(1.1));
        item.setProductionDate(new Date());
        item.setDescription("mmmmmmm");
        item.setPic("zzzzzzzz");
        Integer save = itemMapper.save(item);
        System.out.println("受影响行数：" +save);
    }

    @Test
    @Transactional
    public void update(){
        Item item=new Item();
        item.setId(4L);
        item.setName("中国华为");
        item.setPrice(new BigDecimal(45645));
        item.setProductionDate(new Date());
        item.setDescription("真他妈牛逼");
        item.setPic("hhhhhhhh");
        Integer integer = itemMapper.UpdateById(item);

        System.out.println(integer);
    }
    @Test
    public void findById(){
        Item byId = itemMapper.findById(1L);
        System.out.println("受影响行数为："+byId);

    }

}