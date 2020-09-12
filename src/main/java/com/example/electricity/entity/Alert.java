package com.example.electricity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@ApiModel(value="Alert对象", description="")
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "alertID", type = IdType.AUTO)
    private Integer alertID;

    @ApiModelProperty(value = "警报时间")
    @TableField("alertTime")
    private LocalDateTime alertTime;

    @ApiModelProperty(value = "警报的设备")
    @TableField("alertEquipment")
    private Integer equipmentNO;


}
