package com.util;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * Created by victor on 2018/2/28.
 */
public class WebUtil {
    /**
     * controller向view层返回信息的方法
     * @param request
     * @param response
     * @param s
     * @throws IOException
     */
    public static void outputPage(HttpServletRequest request, HttpServletResponse response, String s) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printwriter = response.getWriter();
        printwriter.print(s);
        printwriter.close();
    }
    /**
     * 根据传进来的参数拼 hql的where条件
     * @param request
     * @return
     */
    public static String getWhereCondition(HttpServletRequest request){
        StringBuilder condition = new StringBuilder();
        String fields = request.getParameter("fields");
        if(!StringUtil.isEmptyString(fields)){
            JSONObject json = JSONObject.fromObject(fields);
            Iterator keyIter = json.keys();
            String key;
            String value;
            while( keyIter.hasNext())
            {
                key = (String)keyIter.next();
                value = (String)json.get(key);
                if(!StringUtil.isEmptyString(value)){
                    condition.append(" and "+key+"='"+value+"'");
                }
            }
        }
        return condition.toString();
    }
    public static String md5(String plainText) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(plainText.getBytes());
            byte[] b = e.digest();
            StringBuffer buf = new StringBuffer("");

            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if(i < 0) {
                    i += 256;
                }

                if(i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return null;
        }
    }
}
