package com.example.electricity.service.impl;

import com.example.electricity.entity.Alert;
import com.example.electricity.mapper.AlertMapper;
import com.example.electricity.service.IAlertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
