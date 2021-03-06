package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.entity.User;
import com.example.electricity.service.IAlertService;
import com.example.electricity.service.ISysUserService;
import com.example.electricity.tool.ReadFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@CrossOrigin
@RestController
@RequestMapping("/alert")
public class AlertController {
    @Resource
    private IAlertService iAlertService;
    @Resource
    private ISysUserService iSysUserService;

    @GetMapping("/getAlert")
    public Result getAlert(Integer userID,String condition,String startDate,String endDate,Integer page,Integer count){
        if(page == null || count == null){
            return ResultUtil.error(500,"分页 页数或条数不能为null") ;
        }
        if (!"".equals(condition) && condition != null) {
            condition = ReadFile.specialStr(condition);// 排除%等通配符
            condition = ReadFile.specialStrKeyword(condition);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userID",userID);
        map.put("condition",condition);
        map.put("startDate",startDate);
        map.put("endDate",endDate);

        User byID = iSysUserService.getByID(userID);
        if(byID.getType().equals(1)){
            return ResultUtil.seccess(iAlertService.getAlertAdmin(map,page,count));
        }else{
            return ResultUtil.seccess(iAlertService.getAlert(map,page,count));
        }

    }
}
