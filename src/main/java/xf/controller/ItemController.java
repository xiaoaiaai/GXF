package xf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xf.mapper.ItemMapper;
import xf.pojo.Item;
import xf.service.ItemService;
import xf.util.PageInfo;
import xf.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static xf.constant.SsmConstant.REDIRECT;

/**
 * 作者:小爱艾
 * 2019/7/16 11:12
 */
@Controller
@RequestMapping("/item")//映射请求路径，防止访问地址相同
public class ItemController {


    //商品的首页

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/list")
    //接收三个参数
    public String list(String name,
                       @RequestParam(value="page",defaultValue = "1")Integer page,
                       @RequestParam(value = "size",defaultValue = "5")Integer size,
                       Model model){

        //1 调用service查询数据
        PageInfo<Item> pageInfo = itemService.findItemByNameLikeAndLimit(name,page,size);
       //2 controller将pageInfo放到request域中，将接收到的商品名称name也放到request域中
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("name",name);//为了查询条件的回显

        //3 转发页面
        return "item/item_list";
    }


    //跳转到商品添加页面
    // /item/add-ui
    @GetMapping("/add-ui")
    public String addUI(String addInfo,Model model){
        model.addAttribute("addInfo",addInfo);
        return "item/item_add";
    }

//    @Value("${pic_types}")
//    public String picType;

    //添加商品信息
    @PostMapping("/add")
    public String add(@Valid Item item, BindingResult bindingResult, MultipartFile picFile,
                      HttpServletRequest request, RedirectAttributes redirectAttributes
                      ) throws IOException {


//        //校验参数
//        if(bindingResult.hasErrors()){
//            //获取具体信息
//            String message = bindingResult.getFieldError().getDefaultMessage();
//            //参数不合法
//            redirectAttributes.addAttribute("addInfo",message);
//          //跳转到添加商品页面
//            return REDIRECT+ITEM_ADD_UI;
//        }
//
//
//        //1 对图片大小做校验
//        long size = picFile.getSize();
//        if(size>5242880L){//图片过大，要求图片小于5M
//            redirectAttributes.addAttribute("addInfo","图片过大，要求小于5M");
//            //重定向到添加页面
//            return REDIRECT+ITEM_ADD_UI;
//        }
//
//        boolean flag = false;
//       // 2 对图片的类型做校验  jpg, png, gif
//        String[] types = picType.split(",");
//        for (String type : types) {
//            if(StringUtils.endsWithIgnoreCase(picFile.getOriginalFilename(), type)){
//                //图片类型正确
//
//                flag = true;
//                break;
//
//            }
//
//
//        }
//        if(!flag){
//            //图片类型不正确
//            redirectAttributes.addAttribute("addInfo","图片类型不正确，要求"+picType);
//            return null;
//
//        }
//        //3 校验图片是否损坏
//        BufferedImage image = ImageIO.read(picFile.getInputStream());
//        if(image == null){
//            //图片已损坏
//            redirectAttributes.addAttribute("addInfo","图片已损坏");
//            return  REDIRECT+ITEM_ADD_UI;
//        }
//
//        //=======================将图片保存到本地=====fastDFS============================
//
//        //给图片起一个新名字
//        String prefix = UUID.randomUUID().toString();
//        String suffix= StringUtils.substringAfterLast(picFile.getOriginalFilename(), ".");
//        String newName = prefix+"."+suffix;
//        //将图片保存到本地
//        String path = request.getServletContext().getRealPath("")+"\\static\\img\\"+newName;
//        File file = new File(path);
//        //健壮性判断
//        if(!file.getParentFile().exists()){
//
//            file.getParentFile().mkdirs();
//
//        }
//        picFile.transferTo(file);
//
//        //3 拿到并封装图片的访问路径
//        String pic = "http://localhost/static/img/"+newName;
//
//        //保存商品信息
//        item.setPic(pic);
//
//        //调用service保存商品信息
//        Integer count = itemService.save(item);
//        //判断count
//        if(count == 1){
//            return REDIRECT + "/item/list";
//        }else{
//            //添加商品信息失败
//            redirectAttributes.addAttribute("addInfo","添加商品信息失败");
//            return REDIRECT+ITEM_ADD_UI;
//        }
        return null;
    }





    @DeleteMapping("/del/{id}")//映射请求路径
    @ResponseBody//跳过视图解析器，将对象转成json（回调函数）
    public ResultVO del(@PathVariable Long id){
        //1 调用service删除商品
        Integer count = itemService.del(id);
        //2 根据结果给页面响应json
        if(count == 1){
            //删除成功
            return new ResultVO(0,"成功",null);
        }else{
            return new ResultVO(1,"删除商品失败",null);
        }
    }


    @GetMapping("/update-ui/{id}")
    public String updateUI(@PathVariable Long id,Model model){
        Item item = itemMapper.findById(id);
        model.addAttribute(item);
        return "item/item_update";
    }

    @PostMapping("/update")
    public String update( Item item){

        Integer integer = itemMapper.UpdateById(item);
        if(integer == 1){
            return REDIRECT+"item/item_list";

        }else{


                return REDIRECT+"item/item_add";
        }

    }

}









