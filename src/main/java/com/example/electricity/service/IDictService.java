package com.example.electricity.service;

import com.example.electricity.entity.Dict;
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
public interface IDictService extends IService<Dict> {

    List<Map<String,Object>> getGuide(String type);

    List<Map<String,Object>> getPhone();
}
