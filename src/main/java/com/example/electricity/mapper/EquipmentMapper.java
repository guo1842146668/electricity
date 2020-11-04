package com.example.electricity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.electricity.entity.Equipment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {

    List<Map<String,Object>> getAllByID(Map<String,Object> map);

    List<Map<String, Object>> getAllByIDAdmin(Map<String, Object> map);

    Map<String, Object> getEquipmentNO(String equipmentNO);
}
