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
@ApiModel(value="Dict对象", description="")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dictID", type = IdType.AUTO)
    private Integer dictID;

    @ApiModelProperty(value = "父级名称")
    @TableField("dictPrent")
    private Integer dictPrent;

    @ApiModelProperty(value = "字典名称")
    @TableField("dictName")
    private String dictName;


}
