package xf.util;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static xf.constant.SsmConstant.CODE;

/**
 * 作者:小爱艾
 * 2019/7/16 20:36
 */
@Component
public class SendSMSUtil {

    @Value("${apikey}")
    private String apikey;

    public String sendSMS(HttpSession session,String phone){
        //初始化clnt，使用单例模式(需要导入依赖)
        YunpianClient clnt = new YunpianClient(apikey).init();
        //生成6位随机数
        int code = (int)((Math.random()*9+1)*100000);
        //将正确的验证码设置到session域中
        session.setAttribute(CODE, code);
        System.out.println(code);
        //发送短信API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, "【云片网】您的验证码是"+code);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);

        clnt.close();
        return r.getMsg();

    }





}










