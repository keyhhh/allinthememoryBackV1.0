package com.cyh.allinthememory.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 宇恒
 */
@Data
public class RecordPublic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @ TableId(type = IdType.ASSIGN_ID)
     * 不知后续需不需要他的主键雪花算法，还是跟随record
     */
    private Long recordId;

    private Long userId;

    /**点赞该记录的用户*/
    private String likedUserId;

    /**是否为记忆 0记录  1记忆 */
    private Integer isMemory;


}
