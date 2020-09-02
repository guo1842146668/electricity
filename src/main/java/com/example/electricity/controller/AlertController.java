package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.service.IAlertService;
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

    @GetMapping("/getAlert")
    public Result getAlert(Integer userID,String condition,String startDate,String endDate){
        Map<String,Object> map=new HashMap<>();
        map.put("userID",userID);
        map.put("condition",condition);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        return ResultUtil.seccess(iAlertService.getAlert(map));
    }
}
