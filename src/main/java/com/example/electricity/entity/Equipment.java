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
@ApiModel(value="Equipment对象", description="")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备ID")
    @TableId(value = "equipmentID", type = IdType.AUTO)
    private Integer equipmentID;

    @ApiModelProperty(value = "设备ID")
    @TableField("equipmentNO")
    private String equipmentNO;

    @ApiModelProperty(value = "所属用户")
    @TableField("userID")
    private String userID;

    @ApiModelProperty(value = "设备类型")
    @TableField("equipmentType")
    private Integer equipmentType;

    @ApiModelProperty(value = "设备安装地址")
    @TableField("equipmentAddress")
    private String equipmentAddress;

    @ApiModelProperty(value = "电流1")
    @TableField("electricCurrentIA")
    private Double electricCurrentIA;

    @ApiModelProperty(value = "电流2")
    @TableField("electricCurrentIB")
    private Double electricCurrentIB;

    @ApiModelProperty(value = "电流3")
    @TableField("electricCurrentIC")
    private Double electricCurrentIC;

    @ApiModelProperty(value = "设备状态  1在线    -1 离线")
    @TableField("electricStatus")
    private Integer electricStatus;

}
