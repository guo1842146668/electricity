package com.example.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.electricity.entity.Equipment;
import com.example.electricity.mapper.EquipmentMapper;
import com.example.electricity.service.IEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    @Resource
    private EquipmentMapper equipmentMapper;
    @Override
    public List<Map<String, Object>> getAllByID(Map<String,Object> map) {
        return equipmentMapper.getAllByID(map);
    }

    @Override
    public List<Map<String, Object>> getAllByIDAdmin(Map<String, Object> map) {
        return equipmentMapper.getAllByIDAdmin(map);
    }

    @Override
    public Map<String, Object> getEquipmentNO(String equipmentNO) {
        return equipmentMapper.getEquipmentNO(equipmentNO);
    }

    @Override
    public int saveEquipment(Equipment equipment) {
        return equipmentMapper.insert(equipment);
    }

    @Override
    public int updateEquipment(Equipment equipment) {
        return equipmentMapper.updateById(equipment);
    }

    @Override
    public Equipment getEquipmentByEquipmentNO(String equipmentNO) {
        QueryWrapper<Equipment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("equipmentNO",equipmentNO);
        return equipmentMapper.selectOne(queryWrapper);
    }
}
