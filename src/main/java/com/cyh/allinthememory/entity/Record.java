package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宇恒
 * 自己创建的记忆记录
 */
@Data
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long recordId;

    private Long userId;

    private LocalDateTime datePublish;

    private String message;

    private String image;

    private String tag;

    private Integer isPublic;


}
