package com.example.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.electricity.entity.Scheduled;
import com.example.electricity.mapper.ScheduledMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.electricity.service.IScheduledService;
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
 * @since 2020-09-02
 */
@Service
public class ScheduledServiceImpl extends ServiceImpl<ScheduledMapper, Scheduled> implements IScheduledService {
    @Resource
    private ScheduledMapper scheduledMapper;
    @Override
    public List<Scheduled> getByUserID(String equipmentNO) {
        QueryWrapper<Scheduled> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("equipmentNO",equipmentNO);
        return scheduledMapper.selectList(queryWrapper);
    }

    @Override
    public int saveScheduled(Scheduled scheduled) {
        return scheduledMapper.insert(scheduled);
    }

    @Override
    public Scheduled getScheduledByID(Integer userID) {
        return scheduledMapper.selectById(userID);
    }

    @Override
    public int updateScheduled(Scheduled scheduled) {
        return scheduledMapper.updateById(scheduled);
    }

}
