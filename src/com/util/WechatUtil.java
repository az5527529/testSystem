package com.util;

import com.vo.WechatVo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 2018/3/9.
 */
public class WechatUtil {
    static Logger  logger = LoggerFactory.getLogger(WechatUtil.class);
    /**
     * 获取access_token已经openid
     * @param code
     * @return
     */
    public static WechatVo getWechatVo(String code){
        logger.info("start:{},code=:{}",System.currentTimeMillis(),code);
        WechatVo vo = new WechatVo();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatConfiguration.getAppid()+"&secret="+WechatConfiguration.getSecret()+"&code="+code+"&grant_type=authorization_code";//请求的url
        Map<String,String> params = new HashMap<String, String>();
        String doPost = HttpTookit.doGet(url, params);//获取access_token
        JSONObject tempRespJson = JSONObject.fromObject(doPost);
        Object openidObj =  tempRespJson.get("openid");
        Object access_tokenObj =  tempRespJson.get("access_token");
        String openid = openidObj==null?"":openidObj.toString();
        String access_token = access_tokenObj==null?"":access_tokenObj.toString();
        vo.setAccessToken(access_token);
        vo.setOpenid(openid);
        logger.info("start:{},vo=:{}",System.currentTimeMillis(),vo.toString());
        return vo;
    }
}
