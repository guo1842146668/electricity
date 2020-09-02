package com.example.electricity.service;

import com.example.electricity.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface IEquipmentService extends IService<Equipment> {

        List<Map<String,Object>> getAllByID(Map<String,Object> map);

        Map<String,Object> getEquipmentNO(String equipmentNO);
}
