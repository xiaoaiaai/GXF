package xf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xf.pojo.User;
import xf.service.UserService;
import xf.util.SendSMSUtil;
import xf.vo.ResultVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static xf.constant.SsmConstant.CODE;
import static xf.constant.SsmConstant.USER;

/**
 * 作者:小爱艾
 * 2019/7/15 10:28
 *
 *
 */

//用户模块的controller层
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private SendSMSUtil sendSMSUtil;

    //跳转到注册页面
    @GetMapping("/register-ui")
    public String registerUI(){

        //转发到注册页面
        return "user/register";//地址写对
    }



    @PostMapping("/check-username")
    @ResponseBody   //不走视图解析器，直接响应；如果返回结果为Map/pojo类，自动序列化为json
    public ResultVO checkUsername(@RequestBody User user){

        //1 json数据需要反序列化成pojo对象

        Integer count = userService.checkUsername(user.getUsername());
//
//        //2 使用Map响应
//        // 封装响应数据的Map
//        Map<String,Object> result = new HashMap<>();
//        result.put("data", "count");
//        result.put("code", 0);
//        result.put("msg", "成功");
//        //3 返回
        //return result;

        //使用ResultVO响应数据
        return new ResultVO(0,"成功",count);




    }


    //发送短信
    //Request URL:http://localhost/user/send-sms
    //Request Method:POST
//    @PostMapping(value = "/send-sms",produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public String sendSMS(@RequestParam String phone, HttpSession session){
//
//        //1 调用工具发短信
//        String result = sendSMSUtil.sendSMS(session,phone);
//        //2 将result响应给ajax引擎
//        return result;
//    }

    //执行注册
//    @PostMapping("/register")
//    public String register(@Valid User user, BindingResult bindingResult, String registercode, HttpSession session, RedirectAttributes redirectAttributes){
//    //1 校验验证码
//        if(!StingUtils.isEmpty(registercode){
//
//
//
//        }
//
//
//    }
//

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, String registerCode, HttpSession session, RedirectAttributes redirectAttributes) {

        //1 校验验证码
        if (!StringUtils.isEmpty(registerCode)) {
            //验证码不能为空
            String code = (String) session.getAttribute(CODE);
            if (!registerCode.equals(code)) {
                //验证码不正确
                redirectAttributes.addAttribute("registerInfo", "验证码不能为空");
                return "redirect:/user/register";
            }

            //2 校验参数是否合法
            if (bindingResult.hasErrors() || StringUtils.isEmpty(registerCode)) {
                //参数不合法
                String msg = bindingResult.getFieldError().getDefaultMessage();
                if (StringUtils.isEmpty(msg)) {

                    msg = "验证码为必填项，岂能不填！";

                }

                redirectAttributes.addAttribute("registerName", msg);
                return "redirect:/user/register";


            }
            //3 调用service执行注册功能
            Integer count = userService.register(user);
            if (count == 1) {
                //注册成功
                return "redirect:/user/login-ui";

            } else {
                //注册失败
                redirectAttributes.addAttribute("redirectInfo", "对不起，注册失败!");
                return "redirect:/user/register";
            }


        }
            return null;
    }




    //跳转到登录页面
    @GetMapping("/login-ui")
    public String loginUI(){

        return "user/login";


    }

    //执行登录(需要添加多个参数，故使用post请求)
    @PostMapping("/login")
    @ResponseBody//前端传递给传递后端json字符串
    public ResultVO login(String username,String password,HttpSession session){

        //1 校验参数是否合法
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            //参数不合法

            return new ResultVO(1,"用户名和密码为必填项没岂能不填",null);


        }
        //2 调用service执行登录
        User user = userService.login(username,password);
        //3 根据service返回结果判断是否登录成功
        if(user != null){
            //4 若登录成功，将用户的信息放到session域中
            session.setAttribute(USER,user);
            return new ResultVO(0,"登录成功",null);
        }else{
            //5 如果失败，响应错误信息给ajax引擎
            return new ResultVO(2,"登录失败",null);

        }



    }




}
