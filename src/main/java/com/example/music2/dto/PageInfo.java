package com.example.music2.dto;

import com.example.music2.entity.BaseEntity;
import lombok.Data;

/**
 * dto类
 * 数据传输对象
 * 将所要的数据返回给前端，以免造成不必要的资源浪费和数据暴露，造成不必要的安全问题。
 * */
@Data
public class PageInfo extends BaseEntity {

    private Integer pageNum;
    private Integer pageSize;
}