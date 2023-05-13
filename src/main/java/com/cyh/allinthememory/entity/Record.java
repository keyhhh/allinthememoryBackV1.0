package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
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

//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate datePublish;

    private String message;

    private String image;

    private String tag;

    private Integer isPublic;

    private Integer isMemory;

    /**点赞该记录的用户*/
    private String likedUserId;


}
