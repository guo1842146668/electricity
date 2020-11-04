package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.entity.Equipment;
import com.example.electricity.entity.User;
import com.example.electricity.service.IEquipmentService;
import com.example.electricity.service.ISysUserService;
import com.example.electricity.tool.ReadFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
@RequestMapping("/equipment")
public class EquipmentController {
    @Resource
    private IEquipmentService iEquipmentService;
    @Resource
    private ISysUserService iSysUserService;
    /**
     *  获取信息列表
     * @param userID      当前登陆用户的ID
     * @param condition   筛选条件
     * @return   查询的数据
     */
    @GetMapping("/getAllByUserID")
    public Result getAllByUserID(Integer userID,String condition){
        if (!"".equals(condition) && condition != null) {
            condition = ReadFile.specialStr(condition);// 排除%等通配符
            condition = ReadFile.specialStrKeyword(condition);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userID",userID);
        map.put("condition",condition);
        User byID = iSysUserService.getByID(userID);
        if(byID.getType().equals(1)){
            return ResultUtil.seccess(iEquipmentService.getAllByIDAdmin(map));
        }else{
            return ResultUtil.seccess(iEquipmentService.getAllByID(map));
        }

    }

    @GetMapping("/getEquipmentNO")
    public Result getEquipmentNO(String equipmentNO){
        return ResultUtil.seccess(iEquipmentService.getEquipmentNO(equipmentNO));
    }

    @PutMapping("/modify")
    public Result getEquipmentByEquipmentNO(String equipmentNO,Integer equipmentType,String equipmentAddress){
        Equipment equipmentByEquipmentNO = iEquipmentService.getEquipmentByEquipmentNO(equipmentNO);
        equipmentByEquipmentNO.setEquipmentType(equipmentType);
        equipmentByEquipmentNO.setEquipmentAddress(equipmentAddress);
        if(iEquipmentService.updateEquipment(equipmentByEquipmentNO) > 0){
            return  ResultUtil.seccess(true);
        }
        return  ResultUtil.seccess(false);
    }

    @PutMapping("open")
    public Result open(String equipmentNO){
        Map<String, Object> equipmentNO1 = iEquipmentService.getEquipmentNO(equipmentNO);
        if(equipmentNO1.get("OperationMode").equals("1")){
            return ResultUtil.error(500,"当前为手动模式，无法操作！");
        }
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://127.0.0.1:80/equipment/open?equipmentNO=" + equipmentNO, String.class);
        if(forObject.equals("success")){
            return ResultUtil.seccess();
        }else{
            return ResultUtil.error(500,"开启失败");
        }
    }

    @PutMapping("down")
    public Result down(String equipmentNO){
        Map<String, Object> equipmentNO1 = iEquipmentService.getEquipmentNO(equipmentNO);
        if(equipmentNO1.get("OperationMode").equals("1")){
            return ResultUtil.error(500,"当前为手动模式，无法操作！");
        }
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://127.0.0.1:80/equipment/down?equipmentNO=" + equipmentNO, String.class);
        if(forObject.equals("success")){
            return ResultUtil.seccess();
        }else{
            return ResultUtil.error(500,"关闭失败");
        }
    }

    @PutMapping("read")
    public Result read(String equipmentNO){
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://127.0.0.1:80/equipment/read?equipmentNO=" + equipmentNO, String.class);
        if(forObject.equals("success")){
            return ResultUtil.seccess();
        }else{
            return ResultUtil.error(500,"关闭失败");
        }
    }
}
