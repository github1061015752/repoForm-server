package com.xjs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.pojo.VeteranImg;
import com.xjs.pojo.VeteranUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//用户信息管理
@Repository
public interface UserMapper extends BaseMapper<VeteranUser> {
    /**
     * 用户登录
     * @param loginName password
     * @return
     */
    VeteranUser login(@Param("loginName")String loginName ,@Param("password")String password);
    /**
     * 查詢用戶根据id
     * @param id
     * @return
     */
    VeteranUser findVeteranUserById(@Param("id") String id);

    /**
     * 添加用户
     * @param veteranUser
     * @return
     */
    int addVeteranUser(VeteranUser veteranUser);
    /**
     * 查询所有用户
     * @param veteranUser
     * @return
     */
    List<VeteranUser> selectAllVeteranUser(VeteranUser veteranUser);

    /**
     * 根据id修改用户信息
     * @param veteranUser
     * @return
     */
    int updateVeteranUser(VeteranUser veteranUser);

    /***
     * 根据id删除用户
     * @param id
     * @return
     */

    int deleteUserById(String id);

   //照片管理
    /**
     * 查詢照片根据id
     * @param id
     * @return
     */
    VeteranImg findVeteranImgById(Integer id);

    /**
     * 添加用户照片
     * @param veteranImg
     * @return
     */
    int addVeteranImg(VeteranImg veteranImg);
    /**
     * 查询所有用户照片
     * @param veteranImg
     * @return
     */
    List<VeteranImg> selectAllVeteranImg(VeteranImg veteranImg);

    /**
     * 根据id修改用户照片信息
     * @param veteranImg
     * @return
     */
    int updateVeteranImg(VeteranImg veteranImg);

    /***
     * 根据id删除用户照片
     * @param id
     * @return
     */
    int deleteVeteranImgById(Integer id);
    /***
     * 根据用户id删除用户照片
     * @param userId
     * @return
     */
    int deleteVeteranImgByUserId(Integer userId);


}
