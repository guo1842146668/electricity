package com.example.electricity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "userID", type = IdType.AUTO)
    private Integer userID;

    @ApiModelProperty(value = "用户姓名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "账号")
    @TableField("userAccount")
    private String userAccount;

    @ApiModelProperty(value = "密码")
    @TableField("userPassword")
    private String userPassword;

    @ApiModelProperty(value = "头像")
    @TableField("userPortrait")
    private String userPortrait;


}
