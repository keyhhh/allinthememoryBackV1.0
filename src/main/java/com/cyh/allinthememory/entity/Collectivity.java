package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宇恒
 *未曾使用的实体类
 */
@Data
public class Collectivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long collectivityId;

    private LocalDateTime datePublish;

    private String message;

    private String image;

    private String tag;

    /**该集体记忆标签出现次数*/
    private Integer times;

    /**要展示的热门记忆*/
    private String hotId;
}
