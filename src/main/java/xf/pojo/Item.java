package xf.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 作者:小爱艾
 * 2019/7/16 11:18
 */
//商品表对应的实体类
@Data
@Setter
@Getter
public class Item {
        //id
        private Long id;
        //商品名称
        @NotBlank(message = "商品名称为必填项，麒能不填")
        private String name;
        //商品价格
        @NotNull(message = "商品价格为必填项，麒能不填")
        private BigDecimal price;//引用数据类型
        //商品的生产日期
        //不转换会容易类型转化异常
        @NotBlank(message = "商品名称为必填项，麒能不填")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date productionDate;
        //商品的描述
        @NotBlank(message = "商品名称为必填项，麒能不填")
        private String description;
        //商品的图片
        private String pic;
        //商品的创建时间
        private Date created;

}
