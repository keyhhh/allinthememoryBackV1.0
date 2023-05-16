package com.cyh.allinthememory.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import darabonba.core.exception.ClientException;

/**
 * 短信发送工具类
 */
public class SMSUtils {


    /**
     * 发送短信
     *
     * @param signName 签名
     * @param templateCode 模板
     * @param phoneNumbers 手机号
     * @param param 参数
     */

    public static final String SignName = "忆无涯";
    public static final String TemplateCodeCapsule = "SMS_460780141";
    public static final String TemplateCodeUrgent = "SMS_460735214";
    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }



    /**
     * @param phoneNumber:
      * @return void
     * @author 宇恒
     * @description TODO 发送胶囊到达时间的信息
     * @date 2023/5/13 15:22
     */
    public static void sendMsg(String phoneNumber, String templateCode) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SMSUtils.createClient(AccessKey_ID, AccessKey_Secret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName(SignName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            //自行打印返回值
            System.out.println(sendSmsResponse.getBody());
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

}
