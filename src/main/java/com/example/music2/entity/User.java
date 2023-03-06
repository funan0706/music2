package com.example.music2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.music2.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-03-03
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @NotBlank(message = "用户名不为空")
    @TableField("username")
    private String username;

    @NotBlank(message = "密码不为空")
    @Length(min = 5,max = 11,message = "密码长度在[5,60]之间")
    @TableField("password")
    private String password;

    @TableField("sex")
    private String sex;

    @TableField("hobby")
    private String hobby;

    @TableField("phone_num")
    private String phoneNum;

    @TableField("introduction")
    private String introduction;


}
