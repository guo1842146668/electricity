package com.example.electricity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.electricity.entity.Dict;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<String> getGuide(String type);
}
