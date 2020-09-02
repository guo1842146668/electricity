package com.example.electricity.service;

import com.example.electricity.entity.Alert;
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
public interface IAlertService extends IService<Alert> {

    List<Map<String,Object>> getAlert(Map<String,Object> map);
}
