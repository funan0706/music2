package com.example.music2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.music2.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-03-06
 */
@Getter
@Setter
@TableName("mv")
@ApiModel(value = "Mv对象", description = "")
public class Mv extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("author")
    private String author;

    @TableField("time")
    private String time;

    @TableField("url")
    private String url;

    @TableField("pic")
    private String pic;


}
