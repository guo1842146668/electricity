package com.example.electricity.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Scheduled对象", description="")
public class Scheduled implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "定时ID")
    @TableId(value = "cron_id", type = IdType.AUTO)
    private Integer cronId;

    @ApiModelProperty(value = "定时名称")
    private String cronName;

    @ApiModelProperty(value = "定时开始")
    @TableField("cronStartTime")
    private String cronStartTime;

    @ApiModelProperty(value = "定时结束")
    @TableField("cronEndTime")
    private String cronEndTime;

    @ApiModelProperty(value = "用户ID")
    @TableField("equipmentNO")
    private String equipmentNO;

    @ApiModelProperty(value = "定时器状态")
    @TableField("cronStatus")
    private Integer cronStatus;

}
