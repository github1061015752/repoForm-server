package com.xjs.controller;


import com.apistd.uni.UniResponse;
import com.xjs.pojo.Template;
import com.xjs.pojo.VeteranUser;
import com.xjs.pojo.userPay;
import com.xjs.service.UserPayService;
import com.xjs.util.CacheConfig;
import com.xjs.util.Example;
import com.xjs.util.ExcelUtil;
import com.xjs.util.random;
import com.xjs.vo.SysResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin    //前后端进行跨域操作
public class UserController {
    @Autowired
    CacheConfig cacheConfig;
    @Autowired
    private UserPayService userService;
    /**用户管理*****************************************************/
    /**
     * @获取验证码
     */
    @GetMapping("/getVerificationCode")
    public SysResult getVerificationCode(@Param("phone")String phone,@Param("code")String code ){
        if(phone.isEmpty()){
            return SysResult.failMsg("请输入手机号");
//        if(code.isEmpty()){
//            return SysResult.failMsg("请输入验证码"); //表示失败!!
//
         }else {
            String firstCode=random.getSixRandom();

            UniResponse res=Example.sendSms(phone,firstCode);
            if(res.code.equals("0")){
                //把验证码存起来
                return SysResult.success(firstCode);
            }else{
                return SysResult.failMsg("发送失败");
            }


        }
    }

          /**
         * @登录
         */
        @GetMapping("/login")
        public SysResult login(@Param("phone")String phone,@Param("code")String code ){
            userPay userPay=new userPay();
            userPay token=new userPay();
            if(phone.isEmpty()){
                return SysResult.failMsg("请输入手机号"); //表示用户登录失败!!
            }
            userPay.setPhone(phone);
            int  addusr=0;
            System.out.println("phone:"+phone);
            try{
                /// Cache demoCache= cacheConfig.cacheManager().getCache("codeCache");
                // String valueCode=demoCache.get(0,String.class);

                System.out.println("phone:"+phone);
                int res=userService.loginCheck(phone);
                if(res>=1){
                    return SysResult.success(token); //表示用户存在!!
                }else{
                    userService.addUserPay(userPay);
                    return SysResult.success(token);//表示用户存在!!
                }


            }catch (Exception e){
                e.printStackTrace();
                return SysResult.fail();
            }


    }


    /**
     * @登录 根据uuid登录
     */
    @GetMapping("/selectLogin")
    public SysResult selectLogin(@Param("phone")String phone,@Param("uuid")String uuid ){
        userPay userPay=new userPay();
        if(phone.isEmpty()){
            return SysResult.failMsg("请输入手机号");
        }
        if(uuid.isEmpty()){
            return SysResult.failMsg("请输入唯一标识");
        }
        userPay.setPhone(phone);
        userPay.setPassword(uuid);
        System.out.println("phone:"+phone);
        try{
            System.out.println("phone:"+phone+"uuid"+uuid);
            userPay user=userService.selectLogin(userPay);
                return SysResult.success(user);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.fail();
        }


    }



    /**
     * 添加模板
     */
    //@ResponseBody
    @PostMapping("/addTemplate")
    public SysResult addTemplate( @RequestBody Template templates){
      System.out.println(templates);
      int result=  userService.addTemplate(templates);
      if(result>0){
          return SysResult.success(); //200
      }else {
          return SysResult.failMsg("添加失败"); //200
      }


    }
    /**
     * 查根据用戶电话查询对应的模板
     * @param phone
     * @return
     */
    @GetMapping("/selectTemplateByPhone")
    public SysResult selectTemplateByPhone(@Param("phone")String phone ){
        List<Template> ListTemplate=  userService.selectTemplateByPhone(phone);
        if(ExcelUtil.isObjectNull(ListTemplate)){
            return SysResult.fail();//201

        }else {
            return SysResult.success(ListTemplate); //200
        }


    }

    /**
     * 根据id修改用户信息
     * @param userPay
     * @return
     */
    @PostMapping("/updateUserPay")
    public SysResult updateUserPay(@RequestBody userPay userPay){

        int result=  userService.updateUserPay(userPay);
        return SysResult.success(result); //200

    }
    /***
     * 根据id删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById")
    public SysResult deleteUserById(@Param("id")String id){
        int result=  userService.deleteUserById(id);
        return SysResult.success(result); //200

    }

    /**
     * 根据id修改模板信息
     * @param template
     * @return
     */
    @PostMapping("/updateTemplateById")
    public SysResult updateTemplateById(@RequestBody Template template){

        int result=  userService.updateTemplateById(template);
        return SysResult.success(result); //200

    }
    /***
     * 根据模板id删除模板
     * @param id
     * @return
     */
    @GetMapping("/deleteTemplateById")
    public SysResult deleteTemplateById(@Param("id")String id){
        int result=  userService.deleteTemplateById(id);
        return SysResult.success(result); //200

    }

    @RequestMapping("batchDeleteTemplateById")
    public SysResult batchDeleteTemplateById(@RequestBody Map<String,Object> map) {
        List<String> ids = Arrays.asList(map.get("ids").toString().split(","));
        userService.batchDeleteTemplateById(ids);
        return SysResult.success();
    }
    /***
     * 根据模板id查询模板
     * @param id
     * @return
     */
    @GetMapping("/selectTemplateById")
    public SysResult selectTemplateById(@Param("id")String id){
        Template template=  userService.selectTemplateById(id);
        return SysResult.success(template); //200

    }





    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoSavePath;   //图标图片存储路径
    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath;   //图标映射路径
    /**
         * 头像上传
          * @param fileUpload 图片资源
          * @return 图映射的虚拟访问路径
          */
    @PostMapping("profilePhotoUpload")
    @ResponseBody
    public String profilePhotoUpload(@RequestParam("file") MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID()+suffixName;
        try {

            File file = new File(ProfilePhotoSavePath+fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //将图片保存到文件夹里
            fileUpload.transferTo(new File(ProfilePhotoSavePath+fileName));
            return ProfilePhotoMapperPath+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 导出设备信息数据列表
     * @param vo
     * @param request
     * @param response
     * @return: xxx.utils.util.CommonResponse
     * @author Mike-GY
     * @since: 2022/8/2
     */
    @RequestMapping(value ="/exportDeviceInfoList" ,method = RequestMethod.POST)
    public void exportDeviceInfoList(@RequestBody VeteranUser vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //通过前端获取公司ID，即保证只能查询和导出自己公司的数据
       // List<String> listId=companyId = Integer.parseInt(HttpUtil.getCompanyId(request));
        vo.setStatus(1);
        //创建一个新的.xls文件
        ServletOutputStream outputStream = response.getOutputStream();
        //调用service层的代码完成具体实现
       // workerHomePageV2Service.exportDeviceInfoList(vo,outputStream);
        //关闭并释放输出流资源
        outputStream.close();
    }




}


