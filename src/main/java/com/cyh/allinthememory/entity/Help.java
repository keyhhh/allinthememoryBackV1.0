package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宇恒
 * 求助信息
 */
@Data
public class Help implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long helpId;

    private Long userId;

    private LocalDateTime datePublish;

    private String message;

    private String image;

    private String tag;

    /**求助信息的标题*/
    private String title;




}
