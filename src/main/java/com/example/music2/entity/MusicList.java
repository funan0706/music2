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
@TableName("music_list")
@ApiModel(value = "MusicList对象", description = "")
public class MusicList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("mid")
    private Integer mid;

    @TableField("lid")
    private Integer lid;


}
