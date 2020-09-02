package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.entity.Equipment;
import com.example.electricity.service.IEquipmentService;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  获取信息列表
     * @param userID      当前登陆用户的ID
     * @param condition   筛选条件
     * @return   查询的数据
     */
    @GetMapping("/getAllByUserID")
    public Result getAllByUserID(Integer userID,String condition){
        Map<String,Object> map=new HashMap<>();
        map.put("userID",userID);
        map.put("condition",condition);
    return ResultUtil.seccess(iEquipmentService.getAllByID(map));
    }

    @GetMapping("/getEquipmentNO")
    public Result getEquipmentNO(String equipmentNO){
        return ResultUtil.seccess(iEquipmentService.getEquipmentNO(equipmentNO));
    }

    @PutMapping("modify")
    public Result getEquipmentByEquipmentNO(String equipmentNO,Integer equipmentType,String equipmentAddress){
        Equipment equipmentByEquipmentNO = iEquipmentService.getEquipmentByEquipmentNO(equipmentNO);
        equipmentByEquipmentNO.setEquipmentType(equipmentType);
        equipmentByEquipmentNO.setEquipmentAddress(equipmentAddress);
        if(iEquipmentService.updateEquipment(equipmentByEquipmentNO) > 0){
            return  ResultUtil.seccess(true);
        }
        return  ResultUtil.seccess(false);
    }
}
