package com.example.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.electricity.entity.User;
import com.example.electricity.mapper.UserMapper;
import com.example.electricity.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User Login(String userAccount, String userPassword) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",userPassword);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int UpdateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User getByID(Integer userID) {
        return userMapper.selectById(userID);
    }
}
