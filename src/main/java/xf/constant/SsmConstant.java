package xf.constant;

/**
 * 作者:小爱艾
 * 2019/7/16 19:23
 */
//自定义的一些常量
public interface SsmConstant {

    //重定向
    String REDIRECT  = "redirect";

    //放在session域中验证码的key
    String CODE = "code";

    //放在session域中的用户信息
    String USER = "user";

    //跳转到注册页面的路径
    String REGISTER_UI = "/user/register-ui";

    //跳转到登陆页面的路径
    String LOGIN_UI = "/user/login_ui";

    //跳转到添加页面的路径
    String ITEM_ADD_UI = "/item/add-ui";


}
