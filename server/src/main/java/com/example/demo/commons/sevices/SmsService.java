package com.example.demo.commons.sevices;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.commons.Controllers.SmsController;
import com.example.demo.commons.mappers.SmsMapper;
import com.example.demo.passport.Dto.QuerySentSmsExpire;
import com.example.demo.passport.Dto.SentSmsInput;
import com.example.demo.passport.entitys.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Service
public class SmsService {

    @Autowired
      private SmsMapper smsMapper;

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
                                            //access访问      Secret是秘密  密钥
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public Rtn<Boolean> sendSms(String phone, String code) throws  Exception{


        Client client = SmsService.createClient("LTAI4GKch5w2H9GrATRYn665", "B2REL70UhtzEqWd0sAk83U4dKN8SUA");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("邦为科技") //sign是标志、招牌的意思
                .setTemplateCode("SMS_127158851")  //template是模板、样板、标准的意思
                .setTemplateParam("{\"code\":\""+code+"\"}");  //param是参数的意思
        ;
        // 复制代码运行请自行打印 API 的返回值
        com.aliyun.dysmsapi20170525.models.SendSmsResponse response= client.sendSms(sendSmsRequest);
        return Rtn.Success(true);


    }


           //拿到近几分钟的最后一条短信  expire失效
    public  Sms getLastSmsExpire(String phoneNumber,int min){

                                     // 不理解？  1000ms = 1s
        Date now= new Date( new Date().getTime()-min*60*1000 );

                                     //当时说让写一个构造器  为了少写一些代码
        QuerySentSmsExpire a = new QuerySentSmsExpire(now,phoneNumber);
       // List<Sms> list = smsMapper.querySentSmsExpire(new QuerySentSmsExpire(now,phone ) );
        List<Sms> list = smsMapper.querySentSmsExpire( a );

            //size是大小或者号码的意思
        // 以下不理解
        if(list.size()>0) {
            return list.get(0);
        }
        return  null;
    }

//    Sms sms= this.smsService.getLastSmsExpire(dbUser.getPhonenumber(),5);
//        if(sms==null){
//        return   Rtn.Error("请先发短信");
//
//    }   else {
//        if(!sms.getCode().equals(userLoginDto.getCode())) {
//
//            return Rtn.Error("验证码错误");
//        }
//    }

    // 拿到最近发送5分钟的短信
    // 当前时间-发送时间 大于=5
    // 发送时间 23点01分10秒  当前时间23点06分10秒

//    <select id="querySentSmsExpire"  resultType="com.example.demo.passport.entitys.Sms" >
//    SELECT * FROM springboot.sms
//    where unix_timestamp(sms.sentAt) > unix_timestamp(#{sentAt})
//    and phoneNumber=#{phoneNumber} order by sentAt desc
//    </select>



}
