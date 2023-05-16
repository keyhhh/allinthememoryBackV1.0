package com.cyh.allinthememory.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 宇恒
 * 未曾使用的实体类
 */
@Data
public class RecordPublic implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long recordId;

    private Long userId;

    /**点赞该记录的用户*/
    private String likedUserId;

    /**是否为记忆 0记录  1记忆 */
    private Integer isMemory;


}
