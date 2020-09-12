package com.example.electricity.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.electricity.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-05
 */
public interface ISysUserService extends IService<User> {
    /**
     *  登陆
     * @param loginName 用户名
     * @param password 密码
     * @return 存在用户返回用户实体
     */
    User Login(String loginName, String password);

    /**
     *   修改密码
     * @param sysUser 用户实体
     * @return 修改结果
     */
    int UpdateUser(User sysUser);

    /**
     *  根据ID查询用户
     * @param userId 用户ID
     * @return 用户实体
     */
    User getByID(Integer userId);

    /**
     * 获取账户的验签方式
     * @param loginName 用户名
     * @return 验签
     */
    String getSalt(String loginName);
}
