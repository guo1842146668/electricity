package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.service.IDictService;
import com.example.electricity.tool.DictEnum;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@RequestMapping("/dict")
public class DictController {
    @Resource
    private IDictService iDictService;


    @GetMapping("/getGuide")
    public Result getGuide(){
        return ResultUtil.seccess(iDictService.getGuide(DictEnum.Guide));
    }

    @GetMapping("/getAgreement")
    public Result getAgreement(){
        return ResultUtil.seccess(iDictService.getGuide(DictEnum.Agreement));
    }

    @GetMapping("/getCustomer")
    public Result getCustomer(){
        return ResultUtil.seccess(iDictService.getGuide(DictEnum.Customer));
    }

    @GetMapping("/getType")
    public Result getType(){
        return ResultUtil.seccess(iDictService.getGuide(DictEnum.Type));
    }
}
