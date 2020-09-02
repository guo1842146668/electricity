package com.example.electricity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.electricity.entity.Alert;

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
public interface AlertMapper extends BaseMapper<Alert> {

    List<Map<String,Object>> getAlert(Map<String,Object> map);
}
