package xf.util;

import lombok.Data;

import java.util.List;

/**
 * 作者:小爱艾
 * 2019/7/16 11:41
 */
@Data
public class PageInfo<T> {

    private Integer page; //已知项

    private  Integer size;

    private Long total;

    private Integer pages;

    private Integer offset; //(page-1)*size

    private List<T> list; //查询得到

    //只提供了有参构造，并且其中两个是通过另外三个计算得出来的
    public PageInfo(Integer page, Integer size, Long total) {
        this.page = page <= 0?1:page;
        this.size = size<=0?5:size;
        this.total = total<0?0:total;
        //计算出pages和offset;
        this.pages =(int) (this.total%this.size==0?this.total/this.size:this.total/this.size+1);

        this.offset = (this.page-1)*this.size;
    }
}


