package com.xjs.mapper;


import com.xjs.pojo.IDFrontImg;
import com.xjs.pojo.IDNationalImg;
import com.xjs.pojo.Template;
import com.xjs.pojo.userPay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//用户信息管理
@Repository
public interface userPayMapper {
    /**
     * 登录
     * @param userPay
     * @return
     */
    userPay login(userPay userPay);
   userPay selectLogin(userPay userPay);
   int loginCheck(@Param("phone")String phone );
    /**
     * 添加用户
     * @param userPay
     * @return
     */
    int addUserPay(userPay userPay);

    /***
     * 根据id删除用户
     * @param id
     * @return
     */

    int deleteUserById(String id);

    /**
     * 根据id修改用户信息
     * @param userPay
     * @return
     */
    int updateUserPay(userPay userPay);

    /**
     * 查詢用戶根据id
     * @param id
     * @return
     */
    userPay findUserPayById(@Param("id") String id);





    /**
     * @魔板管理
     */

    /**
     * 添加模板
     * @param template
     * @return
     */
    int addTemplate(Template template);

    /**
     * 根据模板id删除模板
     * @param Id
     * @return
     */
    int deleteTemplateById(String Id);
    /**
     * 批量删除模板
     * @param ids
     * @return
     */
    int batchDeleteTemplateById(List<String> ids);
    /**
     * 根据模板id修改模板
     */
    int  updateTemplateById(Template template);

    /**
     * 根据用户电话查询模板信息
     * @param phone
     * @return
     */
    List<Template> selectTemplateByPhone(String phone);

    /**
     * 根据模板id查询模板
     * @param Id
     * @return
     */
    Template selectTemplateById(String Id);

    /**
     * 添加模板
     * @param iDFrontImg
     * @return
     */
    int addIdFrontImg(IDFrontImg iDFrontImg);

    /**
     * 添加模板
     * @param iDNationalImg
     * @return
     */
    int addIdNationalImg(IDNationalImg iDNationalImg);

}
