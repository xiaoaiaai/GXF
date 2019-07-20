package xf.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xf.mytest.AcTest;
import xf.pojo.Item;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作者:小爱艾
 * 2019/7/17 21:55
 */
public class ItemServiceTest extends AcTest {

    @Autowired
    private ItemService itemService;
    
    @Test
    @Transactional
    public void del(){

        Integer del = itemService.del((long) 1);
        System.out.println(del);
    }

    @Test
    @Transactional
    public void save(){

        Item item = new Item();
        item.setName("XXXX");
        item.setPrice(new BigDecimal(1.1));
        item.setProductionDate(new Date());
        item.setDescription("mmmmmmm");
        item.setPic("zzzzzzzz");
        Integer save = itemService.save(item);
        System.out.println("受影响行数：" +save);

    }
    
}