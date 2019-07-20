package xf.service;

import xf.pojo.Item;
import xf.util.PageInfo;

/**
 * 作者:小爱艾
 * 2019/7/16 11:36
 */

//item的service层接口
public interface ItemService {

    //分页查询商品信息
    PageInfo<Item> findItemByNameLikeAndLimit(String name, Integer page, Integer size);

    //添加商品
    Integer save(Item item);
    //根据id删除商品
    Integer del(Long id);

}
