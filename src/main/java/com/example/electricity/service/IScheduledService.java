package com.example.electricity.service;

import com.example.electricity.entity.Scheduled;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-02
 */
public interface IScheduledService extends IService<Scheduled> {

    List<Scheduled> getByUserID(String equipmentNO);

    int saveScheduled(Scheduled scheduled);

    Scheduled getScheduledByID(Integer userID);

    int updateScheduled(Scheduled scheduled);

    List<Scheduled> getByCronId(String equipmentNO);

}
