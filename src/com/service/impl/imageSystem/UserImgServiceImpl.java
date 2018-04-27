package com.service.impl.imageSystem;

import com.entity.UserImg;
import com.exception.MessageException;
import com.service.imageSystem.UserImgService;
import com.service.impl.common.BaseServiceImpl;
import com.util.CollectionUtil;
import com.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by victor on 2018/3/13.
 */
@Service("userImgService")
public class UserImgServiceImpl extends BaseServiceImpl<UserImg> implements UserImgService {
    @Override
    public UserImg saveOrUpdateUserImg(UserImg entity) {
        if(entity.getUserImgId() != null && entity.getUserImgId() > 0){
            return this.update(entity);
        }else{
            return this.save(entity);
        }
    }

    @Override
    public UserImg loadUserImgByOpenid(String openid,String activityId) throws MessageException {
        if(StringUtil.isEmptyString(openid)){
            throw new MessageException().setErrorMsg("请进行微信认证");
        }
        String hql = "from UserImg where openid=:id and activityId=:activityId";
        List<UserImg> list = getSession().createQuery(hql).setString("id",openid)
                            .setString("activityId",activityId).list();
        if(!CollectionUtil.isEmptyCollection(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 检验是否已上传过图片
     * @param openid
     * @return
     * @throws MessageException
     */
    @Override
    public boolean isExist(String openid,String activityId) throws MessageException{
        if(StringUtil.isEmptyString(openid)){
            throw new MessageException().setErrorMsg("请进行微信认证");
        }
        String sql = "select 1 from user_img where openid=:id  and activity_Id=:activityId";
        List list = super.getSession().createSQLQuery(sql).setString("id",openid)
                    .setString("activityId",activityId).list();
        if(!CollectionUtil.isEmptyCollection(list)){
           return true;
        }
        return false;
    }

    @Override
    public String getImgeUrlById(long id) {
        String sql = "select img_url from user_img where 1=1 and user_img_id=:id";
        List<String> list = this.getSession().createSQLQuery(sql).setLong("id",id).list();
        if(!CollectionUtil.isEmptyCollection(list)){
            return list.get(0);
        }
        return "";
    }
}
