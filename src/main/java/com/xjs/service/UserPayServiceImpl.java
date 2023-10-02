package com.xjs.service;


import com.xjs.mapper.userPayMapper;
import com.xjs.pojo.IDFrontImg;
import com.xjs.pojo.IDNationalImg;
import com.xjs.pojo.Template;
import com.xjs.pojo.userPay;
import com.xjs.util.random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserPayServiceImpl implements UserPayService {


    @Autowired
    private userPayMapper userPayMapper;


    @Override
    public userPay login(userPay userPay) {

        return userPayMapper.login(userPay);
    }

    @Override
    public userPay selectLogin(userPay userPay) {
        return userPayMapper.selectLogin(userPay);
    }

    @Override
    public int loginCheck(String phone) {
        return userPayMapper.loginCheck(phone);
    }

    @Override
    public int addUserPay(userPay userPay) {
        userPay.setId(UUID.randomUUID().toString());
        userPay.setPassword(random.getEightRandom().toString());//生成8位随机数
        return userPayMapper.addUserPay(userPay);
    }

    @Override
    public int deleteUserById(String id) {
        return userPayMapper.deleteUserById(id);
    }

    @Override
    public int updateUserPay(userPay userPay) {
        return userPayMapper.updateUserPay(userPay);
    }

    @Override
    public userPay findUserPayById(String id) {
        return userPayMapper.findUserPayById(id);
    }

    @Override
    public int addTemplate(Template template) {
        String id = UUID.randomUUID().toString();
        template.setId(id);
        template.setCreateTime(new Date());

        /*template.getIdFrontImg().setId(UUID.randomUUID().toString());
        template.getIdFrontImg().setCreateTime(new Date());
        template.getIdFrontImg().setTemplateId(id);

        userPayMapper.addIdFrontImg(template.getIdFrontImg());

        template.getIdFrontImg().setId(UUID.randomUUID().toString());
        template.getIdNationalImg().setId(UUID.randomUUID().toString());
        template.getIdNationalImg().setCreateTime(new Date());
        template.getIdNationalImg().setTemplateId(id);

        userPayMapper.addIdNationalImg(template.getIdNationalImg());*/
        return userPayMapper.addTemplate(template);
    }

    @Override
    public int deleteTemplateById(String Id) {
        return userPayMapper.deleteTemplateById(Id);
    }

    @Override
    public int batchDeleteTemplateById(List<String> ids) {
        return userPayMapper.batchDeleteTemplateById(ids);
    }

    @Override
    public int updateTemplateById(Template template) {
        return userPayMapper.updateTemplateById(template);
    }

    @Override
    public List<Template> selectTemplateByPhone(String phone) {
        return userPayMapper.selectTemplateByPhone(phone);
    }

    @Override
    public Template selectTemplateById(String Id) {
        return userPayMapper.selectTemplateById(Id);
    }

    @Override
    public int addIdFrontImg(IDFrontImg iDFrontImg) {
        return userPayMapper.addIdFrontImg(iDFrontImg);
    }

    @Override
    public int addIdNationalImg(IDNationalImg iDNationalImg) {
        return userPayMapper.addIdNationalImg(iDNationalImg);
    }


}
