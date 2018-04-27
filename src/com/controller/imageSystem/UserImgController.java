package com.controller.imageSystem;

import com.entity.UserImg;
import com.exception.MessageException;
import com.service.imageSystem.UserImgService;
import com.util.StringUtil;
import com.util.WebUtil;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by victor on 2018/3/13.
 */
@Controller
@RequestMapping("/userImg")
public class UserImgController {
    @Resource
    private UserImgService userImgService;

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public void uploadImg(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        try{
            UserImg entity = new UserImg();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            entity.setUploadTime(sdf.format(new Date()));

            //图片信息
            // 1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
            ServletFileUpload sfu = new ServletFileUpload(factory);
            sfu.setFileSizeMax(3 * 1024 * 1024);//最大3M
            sfu.setHeaderEncoding("utf-8");
            // 3.
            // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            @SuppressWarnings("unchecked")
            List<FileItem> fileItemList = sfu.parseRequest(request);
            Iterator<FileItem> fileItems = fileItemList.iterator();
            // 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
            while (fileItems.hasNext()) {
                FileItem fileItem = fileItems.next();
                //表示普通元素
                if (fileItem.isFormField()) {
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    //用户信息
                    switch (fieldName){
                        case "userName" : entity.setUserName(value == null ? "" : value);
                            break;
                        case "telephone" : entity.setTelephone(value == null ? "" : value);
                            break;
                        case "openid" : entity.setOpenid(value == null ? "" : value);
                            break;
                        case "activityId" : entity.setActivityId(value == null ? 0 : Long.parseLong(value));
                            break;
                        case "userImgId" : entity.setUserImgId(StringUtil.isEmptyString(value) ? 0 : Long.parseLong(value));
                            break;
                    }
                }else{
                    String fileName = fileItem.getName();// 原文件名
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));//扩展名
                    String newFileName = "userImg/"+new Date().getTime() + suffix;//保存到服务器的图片名称

                    entity.setImgName(fileName.substring(0,fileName.lastIndexOf('.')));//不保存扩展名
                    entity.setImgUrl(newFileName);

                    // 5. 调用FileItem的write()方法，写入文件
                    File file = new File("d:\\image\\" + newFileName);
                    fileItem.write(file);
                    // 6. 调用FileItem的delete()方法，删除临时文件
                    fileItem.delete();
                }
            }

            if(entity.getUserImgId()  > 0){//编辑
                //图片修改时，删除旧图片
                String srcImge = this.userImgService.getImgeUrlById(entity.getUserImgId());//将旧数据load出来
                if(!entity.getImgUrl().equals(srcImge)){
                    File file = new File("d:\\image\\" + srcImge);
                    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
                    if (file.exists() && file.isFile()) {
                        if (!file.delete()) {
                            throw  new MessageException().setErrorMsg("删除原背景图片失败");
                        }
                    }
                }

            }
            entity = this.userImgService.saveOrUpdateUserImg(entity);
            result.put("success",1);
            result.put("entity",entity);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",0);
        }
        WebUtil.outputPage(request,response,result.toString());
    }

    /**
     * 通过openid获取用户上传的照片信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/loadUserImgByOpenid", method = RequestMethod.POST)
    public void loadUserImgByOpenid(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String openid = request.getParameter("openid");
        String activityId = request.getParameter("activityId");
        JSONObject result = new JSONObject();
        try {
            UserImg entity = this.userImgService.loadUserImgByOpenid(openid,activityId);
            result.put("success",1);
            result.put("userImg",entity);
        } catch (MessageException e) {
            e.printStackTrace();
            result.put("success",0);
            result.put("errorMsg",e.getErrorMsg());
        }
        WebUtil.outputPage(request,response,result.toString());
    }

    @RequestMapping(value = "/searchImage", method = RequestMethod.POST)
    public void searchImage(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        StringBuffer whereCondition = new StringBuffer();
        String userName = request.getParameter("userName");
        String telephone = request.getParameter("telephone");
        String uploadTimeBegin = request.getParameter("uploadTimeBegin");
        String uploadTimeEnd = request.getParameter("uploadTimeEnd");
        String activityId = request.getParameter("activityId");
        whereCondition.append(" and activityId=" + activityId);
        if(!StringUtil.isEmptyString(userName)){
            whereCondition.append(" and userName='" + userName + "'");
        }
        if(!StringUtil.isEmptyString(telephone)){
            whereCondition.append(" and telephone = '" + telephone + "'");
        }
        if(!StringUtil.isEmptyString(uploadTimeBegin)){
            whereCondition.append(" and uploadTime >= '" + uploadTimeBegin + "'");
        }
        if(!StringUtil.isEmptyString(uploadTimeEnd)){
            whereCondition.append(" and uploadTime <= '" + uploadTimeEnd + "'");
        }

        String pageStr=request.getParameter("page");
        int pageSize = 8;//每页显示9张图片
        int page = 1;
        if(!StringUtil.isEmptyString(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        String sort = "uploadTime";
        String order = "desc";
        List<UserImg> list = userImgService.query(whereCondition.toString(),(page-1)*pageSize,pageSize,sort,order);
        JSONObject o = new JSONObject();
        long total = userImgService.getCount(whereCondition.toString());//总记录数
        boolean isHasPre = page > 1  ? true : false;//是否显示上一页按钮，如果不为第一页则显示
        //是否显示下一页按钮，如果页码*每页记录数小于总记录且总记录数大于每页记录数则显示
        boolean isHasNext = page*pageSize < total && total > pageSize ? true : false;
        o.put("total",total );
        o.put("rows", list);
        o.put("isHasPre",isHasPre);
        o.put("isHasNext",isHasNext);
        WebUtil.outputPage(request, response, o.toString());
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int n = 0;//成功标志
        UserImg entity = this.userImgService.loadById(Long.parseLong(id));
        if(entity != null){
            String imageUrl = entity.getImgUrl();
            File file = new File("d:\\image\\" + imageUrl);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    this.userImgService.delete(entity);
                    n = 1;
                }
            }
        }

        JSONObject o = new JSONObject();
        if(n>0){
            o.put("success", "1");
        }else{
            o.put("errorMsg", "删除失败");
        }
        WebUtil.outputPage(request, response, o.toString());
    }

    /**
     * 通过openid判断用户是否已上传过
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/isExist", method = RequestMethod.POST)
    public void isExist(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String openid = request.getParameter("openid");
        String activityId = request.getParameter("activityId");
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();

        try {
            boolean isExist = this.userImgService.isExist(openid,activityId);
            o.put("success",1);
            o.put("isExist",isExist);
        } catch (MessageException e) {
            e.printStackTrace();
            o.put("errorMsg",e.getErrorMsg());
            o.put("success",0);
        }

        WebUtil.outputPage(request, response, o.toString());
    }

    /**
     * 通过url访问服务器的照片
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getImgByUrl", method = RequestMethod.GET)
    public void getImgByUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String path = "d:\\image\\" + request.getParameter("imgUrl");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        BufferedInputStream bis = null;
        OutputStream os = null;
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        bis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[512];
        response.reset();
        response.setCharacterEncoding("UTF-8");
        //不同类型的文件对应不同的MIME类型
        response.setContentType("image/png");
        //文件以流的方式发送到客户端浏览器
        response.setContentLength(bis.available());
        os = response.getOutputStream();
        int n;
        while ((n = bis.read(buffer)) != -1) {
            os.write(buffer, 0, n);
        }
        bis.close();
        os.flush();
        os.close();
    }
}
