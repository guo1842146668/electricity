package com.example.electricity.service.impl;

import com.example.electricity.entity.Dict;
import com.example.electricity.mapper.DictMapper;
import com.example.electricity.service.IDictService;
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
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Map<String,Object>> getGuide(String type) {
        return dictMapper.getGuide(type);
    }
}
