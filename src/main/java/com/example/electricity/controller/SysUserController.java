package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.entity.User;
import com.example.electricity.service.ISysUserService;
import com.example.electricity.tool.ClassIsNull;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-05
 */
@CrossOrigin
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService ISysUserService;

    @ApiOperation(value = "登陆",notes = "登陆接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userAccount", value = "账户", required = true,dataType = "String"),
            @ApiImplicitParam(name = "userPassword", value = "原始密码", required = true,dataType = "String")
    })
    @GetMapping("/login")
    public Result Login(String userAccount, String userPassword){
        String s = new Md5Hash(userPassword, ISysUserService.getSalt(userAccount)).toString();
        User user = ISysUserService.Login(userAccount, s);
        if(ClassIsNull.isNull(user)){
            return  ResultUtil.error(500,"账户或密码错误！");
        }
        return   ResultUtil.seccess(user);
    }

    @ApiOperation(value = "修改密码",notes = "修改密码接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userID", value = "账户", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "userPassword", value = "原始密码", required = true,dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true,dataType = "String")
    })
    @PutMapping("/modifyPassword")
    public Result modifyPassword(Integer userID, String userPassword,String newPassword){
        User user = ISysUserService.getByID(userID);
        String s = new Md5Hash(userPassword, user.getSalt()).toString();
        if(!user.getPassword().equals(s)){
            return  ResultUtil.error(500,"原密码错误！");
        }
        user.setPassword(new Md5Hash(newPassword, user.getSalt()).toString());
        if(ISysUserService.UpdateUser(user) > 0){
            return ResultUtil.seccess(true);
        }
        return ResultUtil.seccess(false);
    }

    @ApiOperation(value = "上传头像",notes = "上传头像接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "file", value = "文件流，接受图片", required = true,dataType = "MultipartFile",allowMultiple = true),
    })
    @PostMapping(value="/uploadImage")
    @ResponseBody
    public Result uploadImage(@RequestParam(value="file") MultipartFile file, Integer userID){
        User user = ISysUserService.getByID(userID);
        if(ClassIsNull.isNull(user)){
            return  ResultUtil.error(500,"未查询到用户");
        }
        String resource = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("/")).getPath();
        String url="image" + File.separator + File.separator + userID;
        File dest = new File(resource+"static"+ File.separator+File.separator +url);
        if(dest.exists()) {
            dest.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        if(originalFilename == null){
            return  ResultUtil.error(500,"请上传正确的图片");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        url +=File.separator + File.separator + filename;
        String targetUploadPath = dest + File.separator + filename;
        try {
            FileUtils.writeByteArrayToFile(new File(targetUploadPath), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(400,"储存错误");
        }
        user.setActiveCode(url);
        if( ISysUserService.UpdateUser(user) > 0){
            return  ResultUtil.seccess(true);
        }
        return  ResultUtil.seccess(false);
    }

    @ApiOperation(value = "根据ID查询用户",notes = "根据ID查询用户接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userID", value = "账户", required = true,dataType = "Integer"),
    })
    @GetMapping("/getByID")
    public Result getByID(Integer userID){
        return ResultUtil.seccess(ISysUserService.getByID(userID));
    }
}
