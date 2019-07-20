package xf.mapper;

import org.apache.ibatis.annotations.Param;
import xf.pojo.Item;

import java.util.List;

/**
 * 作者:小爱艾
 * 2019/7/16 11:37
 */
public interface ItemMapper {

    //1 查询商品总条数
    Long findCountByName(@Param("name") String name);

    //2 分页查询商品的具体信息
    List<Item> findItemByNameLikeAndLimit(@Param("name")String name,
                                          @Param("offset")Integer offset,
                                          @Param("size")Integer size);


    //3 添加商品
    Integer save(Item item);

    //4 删除商品
    Integer delById(@Param("id")Long id);


    //5 更新商品
    Integer UpdateById(Item item);
    //6 根据id查询商品
    Item findById(@Param("id")Long id);
}
