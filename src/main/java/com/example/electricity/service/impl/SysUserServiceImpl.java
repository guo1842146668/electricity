package com.example.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.electricity.entity.User;
import com.example.electricity.mapper.SysUserMapper;
import com.example.electricity.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, User> implements ISysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public User Login(String loginName, String password) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",loginName);
        queryWrapper.eq("password",password);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public int UpdateUser(User sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public User getByID(Integer userID) {
        return sysUserMapper.selectById(userID);
    }

    @Override
    public String getSalt(String loginName) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",loginName);
        return sysUserMapper.selectOne(queryWrapper).getSalt();
    }
}
