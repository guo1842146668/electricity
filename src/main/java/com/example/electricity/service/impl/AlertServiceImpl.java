package com.example.electricity.service.impl;

import com.example.electricity.entity.Alert;
import com.example.electricity.mapper.AlertMapper;
import com.example.electricity.service.IAlertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class AlertServiceImpl extends ServiceImpl<AlertMapper, Alert> implements IAlertService {
    @Resource
    private AlertMapper alertMapper;
    @Override
    public PageInfo<Map<String, Object>> getAlert(Map<String,Object> map,Integer page,Integer count) {
        PageHelper.startPage(page,count);
        List<Map<String, Object>> alert = alertMapper.getAlert(map);
        PageInfo<Map<String, Object>> pageInfo=new PageInfo<>(alert);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getAlertAdmin(Map<String, Object> map, Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Map<String, Object>> alert = alertMapper.getAlertAdmin(map);
        PageInfo<Map<String, Object>> pageInfo=new PageInfo<>(alert);
        return pageInfo;
    }
}
