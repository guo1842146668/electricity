package com.example.electricity.service;

import com.example.electricity.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface IUserService extends IService<User> {
    /**
     *  登陆
     * @param userAccount 用户名
     * @param userPassword 密码
     * @return 存在用户返回用户实体
     */
    User Login(String userAccount,String userPassword);

    /**
     *   修改密码
     * @param user 用户实体
     * @return 修改结果
     */
    int UpdateUser(User user);

    /**
     *  根据ID查询用户
     * @param userID 用户ID
     * @return 用户实体
     */
    User getByID(Integer userID);
}
